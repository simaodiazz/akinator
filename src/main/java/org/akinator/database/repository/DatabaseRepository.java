package org.akinator.database.repository;

public interface DatabaseRepository {

    void create();
    void delete();

    default void recreate() {
        delete();
        create();
    }
}