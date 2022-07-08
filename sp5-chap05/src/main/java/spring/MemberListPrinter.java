package spring;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("listPrinter")
public class MemberListPrinter {
  
  private MemberDao memberDao;
  private MemberPrinter printer;
  
  /* public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
    this.memberDao = memberDao;
    this.printer = printer;
  } */
  public MemberListPrinter() { }
  
  @Autowired
  public void setMemberDao(MemberDao memberDao) { this.memberDao = memberDao; }
  @Autowired
  @Qualifier("summaryPrinter")
  public void setMemberPrinter(MemberPrinter printer) { this.printer = printer; }
  // public void setMemberPrinter(MemberSummaryPrinter printer) { this.printer = printer; }
  
  
  public void printAll() {
    Collection<Member> members = memberDao.selectAll();
    
    if (members.size() > 0) members.forEach(m -> printer.print(m));
    else System.out.println("데이터 없음\n");
  }

}
