package org.example.dbConncetion;

import org.example.doa.postgres.ItemPostgresDao;

public class test {

    public static void main(String[] args) {
        PostgresCon postgresCon = new PostgresCon();
        ItemPostgresDao itemPostgresDao = new ItemPostgresDao(postgresCon);


    }
}
