package com.example.danielmorales.vrtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.pano_view)
    VrPanoramaView panoramaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        panoramaView.setEventListener(new VrPanoramaEventListener());
        VrPanoramaView.Options options = new VrPanoramaView.Options();
        options.inputType = VrPanoramaView.Options.TYPE_MONO;
        Bitmap bitmap = getBitmapFromResource();
        panoramaView.loadImageFromBitmap(bitmap, options);
    }

    @Override
    protected void onResume() {
        super.onResume();
        panoramaView.resumeRendering();
    }

    @Override
    protected void onPause() {
        super.onPause();
        panoramaView.pauseRendering();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        panoramaView.shutdown();
    }

    private Bitmap getBitmapFromResource() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outMimeType = "application/vnd.google.panorama360+jpg";
        return BitmapFactory.decodeResource(getResources(), R.mipmap.amsterdam, options);
    }
}
