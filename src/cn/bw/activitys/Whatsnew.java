package cn.bw.activitys;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Whatsnew extends Activity {
	private ViewPager mViewPager;	
	private ImageView mPage0;
	private ImageView mPage1;
	private ImageView mPage2;
	private ImageView mPage3;
	private ImageView mPage4;
	private ImageView mPage5;
		
	private int currIndex = 0;
	private Context mCon;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.whatsnew_viewpager);
		mCon = Whatsnew.this;
		mViewPager = (ViewPager) findViewById(R.id.whatsnew_viewpager);
		mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		
		mPage0 = (ImageView)findViewById(R.id.page0);
        mPage1 = (ImageView)findViewById(R.id.page1);
        mPage2 = (ImageView)findViewById(R.id.page2);
        mPage3 = (ImageView)findViewById(R.id.page3);
        mPage4 = (ImageView)findViewById(R.id.page4);
        mPage5 = (ImageView)findViewById(R.id.page5);
        
        //通过布局加载器获取到布局
        LayoutInflater mLi = LayoutInflater.from(mCon);
        View view1 = mLi.inflate(R.layout.whats1, null);
        View view2 = mLi.inflate(R.layout.whats2, null);
        View view3 = mLi.inflate(R.layout.whats3, null);
        View view4 = mLi.inflate(R.layout.whats4, null);
        View view5 = mLi.inflate(R.layout.whats5, null);
        View view6 = mLi.inflate(R.layout.whats6, null);
        
        final ArrayList<View> list = new ArrayList<View>();
        list.add(view1);
        list.add(view2);
        list.add(view3);
        list.add(view4);
        list.add(view5);
        list.add(view6);
        
        PagerAdapter adapter = new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}
			
			@Override
			public int getCount() {
				return list.size();
			}
			
			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager)container).removeView(list.get(position));
			}
			
			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager)container).addView(list.get(position));
				return list.get(position);
			}
		};
        
        mViewPager.setAdapter(adapter);
	}
	
	public class MyOnPageChangeListener implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}

		@Override
		public void onPageSelected(int arg0) {
			switch (arg0) {
			case 0:				
				mPage0.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
				mPage1.setImageDrawable(getResources().getDrawable(R.drawable.page));
				break;
			case 1:
				mPage1.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
				mPage0.setImageDrawable(getResources().getDrawable(R.drawable.page));
				mPage2.setImageDrawable(getResources().getDrawable(R.drawable.page));
				break;
			case 2:
				mPage2.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
				mPage1.setImageDrawable(getResources().getDrawable(R.drawable.page));
				mPage3.setImageDrawable(getResources().getDrawable(R.drawable.page));
				break;
			case 3:
				mPage3.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
				mPage4.setImageDrawable(getResources().getDrawable(R.drawable.page));
				mPage2.setImageDrawable(getResources().getDrawable(R.drawable.page));
				break;
			case 4:
				mPage4.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
				mPage3.setImageDrawable(getResources().getDrawable(R.drawable.page));
				mPage5.setImageDrawable(getResources().getDrawable(R.drawable.page));
				break;
			case 5:
				mPage5.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
				mPage4.setImageDrawable(getResources().getDrawable(R.drawable.page));
				break;
			}
			currIndex = arg0;
		}	
	}
	public void startbutton(View v) {  
      	Intent intent = new Intent();
		intent.setClass(Whatsnew.this,WhatsnewDoor.class);
		startActivity(intent);
		this.finish();
      }
}
