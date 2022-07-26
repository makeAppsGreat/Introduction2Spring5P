package sp5.sp5chapb;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberApi {
  
  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  @GetMapping("/members")
  public List<String> members() { return jdbcTemplate.queryForList("select EMAIL from MEMBER order by EMAIL", String.class); }

}
