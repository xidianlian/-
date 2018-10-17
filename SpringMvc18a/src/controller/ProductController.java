package controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.Product;
import form.ProductForm;

@Controller
public class ProductController {
	private static final Log logger=LogFactory.getLog(ProductController.class);
	/**
	 * p.265
	 * @RequestMapping：映射一个请求和一种方法，用它注解的方法将成为一个请求处理的方法
	 * value属性：将uri映射到方法
	 * method:指示该方法仅处理哪些HTTP方法
	 */
	@RequestMapping(value="product_input",method=RequestMethod.GET)
	public String inputProduct() {
		logger.info("inputProduct called!!!!!!!!!!!!!!!!");
		return "ProductForm";
	}
	/**
	 * @param productForm
	 * @param model 无论是否会使用，SpringMVC都会在每一个请求处理方法被调用时创建一个Model实例
	 * 		  使用Model的主要目的是添加需要在试图中显示的属性
	 * @return
	 */
	@RequestMapping(value="product_save",method= {RequestMethod.POST,RequestMethod.PUT})
	public String saveProduct(ProductForm productForm,Model model) {
		logger.info("saveProduct called!!!!!!!!!!!!!!!!");
		//不需要创建ProductForm实例
		//创建Product
		Product product =new Product();
		product.setName(productForm.getName());
		product.setDescription(productForm.getDescription());
		
		product.setPrice(Float.parseFloat(productForm.getPrice()));
		
		model.addAttribute("product",product);
		return "ProductDetails";
				
	}
}
