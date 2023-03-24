package org.akinator.database.repository;

import org.akinator.Main;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TreeRepository implements DatabaseRepository {

    @Override
    public void create() {
        try (PreparedStatement preparedStatement = Main.getInstance().getHikari().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `trees` (name VARCHAR(16) NOT NULL, questions LONGTEXT NOT NULL)")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {
        try (PreparedStatement preparedStatement = Main.getInstance().getHikari().getConnection().prepareStatement("DELETE * FROM trees")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void recreate() {
        DatabaseRepository.super.recreate();
    }
}