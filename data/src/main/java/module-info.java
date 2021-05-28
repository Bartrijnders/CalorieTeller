module data{
    requires java.sql;
    exports org.example.models;
    exports org.example.doa.fakes to logica;
    exports org.example.doa to logica;
}