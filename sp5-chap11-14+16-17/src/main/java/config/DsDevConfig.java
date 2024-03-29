package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DsDevConfig {

  @Bean(destroyMethod = "close")
  public DataSource dataSource() {
    DataSource ds = new DataSource();
    ds.setDriverClassName("org.mariadb.jdbc.Driver");
    ds.setUrl("jdbc:mariadb://localhost:3306/spring5fs?characterEncoding=utf8");
    ds.setUsername("spring5");
    ds.setPassword("spring5");
    
    ds.setInitialSize(2);
    ds.setMaxActive(10);
    ds.setMaxIdle(ds.getMaxActive());
    ds.setTestWhileIdle(true);
    ds.setMinEvictableIdleTimeMillis(1000 * 60 * 3);
    ds.setTimeBetweenEvictionRunsMillis(1000 * 10);
    
    
    return ds;
  }

}
