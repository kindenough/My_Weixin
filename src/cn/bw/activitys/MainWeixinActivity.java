package cn.bw.activitys;

import java.util.ArrayList;

import cn.bw.information.InformationActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.LayoutParams;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class MainWeixinActivity extends Activity {
	public static MainWeixinActivity instance = null;
	 
	private ViewPager mTabPager;	
	private ImageView mTabImg;// 动画图片
	private ImageView mTab1,mTab2,mTab3,mTab4;
	private int zero = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int one;//单个水平动画位移
	private int two;
	private int three;
	private LinearLayout mClose;
    private LinearLayout mCloseBtn;
    private RelativeLayout information;
    private View layout;	
	private boolean menu_display = false;
	private PopupWindow menuWindow;
	private LayoutInflater inflater;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
		setContentView(R.layout.main_weixin);
		mTabPager = (ViewPager) findViewById(R.id.tabpager);
		//设置ViewPager的页面翻滚监听
		mTabPager.setOnPageChangeListener(new myOnPageChangeListener());
		mTabImg = (ImageView) findViewById(R.id.img_tab_now);
		mTab1 = (ImageView) findViewById(R.id.img_weixin);
		mTab2 = (ImageView) findViewById(R.id.img_address);
		mTab3 = (ImageView) findViewById(R.id.img_friends);
		mTab4 = (ImageView) findViewById(R.id.img_settings);
		mTab1.setOnClickListener(new myOnClickListener(0));
		mTab2.setOnClickListener(new myOnClickListener(1));
		mTab3.setOnClickListener(new myOnClickListener(2));
		mTab4.setOnClickListener(new myOnClickListener(3));
		
		//获取屏幕的分辨率，以计算偏移量
		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		one = width/4;  //因为水平有四个叶卡，所以要均分为四份。
		two = one * 2;
		three = one *3;
		//加载布局
		LayoutInflater inflater = LayoutInflater.from(this);
		View view1 = inflater.inflate(R.layout.main_tab_weixin, null);
		View view2 = inflater.inflate(R.layout.main_tab_address, null);
		View view3 = inflater.inflate(R.layout.main_tab_friends, null);
		View view4 = inflater.inflate(R.layout.main_tab_settings, null);
		//将布局放入集合
		final ArrayList<View> views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		views.add(view3);
		views.add(view4);
		//放入adapter
		PagerAdapter adapter = new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}
			
			@Override
			public int getCount() {
				return views.size();
			}
			
			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager)container).removeView(views.get(position));
			}
			
			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager)container).addView(views.get(position));
				return views.get(position);
			}
		};
		mTabPager.setAdapter(adapter);
	}
	
	public class myOnPageChangeListener implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}

		@Override
		public void onPageSelected(int arg0) {
			Animation anim = null;
			switch (arg0) {
			case 0:
				mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_pressed));
				if (currIndex == 1) {
					anim = new TranslateAnimation(one, 0, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
				}else if (currIndex == 2) {
					anim = new TranslateAnimation(two, 0, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
				}else if (currIndex == 3) {
					anim = new TranslateAnimation(three, 0, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
				}
				break;
			case 1:
				mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_pressed));
				if (currIndex == 0) {
					anim = new TranslateAnimation(zero, one, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_normal));
				}else if (currIndex == 2) {
					anim = new TranslateAnimation(two, one, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
				}else if (currIndex == 3) {
					anim = new TranslateAnimation(three, one, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
				}
				break;
			case 2:
				mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_pressed));
				if (currIndex == 0) {
					anim = new TranslateAnimation(zero, two, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_normal));
				}else if (currIndex == 1) {
					anim = new TranslateAnimation(one, two, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
				}else if (currIndex == 3) {
					anim = new TranslateAnimation(three, two, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
				}
				break;
			case 3:
				mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_pressed));
				if (currIndex == 0) {
					anim = new TranslateAnimation(zero, three, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_normal));
				}else if (currIndex == 1) {
					anim = new TranslateAnimation(one, three, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
				}else if (currIndex == 2) {
					anim = new TranslateAnimation(two, three, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
				}
				break;
			}
			currIndex = arg0;
			anim.setFillAfter(true);//图片停在偏移的位置
			anim.setDuration(150);
			mTabImg.startAnimation(anim);
		}
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {//获取back键
			if (menu_display == true) {//如果退出界面已存在，界面消失
				menuWindow.dismiss();
				menu_display = false;
			}else {
				Intent intent = new Intent();
            	intent.setClass(MainWeixinActivity.this,ExitActivity.class);
            	startActivity(intent);
			}
		}else if (keyCode == KeyEvent.KEYCODE_MENU) {//菜单键
			if (menu_display == false) {
				//点菜单键 ，弹出popupwindow(退出)
				inflater = LayoutInflater.from(this);
				layout = inflater.inflate(R.layout.main_menu, null);
				//添加布局
				menuWindow = new PopupWindow(layout, LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
				//设置布局在popupwindow中的位置
				menuWindow.showAtLocation(layout, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
				mClose = (LinearLayout) layout.findViewById(R.id.menu_close);
				mCloseBtn = (LinearLayout) layout.findViewById(R.id.menu_close_btn);
				mCloseBtn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
		            	intent.setClass(MainWeixinActivity.this,ExitActivity.class);
		            	startActivity(intent);
						menuWindow.dismiss();
					}
				});
				menu_display = true;
			}else {
				menuWindow.dismiss();
				menu_display = false;
			}
			return false;
		}
		return false;
	}
	
	public class myOnClickListener implements OnClickListener{
		int index = 0;
		public myOnClickListener(int i) {
			this.index = i;
		}
		@Override
		public void onClick(View v) {
			mTabPager.setCurrentItem(index);
		}
	}
	//设置标题栏右侧按钮的作用
		public void btnmainright(View v) {  
			Intent intent = new Intent (MainWeixinActivity.this,MainTopRightDialog.class);			
			startActivity(intent);	
			//Toast.makeText(getApplicationContext(), "点击了功能按钮", Toast.LENGTH_LONG).show();
	      }  	
		public void startchat(View v) {      //小黑  对话界面
			Intent intent = new Intent (MainWeixinActivity.this,ChatActivity.class);			
			startActivity(intent);	
			//Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_LONG).show();
	      }  
		public void exit_settings(View v) {                           //退出  伪“对话框”，其实是一个activity
			Intent intent = new Intent (MainWeixinActivity.this,ExitFromSettings.class);			
			startActivity(intent);	
		 }
		public void btn_shake(View v) {                                   //手机摇一摇
			Intent intent = new Intent (MainWeixinActivity.this,ShakeActivity.class);			
			startActivity(intent);	
		}
		public void settings_information(View v) {                         //个人信息
			Intent intent = new Intent(MainWeixinActivity.this,InformationActivity.class);
			startActivity(intent);
		}
}
