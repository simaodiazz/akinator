package org.akinator.model.question.dao;

import org.akinator.model.question.Question;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public interface QuestionDatabaseService {

    CompletableFuture<Question> find(Integer id);
    HashMap<Integer, Question> findAll();
    void create(Question question);
    void update(Question question);

}