package com.mcc.healthservicefinal.activity;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mcc.healthservicefinal.R;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by LINKON on 2/9/2017.
 */

public class ImageViewActivity extends AppCompatActivity {

    String mImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImage=getIntent().getStringExtra("image");

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.image_viewer);


        PhotoView imageView =(PhotoView) findViewById(R.id.imageView);
        Glide.with(this).load(mImage).into(imageView);


        PhotoViewAttacher picView = new PhotoViewAttacher(imageView);

        picView.update();

    }



}

