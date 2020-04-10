package com.hybird.intent;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;


public class second extends AppCompatActivity {
    private Dialog objectDialog;
    EditText Name,Address,Rollno, personId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Name=findViewById(R.id.Name);
        Address=findViewById(R.id.Address);
        Rollno=findViewById(R.id.Rollno);


        try {
            if (getIntent() != null) {
                personalInfo object = getIntent().getParcelableExtra("student");
                Name.setText("Personal information:" + object.getPersonalData().getName());
                Address.setText("Personal information:" + object.getPersonalData().getAddress());
                Rollno.setText("Personal information:" + object.getPersonalData().getName());


            } else {
                Toast.makeText(this, "No data is available", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "E:"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    public void addValuesToFireBase(View view) {

        try {
            if (!personId.getText().toString().isEmpty() && !Name.getText().toString().isEmpty() && !Address.getText().toString().isEmpty() && !Rollno.getText().toString().isEmpty()) {
                objectDialog.show();
                Map<String, Object> objmap = new HashMap<>();

                objmap.put("name", Name.getText().toString());
                objmap.put("address", Address.getText().toString());
                objmap.put("rollNo", Rollno.getText().toString());
                DocumentReference objectFirebaseFirestore = null;
                objectFirebaseFirestore.collection("PersonsData")
                        .document(personId.getText().toString()).set(objmap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                objectDialog.dismiss();
                                Toast.makeText(second.this, "Data Added", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                objectDialog.dismiss();
                                Toast.makeText(second.this, "Data not Added" + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

            } else {
                objectDialog.dismiss();
                Toast.makeText(second.this, "Enter valid data", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {

            Toast.makeText(this, "addValuesToFireBase" + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
