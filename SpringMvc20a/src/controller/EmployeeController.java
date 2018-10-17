package controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Employee;
@Controller
public class EmployeeController {
	
	private static final Log logger=LogFactory.getLog(ProductController.class); 
			
	@RequestMapping(value="employee_input")
	public String inputEmployee(Model model) {
		model.addAttribute(new Employee());
		return "EmployeeForm";
	}
	@RequestMapping(value="employee_save")
	public String saveEmlpoyee(@ModelAttribute Employee employee,BindingResult bindingResult,Model model) {
		//如果转换有错或其他错误
		if(bindingResult.hasErrors()) {
			//获取与字段关联的第一个错误(如果有的话)
			FieldError fieldError = bindingResult.getFieldError();
			
			logger.info("code:"+fieldError.getCode()+", field:"+fieldError.getField());
			return "EmployeeForm";
		}
		//做保存的操作
		
		model.addAttribute("employee",employee);
		
		return "EmployeeDetails";
	}
}
