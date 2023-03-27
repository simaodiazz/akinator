package org.akinator.database.adapter;

import lombok.SneakyThrows;
import org.akinator.model.person.Person;
import org.akinator.model.question.parser.QuestionParser;

import java.sql.ResultSet;

public class PersonAdapter implements Adapter<Person> {

    @Override
    @SneakyThrows
    public Person adapt(ResultSet resultSet) {

        Person person = new Person();
        QuestionParser questionParser = new QuestionParser();

        String name = resultSet.getString("name");
        String questions = resultSet.getString("data");

        person.setName(name);
        person.setQuestions(questionParser.unparse(questions));

        return person;
    }
}