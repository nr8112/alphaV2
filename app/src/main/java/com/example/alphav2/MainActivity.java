package com.example.alphav2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText pass_ET, email_phon_ET;
    TextView telephonOrEmail;
    String eOrp, pass;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pass_ET = findViewById(R.id.TextPassword);
        email_phon_ET = findViewById(R.id.TextEmail);

        telephonOrEmail = findViewById(R.id.telephonOrEmail);
        fAuth = FirebaseAuth.getInstance();

    }




    public void singUp(View view) {


        eOrp = email_phon_ET.getText().toString();
        pass = pass_ET.getText().toString();

        if (TextUtils.isEmpty(eOrp)) {
            email_phon_ET.setError("Email Is Required.");
        }


        if (TextUtils.isEmpty(pass)) {
            pass_ET.setError("Password Is Required.");
        } else {
            if (pass.length() < 8) {
                pass_ET.setError("Password must be longer than 8 digits.");
            } else {
                fAuth.createUserWithEmailAndPassword(eOrp, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "hello new user", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }

    }




  public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.main, menu);


      return true;

  }

    /**
     * goes to the activity.
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

    //---------------------------
