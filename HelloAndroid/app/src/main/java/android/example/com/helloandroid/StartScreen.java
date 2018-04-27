package android.example.com.helloandroid;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.content.Intent;

/**
 * Created by Radhika on 11-04-2018.
 */

public class StartScreen extends Activity{

    private static int SPLASH_TIME_OUT = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_splash);

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run(){
                Intent i  = new Intent(StartScreen.this, OnePageActivity.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}



