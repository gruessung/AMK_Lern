package eu.gruessung.amk;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.gruessung.amk.cards.NavCard;
import eu.gruessung.amk.cards.WirkstoffgruppeCard;
import eu.gruessung.amk.objects.Nav;
import eu.gruessung.amk.objects.Wirkstoffgruppe;

public class WirkstoffgruppenActivity extends AppCompatActivity {

    RecyclerView oList;
    Button selectFarbe;
    AppCompatActivity act;
    String url;
    RequestQueue queue;
    EditText name;
    String farbe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verwalten);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        act = this;



        FloatingActionButton fb = (FloatingActionButton) findViewById(R.id.fab);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(act);
                final View dialog_layout = inflater.inflate(R.layout.dialog_neue_wirkstoffgruppe,null);
                queue = Volley.newRequestQueue(act);
                AlertDialog.Builder db = new AlertDialog.Builder(act);
                db.setView(dialog_layout);
                selectFarbe = (Button) dialog_layout.findViewById(R.id.selectFarbe);
                name = (EditText) dialog_layout.findViewById(R.id.name);
                db.setTitle("Wirkstoffgruppe anlegen");
                db.setPositiveButton("Speichern", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        url = "http://api.gruessung.eu/amk/?apikey=fdihfz89d7shf4krghf79dighf&request=insertwirkstoffgruppe";
                        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>()
                                {
                                    @Override
                                    public void onResponse(String response) {
                                        // response
                                        Log.d("Response", response);
                                        fetchData();
                                        Snackbar snackbar = Snackbar
                                                .make(oList, "Gespeichert." , Snackbar.LENGTH_LONG);
                                        snackbar.show();
                                    }
                                },
                                new Response.ErrorListener()
                                {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // error
                                        Log.d("Error.Response", error.getMessage());
                                    }
                                }
                        ) {
                            @Override
                            protected Map<String, String> getParams()
                            {
                                Map<String, String>  params = new HashMap<String, String>();
                                params.put("name", name.getText().toString());
                                params.put("farbe", farbe);
                                return params;
                            }
                        };
                        queue.add(postRequest);
                    }
                });
                selectFarbe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final ColorPicker cp = new ColorPicker(act, 100, 100, 100);
                        cp.show();

                        cp.setCallback(new ColorPickerCallback() {
                            @Override
                            public void onColorChosen(@ColorInt int color) {
                                selectFarbe.setBackgroundColor(color);
                                farbe = String.valueOf(color);
                                cp.dismiss();
                            }
                        });
                    }
                });
                final AlertDialog dialog = db.show();
            }
        });

        oList = (RecyclerView) findViewById(R.id.cardList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        oList.setLayoutManager(llm);
        fetchData();
    }

    public void fetchData() {
        //Daten holen und verarbeiten
        RequestQueue rq = Volley.newRequestQueue(this);
        JsonArrayRequest jReq = new JsonArrayRequest("http://api.gruessung.eu/amk/?apikey=fdihfz89d7shf4krghf79dighf&request=holewirkstoffgruppen",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Wirkstoffgruppe> result = new ArrayList<Wirkstoffgruppe>();
                        for (int i = 0; i < response.length(); i++) {

                            try {
                                result.add(convertJSONWirkstoffgruppe(response
                                        .getJSONObject(i)));
                            } catch (JSONException e) {

                            }
                        }
                        WirkstoffgruppeCard oListAdapter = new WirkstoffgruppeCard(result, getApplicationContext());
                        oList.setAdapter(oListAdapter);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar snackbar = Snackbar
                        .make(oList, "Keine Internetverbindung :(", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

        rq.add(jReq);
    }

    public Wirkstoffgruppe convertJSONWirkstoffgruppe(JSONObject c) {
        // Storing each json item in variable
        Wirkstoffgruppe item = new Wirkstoffgruppe();
        try {
            item.id = c.getInt("id");
            item.sTitel = c.getString("name");
            item.sBeschreibung = c.getString("beschreibung");
            item.sFarbe = c.getString("farbe");
        } catch (JSONException e) {
        }
        return item;
    }


}
