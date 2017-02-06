package com.mcc.healthservicefinal.activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import com.mcc.healthservicefinal.Hospital_Info.HospitalInfo;
import com.mcc.healthservicefinal.Hospital_Info.HospitalInfoDialog;
import com.mcc.healthservicefinal.R;
import com.mcc.healthservicefinal.adapter.ListAdapterHospitalList;
import com.mcc.healthservicefinal.handler.RequestContent;
import com.mcc.healthservicefinal.objects.Content;
import com.mcc.healthservicefinal.objects.HospitalInfoModel;

/**
 * Created by LINKON on 1/25/2017.
 */


public class HospitalListActivity extends AppCompatActivity {

    private String menuId;
    private ArrayList<Content> hospitals;
    private ListView mListView;
    private HospitalInfoModel mHospital;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_list_activity);

        receiveParameters();
        doOperations();

    }

    private void receiveParameters(){
        menuId = getIntent().getStringExtra("menuId");
    }

    private void doOperations(){
        loadDataInBackground();
        ShowContents();
        setOnClickListener();

    }

    private void ShowContents() {
        mListView = (ListView) findViewById(R.id.lvContent);
        mListView.setAdapter(new ListAdapterHospitalList(this, hospitals));
    }

    private void loadDataInBackground() {

        try {
            RequestContent requestContent = new RequestContent(this,menuId);
            requestContent.execute();
            hospitals = requestContent.getContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setOnClickListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                processHospitalInfo(hospitals.get(position).getDetails());
                showDialogue();
            }
        });
    }

    private void showDialogue() {

        HospitalInfoDialog dialog = new HospitalInfoDialog();
        dialog.setData(mHospital.getRehabName(), mHospital.getRehabAdderss(), mHospital.getRehabContactInfo());
        dialog.show(getFragmentManager(), "fragmentDialog");
    }

    private void processHospitalInfo(String strHospitalInfo) {

        HospitalInfo hospitalInfo = new HospitalInfo(this, strHospitalInfo);
        hospitalInfo.transformIntoHospitalModel();
        mHospital = hospitalInfo.getHospitalInfo();

    }


}
