package org.akinator.game;

import lombok.Getter;
import lombok.SneakyThrows;
import org.akinator.database.dao.PersonDatabase;
import org.akinator.database.dao.QuestionDatabase;
import org.akinator.database.dao.TreeDatabase;
import org.akinator.model.person.Person;
import org.akinator.model.question.Question;
import org.akinator.model.tree.Tree;

import java.util.HashMap;
import java.util.Random;

public class SimpleGame implements Game {

    @Getter
    private final HashMap<String, Person> persons;

    @Getter
    private final HashMap<Integer, Question> questions;

    @Getter
    private final HashMap<Integer, Tree> trees;

    private boolean inGame;

    private Tree tree;

    private Question question;

    @SneakyThrows
    public SimpleGame() {
        // Initialize variables
        this.persons = new HashMap<>();
        this.questions = new HashMap<>();
        this.trees = new HashMap<>();

        this.inGame = false;
        // Loading all data
        PersonDatabase personDatabase = new PersonDatabase();
        this.persons.putAll(personDatabase.findAll().get());

        QuestionDatabase questionDatabase = new QuestionDatabase();
        this.questions.putAll(questionDatabase.findAll().get());

        TreeDatabase treeDatabase = new TreeDatabase();
        this.trees.putAll(treeDatabase.findAll().get());

        this.tree = this.trees.get(0);
    }

    @Override
    public void start() {
        if (inGame) {
            System.out.println("Este jogo já esta a decorrer.");
            return;
        }

        inGame = true;


    }

    @Override
    public void stop() {
        if (!inGame) {
            System.out.println("Este jogo não esta a decorrer.");
        }

        inGame = false;
    }

    public int random() {
        Random random = new Random();
        return random.nextInt(this.tree.getQuestions().size());
    }

    public void yes() {
        if (!inGame) {
            System.out.println("Este jogo não esta a decorrer.");
        }
    }

    public void no() {
        if (!inGame) {
            System.out.println("Este jogo não esta a decorrer.");
        }
    }

    public void idk() {
        if (!inGame) {
            System.out.println("Este jogo não esta a decorrer.");
        }
    }
}
