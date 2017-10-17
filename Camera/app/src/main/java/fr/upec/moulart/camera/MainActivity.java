package fr.upec.moulart.camera;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.*;

import static fr.upec.moulart.camera.R.styleable.View;

public class MainActivity extends AppCompatActivity {

    private Camera mCamera = null;
    private CameraView mCameraView = null;
    private static final int REQUEST_CALL = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        try{
        mCamera = Camera.open();//you can use open(int) to use different cameras
    } catch (Exception e){
        Log.d("ERROR", "Failed to get camera: " + e.getMessage());
    }

        if(mCamera != null) {
        mCameraView = new CameraView(this, mCamera);//create a SurfaceView to show camera data
        FrameLayout camera_view = (FrameLayout)findViewById(R.id.camera_view);
        camera_view.addView(mCameraView);//add the SurfaceView to the layout
    }

    //btn to close the application
    ImageButton imgClose = (ImageButton)findViewById(R.id.imgClose);
        imgClose.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            System.exit(0);
        }
    });


}

    public void onBtnClicked(View v) {

        if (v.getId() == R.id.button){

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }

    }

}
