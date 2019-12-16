package com.jysoft.empmanage.emp.serviceImpl;

import com.jysoft.empmanage.emp.po.Dept;
import com.jysoft.empmanage.emp.po.Emp;
import com.jysoft.empmanage.emp.repository.DeptRepository;
import com.jysoft.empmanage.emp.repository.EmpRepository;
import com.jysoft.empmanage.emp.repository.EmpVoRepository;
import com.jysoft.empmanage.emp.service.EmpService;
import com.jysoft.empmanage.emp.thro.ServiceException;
import com.jysoft.empmanage.emp.vo.EmpVo;
import com.jysoft.empmanage.emp.vo.SearchParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lenovo
 */
@Service
@Slf4j
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpRepository empRepository;
    @Autowired
    private DeptRepository deptRepository;
    @Autowired
    private EmpVoRepository empVoRepository;

    @Override
    public List<EmpVo> selectEmp(Integer page, Integer limit, SearchParam searchParam) {
        //验证当前页码值是否合法
        if(page==null||page<1){
            throw new IllegalArgumentException("当前页码值不正确");
        }
        if(searchParam.getBeginTime()!=null&&searchParam.getEndTime()==null){
            throw new IllegalArgumentException("结束时间不能为空");
        }
        if(searchParam.getBeginTime()==null&&searchParam.getEndTime()!=null){
            throw new IllegalArgumentException("起始时间不能为空");
        }
        if(searchParam.getBeginTime()!=null && searchParam.getEndTime()!=null) {
            if(searchParam.getEndTime().before(searchParam.getBeginTime())) {
                throw new ServiceException("起始时间不能大过结束时间");
            }
        }
        Page<EmpVo> result=null;
        try {
            Sort.Order order=new Sort.Order(Sort.Direction.DESC, "id");
            Specification<EmpVo> query=getSpecification(searchParam);
            Pageable pageReques= PageRequest.of(page-1,limit,Sort.by(order));
            result= empVoRepository.findAll( query,pageReques);
        }catch (Exception e){
            log.info(e.getMessage());
            throw  new ServiceException("数据库查询失败",e);
        }

        return result.getContent();

    }


    @Override
    public Long selectCount(SearchParam searchParam) {
        Specification<EmpVo> query=getSpecification(searchParam);
        long count= empVoRepository.count(query);
        return count;
    }

    @Override
    public Dept findDept(Integer id) {
        return deptRepository.findDeptByDid(id);
    }

    @Override
    public void saveEmp(Emp emp) {
        //emp=null;//测试参数传递异常
        if(emp==null) {
            throw new IllegalArgumentException("数据在途中不见了,小主");
        }
      try {
          Emp save = empRepository.save(emp);
      }catch (Exception e){
          log.info(e.getMessage());
          throw  new ServiceException("数据库添加员工失败",e);
      }

    }

    @Override
    public Emp findEmpById(Integer id) {
        if(id==null){
            throw  new IllegalArgumentException("id值没传过来");
        }
        Emp emp= empRepository.findEmpById(id);
        return emp;
    }

    @Override
    public void updateEmp(Emp emp) {
        //emp=null;
        try {
            empRepository.save(emp);
        }catch (Exception e) {
            log.info(e.getMessage());
            throw new ServiceException("数据库修改失败",e);
        }
    }

    @Override
    public void delEmps(Integer[] ids) {
        if(ids==null||ids.length==0) {
            throw new IllegalArgumentException("必须先选中");
        }
        try {
            empRepository.deleteEmpsById(ids);
        }catch (Exception e) {
            log.info(e.getMessage());
            throw new ServiceException("数据批量删除失败",e);
        }
    }

    /**
     * 专门用来获得断言对象的方法
     * @param searchParam
     * @return
     */
    public  Specification<EmpVo> getSpecification(SearchParam searchParam){
        Specification<EmpVo> query = new Specification<EmpVo>(){
            @Override
            public Predicate toPredicate(Root<EmpVo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (!StringUtils.isEmpty(searchParam.getParam())) {
                    Join<EmpVo,Dept> joinDept = root.join("dept",JoinType.LEFT);
                    Predicate coursePredicate1 = criteriaBuilder.like(joinDept.get("deptName"),"%"+searchParam.getParam()+"%");
                    Predicate coursePredicate2 = criteriaBuilder.like(root.get("name"),"%"+searchParam.getParam()+"%");
                    Predicate coursePredicate3 = criteriaBuilder.like(root.get("job"),"%"+searchParam.getParam()+"%");
                    Predicate coursePredicate=criteriaBuilder.or(coursePredicate1,coursePredicate2,coursePredicate3);
                    predicates.add(coursePredicate);
                }
                if(searchParam.getBeginTime()!=null &searchParam.getEndTime()!=null){
                    Predicate workTimePredicate = criteriaBuilder.between(root.get("workTime"), searchParam.getBeginTime(), searchParam.getEndTime());
                    predicates.add(workTimePredicate);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return  query;
    }
}
