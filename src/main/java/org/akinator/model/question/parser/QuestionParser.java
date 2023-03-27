package org.akinator.model.question.parser;

import org.akinator.Parser;
import java.util.ArrayList;

public class QuestionParser implements Parser<ArrayList<Integer>, String> {

    @Override
    public String parse(ArrayList<Integer> questions) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i <= questions.size(); i++) {

            int question = questions.get(i);

            if (i == questions.size()) {
                stringBuilder.append(question);
            }

            stringBuilder.append(question);
            stringBuilder.append(":");
        }

        return stringBuilder.toString();
    }

    @Override
    public ArrayList<Integer> unparse(String data) {

        ArrayList<Integer> arrayList = new ArrayList<>();

        if (data.equalsIgnoreCase("")) {
            return arrayList;
        }

        String[] questions = data.split(":");

        for (String question : questions) {

            Integer integer = Integer.parseInt(question);
            arrayList.add(integer);

        }

        return arrayList;
    }
}
