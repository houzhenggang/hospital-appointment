package com.kasoft.register.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kylin
 * @create 2019-07-24 17:00
 */
@RestController
public class TestController {

	@GetMapping("/kasoft/register/test")
	public String test() {
		return "hello world！";
	}
}
