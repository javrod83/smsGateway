package com.example.smsgateway;


import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class TextMessageReceiver extends BroadcastReceiver{

	public void onReceive(Context context, Intent intent)
	{
		Bundle bundle=intent.getExtras();

		Object[] messages=(Object[])bundle.get("pdus");
		SmsMessage[] sms=new SmsMessage[messages.length];

		for(int n=0;n<messages.length;n++){
			sms[n]=SmsMessage.createFromPdu((byte[]) messages[n]);
		}

		for(SmsMessage msg:sms){
			
			try {
				etCallHome(msg.getOriginatingAddress(), msg.getMessageBody());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ReceiveSMSActivity.updateMessageBox("\nFrom: "+msg.getOriginatingAddress()+"\n"+
					"Message: "+msg.getMessageBody()+"\n");
		}
	}
	
	
	
	
	
	public void onPostExecute(String res)
	{
		Log.i("MAIN", res);
	}
	
	
	public void etCallHome(String add, String msg) throws InterruptedException, ExecutionException
		{
		
		//String getURL = "http://mpt.godlab.cc:3000/sms?user="+add+"&msg="+msg;
		String getURL = "http://na-godlab.local:3000/sms?user="+add+"&msg="+msg;
	    
		    
		    
		    
		     AsyncTask<String,String,String> myTask =   new RequestTask();
		     myTask.execute(getURL.replace(' ', '+'));
		   // String some =  ((RequestTask) myTask).getResponse();
		    //Log.e("MAIN", myTask.get());
		   // Log.e("URI", getURL.replace(' ', '+'));
		    
		   // ReceiveSMSActivity.updateMessageBox(myTask.get());
		}	
	
}