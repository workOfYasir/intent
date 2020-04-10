package com.hybird.intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void moveData(View view)
    {
        try
        {
            personalInfo objectPersons=new personalInfo();
            Record humanData=new Record();

            humanData.setName("Yasir Iqbal");
            humanData.setAddress("Sheikhupura");
            humanData.setRoll(95);
            objectPersons.setPersonalData(personalData);

            startActivity(new Intent(this, second.class)
                    .putExtra("person",objectPersons)
            );
        }
        catch (Exception e)
        {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
