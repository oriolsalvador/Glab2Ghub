/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author chris
 */
public class MyDataSource {
    //té dues referències privades i estàtiques perquè només hi puguem accedir des de dins d'aquesta classe.
    private static final HikariConfig config = new HikariConfig(); // objecte que ens permetrà configurar el pool
    private static HikariDataSource dataSource; //és el pool de connexions

    //per poder inicialitzar els dos objectes privats ABANS que es cridi el únic mètode públic getConnection
    static {
        config.setJdbcUrl("jdbc:mysql://localhost/projecte1db?useUnicode=true&serverTimezone=Europe/Madrid&allowPublicKeyRetrieval=true&useSSL=false");
        config.setUsername("root");
        config.setPassword("123456");
        config.addDataSourceProperty("maximumPoolSize", 1); //al pool només tindrem 1 connexió
        //altres propietats indicades a https://github.com/brettwooldridge/HikariCP#rocket-initialization
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config); //ja tenim el pool de connexions
    }

    //constructor privat perquè no el puguem cridar i no es puguin crear instancies d'aquesta classe.
    private MyDataSource() {
    }

    
    public static Connection getConnection() throws SQLException {
        //retornarà una connexió disponible del pool de connexions
        return dataSource.getConnection();
    }
    
     public static void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
    
}
