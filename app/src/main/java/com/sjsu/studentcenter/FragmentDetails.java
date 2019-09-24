package com.sjsu.studentcenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentDetails extends android.support.v4.app.Fragment {

    DatabaseHelper myDb;
    String id, first, middle, last, mobil, addres;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button updateButton = view.findViewById(R.id.update);
        final EditText firstName = view.findViewById(R.id.firstName);
        final EditText middleName = view.findViewById(R.id.middleName);
        final EditText lastName = view.findViewById(R.id.lastName);
        EditText mobile = view.findViewById(R.id.mobileNumber);
        EditText address = view.findViewById(R.id.address);
        id = this.getArguments().getString("id");
        first = this.getArguments().getString("firstName");
        middle = this.getArguments().getString("middleName");
        last = this.getArguments().getString("lastName");
        mobil = this.getArguments().getString("mobile");
        addres = this.getArguments().getString("address");
        firstName.setText(first);
        middleName.setText(middle);
        lastName.setText(last);
        mobile.setText(mobil);
        address.setText(addres);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoPage.class);
                intent.putExtra("id", id);
                intent.putExtra("firstName", first);
                intent.putExtra("middleName", middle);
                intent.putExtra("lastName", last);
                intent.putExtra("mobile", mobil);
                intent.putExtra("address", addres);
                startActivity(intent);
                getActivity().getSupportFragmentManager().beginTransaction().remove(FragmentDetails.this).commit();
            }
        });
    }
}