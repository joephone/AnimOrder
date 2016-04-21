package com.order.activity;

import java.util.ArrayList;

import com.order.R;
import com.order.adapter.OrderCalculateAdapter;
import com.order.bean.Order;
import com.order.tool.BaseTools;
import com.order.tool.Constant;
import com.order.view.OrederListView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Sampler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener{
	Button btn_writedeath;
	Button btn_calculate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
	}

	private void initView() {
		btn_writedeath = (Button) findViewById(R.id.btn_writedeath);
		btn_calculate = (Button) findViewById(R.id.btn_calculate);
		btn_writedeath.setOnClickListener(this);
		btn_calculate.setOnClickListener(this);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_writedeath:
			startActivity(new Intent(getApplicationContext(), WriteDeathActivity.class));
			break;
		case R.id.btn_calculate:
			startActivity(new Intent(getApplicationContext(), CalculateActivity.class));
			break;

		default:
			break;
		}
	}
}
