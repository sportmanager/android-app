package example.sportmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

//github
public class Login extends Activity implements OnClickListener {
	Button btnRegister;
	Button btnLogin;
	TextView textView1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		btnRegister = (Button) findViewById(R.id.button_register);
		btnRegister.setOnClickListener(this);
		btnLogin = (Button) findViewById(R.id.button_login);
		btnLogin.setOnClickListener(this);
		textView1 = (TextView) findViewById(R.id.textView1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_register:
			Intent i = new Intent(this, Register.class);
			startActivity(i);
			break;
		case R.id.button_login:
			// Intent i2= new Intent(this, Main.class);
			// startActivity(i2);
			MyTask Task = new MyTask();
			Task.execute();
			
			break;
		default:
			break;
		}
	}

	class MyTask extends AsyncTask<String, String, String> {

		protected void onPreExecute() {
			super.onPreExecute();
			textView1.setText("connecting");
		}

		protected String doInBackground(String... params) {
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost("http://app.sportmanager.zz.mu/login");
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("key1", "value1"));
			pairs.add(new BasicNameValuePair("key2", "value2"));
			try {
				post.setEntity(new UrlEncodedFormEntity(pairs));
				HttpResponse response = client.execute(post);
				InputStream inputstream = response.getEntity().getContent();
				BufferedReader rd = new BufferedReader(new InputStreamReader(inputstream));
				char[] c = new char[1];
				StringBuffer result = new StringBuffer();
				while(rd.read(c) != -1) {
					result.append(c);
				}
				return result.toString();
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return "";
			}

		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			textView1.setText(result);
			Log.e("SPORT", result);
		}
	}

}
