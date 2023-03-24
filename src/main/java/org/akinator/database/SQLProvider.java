package org.akinator.database;

import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import org.akinator.Main;
import org.akinator.database.repository.PersonRepository;

public class SQLProvider {

    private final Main main;

    public SQLProvider(Main main) {
        this.main = main;
    }

    public void setup() {

        HikariDataSource hikariDataSource = new HikariDataSource();

        main.setHikari(hikariDataSource);

        main.getHikari().setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        main.getHikari().addDataSourceProperty("serverName", "127.0.0.1");
        main.getHikari().addDataSourceProperty("port", "3306");
        main.getHikari().addDataSourceProperty("databaseName", "akinator_data");
        main.getHikari().addDataSourceProperty("user", "root");
        main.getHikari().addDataSourceProperty("password", "");

        PersonRepository personRepository = new PersonRepository();
        personRepository.create();
    }
}