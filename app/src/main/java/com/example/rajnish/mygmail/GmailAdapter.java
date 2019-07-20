package com.example.rajnish.mygmail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class GmailAdapter extends RecyclerView.Adapter<GmailAdapter.Viewholder> {
    private Context context;
    private ArrayList<MyGmail> gmailArrayList;

    public GmailAdapter(Context context, ArrayList<MyGmail> gmailArrayList) {
        this.context = context;
        this.gmailArrayList = gmailArrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.gmail_list, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(final Viewholder holder, final int position) {
        final MyGmail data = gmailArrayList.get(position);
        holder.textFrom.setText(data.getFrom());
        holder.textSubject.setText(data.getSubject());
        holder.textMessage.setText(data.getMessage());
        holder.textDate.setText(data.getDate());
        holder.imageView.setImageResource(data.getImage_thumbnail());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                data.setSelected(!data.isSelected());
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.setActionMode(true);
                notifyItemChanged(position);
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setSelected(!data.isSelected());
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.setActionMode(true);
                notifyItemChanged(position);
            }
        });
        if (data.isSelected()) {
            holder.okayImage.setVisibility(View.VISIBLE);
        } else
            holder.okayImage.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return gmailArrayList.size();
    }

    public void setFilter(ArrayList<MyGmail> newList) {
        gmailArrayList = new ArrayList<>();
        gmailArrayList.addAll(newList);
        notifyDataSetChanged();
    }

    public void removeItem(int deleteIndex) {
        gmailArrayList.remove(deleteIndex);
        notifyItemRemoved(deleteIndex);
    }

    public void restoreItem(int deleteIndex, MyGmail deletedItem) {
        gmailArrayList.add(deleteIndex, deletedItem);
        notifyItemInserted(deleteIndex);
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView textFrom;
        TextView textSubject;
        TextView textMessage;
        TextView textDate;
        CircleImageView imageView;
        ImageView okayImage;
        RelativeLayout background, foreground;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            textFrom = itemView.findViewById(R.id.from_user);
            textSubject = itemView.findViewById(R.id.subject);
            textMessage = itemView.findViewById(R.id.message);
            textDate = itemView.findViewById(R.id.dateWithMonth);
            imageView = itemView.findViewById(R.id.thumbnail_img);
            okayImage = itemView.findViewById(R.id.selectedPic);
            background = itemView.findViewById(R.id.view_background);
            foreground = itemView.findViewById(R.id.view_foreground);
        }
    }
}
