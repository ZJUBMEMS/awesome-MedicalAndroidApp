package com.example.zjubme.teethmanagement;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

public class MyLeanCloudApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, "msPsA8aNeLWOVoJgnTK2IhFL-gzGzoHsz", "DSIy3Lmrgd6Qo9Ox0vWmuGRl" );
        AVOSCloud.setDebugLogEnabled(true);
    }
}
