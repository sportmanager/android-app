package example.sportmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;

public class Main extends Activity {
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
		// �������������
		tabHost.setup();

		TabHost.TabSpec tabSpec;

		// ������� ������� � ��������� ���
		tabSpec = tabHost.newTabSpec("tag1");
		// �������� �������
		tabSpec.setIndicator("������� 1");
		// ��������� id ���������� �� FrameLayout, �� � ������ ����������
		tabSpec.setContent(R.id.tvTab1);
		// ��������� � �������� �������
		tabHost.addTab(tabSpec);

		tabSpec = tabHost.newTabSpec("tag2");
		// ��������� �������� � ��������
		// � ����� ������ ������ �������� ���� xml-����,
		// ������� ���������� �������� �� ��������� �������
		tabSpec.setIndicator("������� 2",
				getResources().getDrawable(R.drawable.tab_icon_selector));
		tabSpec.setContent(R.id.tvTab2);
		tabHost.addTab(tabSpec);

		tabSpec = tabHost.newTabSpec("tag3");
		// ������� View �� layout-�����
		View v = getLayoutInflater().inflate(R.layout.tab_header, null);
		// � ������������� ���, ��� ���������
		tabSpec.setIndicator(v);
		tabSpec.setContent(R.id.tvTab3);
		tabHost.addTab(tabSpec);

		// ������ ������� ����� ������� �� ���������
		tabHost.setCurrentTabByTag("tag2");

		// ���������� ������������ �������
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			public void onTabChanged(String tabId) {
				Toast.makeText(getBaseContext(), "tabId = " + tabId,
						Toast.LENGTH_SHORT).show();
			}
		});
	}


}