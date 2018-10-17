package domain;

import java.util.Date;
import java.util.List;

public class Orders {
    private Integer id;

    private Integer user_id;

    private String number;

    private Date createtime;

    private String note;
    
    //用户信息;不用继承User，<resultMap>标签
    private User user;
    

	//订单明细
    private List<Orderdetail> orderdetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer userId) {
        this.user_id = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Orderdetail> getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(List<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", user_id=" + user_id + ", number=" + number + ", createtime=" + createtime
				+ ", note=" + note + ", user=" + user + ", orderdetails=" + orderdetails + "]";
	}


}