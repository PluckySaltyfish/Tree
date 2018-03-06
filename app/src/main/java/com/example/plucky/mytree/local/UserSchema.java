package com.example.plucky.mytree.local;



public class UserSchema {

    public static final  class UserTable
    {
        public static final String NAME = "users";
        public static final class Cols {
            public static final String USERNAME = "username";
            public static final String PASSWORD = "password";
            public static final String PHOTO = "photo";
            public static final String STATUS = "status";
        }
    }
}
