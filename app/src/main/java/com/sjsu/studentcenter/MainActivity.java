package com.sjsu.studentcenter;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button searchButton =  findViewById(R.id.search);
        final EditText collegeId = findViewById(R.id.collegeId);
        myDb = new DatabaseHelper(this);
        myDb.insertData(12455313, "Nirbhay", "", "Kekre", "6692789410", "787 The Alameda" );

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = collegeId.getText().toString();
                Cursor res = myDb.getStudentDetails(id);
                Log.d("id searching is ", id + "");
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    Log.d("First Row Name is: ", res.getString(0));
                    if (res.getString(0).equals(id)) {
                        selectFrag(res.getString(0), res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5));
                    }
                }

            }
        });
    }

    public void selectFrag(String id, String firstName, String middleName, String lastName, String mobile, String address) {

        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("firstName", firstName);
        bundle.putString("middleName", middleName);
        bundle.putString("lastName", lastName);
        bundle.putString("mobile", mobile);
        bundle.putString("address", address);
        Fragment fr = new FragmentDetails();
        fr.setArguments(bundle);
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, fr);
        fragmentTransaction.commit();

    }

}
