package cn.webrelax.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.utils.Page;
import cn.webrelax.pojo.BaseDict;
import cn.webrelax.pojo.Customer;
import cn.webrelax.pojo.QueryVo;
import cn.webrelax.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired 
	private CustomerService customerService;//注入service
	
	//一般在企业开发，不直接写001这种码来作为参数传入
	//可以新建一个类，把这些参数作为常量
	//也可以写一个资源文件
	@Value("${customer.dict.source}")
	private String source;//客户来源编码
	
	@Value("${customer.dict.industry}")
	private String industy;//客户行业编码
	
	@Value("${customer.dict.level}")
	private String level;//客户级别编码
	
	@RequestMapping("/list")
	public String list(QueryVo queryVo, Model model) {
		//根据客户信息来源002查
		List<BaseDict> listSource = customerService.findDictByTypeCode(source);
		//根据客户行业来源001查
		List<BaseDict> listIndusty = customerService.findDictByTypeCode(industy);
		//根据客户行业来源006查
		List<BaseDict> listLevel = customerService.findDictByTypeCode(level);
		
		//设置查询的信息
		if(queryVo.getPage()==null) {
			queryVo.setPage(1);
		}
		//设置第几条数据开始查（分页）
		queryVo.setStart((queryVo.getPage()-1)*queryVo.getSize());
		
		//显示查询出的列表
		List<Customer> rows = customerService.findCustomerByVo(queryVo);
		int count = customerService.findCustomerByVoCount(queryVo);
		
		Page<Customer> page=new Page<Customer>();
		page.setPage(queryVo.getPage());//当前page
		page.setSize(queryVo.getSize());//每页显示数据条数
		page.setTotal(count);//数据总数
		page.setRows(rows);//数据
		model.addAttribute("page", page);
		
		//查询的option下拉列表
		model.addAttribute("fromType",listSource );
		model.addAttribute("industryType",listIndusty );
		model.addAttribute("levelType",listLevel );
		//回显的数据
		model.addAttribute("custName", queryVo.getCustName());
		model.addAttribute("custSource", queryVo.getCustSource());
		model.addAttribute("custIndustry", queryVo.getCustIndustry());
		model.addAttribute("custLevel", queryVo.getCustLevel());
		return "customer";
	}
	
	/**
	 * 当点击修改按钮时，通过Ajax获取信息
	 * @ResponseBody：
	 * 	     注解表示该方法的返回的结果直接写入 HTTP 响应正文（ResponseBody）中，
	 * 一般在异步获取数据时使用。因为，使用 @RequestMapping，返回值通常解析为跳转路径，
	 * 加上 @Responsebody 后返回结果不会被解析为跳转路径，而是直接写入HTTP 响应正文中
	 * 
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Customer edit(int id) {
		Customer cus = customerService.findCustomerById(id);
		return cus;
	}
	
	@RequestMapping("/update")
	public String update(Customer cus) {
		customerService.updateCustommer(cus);
		return  "customer";//这里返回给ajax,返回页面"customer",它会把整个页面html代码看作字符串返回给它，然后reload
	}
	@RequestMapping("/delete")
	public String delete(int id) {
		customerService.deleteCustomerById(id);
		return  "customer";//这里返回给ajax,返回页面"customer",它会把整个页面html代码看作字符串返回给它，然后reload
	}
}
