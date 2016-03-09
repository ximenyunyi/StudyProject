package com.yyty.hao.studyproject.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yyty.hao.studyproject.R;

public class TopView extends FrameLayout implements View.OnClickListener {

	// 返回按钮
	private ImageView ivBack;

	// 标题
	private TextView tvTitle;

	// 右边按钮
	private ImageView ivRight;
	
	// 右边文字按钮
	private TextView tvRight;
	
	// 左边文字按钮
	private TextView tvLeft;
	


	public TopView(Context context) {
		super(context);
		init();
	}

	public TopView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	//初始化
	private void init() {
		View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_top, this);
		initView();
		initOnclickListern();
	}

	/**
	 * 设置监听事件
	 */
	private void initOnclickListern() {
		ivBack.setOnClickListener(this);
	}


	/**
	 * 初始化控件
	 */
	private void initView() {
		ivBack= (ImageView) this.findViewById(R.id.iv_back);
		tvTitle= (TextView) this.findViewById(R.id.tv_title);
		ivRight= (ImageView) this.findViewById(R.id.iv_right);
		tvRight= (TextView) this.findViewById(R.id.tv_right);
		tvLeft= (TextView) this.findViewById(R.id.tv_left);
	}


	//设置标题
	public void setTitleText(String text) {
		tvTitle.setText(text);
	}
	
	//设置返回按钮是否显示
	public void setIvBackVisibility(int state){
		ivBack.setVisibility(state);
	}
	
	//设置返回按钮图标
	public void setIvBackIcon(int resId){
		ivBack.setImageResource(resId);
	}
	
	//设置返回按钮监听
	public void setIvBackClick(OnClickListener listener){
		ivBack.setOnClickListener(listener);
	}
	
	//设置右边按钮是否显示
	public void setIvRightVisibility(int state){
		ivRight.setVisibility(state);
	}
	
	//设置右边按钮图标
	public void setIvRightIcon(int resId){
		ivRight.setImageResource(resId);
	}
	
	//设置右边按钮监听
	public void setIvRightClick(OnClickListener listener){
		ivRight.setOnClickListener(listener);
	}
	
	//设置右边按钮是否显示
	public void setTxtRightVisibility(int state){
		tvRight.setVisibility(state);
	}
		
	//设置右边按钮图标
	public void setTxtRightStr(String str){
		tvTitle.setText(str);
	}
		
	//设置右边按钮监听
	public void setTxtRightClick(OnClickListener listener){
		tvRight.setOnClickListener(listener);
	}

	//设置左边按钮监听
	public void setTxtLeftClick(OnClickListener listener){
		tvLeft.setOnClickListener(listener);
	}
	
	public void setTxtRightColor(int color){
		tvRight.setTextColor(color);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.iv_back:
				((Activity)getContext()).finish();
				break;
		}
	}
}
