package xenon.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${SPRING_DATASOURCE_URL}")
    private String dbUrl;

    @Value("${SPRING_DATASOURCE_USERNAME}")
    private String dbUsername;

    @Value("${SPRING_DATASOURCE_PASSWORD}")
    private String dbPassword;

    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource(dbUrl, dbUsername, dbPassword);
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
