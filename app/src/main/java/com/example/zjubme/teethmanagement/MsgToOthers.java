package com.example.zjubme.teethmanagement;

/**
 * Created by jlx on 04/09/2018.
 */

public class MsgToOthers {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;

    private String content;
    private int type;
    private int imageId;

    public MsgToOthers(String content, int type, int imageId) {
        this.content = content;
        this.type = type;
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
