package com.io.securityInfrun.web.test.vo;

public class TestVo {
	
	private String id;
    private String name;
	
	public TestVo(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "TestVo [id=" + id + ", name=" + name + "]";
	}

}
