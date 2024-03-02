package com.io.securityInfrun.web.urlPathAuth.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.io.securityInfrun.util.UISMap;

@Repository
@Mapper
public interface UrlPathAuthMapper {

	public ArrayList<UISMap> setUrlPathAuth();
	
}
