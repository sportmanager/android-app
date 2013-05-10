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
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

//github
public class Login extends SherlockActivity implements OnClickListener {
	Button btnRegister;
	Button btnLogin;
	TextView textView1;
	ProgressBar progressBar1;
	EditText editEmail;
	EditText editPassword;
	JSONObject serverResponse;
	WebView mWebView;
	public static final String PREFS_NAME = "UserPrefs";
	String email;
	String password;
	SharedPreferences settings;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		settings = getSharedPreferences(PREFS_NAME, 0);

		email = settings.getString("email", "");
		password = settings.getString("password", "");

		setContentView(R.layout.activity_login);

		btnRegister = (Button) findViewById(R.id.button_register);
		btnRegister.setOnClickListener(this);
		btnLogin = (Button) findViewById(R.id.button_login);
		btnLogin.setOnClickListener(this);
		textView1 = (TextView) findViewById(R.id.textView1);
		progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
		editEmail = (EditText) findViewById(R.id.editEmail);
		editPassword = (EditText) findViewById(R.id.editPassword);
		editEmail.setText(email);
		editPassword.setText(password);
		if (email != "" && password != "") {
			new MyTask().execute("http://app.sportmanager.zz.mu/");
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.activity_login, menu);
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
			email = editEmail.getText().toString();
			password = editPassword.getText().toString();
			new MyTask().execute("http://app.sportmanager.zz.mu/");

			break;
		default:
			break;
		}
	}

	class MyTask extends AsyncTask<String, String, JSONObject> {

		protected void onPreExecute() {
			super.onPreExecute();
			progressBar1.setVisibility(View.VISIBLE);
			textView1.setText("connecting");
			btnLogin.setEnabled(false);
			btnRegister.setEnabled(false);
			editEmail.setEnabled(false);
			editPassword.setEnabled(false);

		}

		protected JSONObject doInBackground(String... params) {
			SharedPreferences.Editor editor = settings.edit();
			editor.putString("email", email);
			editor.putString("password", password);
			editor.commit();
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(params[0]);
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("email", email));
			pairs.add(new BasicNameValuePair("password", password));

			try {
				post.setEntity(new UrlEncodedFormEntity(pairs));
				HttpResponse response = client.execute(post);
				InputStream inputstream = response.getEntity().getContent();

				BufferedReader rd = new BufferedReader(new InputStreamReader(
						inputstream));
				char[] c = new char[1];
				StringBuilder result = new StringBuilder();

				while (rd.read(c) != -1) {
					result.append(c);
				}
				JSONObject jObj = null;
				try {
					jObj = new JSONObject(result.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return jObj;

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

			return new JSONObject();
		}

		protected void onPostExecute(JSONObject result) {
			super.onPostExecute(result);

			progressBar1.setVisibility(View.INVISIBLE);
			btnLogin.setEnabled(true);
			btnRegister.setEnabled(true);
			editEmail.setEnabled(true);
			editPassword.setEnabled(true);

			try {
				String error = result.getString("error");
				if (error == "false") {
					SharedPreferences.Editor editor = settings.edit();
					editor.putString("name", result.getString("name"));
					editor.commit();
					Intent i2 = new Intent(Login.this, Main.class);
					startActivity(i2);
				} else {
					Toast.makeText(Login.this,
							"Invalid username/password. :'(", Toast.LENGTH_LONG)
							.show();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Toast.makeText(Login.this, "error", Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}

			// Debug
			if (textView1 != null) {
				textView1.setText("");
			}
			// Log.e("SPORT", result.toString());
		}
	}
}