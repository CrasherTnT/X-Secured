package com.journaldev.passingdatabetweenfragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements fragmentGenerate.SendMessage, fragmentChecker.SendMessage2{
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void sendData(String message) {
        String tag = "android:switcher:" + R.id.viewPager + ":" + 1 ;
        fragmentChecker f = (fragmentChecker) getSupportFragmentManager().findFragmentByTag(tag);
        f.displayReceivedData(message);
    }
   public void sendData2(String message2) {
        String tag2 = "android:switcher:" + R.id.viewPager + ":" + 2 ;
        fragmentEncrypt_Decrypt f2 = (fragmentEncrypt_Decrypt) getSupportFragmentManager().findFragmentByTag(tag2);
        f2.displayReceivedData(message2);
    }

}
