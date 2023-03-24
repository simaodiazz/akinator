package org.akinator;

public class Core {

    public static void main(String[] args) {
        Main main = new Main();

        System.out.println("[INFO]: Preparing to load application");
        main.onLoad();
        System.out.println("[INFO]: Aplication is loaded");
        System.out.println("[INFO]: Preparing to enable application");
        main.onEnable();
        System.out.println("[INFO]: Application is enabled");
        System.out.println("[INFO]: Name of application: " + main.getName());
        System.out.println("[INFO]: Version of application: " + main.getVersion());
        System.out.println("[INFO]: Author of application: " + main.getAuthor());
        System.out.println("[INFO]: Website of author: " + main.getWebsite());
    }
}