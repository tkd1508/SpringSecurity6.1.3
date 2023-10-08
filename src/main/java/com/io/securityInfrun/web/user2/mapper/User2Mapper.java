package com.io.securityInfrun.web.user2.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.io.securityInfrun.util.UISMap;

@Repository
@Mapper
public interface User2Mapper {

	public ArrayList<UISMap> user2InfoSelect();
	public int user2InfoInsert();
	
	/*
	 * @Select("select * from testtable") public String testtt();
	 */
	
}
