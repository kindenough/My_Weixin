package cn.bw.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
	}
	public void welcome_login(View v) {  
      	Intent intent = new Intent();
		intent.setClass(WelcomActivity.this,LoginActivity.class);
		startActivity(intent);
		//this.finish();
      }  
    public void welcome_register(View v) {  
      	Intent intent = new Intent();
		intent.setClass(WelcomActivity.this,MainWeixinActivity.class);
		startActivity(intent);
		//this.finish();
      }  

}
