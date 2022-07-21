package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import controller.ChangePwdController;
import controller.LoginController;
import controller.LogoutController;
import controller.RegisterController;
import spring.AuthService;
import spring.ChangePasswordService;
import spring.MemberRegisterService;
import survey.SurveyController;

@Configuration
public class ControllerConfig {
  
  @Autowired
  private MemberRegisterService memberRegSvc;
  @Autowired
  private AuthService authService;
  @Autowired
  private ChangePasswordService changePasswordService;
  
  @Bean
  public RegisterController registerController() {
    RegisterController controller = new RegisterController();
    controller.setMemberRegisterService(memberRegSvc);
    
    return controller;
  }
  
  @Bean
  public SurveyController surveyController() { return new SurveyController(); }
  
  @Bean
  public LoginController loginController() { return new LoginController(authService); }
  
  @Bean
  public LogoutController logoutController() { return new LogoutController(); }
  
  @Bean
  public ChangePwdController changePwdController() { return new ChangePwdController(changePasswordService); }
  
}
