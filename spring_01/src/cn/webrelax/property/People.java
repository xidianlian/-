package cn.webrelax.property;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 *注入复杂类型 
 *
 */
public class People {
	private String name;
	private String[] arr;
	private List<String>list;
	private Map<String,String>mp;
	private Properties properties;
	public void setName(String name) {
		this.name = name;
	}
	public void setArr(String[] arr) {
		this.arr = arr;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public void setMp(Map<String, String> mp) {
		this.mp = mp;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	public void test() {
		System.out.println("name:"+name);
		System.out.println("arr:"+arr);
		System.out.println("list:"+list);
		System.out.println("map:"+mp);
		System.out.println("properties"+properties);
	}
	
}
