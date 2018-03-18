package com.assessment.intelliment.harkirat.androidassessmentapp.Activities;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;

import com.assessment.intelliment.harkirat.androidassessmentapp.R;
import com.assessment.intelliment.harkirat.androidassessmentapp.Fragments.TestOneFragment;
import com.assessment.intelliment.harkirat.androidassessmentapp.Fragments.TestTwoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager=(ViewPager)findViewById(R.id.viewpager);
        setViewPager(viewPager);

        TabLayout tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter= new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addListItem(new TestOneFragment(),"Test1");
        adapter.addListItem(new TestTwoFragment(),"Test2");
        viewPager.setAdapter(adapter);
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter
    {
        private final List<Fragment> fragmentList= new ArrayList<>();
        private final List<String> tabTitleList= new ArrayList<>();
        public ViewPagerAdapter(FragmentManager fm) {
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

        public void addListItem(Fragment fragment, String title)
        {
            fragmentList.add(fragment);
            tabTitleList.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitleList.get(position);
        }
    }
}
