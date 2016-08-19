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

import java.io.*;
import java.nio.ByteBuffer;

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
	String type;

    public DrawableProxy(Context context, String url, CustomCallBack customCallBack, int position, String type) {
        Log.d(TAG, "DrawableProxy: ");
	    initDrawable(context, position, type);
        this.context = context;
        this.url = url;
        this.customCallBack = customCallBack;
        this.position = position;
	    this.type = type;
    }

	private void initDrawable(Context context, int position, String type) {
		File imgFile = new File(context.getFilesDir(), type+position);
		if(!imgFile.exists()) {
			Bitmap imageView = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
			this.tempDrawable = new BitmapDrawable(context.getResources(), imageView);
		}else {
			Log.d(TAG, "createData: 2");
			this.tempDrawable = new BitmapDrawable(context.getResources(), BitmapFactory.decodeFile(imgFile.getAbsolutePath()));
			this.urlLoaded = true;
		}
	}

	@Override
    public void draw(Canvas canvas) {
        if(tempDrawable !=null){
            tempDrawable.draw(canvas);
	        if(!urlLoaded){
		        new DownloadImageTask(context).execute(url);
	        }
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
		        String fileName = type+position;
		        File file = new File(context.getFilesDir(), fileName);
		        FileOutputStream outputStream;
		        try {
			        outputStream = new FileOutputStream(file);
			        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
			        outputStream.close();
		        } catch (FileNotFoundException e) {
			        e.printStackTrace();
		        } catch (IOException e) {
			        e.printStackTrace();
		        }
		        tempDrawable = new BitmapDrawable(context.getResources(), bitmap);
		        customCallBack.loaded(tempDrawable, position);
		        urlLoaded = true;
	        }
        }
    }
}
