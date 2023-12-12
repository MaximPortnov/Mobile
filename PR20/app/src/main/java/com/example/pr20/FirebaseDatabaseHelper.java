package com.example.pr20;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDatabaseHelper {

    private DatabaseReference mDatabase;

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void addData(String data) {
        String key = mDatabase.child("data").push().getKey();
        DataItem item = new DataItem(key, data);
        mDatabase.child("data").child(key).setValue(item);
    }

    public DatabaseReference getDataReference() {
        return mDatabase.child("data");
    }

}