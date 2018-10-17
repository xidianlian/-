package domain;
/**
 *订单的扩展类，用于接收查询后返回的结果 
 *通过此类映射订单和用户查询的结果，让此类继承包括 字段较多的pojo类(这里选择继承orders)
 */
public class OrdersAndUser extends Orders{
	/**
	 * 查询的User字段名
	 */
	private String username;
	private String birthday;
	private String sex;
	private String address;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "OrdersAndUser [username=" + username + ", birthday=" + birthday + ", sex=" + sex + ", address="
				+ address +"  Order="+super.toString() +"]";
	}
	
	
}
