package service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import domain.Product;

@Service
public class ProductServiceImpl implements ProductService {
	private Map<Long,Product>products=new HashMap<Long,Product>();
	private AtomicLong generator=new AtomicLong();
	
	public ProductServiceImpl() {
		Product product=new Product();
		product.setName("java编程思想");
		product.setDescription("好书");
		product.setPrice(12F);
		add(product);
	}
	
	@Override
	public Product add(Product product) {
		Long newId=generator.incrementAndGet();
		product.setId(newId);
		products.put(newId,product);
		
		return product;
	}

	@Override
	public Product get(Long id) {
		return products.get(id);
	}

}
