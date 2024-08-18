package com.io.securityInfrun.web.admin.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.io.securityInfrun.util.UISMap;

@Repository
@Mapper
public interface AdminMapper {

	public int updateAuthInfo();
	public ArrayList<UISMap> selectRole();
	
}
