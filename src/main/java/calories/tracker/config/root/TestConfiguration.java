package calories.tracker.config.root;


import calories.tracker.app.init.TestDataInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

/**
 * Integration testing specific configuration - creates a in-memory datasource,
 * sets hibernate on create drop mode and inserts some test data on the database.
 * <p>
 * This allows to clone the project repository and start a running application with the command
 * <p>
 * mvn clean install tomcat7:run-war -Dspring.profiles.active=test
 * <p>
 * Access http://localhost:8080/ and login with test123 / Password2, in order to see some test data,
 * or create a new user.
 */
@Configuration
@Profile("test")
@EnableTransactionManagement
public class TestConfiguration {

    @Bean(initMethod = "init")
    public TestDataInitializer initTestData() {
        return new TestDataInitializer();
    }

    @Bean(name = "datasource")
    public DriverManagerDataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(org.hsqldb.jdbcDriver.class.getName());
//        dataSource.setUrl("jdbc:hsqldb:mem:mydb");
//        dataSource.setUsername("sa");
//        dataSource.setPassword("jdbc:hsqldb:mem:mydb");
//        return dataSource;

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:" + System.getenv("ODDS_DB_URL") + "?loglevel=0");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/calories_tracker?loglevel=0");
        dataSource.setUsername(System.getenv("ODDS_USER"));
        dataSource.setPassword(System.getenv("ODDS_PASS"));
        return dataSource;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DriverManagerDataSource dataSource) {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan(new String[]{"calories.tracker.app.model"});
        entityManagerFactoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Map<String, Object> jpaProperties = new HashMap<String, Object>();
        jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
        jpaProperties.put("hibernate.show_sql", "true");
        jpaProperties.put("hibernate.format_sql", "true");
        jpaProperties.put("hibernate.use_sql_comments", "true");
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        entityManagerFactoryBean.setJpaPropertyMap(jpaProperties);

        return entityManagerFactoryBean;
    }

}
