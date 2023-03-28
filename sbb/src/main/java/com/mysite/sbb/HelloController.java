package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "이제 STS에서 프로그램을 수정하면 서버는 물론 브라우저도 리로딩되어 변경된 내용을 추가 작업 없이 즉시 확인할 수 있다.";
	}
}