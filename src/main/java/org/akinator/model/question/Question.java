package org.akinator.model.question;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Question {

    private String text;
    private Integer id;

}