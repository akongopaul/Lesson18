package com.Odhiambo.lesson18;

import java.io.IOException;
import java.net.URI;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UploadService extends Service {
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		//Saving in to the Database
		
//		String url="http://emobilis-server.com/lesson18/data.txt"; //if using your Phone
	
		String url="http://10.0.2.2/lesson18/save.php";
		RequestParams params= new RequestParams();
		params.put("data", "JAmes Bond");
		AsyncHttpClient client= new AsyncHttpClient ();
		client.post(url, params, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] response) {
				String data=new String(response);
				Log.d("DATA", data);
				stopSelf();
								
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				Log.e("ERROR", "Failled to Upload");
				stopSelf();
				
			}
		});
		
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
