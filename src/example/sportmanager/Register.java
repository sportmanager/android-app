package example.sportmanager;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Register extends Activity {

	WebView mWebView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);

		mWebView = (WebView) findViewById(R.id.webview);
		// �������� ��������� JavaScript
		mWebView.getSettings().setJavaScriptEnabled(true);
		// ��������� �������� ��������
		mWebView.loadUrl("http://sportmanager.zz.mu/register");
		mWebView.setWebViewClient(new HelloWebViewClient());
	}

	private class HelloWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
			mWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
