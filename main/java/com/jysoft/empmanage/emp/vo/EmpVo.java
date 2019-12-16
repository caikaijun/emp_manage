package com.jysoft.empmanage.emp.vo;

import java.util.Date;

import com.jysoft.empmanage.emp.po.Dept;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

import javax.persistence.*;

/**
 * @author lenovo
 */
@Data
@Entity
@Table(name = "emp")
public class EmpVo {

	/**
	 *  员工ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/**
	 * 员工姓名
	 */
	private String name;
	/**
	 *  员工性别
	 */
	private Integer gender;
	/**
	 *  员工出生日期
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	/**
	 * 员工任职工作
	 */
	private String job;
	/**
	 * 员工工资
	 */
	private Double sal;
	/**
	 * 员工奖金
	 */
	private Double bonus;
	/**
	 * 入职时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date workTime;
	/**
	 * 部门对象,用于关联查询
	 */
	@ManyToOne
	@JoinColumn(name="deptId",referencedColumnName="did")
	private Dept dept;
}
