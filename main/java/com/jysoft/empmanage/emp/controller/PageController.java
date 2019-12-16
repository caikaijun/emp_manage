package com.jysoft.empmanage.emp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//专门用于页面跳转的
/**
 * @author caikaijun
 */
@Controller
public class PageController {
	@RequestMapping("{page}")
	public String doModuleUI(@PathVariable String page) {
		return page;
	}
}
