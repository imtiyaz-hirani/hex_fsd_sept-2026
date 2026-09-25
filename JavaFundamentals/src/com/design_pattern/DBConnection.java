package com.design_pattern;

public class DBConnection {

    private static DBConnection dbConnection = new DBConnection(); //100X

    private DBConnection() { //constructor marked as private
    }

    public void dbConnect(){

    }

    public void dbClose(){

    }

    public static DBConnection getInstance(){
        return dbConnection;
    }
}
