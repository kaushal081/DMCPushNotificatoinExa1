package com.kaushal.cdac.dmcpushnotificatoinexa1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // If a notification message is tapped, any data accompanying the notification
        // message is available in the intent extras. In this project the launcher
        // intent is fired when the notification is tapped, so any accompanying data would
        // be handled here. If you want a different intent fired, set the click_action
        // field of the notification message to the desired intent. The launcher intent
        // is used when no click_action is specified.
        //
        // Handle possible data accompanying notification message.
        if (getIntent().getExtras() != null) {

            for (String key : getIntent().getExtras().keySet()) {
                String value = getIntent().getExtras().getString(key);

                if (key.equals("SecondActivity") && value.equals("True")) {
                    Intent intent = new Intent(this,
                            SecondActivity.class);
                    intent.putExtra("value", value);
                    startActivity(intent);
                    finish();
                }

            }
        }

        try {
            subscribeToPushService();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void subscribeToPushService()
            throws IOException {
        FirebaseMessaging.getInstance().subscribeToTopic("news");

      
        Toast.makeText(MainActivity.this,
                "Subscribed", Toast.LENGTH_SHORT).show();

        String token = FirebaseInstanceId.
                getInstance().getToken("Test","Test1");

              Toast.makeText(MainActivity.this,
                      token, Toast.LENGTH_SHORT).show();
    }


}
