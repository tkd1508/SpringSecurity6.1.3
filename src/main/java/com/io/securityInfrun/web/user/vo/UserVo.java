package com.io.securityInfrun.web.user.vo;

import lombok.Data;

@Data
public class UserVo {
	
	private int user_id;
    private String password;
    private String user_name;
    private String email;
    private String age;
    private String role;

}
