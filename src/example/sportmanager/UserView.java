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

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

public class UserView extends SherlockFragment {

	public static final String PREFS_NAME = "UserPrefs";
	String email;
	String password;
	SharedPreferences settings;
	View tv;
	View v;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		tv = (View) getActivity().findViewById(R.id.text);
		super.onActivityCreated(savedInstanceState);

		settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
		email = settings.getString("email", "");
		password = settings.getString("password", "");

		new MyTask().execute("http://app.sportmanager.zz.mu/");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.hello_world, container, false);
		return v;
	}

	class MyTask extends AsyncTask<String, String, JSONObject> {

		protected void onPreExecute() {
			super.onPreExecute();

		}

		protected JSONObject doInBackground(String... params) {

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

			try {
				String error = result.getString("error");
				if (error == "false") {
					((TextView) tv).setText("Hello, "
							+ result.getString("name") + "!");
				} else {
					Toast.makeText(getActivity(),
							"Invalid username/password. :'(", Toast.LENGTH_LONG)
							.show();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG)
						.show();
				e.printStackTrace();
			}

		}
	}

}
