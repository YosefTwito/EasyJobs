package com.example.easyjobs.Activities.Jobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.easyjobs.Objects.Job;
import com.example.easyjobs.Objects.User;
import com.example.easyjobs.R;
import com.example.easyjobs.dataBase.FirebaseDBJobs;
import com.example.easyjobs.dataBase.FirebaseDBUsers;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JobProfileActivity extends AppCompatActivity {

    private ImageView backBJP;
    private TextView namesJPTV;
    private TextView descJPTV;
    private TextView locationJPTV;
    private TextView priceJPTV;
    private TextView datesJPTV;
    private TextView phoneJPTV;

    private Button adminEditJob;

    private Job job;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_profile);

        findViews();

        activateButtons();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDataFromDB();
    }

    private void setDataFromDB(){
        DatabaseReference drJobs = FirebaseDBJobs.getJobByID(getIntent().getStringExtra("job_id"));
        drJobs.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                job = snapshot.getValue(Job.class);
                if(job == null)
                {
                    JobProfileActivity.this.onBackPressed();

                }
                else {
                    descJPTV.setText("תיאור: " + job.getDesc());
                    locationJPTV.setText("מיקום עבודה: " + job.getLocation());
                    priceJPTV.setText("מחיר: " + job.getPrice() + " שח");
                    DateFormat df = new SimpleDateFormat("dd/MM/yy");
                    datesJPTV.setText("תאריך: " + df.format(job.getStartDate()) + " - " + df.format(job.getEndDate()));
                    DatabaseReference drUser = FirebaseDBUsers.getUserByID(job.getUser_ID());
                    if(job.getUser_ID().compareTo(FirebaseAuth.getInstance().getCurrentUser().getUid()) ==0)
                    {
                        adminEditJob.setVisibility(View.VISIBLE);
                        adminEditJob.setEnabled(true);
                    }
                    drUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            user = snapshot.getValue(User.class);
                            namesJPTV.setText("שם: " + user.getFirstName() + " " + user.getLastName());
                            phoneJPTV.setText("טלפון: " + user.getPhoneNumber());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    private void findViews(){
        backBJP = findViewById(R.id.back_job_profile);
        namesJPTV = findViewById(R.id.namesJP);
        descJPTV = findViewById(R.id.descriptionJP);
        locationJPTV = findViewById(R.id.locationJP);
        priceJPTV = findViewById(R.id.priceJP);
        datesJPTV = findViewById(R.id.dateJP);
        phoneJPTV = findViewById(R.id.phoneJP);

        adminEditJob = findViewById(R.id.admin_edit_job);
    }

    private void activateButtons(){
        backBJP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JobProfileActivity.super.onBackPressed();
            }
        });

        if(FirebaseDBUsers.isAdmin){
            adminEditJob.setVisibility(View.VISIBLE);
            adminEditJob.setEnabled(true);
        }
        else{
            adminEditJob.setVisibility(View.GONE);
            adminEditJob.setEnabled(false);
        }

        adminEditJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(JobProfileActivity.this, AdminEditJobActivity.class);
                i.putExtra("Job", job);
                i.putExtra("User", user);
                startActivity(i);
            }
        });
    }
}