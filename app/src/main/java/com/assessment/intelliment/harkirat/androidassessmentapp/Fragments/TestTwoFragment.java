package com.assessment.intelliment.harkirat.androidassessmentapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.assessment.intelliment.harkirat.androidassessmentapp.Activities.LocationActivity;
import com.assessment.intelliment.harkirat.androidassessmentapp.Model.Transport;
import com.assessment.intelliment.harkirat.androidassessmentapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TestTwoFragment extends Fragment implements View.OnClickListener {
    private ArrayList<Transport> transports= new ArrayList<Transport>();
    private AppCompatSpinner spinner;
    private Button navigationButton;
    private TextView carTextView;
    private TextView trainTextView;
    private Transport selectedTransport;
    private int pos;
    private String locationName;

    public TestTwoFragment() {
         }
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        populateTransportSpinner();
        updateDurationTextViews();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_test_two, container, false);
        spinner=v.findViewById(R.id.location_spinner);
        navigationButton=v.findViewById(R.id.navigation_button);
        trainTextView=v.findViewById(R.id.train_text_view);
        carTextView=v.findViewById(R.id.car_text_view);

        navigationButton.setOnClickListener(this);
        return v;
    }

    public void populateTransportSpinner(){
        InputStream is=getResources().openRawResource(R.raw.transport);

        try(InputStreamReader isr=new InputStreamReader(is)){

            ArrayList<Transport> tempTransport=new Gson().fromJson(isr,new TypeToken<ArrayList<Transport>>(){}.getType());
            if (tempTransport!=null && tempTransport.size()>0) {
                transports=tempTransport;
                setUpSpinner();
            }
        }
        catch (Exception ioe)
        {
            ioe.printStackTrace();
        }

    }

    public void setUpSpinner() {
        if (getActivity() == null) {
            return;
        }

        ArrayAdapter<Transport> transportArrayAdapter = new ArrayAdapter<Transport>(getActivity(), android.R.layout.simple_list_item_1, transports);
        spinner.setAdapter(transportArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTransport=transports.get(position);
                pos=position;
                locationName=selectedTransport.getName();

                updateDurationTextViews();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void updateDurationTextViews(){
        String carDuration="";
        String trainDuration="";

        if(selectedTransport!=null)
        {
            carDuration=selectedTransport.getTravelDuration().getCar();
            trainDuration=selectedTransport.getTravelDuration().getTrain();
        }

        if(carDuration==null)
        { carTextView.setText("");
            }
            else{
        carTextView.setText(getString(R.string.car_duration,carDuration));}

        if(trainDuration==null)
        {trainTextView.setText("");
       }
       else {
            trainTextView.setText(getString(R.string.train_duration, trainDuration));
        }
    }
    @Override
    public void onClick(View v) {

        if (selectedTransport == null) {
            Toast.makeText(getActivity(), "Please make a selection", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent=new Intent(getActivity(), LocationActivity.class);
        intent.putExtra("EXTRA_LOCATION_ID",selectedTransport.getLocation());
        intent.putExtra("EXTRA_LOCATION_NAME",locationName);
        startActivity(intent);

    }
}
