package com.jysoft.empmanage.emp.thro;

import javax.servlet.http.HttpServletRequest;

import com.jysoft.empmanage.emp.vo.TableResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lenovo
 */
@RestControllerAdvice 	//对Controller层异常有效
@Slf4j	//引入日志
public class SystemException {

	/**
	 * 只对运行时异常有效
	 * @param throwable
	 * @param request
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	public TableResult exception(Throwable throwable, HttpServletRequest request) {
		throwable.printStackTrace();
		log.info(throwable.getMessage());
		request.getSession().setAttribute("msg", throwable.getMessage());
		return TableResult.fail(throwable.getMessage());
	}
}
