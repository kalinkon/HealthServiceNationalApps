package com.mcc.healthservicefinal.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mcc.healthservicefinal.R;
import com.mcc.healthservicefinal.network.NetworkConnection;

public class SplashActivity extends AppCompatActivity {

    private Thread mSplashThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        getSupportActionBar().hide();//hides the action bar
        //setTheme(R.style.AppTheme.);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#737CA1")));
        final SplashActivity SplashScreen = this;
        mSplashThread=new Thread(){
            public void run(){

                try
                {
                    synchronized(this)
                    {

                        // Wait given period of time or exit on touch
                        wait(3000);
                    }
                }
                catch(InterruptedException ex)
                {


                }
                finish();

                Intent intent = new Intent(SplashActivity.this, MainMenuActivity.class);
                startActivity(intent);

            }

        };
        mSplashThread.start();
    }



}
