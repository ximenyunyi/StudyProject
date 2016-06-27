package com.yyty.hao.studyproject.activity;


import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.yyty.hao.studyproject.R;
import com.yyty.hao.studyproject.abstracts.ABaseActivity;
import com.yyty.hao.studyproject.view.SpringBackScrollView;
import com.yyty.hao.studyproject.view.TopView;

/**
 * 回弹的scrollview
 */
public class SpringBackActivity extends ABaseActivity {

    private SpringBackScrollView mScrollView;

    private ImageView mHeadImg;

    private TableLayout mMainLayout;

    @Override
    public void initView() {

        mScrollView = (SpringBackScrollView) findViewById(R.id.scroll_view);

        mHeadImg = (ImageView) findViewById(R.id.background_img);

        mMainLayout = (TableLayout) findViewById(R.id.table_layout);
        showTable();
    }

    @Override
    public void initData() {
        mScrollView.setmHeadView(mHeadImg);
    }

    @Override
    public void setClickListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.act_spring_back;
    }

    @Override
    public void initTitleData() {
        topView.setTitleText(getString(R.string.spring_back));
    }

    public void showTable() {
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.leftMargin = 30;
        layoutParams.bottomMargin = 10;
        layoutParams.topMargin = 10;

        for (int i = 0; i < 30; i++) {
            TableRow tableRow = new TableRow(this);
            TextView textView = new TextView(this);
            textView.setText("Test pull down scroll view " + i);
            textView.setTextSize(20);
            textView.setPadding(15, 15, 15, 15);

            tableRow.addView(textView, layoutParams);
            if (i % 2 != 0) {
                tableRow.setBackgroundColor(Color.LTGRAY);
            } else {
                tableRow.setBackgroundColor(Color.WHITE);
            }

            final int n = i;
            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SpringBackActivity.this, "Click item " + n, Toast.LENGTH_SHORT).show();
                }
            });

            mMainLayout.addView(tableRow);
        }
    }
}
