package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 使用二级缓存需要实现序列化接口
 * 因为二级缓存数据存储介质多种多样，不一样在内存,有可能存硬盘或者远程服务器
 *
 */
public class User implements Serializable {
	private int id;
	private String username;// 用户姓名
	private String sex;// 性别
	private Date birthday;// 生日
	private String address;// 地址
	
	//用户创建的订单列表
	private List<Orders> listOrders;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public List<Orders> getListOrders() {
		return listOrders;
	}
	public void setListOrders(List<Orders> listOrders) {
		this.listOrders = listOrders;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", sex=" + sex + ", birthday=" + birthday + ", address="
				+ address + ", listOrders=" + listOrders + "]";
	}

	
	

}
