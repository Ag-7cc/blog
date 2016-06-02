package com.sqb.blog.api.ctrl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/2")
public class Test2Controller {

	@RequestMapping(method = RequestMethod.GET, params = "export=true")
	public Object get() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", "0000");
		map.put("msg", "export=true");
		return map;
	}

	@RequestMapping(method = RequestMethod.GET, params = "!export")
	public Object post() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", "0000");
		map.put("msg", "!export");
		return map;
	}

}
