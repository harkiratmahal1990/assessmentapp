package com.assessment.intelliment.harkirat.androidassessmentapp.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.assessment.intelliment.harkirat.androidassessmentapp.FragmentOneAdapter;
import com.assessment.intelliment.harkirat.androidassessmentapp.R;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;


public class TestOneFragment extends Fragment implements View.OnClickListener,FragmentOneAdapter.Listener
{
    public String itemNameSelected;
    private View rootView;
    TextView itemSelectedText;
    public String[] names={"Item 1","Item 2","Item 3","Item 4","Item 5"};
    public ArrayList<MyFragment> fragmentList=new ArrayList<MyFragment>() ;
    public int pos;
    public TestOneFragment() {}

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_test_one,container,false);

        Button redBtn = view.findViewById(R.id.button_red);
        redBtn.setOnClickListener(this);
        Button bluBtn = view.findViewById(R.id.button_blue);
        bluBtn.setOnClickListener(this);
        Button greenBtn=view.findViewById(R.id.button_green);
        greenBtn.setOnClickListener(this);

        itemSelectedText=view.findViewById(R.id.item_selected_text);

        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        FragmentOneAdapter fragmentOnePagerAdapter=new FragmentOneAdapter(names,this);
        recyclerView.setAdapter(fragmentOnePagerAdapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        for(int i=1;i<=4 ; i++)
        {
            MyFragment myFragment=MyFragment.makeInstance(i,"Fragment "+i);
            fragmentList.add(myFragment);
        }

        ViewPager viewPager=(ViewPager)view.findViewById(R.id.fragment_viewpager);
        CircleIndicator circleIndicator=(CircleIndicator)view.findViewById(R.id.circle_indicator);
        TestPagerAdapter testPagerAdapter=new TestPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(testPagerAdapter);
        circleIndicator.setViewPager(viewPager);

        rootView= view.findViewById(R.id.root_view);

        return view;
    }



    @Override
    public void onClick(int position) {

        itemNameSelected=names[position];
        itemSelectedText.setText(itemNameSelected);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_red:
                rootView.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.colorRed));
                break;
            case R.id.button_blue:
                rootView.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.colorBlue));
                break;
            case R.id.button_green:
                rootView.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.colorGreen));
                break;
        }
    }

    public class TestPagerAdapter extends FragmentPagerAdapter {
        public TestPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            pos=position;

            return fragmentList.get(position);

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            Fragment clickedFragment=(Fragment)super.instantiateItem(container,position);

            return clickedFragment;
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }


}
