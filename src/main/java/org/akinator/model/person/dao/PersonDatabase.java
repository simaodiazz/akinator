package org.akinator.model.person.dao;

import org.akinator.Main;
import org.akinator.model.person.adapter.PersonAdapter;
import org.akinator.model.person.Person;
import org.akinator.model.question.parser.QuestionParser;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class PersonDatabase implements PersonDatabaseService {

    @Override
    public CompletableFuture<Person> find(String personName) {
        CompletableFuture.supplyAsync(() -> {
            try (PreparedStatement preparedStatement = Main.getInstance().getHikari().getConnection().prepareStatement("SELECT * FROM persons WHERE name=?")) {
                preparedStatement.setString(1, personName);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    PersonAdapter personAdapter = new PersonAdapter();
                    return personAdapter.adapt(resultSet);
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return null;
        });
        return null;
    }

    @Override
    public HashMap<String, Person> findAll() {
        HashMap<String, Person> persons = new HashMap<>();
        CompletableFuture<HashMap<String, Person>> future = CompletableFuture.supplyAsync(() -> {
            try (PreparedStatement preparedStatement = Main.getInstance().getHikari().getConnection().prepareStatement("SELECT * FROM persons")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                PersonAdapter personAdapter = new PersonAdapter();
                while (resultSet.next()) {
                    Person person = personAdapter.adapt(resultSet);
                    persons.put(person.getName(), person);
                }
                return persons;
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return null;
        });
        return future.join();
    }

    @Override
    public void create(@NotNull Person person) {
        try (PreparedStatement preparedStatement = Main.getInstance().getHikari().getConnection().prepareStatement("INSERT INTO persons (name, questions) VALUES (?, ?)")) {
            preparedStatement.setString(1, person.getName());
            QuestionParser questionParser = new QuestionParser();
            preparedStatement.setString(2, questionParser.parse(person.getQuestions()));
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(@NotNull Person person) {
        try (PreparedStatement preparedStatement = Main.getInstance().getHikari().getConnection().prepareStatement("UPDATE persons SET questions=? WHERE name=?")) {
            preparedStatement.setString(1, person.getName());
            QuestionParser questionParser = new QuestionParser();
            preparedStatement.setString(2, questionParser.parse(person.getQuestions()));
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}