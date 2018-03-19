package com.jvillalba.apod.dm.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;

public class PicassoDownloader implements Target {
    private final String name;
    private final Context context;
    private final String folder_apod = "NasaApod";
    public PicassoDownloader(String name,Context context) {
        this.name = name;
        this.context = context;
    }
    @Override
    public void onPrepareLoad(Drawable arg0) {
    }
    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom arg1) {
        String path = Environment.getExternalStorageDirectory().getPath().concat(File.separator).concat(folder_apod).concat(File.separator);
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            String pathFile = path.concat(name);
            file = new File(pathFile);
            file.createNewFile();
            FileOutputStream ostream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
            ostream.close();
            Toast.makeText(context,pathFile,Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context,"ERROR to Write Image",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBitmapFailed(Drawable arg0) {
        Toast.makeText(context,"ERROR to Download Image",Toast.LENGTH_SHORT).show();
    }
}