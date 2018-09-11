package com.example.zjubme.teethmanagement;

public class DiagnoseMessage {
    private String message;
    private String date;

    public DiagnoseMessage(String message, String date){
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }
}
