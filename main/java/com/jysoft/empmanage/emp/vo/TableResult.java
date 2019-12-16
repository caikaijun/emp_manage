package com.jysoft.empmanage.emp.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 封装前端layui表格所需要的格式的对象
 * @author lenovo
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TableResult {
	/**
	 * 定义状态码(0为正确返回状态,1为失败状态)
	 */
	private Integer code;
	/**
	 * 服务器返回客户端的消息
	 */
	private String msg;
	/**
	 * 数据库记录数
	 */
	private Long count;
	/**
	 *服务器返回客户端的数据
	 */
	private Object data;

	/**
	 * 成功(多数用于增删改)
	 * @return
	 */
	public static TableResult success() {
		return new TableResult(0,"",null,null);
	}

	/**
	 * 成功(多数用于查询)
	 * @param count
	 * @param object
	 * @return
	 */
	public static TableResult success(Long count,Object object) {
		return new TableResult(0,"",count,object);
	}

	/**
	 * 调用失败,返回失败信息
	 * @param msg
	 * @return
	 */
	public static TableResult fail(String msg) {
		return new TableResult(1, msg,null,null);
	}
	
}
