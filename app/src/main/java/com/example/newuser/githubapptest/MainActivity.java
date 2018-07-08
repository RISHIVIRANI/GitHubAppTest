package com.example.newuser.githubapptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.TextView;\
import android.util.Log;
import android.view.SurfaceHolder;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    SurfaceView cameraView;
    TextView textView;
    CameraSource cameraSource;
    final int RequestCameraPermissionID = 1001;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraView = (SurfaceView)findViewById(R.id.surface_view);
        textView = (TextView)findViewById(R.id.text_view);

        TextRecognizer textRecognizer = new TextRecognizer().Builder(getApplicationContext());
        if (!textRecognizer.isOperational()) {
            Log.w("MainActivitiy", "Detector dependencies are not yet available");
        }
        else {
            cameraSource = new CameraSource().Builder(getApplicationContext(), textRecognizer).setFacing(CameraSource.CAMERA_FACING_BACK).setRequestedPreviewSize(1280, 1024).setRequestedFps(2.0f).setAutoFocusEnabled(true).build();
            cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void SurfaceCreated(SurfaceHolder surfaceHolder) {

                    try {
                        cameraSource.start(cameraView.getHolder());
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
