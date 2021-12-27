package com.example.alphav2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class image extends AppCompatActivity {

    ImageView iv;
    Uri filePath;

    // request code
    private final int PICK_IMAGE_REQUEST = 22;

    // firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        iv = findViewById(R.id.imageview);

        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }


    public void get_pic(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image from here..."),PICK_IMAGE_REQUEST);
    }




    public void upload_pic(View view) {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());

            ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();Toast.makeText(image.this, "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                }}).addOnFailureListener(new OnFailureListener() {@Override public void onFailure(@NonNull Exception e)
            {
                progressDialog.dismiss();Toast.makeText(image.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);


        return true;

    }

    /**
     * goes to  activity.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        String st = item.getTitle().toString();

        if (st.endsWith("notific")) {
            Intent si = new Intent(this, notific.class);
            startActivity(si);
        }
        else if (st.endsWith("sg up")) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        else if (st.endsWith("image")) {
            Intent si = new Intent(this, image.class);
            startActivity(si);
        }

        return true;
    }


}