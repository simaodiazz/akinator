package org.akinator.database.adapter;

import java.sql.ResultSet;

public interface Adapter<V> {

    V adapt(ResultSet resultSet);

}