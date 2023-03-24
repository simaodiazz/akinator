package org.akinator.database.dao;

import org.akinator.model.person.Person;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public interface PersonDatabaseService {

    CompletableFuture<Boolean> contains(String personName);
    CompletableFuture<Person> find(String playerName);
    CompletableFuture<HashMap<String, Person>> findAll();
    CompletableFuture<Void> add(Person person);
    CompletableFuture<Void> update(String personName);
    CompletableFuture<Void> remove(String personName);

}