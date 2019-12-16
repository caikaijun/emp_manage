package com.jysoft.empmanage.emp.controller;


import javax.servlet.http.HttpServletRequest;

import com.jysoft.empmanage.emp.repository.DeptRepository;
import com.jysoft.empmanage.emp.repository.EmpRepository;
import com.jysoft.empmanage.emp.repository.EmpVoRepository;
import com.jysoft.empmanage.emp.vo.EmpVo;
import com.jysoft.empmanage.emp.po.Dept;
import com.jysoft.empmanage.emp.po.Emp;
import com.jysoft.empmanage.emp.vo.SearchParam;
import com.jysoft.empmanage.emp.vo.TableResult;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;


import com.jysoft.empmanage.emp.service.EmpService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author caikaijun
 */
@RestController
public class EmpController {
	@Autowired
	private EmpService empService;
	@Autowired
	private EmpRepository empRepository;
	@Autowired
	private EmpVoRepository empVoRepository;
	@Autowired
	private DeptRepository deptRepository;
	/**
	 * 分页查询展示
	 * @param page
	 * @param limit
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("emp/findAll")
	public TableResult findEmpByPage(Integer page, Integer limit, SearchParam searchParam) {
		List<EmpVo> list=empService.selectEmp(page,limit,searchParam);
		Long count=empService.selectCount(searchParam);
		return TableResult.success(count,list);
	}


	/**
	 * 根据部门id查询部门
	 * @param id
	 * @return
	 */
	@RequestMapping("emp/findDept")
	public Dept findDeptById(Integer id) {

		Dept dept=empService.findDept(id);

		return dept;
	}
	@RequestMapping("emp/saveEmp")
	public TableResult saveEmp(Emp emp) {
		empService.saveEmp(emp);
		return TableResult.success();
	}
	/**
	 * 根据员工id查询员工信息
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("emp/findEmpById")
	public TableResult findEmpByID(HttpServletRequest request,Integer id){
		Emp emp=empService.findEmpById(id);
		request.getSession().setAttribute("emp", emp);
		return TableResult.success();
	}

	/**
	 * 对员工信息进行更改操作
	 * @param emp
	 * @return
	 */
	@RequestMapping("emp/updateEmp")
	public TableResult updateEmp(Emp emp){
		empService.updateEmp(emp);
		return TableResult.success();
	}

	/**
	 * 批量删除操作
	 * @param ids
	 * @return
	 */
	@RequestMapping("emp/delEmps")
	public TableResult delEmps(Integer... ids) {
		empService.delEmps(ids);
		return TableResult.success();
	}
	@RequestMapping("findvo")
	public  List<EmpVo> findVo(){
		List<EmpVo> list= empVoRepository.findAll();
		return  list;
	}

	/**
	 * 前端下拉选框查询部门
	 * @return
	 */
	@RequestMapping("emp/findDeptList")
	public List<Dept> findDeptList(){
		return  deptRepository.findAll();
	}
}
