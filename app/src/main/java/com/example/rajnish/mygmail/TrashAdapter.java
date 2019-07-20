package com.example.rajnish.mygmail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TrashAdapter extends RecyclerView.Adapter<TrashAdapter.ViewHolder> {
    Context context;
    ArrayList<MyGmail>trashArraylist;

    public TrashAdapter(Context context, ArrayList<MyGmail> trashArraylist) {
        this.context = context;
        this.trashArraylist = trashArraylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.trash_row_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyGmail model = trashArraylist.get(holder.getAdapterPosition());
        holder.from.setText(model.getFrom());
        holder.subject.setText(model.getSubject());
        holder.message.setText(model.getMessage());
        holder.date.setText(model.getDate());
        holder.imageView.setImageResource(model.getImage_thumbnail());
    }



    @Override
    public int getItemCount() {
        return trashArraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView from;
        TextView subject;
        TextView message;
        TextView date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            from = itemView.findViewById(R.id.from_user);
            subject = itemView.findViewById(R.id.subject);
            message = itemView.findViewById(R.id.message);
            date = itemView.findViewById(R.id.dateWithMonth);
            imageView = itemView.findViewById(R.id.thumbnail_img);
        }
    }
}
