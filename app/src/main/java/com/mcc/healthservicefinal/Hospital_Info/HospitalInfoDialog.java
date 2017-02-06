package com.mcc.healthservicefinal.Hospital_Info;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mcc.healthservicefinal.R;

/**
 * Created by nitul on 1/30/17.
 */

public class HospitalInfoDialog extends DialogFragment {

    private String title;
    private String address;
    private String contact;

    public void setData(String title, String address, String contact) {
        this.title = title;
        this.address = address;
        this.contact = contact;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context mContext = getActivity();
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.content_hospital_info, null, false);

        TextView tvRehabName = (TextView) view.findViewById(R.id.tvHospitalName);
        TextView tvAddress = (TextView) view.findViewById(R.id.tvAddress);
        final TextView tvcontact = (TextView) view.findViewById(R.id.tvContact);

        //ImageView imAddress = (ImageView) view.findViewById(R.id.imAddress);
        //ImageView imContact = (ImageView) view.findViewById(R.id.imContact);

        tvRehabName.setText(title);
        tvAddress.setText(address);
        tvcontact.setText(contact);

        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Address", Toast.LENGTH_LONG ).show();
            }
        });

        tvcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
                processContacts(tvcontact.getText().toString());
            }
        });

        return new android.app.AlertDialog.Builder(mContext)
                        .setView(view)
                        .setNegativeButton("Cancel", null)
                        .create();



    }

    private void processContacts(String contacts) {

        HospitalContactDialog contactDialog = new HospitalContactDialog();
        contactDialog.processContacts(contacts);
        contactDialog.show(getFragmentManager(), HospitalConsts.CONTACT_FRAGMENT_TAG);
    }

}
