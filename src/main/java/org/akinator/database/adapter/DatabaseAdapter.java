package org.akinator.database.adapter;

import java.sql.ResultSet;

public interface DatabaseAdapter<T> {

    T adapt(ResultSet resultSet);

}