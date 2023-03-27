package org.akinator.database.dao;

import org.akinator.Main;
import org.akinator.database.adapter.QuestionAdapter;
import org.akinator.model.question.Question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class QuestionDatabase implements QuestionDatabaseService {

    @Override
    public CompletableFuture<Question> find(Integer id) {
        CompletableFuture.supplyAsync(() -> {
            try (PreparedStatement preparedStatement = Main.getInstance().getHikari().getConnection().prepareStatement("SELECT * FROM questions WHERE id=?")) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    QuestionAdapter questionAdapter = new QuestionAdapter();
                    return questionAdapter.adapt(resultSet);
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return null;
        });
        return null;
    }

    @Override
    public CompletableFuture<HashMap<Integer, Question>> findAll() {
        return null;
    }

    @Override
    public void create(Question person) {

    }

    @Override
    public void update(Question person) {

    }
}
