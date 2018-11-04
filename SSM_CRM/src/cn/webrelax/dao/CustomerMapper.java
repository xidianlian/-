package cn.webrelax.dao;

import java.util.List;

import cn.webrelax.pojo.Customer;
import cn.webrelax.pojo.QueryVo;

public interface CustomerMapper {
	public List<Customer> findCustomerByVo(QueryVo vo);
	public int findCustomerByVoCount(QueryVo vo);
	public Customer findCustomerById(int id);
	public void updateCustommer(Customer cus);
	public void deleteCustomerById(int id );
}
