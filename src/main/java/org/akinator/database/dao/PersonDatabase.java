package org.akinator.database.dao;

import org.akinator.Main;
import org.akinator.database.adapter.PersonAdapter;
import org.akinator.model.person.Person;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class PersonDatabase implements PersonDatabaseService {

    @Override
    public CompletableFuture<Boolean> contains(String personName) {
        CompletableFuture.supplyAsync( () -> {
            try (PreparedStatement preparedStatement = Main.getInstance().getHikari().getConnection().prepareStatement("SELECT playerName FROM persons WHERE name=?")) {
                preparedStatement.setString(1, personName);
                return preparedStatement.executeQuery().next();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return null;
    }

    @Override
    public CompletableFuture<Person> find(String playerName) {
        CompletableFuture.supplyAsync( () -> {
            try (PreparedStatement preparedStatement = Main.getInstance().getHikari().getConnection().prepareStatement("SELECT * FROM persons WHERE name=?")) {
                preparedStatement.setString(1, playerName);
                PersonAdapter personAdapter = new PersonAdapter();
                return personAdapter.adapt(preparedStatement.executeQuery());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
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