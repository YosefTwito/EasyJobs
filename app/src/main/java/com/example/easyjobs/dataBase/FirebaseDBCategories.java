package com.example.easyjobs.dataBase;

import com.google.firebase.database.DatabaseReference;

public class FirebaseDBCategories {

    public static DatabaseReference getCatByID(String CatID){
        return FirebaseBaseModel.getRef().child("Categories").child(CatID);
    }

    public static DatabaseReference getAllCat(){
        return FirebaseBaseModel.getRef().child("Categories");
    }
    public static void changeCatName(String catId,String newname){
        //TODO
    }
    public static void addCat(String cat){
        //TODO
    }

}
