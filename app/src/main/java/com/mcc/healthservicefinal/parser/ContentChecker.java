package com.mcc.healthservicefinal.parser;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


import com.mcc.healthservicefinal.constants.ParseKey;
import com.mcc.healthservicefinal.objects.Content;


/**
 * Created by LINKON on 1/26/2017.
 */

public class ContentChecker {
    private Context mContext;

    public ContentChecker(Context mContext){
        this.mContext = mContext;
    }
}

//    বঙ্গবন্ধু শেখ মুজিব মেডিকেল বিশ্ববিদ্যালয় হাসপাতাল
//ঠিকানা: শাহবাগ মোড, ঢাকা-১০০০
//        ফোন:+৮৮ ০২  ৯৬৬১০৫১-৫৬
//
//        শহীদ সোহরাওয়াদী মেডিকেল কলেজ হাসপাতাল
//        ঠিকানা: শেরে-ই-বাংলা নগর,ঢাকা-১০০০
//        ফোন:৯১৩০৮০০-১৯
//
//        ঢাকা মহানগর জেনারেল হাসপাতাল
//        ঠিকানা: ইউসুফ নওয়াব সরক,নয়াবাজার,ঢাকা-১১০০
//        ফোন:৭৩৯০৮৬০
//
//
//        আইসিডিডিআরবি
//        ঠিকানা: মহাখালি ,ঢাকা-১১০০
//        ফোন:৯৮৯৯০৬৭
//
//        স্কয়ার হাসপাতাল
//        ঠিকানা: ১৮/এফ, পশ্চিম পান্থপথ, ঢাকা-১২০৫
//        ফোন:+৮৮-০২-৮১২৯৩৩৪
//
//        এ্যাপোলো হাসপাতাল
//        ঠিকানা: প্লট ৮১, ব্লক ই, বসুন্ধরা আবাসিক এলাকা ,ঢাকা-১২১৯
//        ফোন:৮৪০১৬৬১
//
//        বারডেম জেনারেল হাসপাতাল
//        ঠিকানা: শাহবাগ মোড, ঢাকা-১০০০ ,
//        ফোন:৮৬১৬৬৪১
//
//        শমরিতা হাসপাতাল
//        ঠিকানা: ৮৯/১, পান্থপথ, ঢাকা-১২১৫
//        ফোন:৯১৩১৯০১
//
//        ইউনাইটেড হাসপাতাল
//        ঠিকানা: প্লট – ১৫, সড়ক – ১৭, গুলশান, ঢাকা-১২১২
//        ফোন:৮৮৩৬০০০