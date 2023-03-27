package org.akinator.game;

import lombok.Getter;
import lombok.SneakyThrows;
import org.akinator.Main;
import org.akinator.model.person.dao.PersonDatabase;
import org.akinator.model.question.dao.QuestionDatabase;
import org.akinator.model.tree.dao.TreeDatabase;
import org.akinator.model.person.Person;
import org.akinator.model.question.Question;
import org.akinator.model.tree.Tree;
import org.akinator.utils.Randomizer;

import java.util.*;

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

    private int questionIndex;

    @SneakyThrows
    public SimpleGame() {
        // Initialize variables
        this.persons = new HashMap<>();
        this.questions = new HashMap<>();
        this.trees = new HashMap<>();

        this.inGame = false;
        // Loading all data
        PersonDatabase personDatabase = new PersonDatabase();
        HashMap<String, Person> allPersons = personDatabase.findAll();
        this.persons.putAll(allPersons);

        QuestionDatabase questionDatabase = new QuestionDatabase();
        this.questions.putAll(questionDatabase.findAll());

        TreeDatabase treeDatabase = new TreeDatabase();
        HashMap<Integer, Tree> allTrees = treeDatabase.findAll();
        this.trees.putAll(allTrees);

        this.tree = this.trees.get(0);

        questionIndex = 0;

        System.out.println(tree.getQuestions());

        System.out.println(persons.size());
        System.out.println(questions.size());
        System.out.println(trees.size());
    }

    @Override
    public void start() {
        if (inGame) {
            System.out.println("Este jogo já esta a decorrer.");
            return;
        }

        inGame = true;

        this.random();
    }

    @Override
    public void stop() {
        if (!inGame) {
            System.out.println("Este jogo não esta a decorrer.");
            return;
        }

        inGame = false;
    }

    public void random() {

        questionIndex = Randomizer.randomElement(this.tree.getQuestions());
        question = questions.get(questionIndex);

        System.out.println(" ");
        System.out.println("\u001B[93m/" + question.getText() + "\u001B[0m");
        System.out.println(" ");
        System.out.println(" /yes");
        System.out.println(" /no");
        System.out.println(" ");
        System.out.println(" Probablidades: " + this.persons.values());
        System.out.println(" ");
    }

    public void yes() {
        if (!inGame) {
            System.out.println("Este jogo não esta a decorrer.");
            return;
        }

        this.persons.keySet().removeIf(personName -> !this.persons.get(personName).getQuestions().contains(this.question.getId()));

        if (this.trees.containsKey(this.question.getId())) {
            this.tree = this.trees.get(question.getId());
        }

        if (persons.size() == 1) {

            Person person = null;
            Iterator<Person> iterator = this.persons.values().iterator();
            if (iterator.hasNext()) {
                person = iterator.next();
            }

            assert person != null;
            System.out.println("O seu personagem provavelmente é " + person.getName());
            Main.getInstance().setRunning(false);
            return;

        } else if (persons.size() == 0) {
            System.out.println("Seu personagem é o zé ninguém.");
        }

        this.random();
    }

    public void no() {
        if (!inGame) {
            System.out.println("Este jogo não esta a decorrer.");
            return;
        }

        System.out.println(tree.getQuestions());

        this.tree.getQuestions().remove(tree.getQuestions().indexOf(questionIndex));

        this.persons.keySet().removeIf(personName -> this.persons.get(personName).getQuestions().contains(this.question.getId()));

        if (persons.size() == 1) {

            Person person = null;
            Iterator<Person> iterator = this.persons.values().iterator();
            if (iterator.hasNext()) {
                person = iterator.next();
            }

            assert person != null;
            System.out.println("O seu personagem provavelmente é " + person.getName());
            Main.getInstance().setRunning(false);
            return;

        } else if (persons.size() == 0) {
            System.out.println("Seu personagem é o zé ninguém.");
        }

        this.random();
    }

    public void idk() {
        if (!inGame) {
            System.out.println("Este jogo não esta a decorrer.");
            return;
        }

        random();
    }
}
