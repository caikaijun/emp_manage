package com.jysoft.empmanage.emp.repository;


import com.jysoft.empmanage.emp.vo.EmpVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author lenovo
 */
public interface EmpVoRepository extends JpaRepository<EmpVo,Integer> , JpaSpecificationExecutor<EmpVo> {

}
