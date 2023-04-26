package Grupo_10.SuaViagem.com.JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class MySQLConnection {
        public static void main(String[] args) throws SQLException {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://database-1-teste.cljvdwhbsvzr.us-east-2.rds.amazonaws.com:3307/db_projeto_integrador?createDatabaseIfNotExist=true",
                    "admin", "password"
            );
            System.out.println("Connected to database!");
        }
    }