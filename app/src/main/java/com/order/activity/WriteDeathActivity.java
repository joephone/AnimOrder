package com.order.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;

import com.order.R;
import com.order.adapter.OrderCalculateAdapter;
import com.order.adapter.OrderWriteDeathAdapter;
import com.order.bean.Order;
import com.order.tool.BaseTools;
import com.order.tool.Constant;
import com.order.view.OrederListView;
/**
 * 布局内写死ITEM高度
 * */
public class WriteDeathActivity extends Activity {
	private OrederListView wListview;
	private OrderWriteDeathAdapter mAdapter;
	/** ListView 总高度 */
	private int height = 0;
	/** ListView内ITEM高度 ,布局中单位为dip */
	private int item_height = 70;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.writerdeath);
		initView();
		newThread();
	}

	private void initView() {
		wListview = (OrederListView) findViewById(R.id.wListview);
		View view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.order_footer, null);
		wListview.addFooterView(view);
		wListview.setAdapter(null);
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
		mAdapter = new OrderWriteDeathAdapter(getApplicationContext(), orderlist);
		wListview.setAdapter(mAdapter);
		startAnimation();
	}

	private Animation anim;

	/**
	 * 启动打印订单动画
	 */
	private void startAnimation() {
		// wListview.layout(0, 0, -layout_order_info.getBottom(), 0);
		height = height + mAdapter.getCount()
				* BaseTools.dip2px(getApplicationContext(), item_height);
		height = height
				+ (wListview.getDividerHeight() * (mAdapter.getCount() - 1));// 总高度加上分割线高度
		anim = new TranslateAnimation(0.0f, 0.0f, -height, 0);
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
				wListview.clearAnimation();
			}
		});
		wListview.startAnimation(anim);
	}
}
