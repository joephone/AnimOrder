package com.order.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.order.R;
import com.order.adapter.OrderCalculateAdapter;
import com.order.bean.Order;
import com.order.tool.Constant;
import com.order.view.OrederListView;

/**
 * 计算过ITEM高度
 * */
public class CalculateActivity extends Activity {
	private OrederListView cListview;
	private OrderCalculateAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculate);
		initView();
		newThread();
	}

	private void initView() {
		cListview = (OrederListView) findViewById(R.id.cListview);
		View view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.order_footer, null);
		cListview.addFooterView(view);
		cListview.setAdapter(null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * 测试用的,开启子线程,类似网络获取后显示
	 */
	private void newThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				handler.obtainMessage(0).sendToTarget();
			}
		}).start();
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
				case 0:
					initListview();
					break;

				default:
					break;
			}
			super.handleMessage(msg);
		}
	};

	ArrayList<Order> orderlist;

	public void initListview() {
		String[] ordername = Constant.ordername;
		int[] orderimg = Constant.orderimg;
		orderlist = new ArrayList<Order>();
		for (int i = 0; i < ordername.length; i++) {
			Order order = new Order();
			order.setId(i);
			order.setTitle(ordername[i]);
			order.setImg(orderimg[i]);
			orderlist.add(order);
		}
		mAdapter = new OrderCalculateAdapter(getApplicationContext(), orderlist);
		cListview.setAdapter(mAdapter);
		setListViewHeightBasedOnChildren(cListview);
		startAnimation();
	}

	private Animation anim;

	/**
	 * 启动打印订单动画
	 */
	private void startAnimation() {
		// cListview.layout(0, 0, -layout_order_info.getBottom(), 0);
		// height = height + mAdapter.getCount() *
		// BaseTools.dip2px(getApplicationContext(), 70);
		// height = height + (cListview.getDividerHeight() *
		// (cListview.getCount()));//总高度加上分割线高度
		anim = new TranslateAnimation(0.0f, 0.0f, -totalHeight, 0);
		anim.setDuration(1000);
		anim.setFillAfter(true);
		anim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				cListview.clearAnimation();
			}
		});
		cListview.startAnimation(anim);
	}
	/** ListView 总高度 */
	public static int totalHeight = 0;

	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		totalHeight = 0;
		// 由于ADD了个footer，所以总量减去1
		Log.d("listAdapter.getCount()", "" + listAdapter.getCount());
		for (int i = 0, len = listAdapter.getCount() - 1; i < len; i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
			Log.d("getMeasuredHeight", "" + listItem.getMeasuredHeight());
		}

		totalHeight = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		// ViewGroup.LayoutParams params = listView.getLayoutParams();
		// params.height = totalHeight + (listView.getDividerHeight() *
		// (listAdapter.getCount() - 1));
		// listView.setLayoutParams(params);
	}
}
