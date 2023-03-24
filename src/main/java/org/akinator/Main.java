package org.akinator;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;

public class Main implements Application {

    @Getter
    @Setter
    private static Main instance;

    @Getter
    @Setter
    private HikariDataSource hikari;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {

    }

    @Override
    public String getName() {
        return "Akinator";
    }

    @Override
    public String getVersion() {
        return "1.0.0-SNAPSHOT";
    }

    @Override
    public String getAuthor() {
        return "simaodiazz";
    }

    @Override
    public String getWebsite() {
        return "https://github.com/simaodiazz";
    }
}