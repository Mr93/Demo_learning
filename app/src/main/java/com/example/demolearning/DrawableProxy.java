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
public class DrawableProxy extends Drawable implements Drawable.Callback {
    private static final String TAG = DrawableProxy.class.getSimpleName();
    public Drawable tempDrawable;
    Context context;
    String url;
    public Canvas mCanvas;

    public DrawableProxy(Drawable drawable, Context context, String url) {
        this.tempDrawable = drawable;
        this.context = context;
        this.url = url;
    }

    @Override
    public void draw(final Canvas canvas) {
        if(tempDrawable !=null){
            tempDrawable.draw(canvas);
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

    @Override
    public void invalidateDrawable(Drawable drawable) {
        Log.d(TAG, "invalidateDrawable: aaaaaaaaaaa");
        onBoundsChange(drawable.getBounds());
        int w = 50, h = 50;

        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(w, h, conf); // this creates a MUTABLE bitmap
        Canvas canvas = new Canvas(bmp);
        draw(canvas);
    }

    @Override
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long l) {

    }

    @Override
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {

    }

    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        Context context;

        public DownloadImageTask(Context context) {
            this.context = context;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {

            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
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
            tempDrawable = new BitmapDrawable(context.getResources(), bitmap);
            Log.d(TAG, "onPostExecute: " + tempDrawable);

            invalidateDrawable(tempDrawable);
            Toast.makeText(context, "downloaded", Toast.LENGTH_SHORT).show();
        }
    }
}
