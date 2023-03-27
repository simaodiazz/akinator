package org.akinator.database.dao;

import org.akinator.model.person.Person;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public interface PersonDatabaseService {

    CompletableFuture<Person> find(String personName);
    CompletableFuture<HashMap<String, Person>> findAll();
    void create(Person person);
    void update(Person person);

}