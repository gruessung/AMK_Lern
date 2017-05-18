package eu.gruessung.amk.cards;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import eu.gruessung.amk.R;
import eu.gruessung.amk.WirkstoffgruppenActivity;
import eu.gruessung.amk.objects.Nav;

/**
 * Created by alexander on 03.05.17.
 */
public class NavCard extends RecyclerView.Adapter<NavCard.ItemViewHolder> {

    private List<Nav> list;
    private Context ctx;

    public NavCard(List<Nav> list,Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }


    @Override
    public NavCard.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View viewItem = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_main_activity, parent, false);

        viewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view.getTag().toString() == "Verwalten") {
                    Intent myIntent = new Intent(ctx, WirkstoffgruppenActivity.class);
                    ctx.startActivity(myIntent);
                } else if (view.getTag().toString() == "Lernen") {
                    Snackbar snackbar = Snackbar
                            .make(viewItem, "Funktion in Arbeit" , Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

        return new ItemViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(NavCard.ItemViewHolder holder, int position) {
        Nav item = list.get(position);

        holder.cardTitle.setText(item.sTitel);
        holder.cardImage.setImageResource(item.iBild);
        holder.cardView.setTag(item.sTitel);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        protected ImageView cardImage;
        protected TextView cardTitle;
        protected CardView cardView;

        public ItemViewHolder(View v)
        {
            super(v);
            cardTitle = (TextView) v.findViewById(R.id.title);
            cardImage = (ImageView) v.findViewById(R.id.imageView);
            cardView = (CardView) v.findViewById(R.id.card_view);
        }


    }
}


