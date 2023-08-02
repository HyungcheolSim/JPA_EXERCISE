package me.shc.jpa.jdbc;


import me.shc.jpa.jdbc.template.AccountTemplateDAO;
import me.shc.jpa.jdbc.vo.AccountVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class JDBCTemplateTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  @Test
  @DisplayName("SQL Mapper = JDBC Template 실습")
  void sqlMapper_JDBCTemplateTest() {
    var accountTemplateDAO = new AccountTemplateDAO(jdbcTemplate);

    var id = accountTemplateDAO.insertAccount(new AccountVO("new user2", "new password2"));

    var account = accountTemplateDAO.selectAccount(id);
    assert account.getUsername().equals("new user2");
  }
}
