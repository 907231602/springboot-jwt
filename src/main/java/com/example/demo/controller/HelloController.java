package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.util.JwtUtil;

@RestController
public class HelloController {
	//没有token，访问不到index
	@RequestMapping("/index")
	public String index() {
		return "hello";
	}
	
	@RequestMapping("/login")
	public Map<String,Object> login(@RequestBody Map<String,String> map){
		String name=map.get("name");
		String passwd=map.get("passwd");
		Map<String, Object> hash=new HashMap<>();
		boolean isSuccess=true;
		if(isSuccess) {
			String token=JwtUtil.sign(name, passwd);
			if(token!=null) {
				hash.put("info", token);
				return  hash;
			}
				
		}
		hash.put("info", "error");
		return hash;
		
		
	}
	
}
