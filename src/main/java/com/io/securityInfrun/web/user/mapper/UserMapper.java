package com.io.securityInfrun.web.user.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.io.securityInfrun.util.UISMap;
import com.io.securityInfrun.web.user.vo.UserVo;

@Repository
@Mapper
public interface UserMapper {

	public UserVo findUserInfo(String username);
	public ArrayList<UISMap> userInfoSelect();
	public int userInfoInsert(UISMap input);
	
	
}
