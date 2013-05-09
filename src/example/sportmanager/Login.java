package example.sportmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

//github
public class Login extends Activity implements OnClickListener{
	Button btnRegister;
	Button btnLogin;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        btnRegister = (Button) findViewById(R.id.button_register);
        btnRegister.setOnClickListener(this);
        btnLogin = (Button) findViewById(R.id.button_login);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.button_register: 
			Intent i= new Intent(this, Register.class);
			startActivity(i);
			break;
		case R.id.button_login: 
			Intent i2= new Intent(this, Main.class);
			startActivity(i2);
			break;
			default: break;
		}		
	}
	
}
