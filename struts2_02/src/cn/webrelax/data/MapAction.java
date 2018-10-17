package cn.webrelax.data;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import cn.webrelax.entity.User;

/**
 * 封装数据到Map集合
 * 第一步 声明map集合
 * 第二步 生成get和set方法
 * 第三步 在表单输入项的name属性值里面写表达式("mp['one'].username")
 */
public class MapAction extends ActionSupport {
	private Map<String,User>mp;

	public Map<String, User> getMp() {
		return mp;
	}

	public void setMp(Map<String, User> mp) {
		this.mp = mp;
	}

	public String execute() {
		for(Map.Entry<String, User> m:mp.entrySet()) {
			System.out.println(m.getKey()+":"+m.getValue());
		}
		return NONE;
	}
}
