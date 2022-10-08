package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wechat2.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private String[] contents;
    private Boolean[] senderSend;
    private String[] time;
    private int sender;
    private int receiver;

    @Override
    public int getItemViewType(int position) {

        if (senderSend[position] == true) {
            return 1; // 表示是发送者
        } else if (senderSend[position] == false) {
            return 0; // 表示是接收者
        } else {
            return super.getItemViewType(position);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = null;
        if (viewType == 1) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chit_message_row_item2, parent, false);
        } else if (viewType == 0) {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chit_message_row_item, parent, false);

        }
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.getContent().setText(contents[position]);
        if (senderSend[position] == true) {
            holder.getHead_picture().setImageResource(sender);
        } else
            holder.getHead_picture().setImageResource(receiver);
        holder.getTimeShow().setText(time[position]);

    }

    @Override
    public int getItemCount() {
        return contents.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView content;
        private final ImageView head_picture;
        private final TextView timeShow;


        public ViewHolder(View v) {
            super(v);

            content = (TextView) v.findViewById(R.id.content);
            head_picture = (ImageView) v.findViewById(R.id.head_picture);
            timeShow = (TextView) v.findViewById(R.id.time_show);
        }

        public TextView getContent() {
            return content;
        }

        public ImageView getHead_picture() {
            return head_picture;
        }

        public TextView getTimeShow() {
            return timeShow;
        }

    }

    public CustomAdapter(String[] contents, Boolean[] senderSend, String[] time, int receiver, int sender) {
        this.contents = contents;
        this.senderSend = senderSend;
        this.time = time;
        this.sender = sender;
        this.receiver = receiver;
    }
}