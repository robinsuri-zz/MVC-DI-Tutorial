package foo.bar;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@ComponentScan("foo.bar")
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Value("${db.user}") private String dbUser;
    @Value("${db.passwd}") private String dbPasswd;
    @Value("${db.driver}") private String dbDriver;
    @Value("${db.jdbc.url}") private String dbJdbcUrl;
    @Value("${db.min.pool.size}") private int dbMinPoolSize;
    @Value("${db.max.pool.size}") private int dbMaxPoolSize;
    @Value("${db.init.pool.size}") private int dbInitPoolSize;
    @Value("${db.max.statements}") private int dbMaxStatements;

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(dbUser);
        dataSource.setPassword(dbPasswd);
        dataSource.setDriverClass(dbDriver);
        dataSource.setJdbcUrl(dbJdbcUrl);
        dataSource.setMinPoolSize(dbMinPoolSize);
        dataSource.setMaxPoolSize(dbMaxPoolSize);
        dataSource.setInitialPoolSize(dbInitPoolSize);
        dataSource.setMaxStatements(dbMaxStatements);
        return dataSource;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
