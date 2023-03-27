package org.akinator.database.adapter;

import lombok.SneakyThrows;
import org.akinator.model.question.Question;

import java.sql.ResultSet;

public class QuestionAdapter implements Adapter<Question> {

    @Override
    @SneakyThrows
    public Question adapt(ResultSet resultSet) {

        int id = resultSet.getInt("id");
        String text = resultSet.getString("text");

        return new Question(id, text);
    }
}