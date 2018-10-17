package service;

import domain.Product;

public interface ProductService {
	Product add(Product product);
	Product get(Long id);
}
