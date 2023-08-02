package me.shc.jpa.jdbc.template;

import me.shc.jpa.jdbc.vo.AccountVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        var vo = new AccountVO();
        vo.setId(rs.getInt("ID"));
        vo.setUsername(rs.getString("USERNAME"));
        vo.setPassword(rs.getString("PASSWORD"));
        return vo;
    }
}
