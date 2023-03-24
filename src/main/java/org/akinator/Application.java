package org.akinator;

public interface Application {

    String getName();
    String getVersion();
    String getAuthor();
    String getWebsite();
    void onLoad();
    void onEnable();

}