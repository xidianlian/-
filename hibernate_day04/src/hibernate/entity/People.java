package hibernate.entity;

import java.util.HashSet;
import java.util.Set;

public class People {
	private Integer pid;
	private String name;
	private Set<Role> roles=new HashSet<Role>();
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "People [pid=" + pid + ", name=" + name + "]";
	}
}
