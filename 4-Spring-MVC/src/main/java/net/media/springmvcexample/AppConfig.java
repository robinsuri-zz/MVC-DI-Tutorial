package net.media.springmvcexample;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@PropertySource("classpath:application.properties")
@EnableWebMvc
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
    public JdbcTemplate dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(dbUser);
        dataSource.setPassword(dbPasswd);
        dataSource.setDriverClass(dbDriver);
        dataSource.setJdbcUrl(dbJdbcUrl);
        dataSource.setMinPoolSize(dbMinPoolSize);
        dataSource.setMaxPoolSize(dbMaxPoolSize);
        dataSource.setInitialPoolSize(dbInitPoolSize);
        dataSource.setMaxStatements(dbMaxStatements);
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
