package cl.tbd.backend.repositories;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sql2o.Sql2o;

@Configuration
public class DatabaseContext {
    // jdbc:postgresql://<HOST>:<PORT>/<DB_NAME>
    String db = "jdbc:postgresql://127.0.0.1:5433/books"; 
    String user = "postgres";
    String password = "123456";

    @Bean
    public Sql2o sql2o(){
        return new Sql2o(db,user,password);
    }
    
}
