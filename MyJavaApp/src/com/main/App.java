package com.main;

import com.utility.DBConnection;

public class App {

    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();
        dbConnection.dbConnect();
        dbConnection.dbClose();
    }
}
