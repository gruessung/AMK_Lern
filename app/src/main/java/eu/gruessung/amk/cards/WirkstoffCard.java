package eu.gruessung.amk.cards;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import eu.gruessung.amk.R;
import eu.gruessung.amk.WirkstoffeActivity;
import eu.gruessung.amk.objects.Wirkstoff;
import eu.gruessung.amk.objects.Wirkstoffgruppe;

/**
 * Created by alexander on 03.05.17.
 */
public class WirkstoffCard extends RecyclerView.Adapter<WirkstoffCard.ItemViewHolder> {

    private List<Wirkstoff> list;
    private Context ctx;

    public WirkstoffCard(List<Wirkstoff> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }


    @Override
    public WirkstoffCard.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View viewItem = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_main_item, parent, false);
        return new ItemViewHolder(viewItem);
    }


    @Override
    public void onBindViewHolder(WirkstoffCard.ItemViewHolder holder, int position) {
        Wirkstoff item = list.get(position);
        holder.cardTitle.setText(item.sTitel);
        holder.cardView.setTag(item.id);
        holder.cardColor.setBackgroundColor(Color.WHITE);
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


