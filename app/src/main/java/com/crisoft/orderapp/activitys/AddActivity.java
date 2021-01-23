package com.crisoft.orderapp.activitys;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.crisoft.orderapp.R;
import com.crisoft.orderapp.utils.FileUtil;

import java.io.File;

public class AddActivity extends AppCompatActivity {

    File mImageFile;
    ImageView mImageViewPost1;
    private final int REQUEST_CODE_GALLERY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        mImageViewPost1 = findViewById(R.id.imageViewPost1);

        mImageViewPost1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });

    }

    private void OpenGallery() {
        //OPEN GALLERY OF OUR PHONE
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
    }

    //GO GALLERY AND SELECT PICTURE
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK) {
            try {
                mImageFile = FileUtil.from(this, data.getData());
                //show image on imageView
                mImageViewPost1.setImageBitmap(BitmapFactory.decodeFile(mImageFile.getAbsolutePath()));
            } catch (Exception exception) {
                Log.d("ERROR", "SE PRODUJO UN ERROR " + exception.getMessage());
                Toast.makeText(this, "Se produjo un error " + exception.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
