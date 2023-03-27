package org.akinator.model.question.repository;

import lombok.SneakyThrows;
import org.akinator.Main;
import org.akinator.database.repository.Repository;

import java.sql.PreparedStatement;

public class QuestionRepository implements Repository {

    private final Main main;

    public QuestionRepository(Main main) {
        this.main = main;
    }

    @Override
    @SneakyThrows
    public void create() {
        try (PreparedStatement preparedStatement = main.getHikari().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS questions (id INT, text VARCHAR(128))")) {
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete() {

    }
}
