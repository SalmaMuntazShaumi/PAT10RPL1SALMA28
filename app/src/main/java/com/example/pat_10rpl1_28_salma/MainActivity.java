package com.example.pat_10rpl1_28_salma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String username,password,name,phone,gender;
    private int age;
    private ArrayList<PhoneModel> arrayList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("contacts");

        arrayList = new ArrayList<>();

        TextInputEditText txt_username = findViewById(R.id.txt_username);
        TextInputEditText txt_password = findViewById(R.id.txt_password);
        TextInputEditText txt_name = findViewById(R.id.txt_name);
        TextInputEditText txt_age = findViewById(R.id.txt_age);
        TextInputEditText txt_phone = findViewById(R.id.txt_phone);

        AutoCompleteTextView txt_gender = findViewById(R.id.txt_gender);

        Button btn_add = findViewById(R.id.btn_add);
        Button btn_intent = findViewById(R.id.btn_intent);

        String[] items = new String[]{"Laki - Laki", "Perempuan", "Others"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, items);
        txt_gender.setAdapter(arrayAdapter);

        // Spinner OnItemClick
        txt_gender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gender = items[position];
                Toast.makeText(getApplicationContext(), gender, Toast.LENGTH_SHORT).show();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = txt_username.getText().toString();
                password = txt_password.getText().toString();
                name = txt_name.getText().toString();
                String ageString = txt_age.getText().toString();
                phone = txt_phone.getText().toString();

                if (username.trim().isEmpty() ||password.trim().isEmpty() || name.trim().isEmpty() || ageString.trim().isEmpty() || phone.trim().isEmpty() || gender.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Data belum lengkap!", Toast.LENGTH_SHORT).show();
                    Log.d("username",username);

                } else {
                    age = Integer.parseInt(txt_age.getText().toString());

                    String key = myRef.push().getKey();
                    PhoneModel phoneModel = new PhoneModel(name, username, phone, gender, password, age);
                    myRef.child(key).setValue(phoneModel);

                    txt_username.setText("");
                    txt_password.setText("");
                    txt_name.setText("");
                    txt_age.setText("");
                    txt_phone.setText("");
                    txt_gender.clearListSelection();

                    Toast.makeText(getApplicationContext(),"Data telah ditambahkan!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PhoneActivity.class);
                intent.putExtra("arraylist", arrayList);
                startActivity(intent);
            }
        });

    }
}