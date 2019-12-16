package com.jysoft.empmanage.emp.service;



import com.jysoft.empmanage.emp.po.Dept;
import com.jysoft.empmanage.emp.po.Emp;

import com.jysoft.empmanage.emp.vo.EmpVo;
import com.jysoft.empmanage.emp.vo.SearchParam;

import java.util.List;

/**
 * @author lenovo
 */
public interface EmpService {
	/**
	 * 查询员工信息(用于分页查询,全表全字段查询,入职时间范围查询)
	 * @param page
	 * @param limit
	 * @param searchParam
	 * @return
	 */
	List<EmpVo> selectEmp(Integer page, Integer limit, SearchParam searchParam);

	/**
	 * 查询数量(用于分页查询,全表全字段查询,入职时间范围查询)
	 * @param searchParam
	 * @return
	 */
	Long selectCount(SearchParam searchParam);

	/**
	 * 根据部门id查询部门对象
	 * @param id
	 * @return
	 */
	Dept findDept(Integer id);

	/**
	 * 新增员工
	 * @param emp
	 */
	void saveEmp(Emp emp);

	/**
	 * 根据员工id查询员工信息
	 * @param id
	 * @return
	 */
	Emp findEmpById(Integer id);

	/**
	 * 根据员工id修改员工信息
	 * @param emp
	 */
	void updateEmp(Emp emp);

	/**
	 * 根据传来的id数组批量删除员工
	 * @param ids
	 */
	void delEmps(Integer[] ids);
}
