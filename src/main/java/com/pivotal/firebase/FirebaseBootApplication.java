package com.pivotal.firebase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.firebase.client.Config;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.Logger.Level;
import com.firebase.client.ValueEventListener;
import com.firebase.client.FirebaseError;
@SpringBootApplication
public class FirebaseBootApplication {

    private static final String ROOT_URL = "https://cloudfoundry.firebaseio.com/";

    public static void main(String[] args) {



        SpringApplication.run(FirebaseBootApplication.class, args);

        try {

            Firebase myFirebaseRef = new Firebase(ROOT_URL);
            myFirebaseRef.child("message").setValue("Do you have App? You'll love CloudFoundry. ");


            myFirebaseRef.child("message").addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    System.out.println(snapshot.getValue());
                    if (snapshot.getValue().toString().compareToIgnoreCase("Ping") == 0){
                        myFirebaseRef.child("message").setValue("Pong");
                    }
                }

                @Override
                public void onCancelled(FirebaseError error) { }

            });
        }catch(Exception e){
            System.out.println(e.toString());
        }

    }
}
