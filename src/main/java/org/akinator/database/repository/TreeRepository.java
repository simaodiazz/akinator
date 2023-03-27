package org.akinator.database.repository;

import lombok.SneakyThrows;
import org.akinator.Main;

import java.sql.PreparedStatement;

public class TreeRepository implements Repository {

    private final Main main;

    public TreeRepository(Main main) {
        this.main = main;
    }

    @Override
    @SneakyThrows
    public void create() {
        try (PreparedStatement preparedStatement = main.getHikari().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS trees (id INT, question VARCHAR(128))")) {
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete() {

    }
}