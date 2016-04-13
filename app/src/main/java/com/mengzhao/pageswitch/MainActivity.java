package com.mengzhao.pageswitch;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mengzhao.pageswitch.pagefragment.ChatFragment;
import com.mengzhao.pageswitch.pagefragment.MainFragment;
import com.mengzhao.pageswitch.pagefragment.MineFragment;
import com.mengzhao.pageswitch.pagefragment.SaveMoneyFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RadioGroup rg;
    private ViewPager vp;
    private List<Fragment> fragmentList;
    private MainFragment mainFragment;
    private SaveMoneyFragment saveMoneyFragment;
    private MineFragment mineFragment;
    private ChatFragment chatFragment;
    private viewPagerAdapter vpAdapter;
    private RadioButton childButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        rg= (RadioGroup) findViewById(R.id.rg_content);
        vp= (ViewPager) findViewById(R.id.viewPager);
        fragmentList=new ArrayList<>();
        mainFragment=new MainFragment();
        fragmentList.add(mainFragment);
        saveMoneyFragment=new SaveMoneyFragment();
        fragmentList.add(saveMoneyFragment);
        mineFragment=new MineFragment();
        fragmentList.add(mineFragment);
        chatFragment=new ChatFragment();
        fragmentList.add(chatFragment);
        vpAdapter=new viewPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(vpAdapter);
        vp.setCurrentItem(0);
        /**
         * 控制滑动切换
         * setOnPageChangeListener过时,改用add
         */
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                childButton= (RadioButton) rg.getChildAt(position);
                childButton.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        /**
         * 控制点击切换
         */
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.buynew:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.save:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.mine:
                        vp.setCurrentItem(2);
                        break;
                    case R.id.chat:
                        vp.setCurrentItem(3);
                        break;
                    default:
                        break;
                }
            }
        });
    }
    class viewPagerAdapter extends FragmentPagerAdapter{
        public viewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
