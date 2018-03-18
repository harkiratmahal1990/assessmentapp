package com.assessment.intelliment.harkirat.androidassessmentapp.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.assessment.intelliment.harkirat.androidassessmentapp.R;


public class MyFragment extends Fragment {

    private int index;
    private String fragmentName="";

    public MyFragment() {
    }

    public static MyFragment makeInstance(int index, String fragmentName)
    {
      MyFragment f= new MyFragment();
      f.index=index;
      f.fragmentName=fragmentName;
      return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_my, container, false);
        TextView fragmentNameTextView=view.findViewById(R.id.fragment_name_text_view);
        fragmentNameTextView.setText(fragmentName);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Fragment: "+index+" clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
