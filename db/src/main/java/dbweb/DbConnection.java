/*
 * The traditional way of doing a database connection with a driver
 * Maven dependency :
 *       <dependency>
 *           <groupId>mysql</groupId>
 *           <artifactId>mysql-connector-java</artifactId>
 *           <version>5.1.22</version>
 * 	</dependency>
 */
package dbweb;

/**
 *
 * @author saara
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class  DbConnection{
    public static void main(String[] args) throws Exception {
        
        Class.forName("com.mysql.jdbc.Driver");
        String jdbcUrl = "jdbc:mysql://localhost:3306/TEST";
        String username = "root";
        String password = "rootpw";
        
Connection connection = null;

try {
    System.out.println("Connecting database...");
    connection = DriverManager.getConnection(jdbcUrl, username, password);
    System.out.println("Database connected!");
    String result = query(connection);
    System.out.println(result);
    
} catch (SQLException e) {
    throw new RuntimeException("Cannot connect the database!", e);
} finally {
    System.out.println("Closing the connection.");
    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
}
    }   
    
    private static String query(Connection connection) throws SQLException{
        
        String query="";
         Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM table1");
        
        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String nimi = resultSet.getString("name"); 
            
            query = query + id + "\t" + nimi + "\n";
        }
        return query;
    }
}