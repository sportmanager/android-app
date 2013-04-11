package example.sportmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Register extends Activity implements OnClickListener {

	Button btnRegister;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		
		btnRegister = (Button) findViewById(R.id.button_make_registration);
        btnRegister.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_make_registration:
			Intent i = new Intent(this, Login.class);
			startActivity(i);
			break;
		}
	}

}
