package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessageListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_list_fragment, container, false);
        RecyclerView messageTitleRecyclerView = (RecyclerView)view.findViewById(R.id.message_title_recycler_view1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        messageTitleRecyclerView.setLayoutManager(layoutManager);
        MessageAdapter adapter = new MessageAdapter(getMessages());
        messageTitleRecyclerView.setAdapter(adapter);
        return view;
    }

    private List<Messages> getMessages(){
        List<Messages> messagesList = new ArrayList<>();
        for (int i = 1; i <= 50; i++){
            Messages messages = new Messages();
            messages.setTitle("Title" + i);
            messages.setTitle_context("content" + i);
            messages.setImageId(R.drawable.back);
            messagesList.add(messages);
        }
        return messagesList;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{

        private List<Messages> mMessageList;

        class ViewHolder extends RecyclerView.ViewHolder{
            View messageView;
            LinearLayout messageLinearlayout;
            TextView messageTitleText;
            TextView messageTitleText1;
            ImageView messageTitleImage;

            public ViewHolder(View view){
                super(view);
                messageView = view;
                messageLinearlayout = (LinearLayout)view.findViewById(R.id.message_linearlayout);
                messageTitleText = (TextView)view.findViewById(R.id.message_title);
                messageTitleText1 = (TextView)view.findViewById(R.id.message_content);
                messageTitleImage = (ImageView)view.findViewById(R.id.image);
            }
        }

        public MessageAdapter(List<Messages> messagesList){
            mMessageList = messagesList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            holder.messageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    Messages messages = mMessageList.get(position);
                    Intent intent = new Intent(getActivity(), MessageContent.class);
                    intent.putExtra("account_name", messages.getTitle());
                    intent.putExtra("others_msg",messages.getTitle_context());
                    startActivity(intent);
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Messages messages = mMessageList.get(position);
            holder.messageTitleText.setText(messages.getTitle());
            holder.messageTitleText1.setText(messages.getTitle_context());
            holder.messageTitleImage.setImageResource(messages.getImageId());
        }


        @Override
        public int getItemCount() {
            return mMessageList.size();
        }
    }
}
