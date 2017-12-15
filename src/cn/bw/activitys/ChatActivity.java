package cn.bw.activitys;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class ChatActivity extends Activity implements OnClickListener {
	
	private Button mBtnSend;
	private Button mBtnBack;
	private ImageButton rightBtn;
	private EditText mEditTextContent;
	private ListView mListView;
	private ChatMsgViewAdapter mAdapter;
	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();
	private String[] msgArray = new String[] { "有大吗", "有！你呢？", "我也有", "那上吧",
			"打啊！你放大啊", "你tm咋不放大呢？留大抢人头那！Cao的。你个菜b", "2B不解释", "尼滚....", };

	private String[] dateArray = new String[] { "2012-12-09 18:00",
			"2012-12-09 18:10", "2012-12-09 18:11", "2012-12-09 18:20",
			"2012-12-09 18:30", "2012-12-09 18:35", "2012-12-09 18:40",
			"2012-12-09 18:50" };
	private final static int COUNT = 8;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat_xiaohei);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		mListView = (ListView) findViewById(R.id.listview);
		mBtnSend = (Button) findViewById(R.id.btn_send);
		mBtnSend.setOnClickListener(this);
		mBtnBack = (Button) findViewById(R.id.btn_back);
		mBtnBack.setOnClickListener(this);
		rightBtn = (ImageButton) findViewById(R.id.right_btn);
		rightBtn.setOnClickListener(this);

		mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
		
		initData();
	}

	private void initData() {
		for (int i = 0; i < COUNT; i++) {
			ChatMsgEntity entity = new ChatMsgEntity();
			entity.setDate(dateArray[i]);
			if (i%2 == 0) {
				entity.setName("小黑");
				entity.setMsgType(true);
			}else {
				entity.setName("人马");
				entity.setMsgType(false);
			}
			entity.setText(msgArray[i]);
			mDataArrays.add(entity);
		}
		mAdapter = new ChatMsgViewAdapter(this, mDataArrays);
		mListView.setAdapter(mAdapter);
	}
	
	private String getDate(){
		Calendar c = Calendar.getInstance();
		String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH)+1);
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String mins = String.valueOf(c.get(Calendar.MINUTE));
        
        
        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":" + mins); 
		return sbBuffer.toString();
	}
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;

		case R.id.btn_send:
			String content = mEditTextContent.getText().toString();
			if (content.length() > 0)
			{
				ChatMsgEntity entity = new ChatMsgEntity();
				entity.setDate(getDate());
				entity.setName("人马");
				entity.setMsgType(false);
				entity.setText(content);
				
				mDataArrays.add(entity);
				//更新listview
				mAdapter.notifyDataSetChanged();
				
				mEditTextContent.setText("");
				
				mListView.setSelection(mListView.getCount() - 1);
			}
			break;
		case R.id.right_btn:
			Intent intent = new Intent (ChatActivity.this,InfoXiaohei.class);			
			startActivity(intent);	
			break;
		}

	}
		//点击小黑图像
	public void head_xiaohei(View v) {     //标题栏 返回按钮
    	Intent intent = new Intent (ChatActivity.this,InfoXiaohei.class);			
		startActivity(intent);	
    } 

}
