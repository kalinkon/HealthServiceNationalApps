package com.mcc.healthservicefinal.activity;
import android.app.SearchManager;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

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
    private ArrayList<Content> searchedHospitals=new ArrayList<>();
    private ListView mListView;
    private HospitalInfoModel mHospital;
    private String searchString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_list_activity);

        receiveParameters();
        loadDataInBackground();
        doOperations();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        ImageView closeButton = (ImageView)searchView.findViewById(R.id.search_close_btn);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextChange(String newText){
//                searchString =newText;
//                searchView();
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query){
                searchString =query;
                searchView();
                return true;
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Clear query
                searchView.setQuery("", false);
                //Collapse the action view
                searchView.onActionViewCollapsed();
                //Collapse the search widget

               doOperations();

            }
        });
        return true;
    }

    private void searchView(){

        for(int index=0;index<hospitals.size();index++){
            if(hospitals.get(index).getDetails().contains(searchString)){
                searchedHospitals.add(hospitals.get(index));
            }
            else{
                Toast.makeText(this,"Not Available",Toast.LENGTH_SHORT).show();
            }
        }
        ShowContents(searchedHospitals);
        setOnClickListener(searchedHospitals);

    }




    private void receiveParameters(){
        menuId = getIntent().getStringExtra("menuId");
    }

    private void doOperations(){

        ShowContents(hospitals);
        setOnClickListener(hospitals);
    }

    private void ShowContents(ArrayList<Content> hospitals) {
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

    private void setOnClickListener(final ArrayList<Content> hospitals) {
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
