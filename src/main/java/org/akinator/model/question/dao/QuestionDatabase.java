package org.akinator.model.question.dao;

import org.akinator.Main;
import org.akinator.model.question.Question;
import org.akinator.model.question.adapter.QuestionAdapter;

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
    public HashMap<Integer, Question> findAll() {
        HashMap<Integer, Question> questions = new HashMap<>();
        CompletableFuture<HashMap<Integer, Question>> future = CompletableFuture.supplyAsync(() -> {
            try (PreparedStatement preparedStatement = Main.getInstance().getHikari().getConnection().prepareStatement("SELECT * FROM questions")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                QuestionAdapter questionAdapter = new QuestionAdapter();
                while (resultSet.next()) {
                    Question question = questionAdapter.adapt(resultSet);
                    questions.put(question.getId(), question);
                }
                return questions;
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return null;
        });
        return future.join();
    }

    @Override
    public void create(Question question) {
        try (PreparedStatement preparedStatement = Main.getInstance().getHikari().getConnection().prepareStatement("INSERT INTO questions (id, text) VALUES (?, ?)")) {
            preparedStatement.setInt(1, question.getId());
            preparedStatement.setString(2, question.getText());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Question question) {
        try (PreparedStatement preparedStatement = Main.getInstance().getHikari().getConnection().prepareStatement("UPDATE questions SET text=? WHERE id=?")) {
            preparedStatement.setString(1, question.getText());
            preparedStatement.setInt(2, question.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
