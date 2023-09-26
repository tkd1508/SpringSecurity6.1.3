package com.io.securityInfrun.web.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.io.securityInfrun.web.test.vo.TestVo;



@Repository
@Mapper
public interface TestMapper {

	List<TestVo> selectTest();
	
	/*
	 * @Select("select * from testtable") public String testtt();
	 */
	
}
