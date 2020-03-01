package com.example.mytodoapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference reference;
    RecyclerView ourList;
    ArrayList<MyList> list;
    MyListAdapter listAdapter;
    FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ourList = findViewById(R.id.ourList);
        ourList.setLayoutManager(new LinearLayoutManager(this));
        ourList.setHasFixedSize(true);
        list = new ArrayList<MyList>();

        //To get data from firebase
        reference =  FirebaseDatabase.getInstance().getReference().child("MyToDoApp");
        reference.addValueEventListener(new ValueEventListener() {
            //set code to retrieve data

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                    MyList data = dataSnapshot1.getValue(MyList.class);
                    list.add(data);
                    Log.i("LIST OF ITEMS", list.toString());
                }

                listAdapter = new MyListAdapter(MainActivity.this, list);
                ourList.setAdapter(listAdapter);
                listAdapter.notifyDataSetChanged();




            }
            //set code to show an error
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_LONG);
            }
        });
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent a = new Intent(MainActivity.this, AddList.class);
        startActivity(a);
    }


}
