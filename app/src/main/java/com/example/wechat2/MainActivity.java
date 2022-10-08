package com.example.wechat2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去除默认的toolbar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        // 导航栏
        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // 设置初始界面
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(currentFragment!=null){
            fragmentTransaction.remove(currentFragment);
        }

        MessageFragment fragment = new MessageFragment();
        currentFragment = fragment ;
        fragmentTransaction.add(R.id.frame_content,fragment);
        fragmentTransaction.commit();

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //设置菜单
        toolbar.inflateMenu(R.menu.base_toolbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                int menuItemId = item.getItemId();

                if (menuItemId==R.id.search_select){
                    Toast.makeText(MainActivity.this,"search",Toast.LENGTH_LONG).show();
                }else if(menuItemId==R.id.other_select){
                    Toast.makeText(MainActivity.this,"other",Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
    }

    private Fragment currentFragment = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch(item.getItemId()){
                case R.id.navigation_chit:
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    if(currentFragment!=null){
                        fragmentTransaction.remove(currentFragment);
                    }

                    MessageFragment fragment = new MessageFragment();
                    currentFragment = fragment ;
                    fragmentTransaction.add(R.id.frame_content,fragment);
                    fragmentTransaction.commit();

                    return true;
                case R.id.navigation_contacts:
                    FragmentManager fragmentManager2 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();

                    if(currentFragment!=null){
                        fragmentTransaction2.remove(currentFragment);
                    }

                    ContactsFragment fragment2 = new ContactsFragment();
                    currentFragment = fragment2;
                    fragmentTransaction2.add(R.id.frame_content,fragment2);
                    fragmentTransaction2.commit();

                    return true;
                case R.id.navigation_find:
                    FragmentManager fragmentManager3 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();

                    if(currentFragment!=null){
                        fragmentTransaction3.remove(currentFragment);
                    }

                    FindFragment fragment3 = new FindFragment();
                    currentFragment = fragment3;
                    fragmentTransaction3.add(R.id.frame_content,fragment3);
                    fragmentTransaction3.commit();

                    return true;
                case R.id.navigation_my:
                    FragmentManager fragmentManager4 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction4 = fragmentManager4.beginTransaction();

                    if(currentFragment!=null){
                        fragmentTransaction4.remove(currentFragment);
                    }

                    PersonFragment fragment4 = new PersonFragment();
                    currentFragment = fragment4;
                    fragmentTransaction4.add(R.id.frame_content,fragment4);
                    fragmentTransaction4.commit();

                    return true;
            }
            return false;
        }
    };
}