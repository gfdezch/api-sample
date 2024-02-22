package com.gfc.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

/**
 * Represents the base class for all the test classes.
 * Initializes the MSSQL server container and sets the datasource properties.
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Testcontainers
public abstract class AbstractTest {

    @LocalServerPort
	protected int port;   
    
    /**
     * Represents the version of the MSSQL server container.
     */
    protected static final String MSSQL_VERSION = "mcr.microsoft.com/mssql/server:2022-latest";

    /**
     * Runs a MSSQL server container.
     */    
    protected static MSSQLServerContainer<?> mssqlserver = new MSSQLServerContainer<>(DockerImageName.parse(MSSQL_VERSION))
    .acceptLicense()
    .withUrlParam("trustServerCertificate", "true")
    .withReuse(true);
    
    /**
     * Sets the datasource properties.
     * @param registry The dynamic property registry.
     */
    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mssqlserver::getJdbcUrl);
        registry.add("spring.datasource.username", mssqlserver::getUsername);
        registry.add("spring.datasource.password", mssqlserver::getPassword);
        registry.add("spring.flyway.enabled", () -> "true");        
    }

    @BeforeAll
    public static void beforeAll() {
        mssqlserver.start();
    }
}
