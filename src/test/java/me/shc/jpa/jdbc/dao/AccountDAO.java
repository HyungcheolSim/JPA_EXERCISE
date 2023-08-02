package me.shc.jpa.jdbc.dao;

import me.shc.jpa.jdbc.vo.AccountVO;

import java.sql.*;

public class AccountDAO {
    private Connection conn=null;
    private PreparedStatement stmt=null;
    private ResultSet rs=null;

    String url = "jdbc:postgresql://localhost:5432/messenger";
    String username = "hc";
    String password = "pass";
    private final String ACCOUNT_INSERT = "INSERT INTO account(ID, USERNAME, PASSWORD) "
            + "VALUES((SELECT coalesce(MAX(ID),0)+1 from account A),?,?)";

    private final String ACCOUNT_SELECT = "SELECT * FROM account WHERE ID=?";

    public Integer insertAccount(AccountVO vo){
        var id =-1;
        try{
            String[] returnId ={"id"};
            conn = DriverManager.getConnection(url,username,password);
            stmt = conn.prepareStatement(ACCOUNT_INSERT,returnId);
            stmt.setString(1,vo.getUsername());
            stmt.setString(2, vo.getPassword());
            stmt.executeUpdate(); //실행결과 update 된 개수를 return 한다.

            try(ResultSet rs=stmt.getGeneratedKeys()){
                if(rs.next()){
                    id=rs.getInt(1);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return id;
    }

    public AccountVO selectAccount(Integer id){
        AccountVO vo=null;
        try{
            conn =DriverManager.getConnection(url,username,password);
            stmt=conn.prepareStatement(ACCOUNT_SELECT);
            stmt.setInt(1,id);;
            var rs=stmt.executeQuery();
            if(rs.next()){
                vo= new AccountVO();
                vo.setId(rs.getInt("ID"));
                vo.setUsername(rs.getString("USERNAME"));
                vo.setPassword(rs.getString("PASSWORD"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return vo;
    }
}
