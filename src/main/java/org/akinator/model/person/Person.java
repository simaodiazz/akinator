package org.akinator.model.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.akinator.model.question.Question;

import java.util.Set;

@AllArgsConstructor
@Data
public class Person {

    private String personName;

    // Questões corretas
    private Set<Question> questions;

}