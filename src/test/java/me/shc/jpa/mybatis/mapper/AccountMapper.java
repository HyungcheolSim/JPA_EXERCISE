package me.shc.jpa.mybatis.mapper;

import me.shc.jpa.mybatis.vo.AccountMyBatisVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {

  AccountMyBatisVO selectAccount(@Param("id") int id);

  void insertAccount(AccountMyBatisVO vo);
}