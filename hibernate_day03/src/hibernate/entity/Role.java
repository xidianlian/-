package hibernate.entity;

import java.util.HashSet;
import java.util.Set;

public class Role {
	private Integer rid;
	private String profession;
	private Set<People> ps=new HashSet<People>();
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public Set<People> getPs() {
		return ps;
	}
	public void setPs(Set<People> ps) {
		this.ps = ps;
	}
	
}
