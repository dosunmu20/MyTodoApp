package com.example.mytodoapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter <MyListAdapter.MyViewHolder>{

    Context context;
    ArrayList<MyList> myList;

    public MyListAdapter (Context context, ArrayList<MyList> myList){
        this.context = context;
        this.myList = myList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        holder.itemTitle.setText(myList.get(position).getItemTitle());
        holder.desc.setText(myList.get(position).getDesc());
        holder.time.setText(myList.get(position).getTime());

        final String getItemTitle = myList.get(position).getItemTitle();
        final String getDesc = myList.get(position).getDesc();
        final String getTime = myList.get(position).getTime();
        final String getKey  = myList.get(position).getKey();


       holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(context, EditList.class);
                a.putExtra("itemTitle",getItemTitle);
                a.putExtra("desc",getDesc);
                a.putExtra("time",getTime);
                a.putExtra("key",getKey);


                context.startActivity(a);
            }
        });



    }


    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView itemTitle, desc,time, key;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            desc = itemView.findViewById(R.id.desc);
            time = itemView.findViewById(R.id.time);
        }
    }
}
