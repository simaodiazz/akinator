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
}