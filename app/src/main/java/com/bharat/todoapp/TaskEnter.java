package com.bharat.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class TaskEnter extends AppCompatActivity {
    EditText title;
    EditText description;
    Button SubmitBtn;
          ImageButton button;
    TextView textBtn;

    DatabaseReference databaseReference;
    FirebaseDatabase db;
    ProgressDialog progressDialog;




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(TaskEnter.this,MainActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_enter);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        SubmitBtn = findViewById(R.id.SubmitBtn);
        button = findViewById(R.id.button);
        textBtn = findViewById(R.id.textBtn);




        progressDialog = new ProgressDialog(this);

        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title = title.getText().toString();
                String Description = description.getText().toString();
                if (Title.isEmpty()){
                    Toast.makeText(TaskEnter.this, "Please Enter Title..!", Toast.LENGTH_SHORT).show();
                    return;
                }else if (Description.isEmpty()){
                    Toast.makeText(TaskEnter.this, "Please Enter Description..!", Toast.LENGTH_SHORT).show();
                    return;

                }

                progressDialog.setMessage("Please wait...");
                progressDialog.show();
//                deRef =databaseReference.child("Data");
                db = FirebaseDatabase.getInstance();
                databaseReference  =  db.getReference("SubmitData");
                Model model = new Model(Title,Description);
//                final String Uniquekey  = databaseReference.push().getKey();
//                assert Uniquekey!=null;
                databaseReference.child(Title).setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.cancel();
                        Toast.makeText(TaskEnter.this, "Data Successfully Added..!", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.cancel();
                        Toast.makeText(TaskEnter.this, "Oop.! Something Went Wrong..!", Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskEnter.this,MainActivity.class);
                startActivity(intent);
            }
        });
        textBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskEnter.this,MainActivity.class);
                startActivity(intent);

            }
        });


    }
}