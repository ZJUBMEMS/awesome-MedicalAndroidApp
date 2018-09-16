package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{
    private ArrayList<DiagnoseMessage> diagnoseMessages;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView date;
        CardView cardView;
        public ViewHolder(View view){
            super(view);
            textView = (TextView)view.findViewById(R.id.title_message);
            date = (TextView)view.findViewById(R.id.date_message);
            cardView = (CardView)view.findViewById(R.id.card_view);
        }
    }

    public MessageAdapter(ArrayList<DiagnoseMessage> diagnoseMessages){
        this.diagnoseMessages = diagnoseMessages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_item, viewGroup, false);
        MessageAdapter.ViewHolder viewHolder = new MessageAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder viewHolder, int i) {
        viewHolder.textView.setText("诊断反馈通知");
        viewHolder.date.setText(diagnoseMessages.get(i).getDate());
        viewHolder.cardView.setId(i);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DiagnoseMessageActivity.class);
                intent.putExtra("message", diagnoseMessages.get(view.getId()).getMessage());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return diagnoseMessages.size();
    }

}
