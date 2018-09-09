package com.example.zjubme.teethmanagement;

import android.app.Application;

import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.PushService;

public class MyLeanCloudApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, "msPsA8aNeLWOVoJgnTK2IhFL-gzGzoHsz", "DSIy3Lmrgd6Qo9Ox0vWmuGRl" );
        PushService.setDefaultPushCallback(this, HomePage.class);
        PushService.subscribe(this, "public", HomePage.class);
        AVInstallation.getCurrentInstallation().saveInBackground();
        AVOSCloud.setDebugLogEnabled(true);
    }
}
