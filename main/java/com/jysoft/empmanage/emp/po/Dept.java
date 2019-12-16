package com.jysoft.empmanage.emp.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lenovo
 */
@Data
@Entity
@Table(name = "emp_dept")
public class Dept {
	/**
	 * 部门ID
	 */
	@Id
	private Integer did;
	/**
	 * 部门名称
	 */
	@Column(name="deptName")
	private String deptName;
}
