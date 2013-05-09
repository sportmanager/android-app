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
		// инициализаци€
		tabHost.setup();

		TabHost.TabSpec tabSpec;

		// создаем вкладку и указываем тег
		tabSpec = tabHost.newTabSpec("tag1");
		// название вкладки
		tabSpec.setIndicator("¬кладка 1");
		// указываем id компонента из FrameLayout, он и станет содержимым
		tabSpec.setContent(R.id.tvTab1);
		// добавл€ем в корневой элемент
		tabHost.addTab(tabSpec);

		tabSpec = tabHost.newTabSpec("tag2");
		// указываем название и картинку
		// в нашем случае вместо картинки идет xml-файл,
		// который определ€ет картинку по состо€нию вкладки
		tabSpec.setIndicator("¬кладка 2",
				getResources().getDrawable(R.drawable.tab_icon_selector));
		tabSpec.setContent(R.id.tvTab2);
		tabHost.addTab(tabSpec);

		tabSpec = tabHost.newTabSpec("tag3");
		// создаем View из layout-файла
		View v = getLayoutInflater().inflate(R.layout.tab_header, null);
		// и устанавливаем его, как заголовок
		tabSpec.setIndicator(v);
		tabSpec.setContent(R.id.tvTab3);
		tabHost.addTab(tabSpec);

		// втора€ вкладка будет выбрана по умолчанию
		tabHost.setCurrentTabByTag("tag2");

		// обработчик переключени€ вкладок
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			public void onTabChanged(String tabId) {
				Toast.makeText(getBaseContext(), "tabId = " + tabId,
						Toast.LENGTH_SHORT).show();
			}
		});
	}


}