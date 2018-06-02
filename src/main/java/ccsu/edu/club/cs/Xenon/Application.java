package ccsu.edu.club.cs.Xenon;

import ccsu.edu.club.cs.Xenon.Models.CustomerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Value("${SPRING_NAME}")
    private String name;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Environment env;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.printf("The application is running %s!\n", name);

            System.out.printf("The original password is: %s, and the hashed password is: %s\n", "pass1", passwordEncoder.encode("pass1"));
            System.out.printf("The original password is: %s, and the hashed password is: %s\n", "pass2", passwordEncoder.encode("pass2"));
            System.out.printf("The original password is: %s, and the hashed password is: %s\n", "pass3", passwordEncoder.encode("pass3"));


            // If the environment is dev, then run schema.sql to reinitialize the schema and repopulate test data
            if(env.getActiveProfiles()[0].equalsIgnoreCase("dev")) {
                // Initialize schema
                Resource resource = new ClassPathResource("schema.sql");
                ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
                databasePopulator.execute(dataSource);

                // Fill data
                Resource dataResource = new ClassPathResource("data.sql");
                ResourceDatabasePopulator databasePopulatorData = new ResourceDatabasePopulator(dataResource);
                databasePopulatorData.execute(dataSource);
            }

            log.info("Querying for customer records where last_name = 'Gruber':");
            jdbcTemplate.query(
                    "SELECT id, first_name, last_name FROM customers WHERE last_name= ?",
                    (rs, rowNum) -> new CustomerModel(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name")),
                    "Gruber"
            ).forEach(customer -> log.info(customer.toString()));
        };
    }

    /**
     * Leaving this here as a reference
     */
//    public void run(String... strings) throws Exception {
//
//        log.info("Creating tables");
//
//        jdbcTemplate.execute("DROP TABLE IF EXISTS customers");
//        jdbcTemplate.execute("CREATE TABLE customers(" +
//                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
//
//        // Split up the array of whole names into an array of first/last names
//        List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
//                .map(name -> name.split(" "))
//                .collect(Collectors.toList());
//
//        // Use a Java 8 stream to print out each tuple of the list
//        splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));
//
//        // Uses JdbcTemplate's batchUpdate operation to bulk load data
//        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);
//
//        log.info("Querying for customer records where first_name = 'Josh':");
//        jdbcTemplate.query(
//                "SELECT id, first_name, last_name FROM customers",
//                (rs, rowNum) -> new CustomerModel(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
//        ).forEach(customer -> log.info(customer.toString()));
//    }
}