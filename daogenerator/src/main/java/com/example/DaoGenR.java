package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoGenR {

    public static void main(String[] args) throws Exception {

        Schema schema = new Schema(1, "imis.vorsichtung.db");

        Entity patient = schema.addEntity("Patient");

        patient.addIdProperty().primaryKey().autoincrement();
        patient.addIntProperty("category");

        Schema schema2 = new Schema(2, "imis.vorsichtung.db");

        Entity patient2 = schema2.addEntity("Patient");

        patient2.addIdProperty().primaryKey().autoincrement();
        patient2.addIntProperty("category");
        patient2.addIntProperty("answers");

        new DaoGenerator().generateAll(schema, "wear/src/main/java");
        new DaoGenerator().generateAll(schema2, "wear/src/main/java");
    }

}
