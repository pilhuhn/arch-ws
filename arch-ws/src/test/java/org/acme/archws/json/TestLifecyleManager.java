package org.acme.archws.json;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class TestLifecyleManager implements QuarkusTestResourceLifecycleManager {

    PostgreSQLContainer postgreSQLContainer ;

    @Override
    public Map<String, String> start() {
        Map<String,String> props = new HashMap<>();
        postgreSQLContainer =
              new PostgreSQLContainer("postgres");
         postgreSQLContainer.start();
         // Now that postgres is started, we need to get its URL and tell Quarkus
         String jdbcUrl = postgreSQLContainer.getJdbcUrl();
         props.put("quarkus.datasource.jdbc.url", jdbcUrl);
         props.put("quarkus.datasource.username","test");
         props.put("quarkus.datasource.password","test");
         props.put("quarkus.datasource.db-kind", "postgresql");

        System.out.println(" +++ Postgres is at " + jdbcUrl);

         return props;
    }

    @Override
    public void stop() {
        postgreSQLContainer.stop();
    }
}
