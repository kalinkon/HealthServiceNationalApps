package com.mcc.healthservicefinal.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.mcc.healthservicefinal.R;

import com.mcc.healthservicefinal.adapter.TabPagerAdapter;
import com.mcc.healthservicefinal.handler.RequestContent;
import com.mcc.healthservicefinal.handler.RequestSubMenuDiseaseList;
import com.mcc.healthservicefinal.objects.Content;
import com.mcc.healthservicefinal.objects.SubMenu;

import java.util.ArrayList;

/**
 * Created by LINKON on 1/31/2017.
 */

public class ContentDiseaseActivity extends AppCompatActivity {

    private String menuId;
    private String subMenuId;
    private String symptomSubSubMenuId;
    private String preventionsSubSubMenuId;

    private ArrayList<Content> symptoms;
    private ArrayList<Content>preventions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        receiveParameters();
        doOperations();


        setContentView(R.layout.tab_content_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle(getSubMenuNameForTitle());
        setSupportActionBar(toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(symptoms.get(0).getTitle()));
        tabLayout.addTab(tabLayout.newTab().setText(preventions.get(0).getTitle()));



        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final TabPagerAdapter adapter = new TabPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount(),symptoms,preventions,this);
        viewPager.setAdapter(adapter);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void receiveParameters(){
        menuId= getIntent().getStringExtra("menuId");
        subMenuId= getIntent().getStringExtra("subMenuId");
        symptomSubSubMenuId =getIntent().getStringExtra("symtomId");
        preventionsSubSubMenuId =getIntent().getStringExtra("preventionId");
    }

    private void doOperations(){
        loadDataInBackground();
    }

    private void loadDataInBackground() {

        try {
            RequestContent requestContentSymptom = new RequestContent(this,menuId,subMenuId,symptomSubSubMenuId);
            requestContentSymptom.execute();
            symptoms = requestContentSymptom.getContent();

            RequestContent requestContentPrevention =new RequestContent(this,menuId,subMenuId,preventionsSubSubMenuId);
            requestContentPrevention.execute();
            preventions=requestContentPrevention.getContent();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getSubMenuNameForTitle(){
        RequestSubMenuDiseaseList requestSubMenu =new RequestSubMenuDiseaseList(this,menuId);
        requestSubMenu.execute();
        ArrayList<SubMenu> subMenus = requestSubMenu.getSubMenus();
        for(int i =0;i<subMenus.size();i++){
            if(subMenus.get(i).getSubMenuId().equals(subMenuId)){
                return subMenus.get(i).getSubMenuName();
            }
        }
        return null;
    }
}

