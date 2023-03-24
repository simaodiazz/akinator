package org.akinator.database.repository;

import org.akinator.Main;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonRepository implements DatabaseRepository {

    @Override
    public void create() {
        try (PreparedStatement preparedStatement = Main.getInstance().getHikari().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `persons` (name VARCHAR(16) NOT NULL, questions LONGTEXT NOT NULL)")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {

    }

    @Override
    public void recreate() {
        DatabaseRepository.super.recreate();
    }
}