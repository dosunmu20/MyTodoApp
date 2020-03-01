package com.example.mytodoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class AddList extends AppCompatActivity  {

    private TextInputEditText addTask, addTime, addDesc;
    private Button btnSave, btnCancel;
    private DatabaseReference reference;
    private String key;



    MyList newList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        addTask = findViewById(R.id.addTitle);
        addTime = findViewById(R.id.addTime);
        addDesc = findViewById(R.id.addDesc);

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);


        reference  = FirebaseDatabase.getInstance().getReference("MyToDoApp");

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask.setText("");
                addTime.setText("");
                addDesc.setText("");

                Intent a = new Intent(AddList.this, MainActivity.class);
                startActivity(a);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String strAddTask = addTask.getText().toString().trim();
                String strAddDesc = addDesc.getText().toString().trim();
                String strAddTime = addTime.getText().toString().trim();
                String key  = reference.push().getKey();

                newList = new MyList(strAddTask, strAddDesc, strAddTime, key);

                if (!strAddTask.isEmpty() || !strAddDesc.isEmpty() || !strAddTask.isEmpty()) {
                    reference.child(key).setValue(newList);
                    Toast.makeText(getApplicationContext(), "New task item added", Toast.LENGTH_LONG).show();


//                    reference.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            dataSnapshot.getRef().child("itemTitle").setValue(addTask.getText().toString());
//                            dataSnapshot.getRef().child("desc").setValue(addDesc.getText().toString());
//                            dataSnapshot.getRef().child("time").setValue(addTime.getText().toString());
//                            dataSnapshot.getRef().child("key").setValue(key);
//
//
                    Intent a = new Intent(AddList.this, MainActivity.class);
                    startActivity(a);
                } else {
                    Toast.makeText(getApplicationContext(), "All Fields must be filled", Toast.LENGTH_LONG).show();

                }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                            Log.d("ERROR", databaseError.getMessage() );
//
//                        }
//                    });
                }

        });

    }




}
