package com.example.demolearning;

import android.graphics.Bitmap;
import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by mamram on 8/14/2016.
 */
public class Item {
    Bitmap imageView;

    public Item(Bitmap imageView){
        this.imageView = imageView;
    }

    public Bitmap getImageView(){
        return imageView;
    }

    public void setImageView(Bitmap imageView){
        this.imageView = imageView;
    }
}
