package com.io.securityInfrun.web.user.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.io.securityInfrun.util.UISMap;

@Repository
@Mapper
public interface UserMapper {

	public ArrayList<UISMap> userInfoSelect();
	public int userInfoInsert();
	
	/*
	 * @Select("select * from testtable") public String testtt();
	 */
	
}
