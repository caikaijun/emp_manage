package com.jysoft.empmanage.emp.repository;

import com.jysoft.empmanage.emp.po.Emp;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author caikiajun
 */
@Repository
public interface EmpRepository extends JpaRepository<Emp,Integer> , JpaSpecificationExecutor<Emp> {
    /**
     * 根据员工id查询员工信息
     * @param id
     * @return
     */
    Emp findEmpById(Integer id);

    /**
     * 根据员工id批量删除员工
     * @param ids
     */
    @Modifying
    @Transactional(rollbackOn=Exception.class)
    @Query(value = "delete from Emp s where s.id in (?1)")
    void deleteEmpsById(@Param("ids") Integer[] ids);
}
