package com.sqb.blog.api.ctrl;

import com.sqb.blog.api.view.View;
import com.sqb.blog.api.view.ViewUtil;
import com.sqb.blog.api.view.body.TestBody;
import com.sqb.blog.api.view.vo.TestVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

	@RequestMapping(method = RequestMethod.GET)
	public View get() {
		// 参数校验

		// 业务调用

		// 结果封装
		TestVo vo = new TestVo();
		vo.setName("XXX");
		vo.setAge(20);

		TestBody body = new TestBody();
		body.setTestBean(vo);

		View view = ViewUtil.defaultView();
		view.setBody(body);
		return view;
	}

	@RequestMapping(method = RequestMethod.POST)
	public View post(@RequestParam("name") String name, @RequestParam("value") String[] values) {
		System.out.println(name);
		System.out.println(Arrays.toString(values));
		return ViewUtil.defaultView();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Object put() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", "0000");
		map.put("msg", "PUT");
		return map;
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public Object delete() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", "0000");
		map.put("msg", "DELETE");
		return map;
	}

}
