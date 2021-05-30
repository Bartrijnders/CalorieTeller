package org.example.demo;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try{
            Iteratie1 iteratie1 = new Iteratie1();
            iteratie1.start();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
