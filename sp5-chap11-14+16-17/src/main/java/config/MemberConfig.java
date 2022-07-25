package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import spring.AuthService;
import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

@Configuration
@EnableTransactionManagement
public class MemberConfig {
  
  /* @Bean(destroyMethod = "close")
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
  } */
  
  @Autowired
  private DataSource dataSource;
  
  @Bean
  public PlatformTransactionManager transactionManager() {
    DataSourceTransactionManager tm = new DataSourceTransactionManager();
    tm.setDataSource(dataSource);
    
    return tm;
  }
  
  @Bean
  public MemberDao memberDao() { return new MemberDao(dataSource); }
  
  @Bean
  public MemberRegisterService memberRegSvc() { return new MemberRegisterService(memberDao()); }
  
  @Bean
  public ChangePasswordService changePwdSvc() { return new ChangePasswordService(memberDao()); }
  
  @Bean
  public AuthService authService() { return new AuthService(memberDao()); }

}
