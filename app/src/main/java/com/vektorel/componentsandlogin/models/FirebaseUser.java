package com.vektorel.componentsandlogin.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.UUID;

@IgnoreExtraProperties
public class FirebaseUser {
        public String id;
        public String username;
        public String email;

        public FirebaseUser() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public FirebaseUser(String username, String email) {
            this.id = UUID.randomUUID().toString();
            this.username = username;
            this.email = email;
        }

}
