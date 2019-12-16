package com.jysoft.empmanage.emp.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
/**
 *封装搜索框内容的对象
 * @author lenovo
 */
@Data
public class SearchParam {
	/**
	 * 全表查询搜索框内容
	 */
	private String param;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	/**
	 * 入职时间开始段
	 */
	private Date beginTime;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	/**
	 * 入职时间结束段
	 */
	private Date endTime;
}
