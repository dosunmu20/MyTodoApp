package com.example.mytodoapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditList extends AppCompatActivity {

    private TextInputEditText addEditTitle, addEditTime, addEditDesc;
    private Button btnSaveUpdate, btnDelete;
    private DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);

        addEditTitle = findViewById(R.id.addEditTitle);
        addEditTime = findViewById(R.id.addEditTime);
        addEditDesc = findViewById(R.id.addEditDesc);

        btnSaveUpdate = findViewById(R.id.btnSaveUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        addEditTitle.setText(getIntent().getStringExtra("itemTitle"));
        addEditTime.setText(getIntent().getStringExtra("time"));
        addEditDesc.setText(getIntent().getStringExtra("desc"));

        final String key;
        key = getIntent().getStringExtra("key");


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference().child("MyToDoApp");
                reference.child(key).removeValue();

                Toast.makeText(getApplicationContext(), "List item deleted", Toast.LENGTH_LONG).show();
                Log.i("ITEM DELETED", "LIST ITEM DELETED");

                Intent a = new Intent(EditList.this, MainActivity.class);
                startActivity(a);
            }

        });

        btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(addEditTitle.toString()) || !TextUtils.isEmpty(addEditDesc.toString()) || !TextUtils.isEmpty(addEditTime.toString()))
                {
                    updateList(addEditTitle.getText().toString(), addEditDesc.getText().toString(), addEditTime.getText().toString(), key);

                    Intent a = new Intent(EditList.this, MainActivity.class);
                    startActivity(a);
                } else
                    Toast.makeText(getApplicationContext(),"All fields must be filled", Toast.LENGTH_LONG).show();

//                    reference.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            dataSnapshot.getRef().child("itemTitle").setValue(addEditTitle.getText().toString());
//                            dataSnapshot.getRef().child("desc").setValue(addEditDesc.getText().toString());
//                            dataSnapshot.getRef().child("time").setValue(addEditTime.getText().toString());
//
//
//
//                            Intent a = new Intent(EditList.this, MainActivity.class);
//                            startActivity(a);
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });
                }

        });



    }

    private boolean updateList (String itemTitle, String desc, String time, String key) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("MyToDoApp").child(key);
        MyList myList = new MyList(itemTitle, desc, time, key);
        ref.setValue(myList);

        return  true;
    }



}
