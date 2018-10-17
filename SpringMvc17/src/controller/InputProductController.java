package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 通过 配置文件web.xml配置Servlet 
 *
 */
public class InputProductController implements Controller{
	private static final Log logger = LogFactory.getLog(InputProductController.class);
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.info("InputProductController called!!!!!!!!!!!!!!!!!!!!!!!!!");
		
		// /WEB-INF/jsp/ProductForm.jsp 通过配置view Resource简化
		return new ModelAndView("ProductForm");
	}

}
