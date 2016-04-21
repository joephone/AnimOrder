package com.order.adapter;

import java.util.ArrayList;

import com.order.R;
import com.order.bean.Order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderWriteDeathAdapter extends BaseAdapter {
	public Context context;
	public ArrayList<Order> orderlist;
	private static LayoutInflater inflater = null;

	public OrderWriteDeathAdapter(Context context, ArrayList<Order> orderlist) {
		this.context = context;
		this.orderlist = orderlist;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return orderlist == null ? 0 : orderlist.size();
	}

	@Override
	public Order getItem(int position) {
		// TODO Auto-generated method stub
		if (orderlist != null && orderlist.size() != 0) {
			return orderlist.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			view = inflater.inflate(R.layout.order_writedeath_item, null);
			holder = new ViewHolder();
			holder.order_item_title = (TextView) view.findViewById(R.id.order_item_title);
			holder.order_item_img = (ImageView) view.findViewById(R.id.order_item_img);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.order_item_title.setText(orderlist.get(position).getTitle());
		holder.order_item_img.setImageResource(orderlist.get(position).getImg());
		return view;
	}

	class ViewHolder {
		TextView order_item_title;
		ImageView order_item_img;
	}
}
