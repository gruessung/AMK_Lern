package eu.gruessung.amk.cards;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import eu.gruessung.amk.R;
import eu.gruessung.amk.objects.Item;
import eu.gruessung.amk.objects.Wirkstoffgruppe;

/**
 * Created by alexander on 03.05.17.
 */
public class WirkstoffgruppeCard extends RecyclerView.Adapter<WirkstoffgruppeCard.ItemViewHolder> {

    private List<Wirkstoffgruppe> list;
    private Context ctx;

    public WirkstoffgruppeCard(List<Wirkstoffgruppe> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }


    @Override
    public WirkstoffgruppeCard.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View viewItem = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_main_item, parent, false);

        viewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar snackbar = Snackbar
                        .make(viewItem, "Funktion in Arbeit "+view.getTag().toString() , Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

        return new ItemViewHolder(viewItem);
    }


    @Override
    public void onBindViewHolder(WirkstoffgruppeCard.ItemViewHolder holder, int position) {
        Wirkstoffgruppe item = list.get(position);
        holder.cardTitle.setText(item.sTitel);
        holder.cardView.setTag(item.id);
        try {
            holder.cardColor.setBackgroundColor(Color.parseColor(item.sFarbe));
        } catch (Exception e) {
            holder.cardColor.setBackgroundColor(Integer.parseInt(item.sFarbe));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        protected LinearLayout cardColor;
        protected TextView cardTitle;
        protected CardView cardView;

        public ItemViewHolder(View v)
        {
            super(v);
            cardTitle = (TextView) v.findViewById(R.id.title);
            cardColor = (LinearLayout) v.findViewById(R.id.cardColor);
            cardView = (CardView) v.findViewById(R.id.card_view);
        }


    }
}


