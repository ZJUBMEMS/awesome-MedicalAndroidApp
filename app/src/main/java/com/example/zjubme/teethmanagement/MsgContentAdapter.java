package com.example.zjubme.teethmanagement;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jlx on 04/09/2018.
 */

public class MsgContentAdapter extends RecyclerView.Adapter<MsgContentAdapter.ViewHolder>{

    private List<MsgToOthers> mMsgToOthersList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout leftLayout;
        LinearLayout rightLayout;

        TextView leftMsg;
        TextView rightMsg;

        ImageView othersAccountImg;
        ImageView myAccountImg;

        public ViewHolder(View view){
            super(view);
            leftLayout = (LinearLayout)view.findViewById(R.id.content_left_layout);
            rightLayout = (LinearLayout)view.findViewById(R.id.content_right_layout);
            leftMsg = (TextView)view.findViewById(R.id.left_msg);
            rightMsg = (TextView)view.findViewById(R.id.right_msg);
            othersAccountImg = (ImageView)view.findViewById(R.id.others_account_image);
            myAccountImg = (ImageView)view.findViewById(R.id.my_account_image);
        }
    }

    public MsgContentAdapter(List<MsgToOthers> mMsgToOthersList) {
        this.mMsgToOthersList = mMsgToOthersList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_content_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MsgToOthers msgToOthers = mMsgToOthersList.get(position);
        if (msgToOthers.getType() == MsgToOthers.TYPE_RECEIVED){
            //收到消息，显示left,隐藏right
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msgToOthers.getContent());
            holder.othersAccountImg.setImageResource(msgToOthers.getImageId());
        }else if (msgToOthers.getType() == MsgToOthers.TYPE_SENT){
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightMsg.setText(msgToOthers.getContent());
            holder.myAccountImg.setImageResource(msgToOthers.getImageId());
        }
    }

    @Override
    public int getItemCount() {
        return mMsgToOthersList.size();
    }
}
