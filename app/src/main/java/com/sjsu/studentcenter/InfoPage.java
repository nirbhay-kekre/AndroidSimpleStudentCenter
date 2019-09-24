package com.sjsu.studentcenter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InfoPage extends AppCompatActivity {
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_page);
        myDb = new DatabaseHelper(this);
        Intent intent = getIntent();
        TextView collegeIdTextview=(TextView) findViewById(R.id.studentId);
        final String id=intent.getStringExtra("id");
        collegeIdTextview.setText("Student Id:          "+id);
        final EditText editTextFirst=(EditText) findViewById(R.id.firstName);
        editTextFirst.setText(intent.getStringExtra("firstName"));
        final EditText editTextMiddle=(EditText) findViewById(R.id.middleName);
        editTextMiddle.setText(intent.getStringExtra("middleName"));
        final EditText editTextLast=(EditText) findViewById(R.id.lastName);
        editTextLast.setText(intent.getStringExtra("lastName"));
        final EditText editTextMobile=(EditText) findViewById(R.id.mobileNumber);
        editTextMobile.setText(intent.getStringExtra("mobile"));
        final EditText editTextAddress=(EditText) findViewById(R.id.address);
        editTextAddress.setText(intent.getStringExtra("address"));
        Button updateButton=(Button) findViewById(R.id.update);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(InfoPage.this)
                        .setTitle("Profile Change")
                        .setMessage("Do you really want to make changes?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                boolean isUpdate = myDb.updateData(id,editTextFirst.getText().toString(),editTextMiddle.getText().toString(),editTextLast.getText().toString(),editTextMobile.getText().toString(),editTextAddress.getText().toString());
                                if(isUpdate == true)
                                    Toast.makeText(InfoPage.this,"Data Updated",Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(InfoPage.this,"Data not Updated",Toast.LENGTH_LONG).show();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();

            }
        });

    }
}
