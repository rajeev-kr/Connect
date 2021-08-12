package com.link.connect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class assignment extends AppCompatActivity {

    ListView lvassignment;

    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    FirebaseDatabase mDB = FirebaseDatabase.getInstance();
    DatabaseReference mRef = mDB.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        lvassignment = (ListView) findViewById(R.id.lvassignment);

        Toast.makeText(this, "Retrieved", Toast.LENGTH_SHORT).show();

        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        adapter = new ArrayAdapter<>(this, R.layout.listview, arrayList);
        lvassignment.setAdapter(adapter);

        mRef.child("ME18/Assignment").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String timestamp = snapshot.getKey();
                String data = snapshot.getValue(String.class);
                StringBuffer sb = new StringBuffer(timestamp);
                sb.delete(timestamp.length() - 20, timestamp.length());
                timestamp = sb.toString();

                data = data.replace('$', '|');

                arrayList.add(timestamp+" | "+data);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
