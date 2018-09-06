package com.example.zjubme.teethmanagement;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessageContent extends AppCompatActivity {

   private List<MsgToOthers> msgToOthersList = new ArrayList<>();

   private EditText inputText;

   private Button sendButton;

   private TextView accountName;

   private RecyclerView msgRecyclerView;

   private MsgContentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_content_layout);
        Intent intent = getIntent();
        String accountID = intent.getStringExtra("account_name");
        String othersMsg = intent.getStringExtra("others_msg");
//        initMsgs();
        accountName = (TextView)findViewById(R.id.account_name);
        inputText = (EditText)findViewById(R.id.input_text);
        sendButton = (Button)findViewById(R.id.send_msg_button);
        msgRecyclerView = (RecyclerView)findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter =new MsgContentAdapter(msgToOthersList);
        msgRecyclerView.setAdapter(adapter);
        accountName.setText(accountID);
        MsgToOthers msg1 = new MsgToOthers(othersMsg,MsgToOthers.TYPE_RECEIVED, R.drawable.account);

        msgToOthersList.add(msg1);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = inputText.getText().toString();
                if (!"".equals(content)){
                    MsgToOthers msgToOthers = new MsgToOthers(content,MsgToOthers.TYPE_SENT, R.drawable.account);
                    msgToOthersList.add(msgToOthers);
                    adapter.notifyItemInserted(msgToOthersList.size()-1);
                    msgRecyclerView.scrollToPosition(msgToOthersList.size()-1);
                    inputText.setText("");
                }
            }
        });

    }
}
