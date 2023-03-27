package org.akinator;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import org.akinator.database.SQLProvider;
import org.akinator.game.Game;
import org.akinator.game.SimpleGame;

import java.util.Scanner;

public class Main implements Application {

    @Getter
    @Setter
    private static Main instance;

    @Getter
    @Setter
    private HikariDataSource hikari;

    @Getter
    @Setter
    private boolean running;

    @Getter
    @Setter
    private SimpleGame simpleGame;

    @Override
    public void onLoad() {

        instance = this;

        SQLProvider sqlProvider = new SQLProvider(this);
        sqlProvider.setup();
    }

    @Override
    public void onEnable() {

        running = true;

        while (running) {

            System.out.println(" ");
            System.out.println("\u001B[93m/start.\u001B[0m");
            System.out.println("    Começar um jogo.");
            System.out.println(" ");
            System.out.println("\u001B[93m/stop.\u001B[0m");
            System.out.println("    Parar um jogo.");
            System.out.println(" ");
            System.out.println("\u001B[93m/yes.\u001B[0m");
            System.out.println("    Responder que a pergunta é correta.");
            System.out.println(" ");
            System.out.println("\u001B[93m/no.\u001B[0m");
            System.out.println("    Responder que a pergunta é incorreta.");
            System.out.println(" ");
            System.out.println("\u001B[93m/idk.\u001B[0m");
            System.out.println("    Responder que não sei se a pergunta é verdadeira ou falsa.");
            System.out.println(" ");

            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();

            switch (message) {

                case "/start":

                    simpleGame = new SimpleGame();
                    simpleGame.start();

                case "/stop":

                    simpleGame.stop();

                case "/yes":

                    simpleGame.yes();

                case "/no":

                    simpleGame.no();

                case "/idk":

                    simpleGame.idk();
            }
        }
    }
}
