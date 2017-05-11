package com.mobilelab.splashscreen;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by ivy on 5/11/2017.
 */

public class Splashscreen extends AppCompatActivity {

    ProgressBar mprogressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView img = (ImageView) findViewById(R.id.imageView);

        Animation anim = AnimationUtils.loadAnimation(this , R.anim.anim_down);
        img.setAnimation(anim);

        mprogressbar = (ProgressBar) findViewById(R.id.progressBar) ;

        ObjectAnimator anim1 = ObjectAnimator.ofInt(mprogressbar , "progress" ,0,100);
        anim1.setDuration(5000);
        anim1.setInterpolator(new DecelerateInterpolator());
        anim1.start();


        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splashscreen.this, HomeActivity.class));
                finish();
            }
        }, 3000);


    }


}
