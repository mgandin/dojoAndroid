package com.example.helloworld;


import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    TextView tvIsConnected;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        // get reference to the views
        tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);
 
        // check if you are connected or not
        if(isConnected()){
            tvIsConnected.setBackgroundColor(0xFF00CC00);
            
            tvIsConnected.setText("It works, for Balou !");
            scheduleAsyncDisplay("http://172.18.9.86:6797/package");
        }
        else{
            tvIsConnected.setText("It sucks !");
        }

    }
 
    private void scheduleAsyncDisplay(final String url) {
    	//AsyncTask<Params, Progress, Result>
    	AsyncTask<String, Void, String> displayToto59 = new AsyncTask<String, Void, String>() {
			@Override
			protected String doInBackground(String... params) {
				AndroidHttpClient httpClient = AndroidHttpClient.newInstance("Android");
				HttpPost request = new HttpPost(url);
				HttpResponse response;
				try {
					response = httpClient.execute(request);
				} catch (IOException e) {
					Log.e("MainActivity", e.getMessage(), e);
					throw new RuntimeException(e);
				}
				
				HttpEntity responseEntity = response.getEntity();
				
				try {
					return EntityUtils.toString(responseEntity);
				} catch (IOException | ParseException e) {
					Log.e("MainActivity", e.getMessage(), e);
					throw new RuntimeException(e);
				}
				
			}
			
			@Override
			protected void onPostExecute(String result) {
				JSONObject jsonObject = null;
				try {
					jsonObject = new JSONObject(result);
					
				} catch (JSONException e) {
					throw new RuntimeException(e);
				}
				
				TableLayout passengerTableLayout = (TableLayout) findViewById(R.id.passengerTableLayout);
//				LinearLayout passengerLayout = (LinearLayout) findViewById(R.id.passengerLayout);
				try {
					JSONArray passengers = jsonObject.getJSONArray("passengers");
					TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
					tableLayoutParams.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
					
					passengerTableLayout.setLayoutParams(tableLayoutParams);
					for (int i = 0; i < passengers.length(); i++) {
						JSONObject passenger = (JSONObject) passengers.get(i);
						
						TableRow row = new TableRow(MainActivity.this);
						passengerTableLayout.addView(row);
						
						TableRow.LayoutParams rowLayoutParams = new TableRow.LayoutParams();
						rowLayoutParams.setMargins(10, 10, 10, 10);
						
						TextView colName = new TextView(MainActivity.this);
						colName.setText(passenger.getString("givenName"));
						row.addView(colName, rowLayoutParams);
						
						TextView colSurname = new TextView(MainActivity.this);
						colSurname.setText(passenger.getString("surname"));
						row.addView(colSurname, rowLayoutParams);
						
						TextView colRph = new TextView(MainActivity.this);
						colRph.setText(passenger.getString("rph"));
						row.addView(colRph, rowLayoutParams);
						
						TextView colCode = new TextView(MainActivity.this);
						colCode.setText(passenger.getString("code"));
						row.addView(colCode, rowLayoutParams);
					}
				} catch (JSONException e) {
					throw new RuntimeException(e);
				}
			}
    	};
    	
    	displayToto59.execute();
	}

	public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) 
                return true;
            else
                return false;   
    }
    
}
