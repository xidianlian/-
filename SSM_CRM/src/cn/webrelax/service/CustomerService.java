package cn.webrelax.service;

import java.util.List;


import cn.webrelax.pojo.BaseDict;
import cn.webrelax.pojo.Customer;
import cn.webrelax.pojo.QueryVo;
public interface CustomerService {
	public List<BaseDict> findDictByTypeCode(String typeCode);
	public List<Customer> findCustomerByVo(QueryVo vo);
	public int findCustomerByVoCount(QueryVo vo);
	public Customer findCustomerById(int id);
	public void updateCustommer(Customer cus);
	public void deleteCustomerById(int id );
}	
