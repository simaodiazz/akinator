package org.akinator.model.tree.dao;

import org.akinator.model.tree.Tree;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public interface TreeDatabaseService {

    CompletableFuture<Tree> find(Integer id);
    HashMap<Integer, Tree> findAll();
    void create(Tree tree);
    void update(Tree tree);

}