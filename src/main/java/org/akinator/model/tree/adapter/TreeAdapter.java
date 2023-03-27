package org.akinator.model.tree.adapter;

import lombok.SneakyThrows;
import org.akinator.database.adapter.Adapter;
import org.akinator.model.question.parser.QuestionParser;
import org.akinator.model.tree.Tree;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TreeAdapter implements Adapter<Tree> {

    @Override
    @SneakyThrows
    public Tree adapt(ResultSet resultSet) {

        Integer id = resultSet.getInt("id");

        QuestionParser questionParser = new QuestionParser();

        ArrayList<Integer> questions = questionParser.unparse(resultSet.getString("questions"));

        return new Tree(id, questions);
    }
}
