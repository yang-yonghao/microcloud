package com.springcloud.entity;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

@SuppressWarnings("serial")
@Data
@Accessors(chain=true)
public class Dept implements Serializable{
	private int deptno;
	private String dname;
	private String dbsource;
}
