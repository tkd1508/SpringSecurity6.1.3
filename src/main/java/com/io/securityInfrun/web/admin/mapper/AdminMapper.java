package com.io.securityInfrun.web.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminMapper {

	public int updateAuthInfo();
	
}
