package org.akinator.database.repository;

import lombok.SneakyThrows;
import org.akinator.Main;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonRepository implements Repository {

    private final Main main;

    public PersonRepository(Main main) {
        this.main = main;
    }

    @Override
    public void create() {
        try (PreparedStatement preparedStatement = main.getHikari().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS persons (name VARCHAR(32), questions VARCHAR(1000))")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {

    }
}