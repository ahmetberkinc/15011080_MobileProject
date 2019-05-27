package com.example.mobil_proje;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<ExampleNote> mexampleNote;
    private OnNoteListener monNoteListener;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView date;
        public TextView priority;
        OnNoteListener onNoteListener;
        public ExampleViewHolder(@NonNull View itemView,OnNoteListener onNoteListener) {
            super(itemView);
            title= itemView.findViewById(R.id.title);
            date= itemView.findViewById(R.id.date);
            priority = itemView.findViewById(R.id.priority);
            this.onNoteListener=onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public ExampleAdapter(ArrayList<ExampleNote> exampleNote,OnNoteListener onNoteListener){
        mexampleNote=exampleNote;
        this.monNoteListener=onNoteListener;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.example_note,viewGroup,false);
        ExampleViewHolder exampleViewHolder=new ExampleViewHolder(v,monNoteListener);
        return exampleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
        ExampleNote currentitem=mexampleNote.get(i);
        Log.d("myTag", currentitem.getAy());
        exampleViewHolder.title.setText(currentitem.getTitle());
        exampleViewHolder.date.setText(currentitem.getGun()+"/"+currentitem.getAy()+"/"+currentitem.getYil());
        exampleViewHolder.priority.setText(currentitem.getPriority());
    }

    @Override
    public int getItemCount() {
        return mexampleNote.size();
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}