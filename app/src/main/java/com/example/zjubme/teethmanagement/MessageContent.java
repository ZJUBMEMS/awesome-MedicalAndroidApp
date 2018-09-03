package com.example.zjubme.teethmanagement;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MessageContent extends AppCompatActivity {

    public static void actionStart(Context context, String messageTitle, String messageContent){
        Intent intent = new Intent(context, MessageContent.class);
        intent.putExtra("message_title", messageTitle);
        intent.putExtra("message_context", messageContent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_content_layout);
        String messageTitle = getIntent().getStringExtra("message_title");
        String messageContent = getIntent().getStringExtra("message_content");
        MessageContentFragment messageContentFragment = (MessageContentFragment)
                getSupportFragmentManager().findFragmentById(R.id.message_content_fragment);
        messageContentFragment.refresh(messageTitle, messageContent);
    }
}
