package controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.DuplicateMemberException;
import spring.Member;
import spring.MemberDao;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

@RestController
public class RestMemberController {
  
  private MemberDao memberDao;
  private MemberRegisterService registerService;
  
  public RestMemberController(MemberDao memberDao, MemberRegisterService registerService) {
    this.memberDao = memberDao;
    this.registerService = registerService;
  }
  
  @GetMapping("/api/members")
  public List<Member> members() { return memberDao.selectAll(); }
  
  @GetMapping("/api/members/{id}")
  public Member member(@PathVariable Long id) throws IOException {
    Member member = memberDao.selectById(id);
    if (member == null) throw new MemberNotFoundException();
    
    return member;
  }
  
  @PostMapping("/api/members")
  public ResponseEntity<Object> newMember(@RequestBody @Valid RegisterRequest regReq) throws IOException {
    try {
      Long newMemberId = registerService.regist(regReq);
      URI uri = URI.create("/api/members/" + newMemberId);
      
      return ResponseEntity.created(uri).build();
      
    } catch (DuplicateMemberException e) { return ResponseEntity.status(HttpStatus.CONFLICT).build(); }
  }

}
