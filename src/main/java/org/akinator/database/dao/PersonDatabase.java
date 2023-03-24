package org.akinator.database.dao;

import org.akinator.model.person.Person;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class PersonDatabase implements PersonDatabaseService {

    @Override
    public CompletableFuture<Boolean> contains(String personName) {
        return null;
    }

    @Override
    public CompletableFuture<Person> find(String playerName) {
        return null;
    }

    @Override
    public CompletableFuture<HashMap<String, Person>> findAll() {
        return null;
    }

    @Override
    public CompletableFuture<Void> add(Person person) {
        return null;
    }

    @Override
    public CompletableFuture<Void> update(String personName) {
        return null;
    }

    @Override
    public CompletableFuture<Void> remove(String personName) {
        return null;
    }
}