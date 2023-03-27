package org.akinator.database.dao;

import org.akinator.model.tree.Tree;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public interface TreeDatabaseService {

    CompletableFuture<Tree> find(Integer id);
    CompletableFuture<HashMap<Integer, Tree>> findAll();
    void create(String treeName);
    void update(String treeName);

}