package org.akinator.database;

import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.akinator.Main;
import org.akinator.model.person.repository.PersonRepository;
import org.akinator.model.question.repository.QuestionRepository;
import org.akinator.model.tree.repository.TreeRepository;

@AllArgsConstructor
public class SQLProvider {

    private final Main main;

    public void setup() {

        HikariDataSource hikari = new HikariDataSource();

        hikari.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikari.setJdbcUrl("jdbc:mysql://localhost:3306/akinator");
        hikari.setUsername("root");
        hikari.setPassword("");

        hikari.setAutoCommit(true);
        hikari.addDataSourceProperty("characterEncoding", "utf8");
        hikari.addDataSourceProperty("autoReconnect", "true");
        hikari.addDataSourceProperty("cachePrepStmts", "true");
        hikari.addDataSourceProperty("useServerPrepStmts", "true");
        hikari.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hikari.addDataSourceProperty("rewriteBatchedStatements", "true");

        Main.getInstance().setHikari(hikari);

        PersonRepository personRepository = new PersonRepository(main);
        personRepository.create();

        QuestionRepository questionRepository = new QuestionRepository(main);
        questionRepository.create();

        TreeRepository treeRepository = new TreeRepository(main);
        treeRepository.create();
    }
}