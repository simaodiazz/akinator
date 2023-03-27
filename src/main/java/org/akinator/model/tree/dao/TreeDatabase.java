package org.akinator.model.tree.dao;

import org.akinator.Main;
import org.akinator.model.question.Question;
import org.akinator.model.question.parser.QuestionParser;
import org.akinator.model.tree.Tree;
import org.akinator.model.tree.adapter.TreeAdapter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class TreeDatabase implements TreeDatabaseService {

    @Override
    public CompletableFuture<Tree> find(Integer id) {
        CompletableFuture.supplyAsync(() -> {
            try (PreparedStatement preparedStatement = Main.getInstance().getHikari().getConnection().prepareStatement("SELECT * FROM trees WHERE id=?")) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    TreeAdapter treeAdapter = new TreeAdapter();
                    return treeAdapter.adapt(resultSet);
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return null;
        });
        return null;
    }

    @Override
    public HashMap<Integer, Tree> findAll() {
        HashMap<Integer, Tree> trees = new HashMap<>();
        CompletableFuture<HashMap<Integer, Tree>> future = CompletableFuture.supplyAsync(() -> {
            try (PreparedStatement preparedStatement = Main.getInstance().getHikari().getConnection().prepareStatement("SELECT * FROM trees")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                TreeAdapter treeAdapter = new TreeAdapter();
                while (resultSet.next()) {
                    Tree tree = treeAdapter.adapt(resultSet);
                    trees.put(tree.getId(), tree);
                }
                return trees;
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return null;
        });
        return future.join();
    }
    @Override
    public void create(Tree tree) {
        try (PreparedStatement preparedStatement = Main.getInstance().getHikari().getConnection().prepareStatement("INSERT INTO trees (id, questions) VALUES (?, ?)")) {
            QuestionParser questionParser = new QuestionParser();
            preparedStatement.setInt(1, tree.getId());
            preparedStatement.setString(2, questionParser.parse(tree.getQuestions()));
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Tree tree) {
        try (PreparedStatement preparedStatement = Main.getInstance().getHikari().getConnection().prepareStatement("UPDATE trees SET questions=? WHERE id=?")) {
            QuestionParser questionParser = new QuestionParser();
            preparedStatement.setString(1, questionParser.parse(tree.getQuestions()));
            preparedStatement.setInt(2, tree.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}