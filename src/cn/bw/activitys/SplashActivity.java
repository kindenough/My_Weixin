package cn.bw.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;

public class SplashActivity extends Activity {
	private LinearLayout layout;
	private Context mCon;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		//设置activity的样式
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mCon = SplashActivity.this;
		layout = (LinearLayout) findViewById(R.id.ll_splash);
		AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
		aa.setDuration(2000);
		layout.startAnimation(aa);
	}

	@Override
	protected void onStart() {
		super.onStart();
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent intent = new Intent();
				intent.setClass(mCon, WelcomActivity.class);
				startActivity(intent);
				finish();
			}
		}, 2000);
	}
}