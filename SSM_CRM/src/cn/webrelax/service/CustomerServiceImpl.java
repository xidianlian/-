package cn.webrelax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.webrelax.dao.CustomerMapper;
import cn.webrelax.dao.DictMapper;
import cn.webrelax.pojo.BaseDict;
import cn.webrelax.pojo.Customer;
import cn.webrelax.pojo.QueryVo;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private DictMapper dictMapper;
	@Autowired
	private CustomerMapper customerMapper;
	
	@Override
	public List<BaseDict> findDictByTypeCode(String typeCode) {
		return dictMapper.findDictByTypeCode(typeCode);
	}

	@Override
	public List<Customer> findCustomerByVo(QueryVo vo) {
		List<Customer> list = customerMapper.findCustomerByVo(vo);
		return list;
	}

	@Override
	public int findCustomerByVoCount(QueryVo vo) {
		int count = customerMapper.findCustomerByVoCount(vo);
		return count;
	}

	@Override
	public Customer findCustomerById(int id) {
		Customer cus = customerMapper.findCustomerById(id);
		return cus;
	}

	@Override
	public void updateCustommer(Customer cus) {
		// TODO Auto-generated method stub
		customerMapper.updateCustommer(cus);
	}

	@Override
	public void deleteCustomerById(int id) {
		customerMapper.deleteCustomerById(id);
		
	}

}
