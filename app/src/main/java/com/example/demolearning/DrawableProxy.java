package com.example.demolearning;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.InputStream;

/**
 * Created by mamram on 8/16/2016.
 */
public class DrawableProxy extends Drawable {
    private static final String TAG = DrawableProxy.class.getSimpleName();
    public Drawable tempDrawable;
    Context context;
    String url;
    CustomCallBack customCallBack;
    boolean urlLoaded = false;
    int position;

    public DrawableProxy(Context context, String url, CustomCallBack customCallBack, int position) {
        Log.d(TAG, "DrawableProxy: ");
        Bitmap imageView = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        this.tempDrawable = new BitmapDrawable(context.getResources(), imageView);
        this.context = context;
        this.url = url;
        this.customCallBack = customCallBack;
        this.position = position;
    }

    @Override
    public void draw(Canvas canvas) {
        if(tempDrawable !=null){
            tempDrawable.draw(canvas);
                Log.d(TAG, "draw: ");
                new DownloadImageTask(context).execute(url);
        }
    }

    @Override
    public void setAlpha(int i) {
        if(tempDrawable !=null){
            tempDrawable.setAlpha(i);
        }
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        if(tempDrawable !=null){
            tempDrawable.setColorFilter(colorFilter);
        }
    }

    @Override
    public int getOpacity() {
        return tempDrawable != null ? tempDrawable.getOpacity() : PixelFormat.TRANSPARENT;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        if(tempDrawable !=null){
            tempDrawable.setBounds(bounds);
        }
    }

    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        Context context;

        public DownloadImageTask(Context context) {
            this.context = context;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
	        Bitmap mIcon11 = null;
	        String urldisplay = urls[0];
	        if(OrderActivity.cancelAsync){
		        return mIcon11;
	        }

	        try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                in.close();
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            Log.d(TAG, "onPostExecute: " + tempDrawable);
	        if(bitmap != null){
		        tempDrawable = new BitmapDrawable(context.getResources(), bitmap);
		        customCallBack.loaded(tempDrawable, position);
		        urlLoaded = true;
	        }
        }
    }
}
