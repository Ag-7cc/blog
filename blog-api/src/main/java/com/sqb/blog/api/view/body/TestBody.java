package com.sqb.blog.api.view.body;

import com.sqb.blog.api.view.ViewBody;
import com.sqb.blog.api.view.vo.TestVo;

public class TestBody extends ViewBody {
	private TestVo testBean;

	public TestVo getTestBean() {
		return testBean;
	}

	public void setTestBean(TestVo testBean) {
		this.testBean = testBean;
	}

}
