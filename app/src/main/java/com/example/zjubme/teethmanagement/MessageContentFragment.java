package com.example.zjubme.teethmanagement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jlx on 01/09/2018.
 */

public class MessageContentFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.message_content_frag, container, false);
        return view;
    }

    public void refresh(String messageTitle, String messageContent){
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView messageTitleText = (TextView)view.findViewById(R.id.message_title);
        TextView messageContentText = (TextView)view.findViewById(R.id.message_content);
        messageTitleText.setText(messageTitle);
        messageContentText.setText(messageContent);
    }
}
