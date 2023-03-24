package org.akinator.database.adapter;

import org.akinator.model.person.Person;

import java.sql.ResultSet;

public class PersonAdapter implements DatabaseAdapter<Person> {

    @Override
    public Person adapt(ResultSet resultSet) {

        return null;
    }
}