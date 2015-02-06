/*
 * A more modern way of doing a database connection using datasource
 * Maven dependencies:

The Data Source dependency
        <dependency>
             <groupId>commons-dbcp</groupId>
             <artifactId>commons-dbcp</artifactId>
             <version>1.2.2</version>
        </dependency>

This brings us ClassPathXmlApplicationContext

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>3.1.2.RELEASE</version>
        </dependency>

Put beans.xml to src/main/resources

<?xml version="1.0" encoding="UTF-8"?> 
<beans xmlns="http://www.springframework.org/schema/beans" 
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
       xsi:schemaLocation="http://www.springframework.org/schema/beans 
                           http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">
    
    <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
        <property name="driverClassName" value="org.h2.Driver"/>
        <property name="url" value="<kannan osoite>"/>
        <property name="username" value="SA" />
        <property name="password" value="" />
    </bean>
</beans>
 */
package dbweb;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author saara
 */
public class DataSourceConnection {
    public static void main(String[] args) throws Exception {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");
        DataSource dataSource = (DataSource) appContext.getBean("dataSource");

        Connection connection = dataSource.getConnection();
        System.out.println("Database with data source connected!");
        String result = query(connection);
        System.out.println(result);
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
