module data{
    requires java.sql;
    requires domain;

    exports org.example.doa.fakes;
    exports org.example.dbConncetion;
    exports org.example.doa.postgres;
    exports org.example.doa;
}