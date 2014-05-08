package com.example.smsgateway;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReceiveSMSActivity extends Activity{

	static Button button;
	static TextView messageBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_sms);
        messageBox=(TextView)findViewById(R.id.messageBox);
    }

    public static void updateMessageBox(String msg)
    {
    	messageBox.append(msg+"\n");
    }
    
    
    
    
    
    public void takePic(View view)
    {
    	Log.v("ACTIVITY"," ACA !");
    }
}