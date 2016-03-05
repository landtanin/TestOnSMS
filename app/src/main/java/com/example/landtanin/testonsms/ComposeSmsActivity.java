package com.example.landtanin.testonsms;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by landtanin on 3/5/16 AD.
 */
public class ComposeSmsActivity extends Activity {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();

        final String myPackageName = getPackageName();
        if (!Telephony.Sms.getDefaultSmsPackage(this).equals(myPackageName)) {
            // App is not default.
            // Show the "not currently set as the default SMS app" interface
            View viewGroup = findViewById(R.id.not_default_app);
            viewGroup.setVisibility(View.VISIBLE);

            // Set up a button that allows the user to change the default SMS app
            Button button = (Button) findViewById(R.id.change_default_app);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent =
                            new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
                    intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME,
                            myPackageName);
                    startActivity(intent);
                }
            });
        } else {
            // App is the default.
            // Hide the "not currently set as the default SMS app" interface
            View viewGroup = findViewById(R.id.not_default_app);
            viewGroup.setVisibility(View.GONE);

            // try to check whether this app is really become default sms app
            Button button = (Button) findViewById(R.id.change_default_app);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText("ALL GOOD");
                }
            });

        }



    }

}

