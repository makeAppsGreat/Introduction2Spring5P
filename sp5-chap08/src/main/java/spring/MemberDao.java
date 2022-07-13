package spring;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MemberDao {
  
  private JdbcTemplate jdbcTemplate;
  
  public MemberDao(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

  public Member selectByEmail(String email) {
    /* List<Member> results = jdbcTemplate.query(
        "select * from MEMBER where EMAIL = ?",
        new RowMapper<Member>() {
          @Override
          public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member(
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"),
                rs.getString("NAME"),
                rs.getTimestamp("REGDATE").toLocalDateTime());
            member.setId(rs.getLong("ID"));
            
            return member;
          }
        }, email); */
    
    // Lambda
    /* List<Member> results = jdbcTemplate.query(
        "select * from MEMBER where EMAIL = ?",
        (ResultSet rs, int rowNum) -> {
          Member member = new Member(
              rs.getString("EMAIL"),
              rs.getString("PASSWORD"),
              rs.getString("NAME"),
              rs.getTimestamp("REGDATE").toLocalDateTime());
          member.setId(rs.getLong("ID"));
          
          return member;
        }, email); */
    
    // Reusable RowMapper
    List<Member> results = jdbcTemplate.query("select * from MEMBER where EMAIL = ?", new MemberRowMapper(), email);
    
    return results.isEmpty() ? null : results.get(0);
  }
  
  public Collection<Member> selectAll() { return null; }

  public void insert(Member member) { }
  
  public void update(Member member) { }
  
  private class MemberRowMapper implements RowMapper<Member> {
    
    @Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
      Member member = new Member(
          rs.getString("EMAIL"),
          rs.getString("PASSWORD"),
          rs.getString("NAME"),
          rs.getTimestamp("REGDATE").toLocalDateTime());
      member.setId(rs.getLong("ID"));
      
      return member;
    }
    
  }
  
}
