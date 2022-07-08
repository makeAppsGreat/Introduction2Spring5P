package spring;

import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberPrinter {
  
  private DateTimeFormatter dtf;
  
  @Autowired(required = false)
  public void setDateFormatter(DateTimeFormatter dtf) { this.dtf = dtf; }
  
  public void print(Member member) {
    if (dtf == null) {
      System.out.printf("회원 정보 : 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
          member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());
    } else {
      System.out.printf("회원 정보 : 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
          member.getId(), member.getEmail(), member.getName(), dtf.format(member.getRegisterDateTime()));
    }
  }

}
