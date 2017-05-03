package eu.gruessung.amk;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.joaquimley.faboptions.FabOptions;

import java.util.ArrayList;
import java.util.List;

import eu.gruessung.amk.cards.NavCard;
import eu.gruessung.amk.objects.Nav;

public class MainActivity extends AppCompatActivity {

    RecyclerView oList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        oList = (RecyclerView) findViewById(R.id.cardList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        oList.setLayoutManager(llm);

        //Menu aufbauen
        List<Nav> list = new ArrayList<Nav>();
        Nav item = new Nav();
        item.sTitel = "Lernen";
        item.iBild = R.drawable.books;
        list.add(item);
        Nav item2 = new Nav();
        item2.sTitel = "Verwalten";
        item2.iBild = R.drawable.medications;
        list.add(item2);


        NavCard oNavAdapter = new NavCard(list, this);
        oList.setAdapter(oNavAdapter);
    }


}
