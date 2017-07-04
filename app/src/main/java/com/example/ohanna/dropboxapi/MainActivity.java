package com.example.ohanna.dropboxapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AppKeyPair;

import java.io.File;
import java.io.FileOutputStream;


public class MainActivity extends AppCompatActivity {

     static private Button btn;

    final static private String APP_KEY = "4jokoovkx2jjtns";
    final static private String APP_SECRET = "4gbojqp5jecw0fs";
    final static private String ACCESS_TOKEN = "uWJztpIUWYAAAAAAAAAANyMKqMp-Qq5h8VEN0arTtUpYixqcD2N1DK6LeeWXyXjM";


    public static String DropboxDownloadPathFrom = "https://www.dropbox.com/home/Apps/MathsOlympiad";
    public static String DropboxDownloadPathTo = "/storage/emulated/0/DropboxItems";
    public static File dir=new File(DropboxDownloadPathTo);

    // In the class declaration section:
    static private DropboxAPI<AndroidAuthSession>  mDBApi;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
        AndroidAuthSession session = new AndroidAuthSession(appKeys);
        session.setOAuth2AccessToken(ACCESS_TOKEN);

        mDBApi = new DropboxAPI<AndroidAuthSession>(session);

        btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(click);
    }

private View.OnClickListener click=new View.OnClickListener() {
    @Override
    public void onClick(View view) {
     DownloadFromDropboxFromPath();
    }
};

























    //private void DownloadFromDropboxFromPath (String downloadPathTo, String downloadPathFrom)

       // DropboxDownloadPathTo = downloadPathTo;
        //DropboxDownloadPathFrom = downloadPathFrom;
       private void DownloadFromDropboxFromPath(){
           {    dir.mkdirs();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Download file ...", Toast.LENGTH_SHORT).show();
                Thread th = new Thread(new Runnable() {
                    public void run() {
                        File file = new File(DropboxDownloadPathTo + DropboxDownloadPathFrom.substring(DropboxDownloadPathFrom.lastIndexOf('.')));
                        if (file.exists()) file.delete();
                        try {
                            FileOutputStream outputStream = new FileOutputStream(file);
                            MainActivity.mDBApi.getFile(DropboxDownloadPathFrom, null, outputStream, null);
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "File successfully downloaded.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                th.start();
            }
        });
    }







}}