package com.link.connect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.link.connect.R.id.lectureid;

public class lecture_status extends AppCompatActivity {

    FirebaseDatabase mDB = FirebaseDatabase.getInstance();
    DatabaseReference  mRef = mDB.getReference();

    TextView lectureid;
    TextView lecturestatus;
    Button assignment;
    Button notification;

    private Object ValueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_status);
        //mRef.setValue("Rajeev");

        Toast.makeText(this, "Retrieved", Toast.LENGTH_SHORT).show();


        lectureid = (TextView)findViewById(R.id.lectureid);
        lecturestatus = (TextView)findViewById(R.id.lecturestatus);
        assignment = (Button) findViewById(R.id.assignment);
        notification = (Button) findViewById(R.id.notificaiton);


        mRef.child("ME18/Lecture/CurrentLecture/LectureID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.getValue(String.class);
                lectureid.setText(data);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mRef.child("ME18/Lecture/CurrentLecture/Status").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.getValue(String.class);
                lecturestatus.setText(data);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(lecture_status.this,assignment.class);
                startActivity(intent);
                //overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_in_left);
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(lecture_status.this,notification.class);
                startActivity(intent);
            }
        });

    }

}
