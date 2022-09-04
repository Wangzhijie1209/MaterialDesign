package com.example.materialdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class FruitActivity extends AppCompatActivity {

    public static final String FRUIT_NAME = "fruit_name";
    public static final String FRUIT_IMAGE_ID="fruit_image_id";
    private Toolbar fruit_toolbar;
    private CollapsingToolbarLayout collapsing_toolbar;
    private ImageView fruit_image_view;
    private TextView fruit_content_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        initView();

        Intent intent = getIntent();
        String fruitName = intent.getStringExtra(FRUIT_NAME);
        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);

        collapsing_toolbar.setTitle(fruitName);
        Glide.with(this).load(fruitImageId).into(fruit_image_view);
        String fruitContent = generateFruitContent(fruitName);
        fruit_content_text.setText(fruitContent);
    }

    private void initView() {
        fruit_toolbar = findViewById(R.id.fruit_toolbar);
        collapsing_toolbar = findViewById(R.id.collapsing_toolbar);
        fruit_image_view = findViewById(R.id.fruit_image_view);
        fruit_content_text = findViewById(R.id.fruit_content_text);
        //toolbar的标准用法 将他作为ActionBar显示,
        setSupportActionBar(fruit_toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            //启动HomeAsUp按钮,此按钮的默认图标就是一个返回箭头
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    //将水果的名循环拼接500次
    private String generateFruitContent(String fruitName){
        StringBuilder fruitContent = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            fruitContent.append(fruitName);
        }
        return fruitContent.toString();
    }

    //处理HomeAsUp按钮的点击事件,当点击了这个按钮时,就调用finish()方法关闭当前的活动,从而返回上一个活动
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}