package com.example.alphav2;

import static com.example.alphav2.help.CHANNEL_ID;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class notific extends AppCompatActivity {

   public EditText notiText;
    public NotificationManagerCompat nManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notific);
        notiText=findViewById(R.id.et);

       nManager = NotificationManagerCompat.from(this);
    }




    public void Notification(View view) {
        String title = "NOTIFICATION";
        String mess = notiText.getText().toString();
        notiText.setText("");
//create notification
        Notification n = new NotificationCompat.Builder(this, CHANNEL_ID)

                .setContentTitle(title)
                .setContentText(mess)
                .setSmallIcon(R.drawable.icon)
                .setPriority(NotificationCompat.PRIORITY_LOW) //importens
                .build();

        nManager.notify(1, n);

    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);


        return true;

    }

    /**
     * menu ....
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
        else if (st.endsWith("alarm")) {
            Intent si = new Intent(this, alarm.class);
            startActivity(si);
        }
        else if (st.endsWith("qr")) {
            Intent si = new Intent(this, qr.class);
            startActivity(si);
        }
        return true;
    }


}
