package org.akinator.model.tree.dao;

import org.akinator.model.tree.Tree;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class TreeDatabase implements TreeDatabaseService {

    @Override
    public CompletableFuture<Tree> find(Integer id) {
        return null;
    }

    @Override
    public CompletableFuture<HashMap<Integer, Tree>> findAll() {
        return null;
    }

    @Override
    public void create(String treeName) {

    }

    @Override
    public void update(String treeName) {

    }
}