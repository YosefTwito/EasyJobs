package com.example.easyjobs.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.easyjobs.Objects.Category;
import com.example.easyjobs.Objects.Prof;
import com.example.easyjobs.Objects.User;
import com.example.easyjobs.R;
import com.example.easyjobs.dataBase.FirebaseDBCategories;
import com.example.easyjobs.dataBase.FirebaseDBProfs;
import com.example.easyjobs.utils.Validator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminEditPostActivity extends AppCompatActivity {

    private ImageView backButton;
    private TextView namesText;
    private TextView rateText;
    private EditText descText;
    private EditText catText;
    private Button spinner;
    private EditText locText;
    private TextView phoneText;
    private Button approveChanges;
    private MaterialDialog md;

    private User user;
    private Prof prof;
    List<Category> oldCatList;
    List<String> catList;

    private boolean changedIt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_post);

        user = (User) getIntent().getSerializableExtra("User");
        prof = (Prof) getIntent().getSerializableExtra("Prof");
        oldCatList = new ArrayList<Category>();
        catList = new ArrayList<String>();

        findViews();
        inputTempData();
        setUpSpinner();
        activateButtons();
    }

    private void findViews() {
        backButton = findViewById(R.id.back_admin_editPost);
        namesText = findViewById(R.id.namesEP);
        rateText = findViewById(R.id.ratingEP);
        descText = findViewById(R.id.descriptionEP);
        catText = findViewById(R.id.catEP);
        spinner = findViewById(R.id.pickCategoryEditProf);
        locText = findViewById(R.id.locationEP);
        phoneText = findViewById(R.id.phoneEP);
        approveChanges = findViewById(R.id.changeProfDetails);
    }

    private void inputTempData() {
        namesText.setText(user.getFirstName() + " " + user.getLastName());
        rateText.setText(user.getRating()+ " ("+ user.getRatingsAmount()+")");
        descText.setText(prof.getDesc());

        DatabaseReference catDR;
        for (int i=0; i<prof.getCategory().size(); i++){
            final int x = i;
            catDR = FirebaseDBCategories.getCatByID("\"" + prof.getCategory().get(i) + "\"");
            catDR.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Category c = snapshot.getValue(Category.class);
                    oldCatList.add(c);
                    catText.setText(catText.getText().toString() + c.getCat_name());
                    if (x < prof.getCategory().size() - 1){
                        catText.setText(catText.getText().toString() + ", ");
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) { }
            });
        }

        locText.setText(prof.getLocation());
        phoneText.setText(user.getPhoneNumber());
    }

    private void setUpSpinner(){
        DatabaseReference dr = FirebaseDBCategories.getAllCat();
        dr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Category> items = new ArrayList<>();
                for(DataSnapshot category : snapshot.getChildren()){
                    Category c = category.getValue(Category.class);
                    items.add(c);
                }
                md = new MaterialDialog.Builder(AdminEditPostActivity.this).title("בחר קטגוריות").items(items).itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                        for (Integer i : which) {
                            catList.add(items.get(i).getCategory_id());
                        }
                        return true;
                    }
                }).positiveText("אישור").build();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                for (Category c : oldCatList){
                    catList.add(c.getCat_name());
                }
            }
        });
    }

    private void activateButtons() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminEditPostActivity.super.onBackPressed();
            }
        });

        spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                md.show();
            }
        });

        approveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changedIt = true;
                if(!Validator.ValidateDescription(descText.getText().toString())){
                    changedIt = false;
                    descText.setError("תיאור לא טוב");
                }
                if(!Validator.ValidateLocation(locText.getText().toString())){
                    changedIt = false;
                    locText.setError("מיקום לא טוב");
                }
                if(changedIt){
                    FirebaseDBProfs.EditProf(prof.getProf_ID(), user.getUser_ID(), descText.getText().toString(), catList, locText.getText().toString());
                }
            }
        });
    }
}