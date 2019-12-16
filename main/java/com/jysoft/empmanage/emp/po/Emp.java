package com.jysoft.empmanage.emp.po;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

import javax.persistence.*;

/**
 * @author lenovo
 */
@Data
@Entity
@Table(name = "emp")
public class Emp {
	/**
	 * 员工ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/**
	 * 员工姓名
	 */
	@Column(name = "name")
	private String name;
	/**
	 * 员工性别
	 */
	@Column(name = "gender")
	private Integer gender;
	/**
	 * 员工出生日期
	 */
	@Column(name = "birthday")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	/**
	 * 员工所在部门ID
	 */
	@Column(name = "deptId")
	private Integer deptId;
	/**
	 * 员工任职工作
	 */
	@Column(name = "job")
	private String job;
	/**
	 *  员工工资
	 */
	@Column(name = "sal")
	private Double sal;
	/**
	 * 员工奖金
	 */
	@Column(name = "bonus")
	private Double bonus;
	/**
	 * 入职时间
	 */
	@Column(name = "workTime")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date workTime;
}
