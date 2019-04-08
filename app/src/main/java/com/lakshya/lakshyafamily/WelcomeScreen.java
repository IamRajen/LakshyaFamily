package com.lakshya.lakshyafamily;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeScreen extends AppCompatActivity {

    public  int SLEEP_TIMER = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome_screen);
        getSupportActionBar().hide();
        ImageView imageView =findViewById(R.id.appIcon);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        imageView.startAnimation(animation);
        TextView textView= findViewById(R.id.appName);
        textView.startAnimation(animation);
        TextView textView1 = findViewById(R.id.themeText);
        textView1.startAnimation(animation);
        TextView textView2 = findViewById(R.id.theme2text);
        textView2.startAnimation(animation);
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();



    }

    private class LogoLauncher extends Thread{
        public void run(){
            try{
                sleep(1000 * SLEEP_TIMER);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            if(load())
            {
                Intent intent = new Intent(WelcomeScreen.this, SelectUser.class);
                startActivity(intent);
                WelcomeScreen.this.finish();
            }
            else
            {
                Intent intent = new Intent(WelcomeScreen.this, HomePage.class);
                startActivity(intent);
                WelcomeScreen.this.finish();
            }

        }
    }

    private boolean load() {

        SharedPreferences sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("username","");
        String password = sharedPreferences.getString("password","");
        if(email.equals("") || password.equals(""))
        {
            return true;
        }
        else
                return false;

    }
}
