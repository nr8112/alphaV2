package com.example.alphav2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
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
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(
                Color.parseColor("#0F9D58"));
        actionBar.setBackgroundDrawable(colorDrawable);


        iv = findViewById(R.id.imageview);

        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }


    public void upload_pic(View view) {
        if (filePath != null) {

            // no image
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("no image upload....");
            progressDialog.show();

            // create child of storageReference
            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());

            //  failure
            ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(// Image uploaded successfully
                        UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(image.this, "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                                }
                            })



                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(MainActivity.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override

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

