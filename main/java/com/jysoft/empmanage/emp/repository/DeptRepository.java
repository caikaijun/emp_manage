package com.jysoft.empmanage.emp.repository;

import com.jysoft.empmanage.emp.po.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lenovo
 */
public interface DeptRepository extends JpaRepository<Dept,Integer> {
    /**
     * 根据部门id查询部门对象
     * @param did
     * @return
     */
    Dept findDeptByDid(Integer did);
}
