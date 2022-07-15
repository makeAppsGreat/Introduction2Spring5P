package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.VersionPrinter;

@Configuration
@EnableTransactionManagement
public class AppCtx {
  
  @Bean(destroyMethod = "close")
  public DataSource dataSource() {
    DataSource ds = new DataSource();
    // System.out.println(String.format("1 Max Idle : %d, Max Active : %d", ds.getMaxIdle(), ds.getMaxActive()));
    
    ds.setDriverClassName("org.mariadb.jdbc.Driver");
    ds.setUrl("jdbc:mariadb://localhost:3306/spring5fs?characterEncoding=utf8");
    ds.setUsername("spring5");
    ds.setPassword("spring5");
    // System.out.println(String.format("2 Max Idle : %d, Max Active : %d", ds.getMaxIdle(), ds.getMaxActive()));
    
    ds.setInitialSize(2);
    ds.setMaxActive(10);
    ds.setMaxIdle(ds.getMaxActive()); // Warn: maxIdle is larger than maxActive, setting maxIdle to: 10

    ds.setTestWhileIdle(true);
    ds.setMinEvictableIdleTimeMillis(1000 * 60 * 3);
    ds.setTimeBetweenEvictionRunsMillis(1000 * 10);
    // System.out.println(String.format("3 Max Idle : %d, Max Active : %d", ds.getMaxIdle(), ds.getMaxActive()));
    
    return ds;
  }
  
  @Bean
  public PlatformTransactionManager transactionManager() {
    DataSourceTransactionManager tm = new DataSourceTransactionManager();
    tm.setDataSource(dataSource());
    
    return tm;
  }
  
  @Bean
  public MemberDao memberDao() { return new MemberDao(dataSource()); }
  
  @Bean
  public MemberRegisterService memberRegSvc() { return new MemberRegisterService(memberDao()); }
  
  @Bean
  public ChangePasswordService changePwdSvc() { return new ChangePasswordService(memberDao()); }
  
  @Bean
  public MemberPrinter memberPrinter() { return new MemberPrinter(); }
  
  @Bean
  public MemberListPrinter listPrinter() { return new MemberListPrinter(memberDao(), memberPrinter()); }
  
  @Bean
  public MemberInfoPrinter infoPrinter() { return new MemberInfoPrinter(memberDao(), memberPrinter()); }
  
  @Bean
  public VersionPrinter versionPrinter() {
    VersionPrinter verPrinter = new VersionPrinter();
    verPrinter.setMajorVersion(6);
    verPrinter.setMinorVersion(0);
    
    return verPrinter;
  }

}
