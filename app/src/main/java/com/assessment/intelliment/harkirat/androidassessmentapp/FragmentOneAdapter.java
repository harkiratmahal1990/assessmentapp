package com.assessment.intelliment.harkirat.androidassessmentapp;

import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentOneAdapter extends RecyclerView.Adapter<FragmentOneAdapter.FragmentOneViewHolder> {

    private String[] itemNames;
    public Listener listener;
    private int width;

    public interface Listener{
        public void onClick(int position);
    }

    public FragmentOneAdapter(String[] itemNames, Listener listener) {
        this.itemNames=itemNames;
        this.listener=listener;
        this.width=width;
    }

    @Override
    public FragmentOneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView card_text=(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_text,parent,false);
        int screenWidth= Resources.getSystem().getDisplayMetrics().widthPixels;
        card_text.getLayoutParams().width = screenWidth/3;
        return new FragmentOneViewHolder(card_text);
    }

    @Override
    public void onBindViewHolder(FragmentOneViewHolder holder, final int position) {
        CardView cardView=holder.cardView;
        TextView card_text=(TextView)cardView.findViewById(R.id.card_text);
        String text=itemNames[position];
        card_text.setText(text);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)
                {
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemNames.length;
    }

    public class FragmentOneViewHolder extends RecyclerView.ViewHolder
{

    private CardView cardView;
    public FragmentOneViewHolder(CardView cardView)
    {
        super(cardView);
        this.cardView=cardView;
    }
}
}

