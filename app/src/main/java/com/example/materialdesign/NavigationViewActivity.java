package com.example.materialdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NavigationViewActivity extends AppCompatActivity {

    private DrawerLayout mNav_drawer_layout;
    private NavigationView nav_view;
    private ActionBar actionBar;
    private FloatingActionButton fab;
    private RecyclerView recycler_view;
    private Toolbar toolbar;

    //定义了一个数组,数组中存放了很多个Fruit的实例,每个实例都代表一种水果
    private Fruit[] fruits = {new Fruit("Apple", R.drawable.a),
            new Fruit("Banana", R.drawable.b),
             new Fruit("Banana", R.drawable.c),
             new Fruit("Banana", R.drawable.d),
             new Fruit("Banana", R.drawable.e),
             new Fruit("Banana", R.drawable.f),
             new Fruit("Banana", R.drawable.g),
             new Fruit("Banana", R.drawable.h),
             new Fruit("Banana", R.drawable.i),
             new Fruit("Banana", R.drawable.j),
             new Fruit("Banana", R.drawable.q),
             new Fruit("Banana", R.drawable.s),
    };

    private List<Fruit> fruitList = new ArrayList<>();
    private SwipeRefreshLayout swipe_refresh;
    private FruitAdapter fruitAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);
        initView();
        initFruits();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.b);
        }
        nav_view.setCheckedItem(R.id.nav_call);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mNav_drawer_layout.closeDrawers();
                return true;
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(NavigationViewActivity.this, "Data restored", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recycler_view.setLayoutManager(layoutManager);
        fruitAdapter = new FruitAdapter(fruitList);
        recycler_view.setAdapter(fruitAdapter);

        swipe_refresh.setColorSchemeResources(R.color.design_default_color_primary);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //通常情况下 onRefresh中应该去网络上请求最新的数据,然后再将这些数据展示出来,
                refreshFruits();
            }
        });
    }

    private void refreshFruits(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        fruitAdapter.notifyDataSetChanged();
                        swipe_refresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFruits() {
        //先清空一下fruitList中的数据,接着使用随机函数,从刚才定义的Fruit数组中随机取出一个
        //水果放到List中
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    private void initView() {
        toolbar = findViewById(R.id.nav_toolbar);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);
        mNav_drawer_layout = findViewById(R.id.nav_drawer_layout);
        nav_view = findViewById(R.id.nav_view);
        actionBar = getSupportActionBar();
        recycler_view = findViewById(R.id.recycler_view);
        swipe_refresh = findViewById(R.id.swipe_refresh);
    }
}