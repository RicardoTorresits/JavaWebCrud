/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author it-seekers
 */
public class DbConnection {
    
    private static DbConnection instance;
    private Connection connection;
    
    private String username = "TesloDB";
    private String password = "MyScr3tPassWord@as2";
    private static final String URL = "jdbc:postgresql://localhost:5432/Personas";
    
     private static final Logger LOG = Logger.getLogger(DbConnection.class.getName());


    private DbConnection(){
        try {
            Class.forName("org.postgresql.Drive");
            this.connection = DriverManager.getConnection(URL,username,password);
            LOG.info("Conexión a PosttgresSql establecida con éxito");
        } catch (ClassNotFoundException| SQLException e) {
            LOG.log(Level.WARNING, "Error al establecer la coneci\u00f3n:{0}", e.getMessage());
        }
    }
    
    public static DbConnection getInstance(){
        if(instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    public void closeConnection(){
        if(connection != null){
            try{
                connection.close();
                LOG.info("Conxion a postgreSQL cerrada con éxito.");
            }catch (SQLException e){
                LOG.log(Level.WARNING, "Error al cerrar la conexi\u00f3n:{0}", e.getMessage());
                
            }
        }
    }
    
}
