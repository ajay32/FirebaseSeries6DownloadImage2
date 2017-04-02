package com.hackingbuzz.firebaseseries6downloadimage2;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    ImageView getImage;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          getImage = (ImageView) findViewById(R.id.iv_get_image);


        btn = (Button) findViewById(R.id.btn_getimage);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
            }
        });

    }



    public void getImage() {

        FirebaseStorage storage = FirebaseStorage.getInstance();

     //   StorageReference storageRef = storage.getReferenceFromUrl("gs://fireapp-494a3.appspot.com").child("aa.jpg");    // creating a url from our firbase storage url and image then then we can get download url (https vala ) from storage reference object...whom we giving our storge url (gs vala )

        StorageReference storageRef = storage.getReferenceFromUrl("gs://fireapp-494a3.appspot.com").child("pics").child("bb.jpg");  // this is the other way around... first our storage url then ..folder name (pics) then image name (bb.jpg)

        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.i("ajaymehta+", "uri: " + uri.toString());

                                                                                                          //  getImage.setImageURI(uri);  // this only takes the uri from android i guess like android.resources///:  something like this
                Picasso.with(MainActivity.this).load(uri).fit().centerCrop().into(getImage);  //this load the  url from https://
           // first it takes time to get the image from server..then again it shoud be from cache memory not getting all the time from server..for that use
                /// FirbaseImageLoader...cool

            }
        });

    }
}
