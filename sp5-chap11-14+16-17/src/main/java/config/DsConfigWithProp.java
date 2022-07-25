package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DsConfigWithProp {
  
  @Value("${db.driver}")
  private String driver;
  @Value("${db.url}")
  private String url;
  @Value("${db.user}")
  private String user;
  @Value("${db.password}")
  private String password;
  
  @Bean(destroyMethod = "close")
  public DataSource dataSource() {
    System.out.println(driver);
    System.out.println(url);
    System.out.println(user);
    System.out.println(password);
    
    DataSource ds = new DataSource();
    ds.setDriverClassName(driver);
    ds.setUrl(url);
    ds.setUsername(user);
    ds.setPassword(password);
    
    ds.setInitialSize(2);
    ds.setMaxActive(10);
    ds.setMaxIdle(ds.getMaxActive());
    ds.setTestWhileIdle(true);
    ds.setMinEvictableIdleTimeMillis(1000 * 60 * 3);
    ds.setTimeBetweenEvictionRunsMillis(1000 * 10);
    
    
    return ds;
  }

}
