package de.mainPackage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.mainPackage.model.product.Product;
import de.mainPackage.repository.product.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private final ProductRepository productRepo;
	
	
	public ProductService(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}
	
	
	public Optional<Product> findProduct(int id){
		return productRepo.findById(id);		
	}
	
	public List<Product> findAllProducts(){
		return productRepo.findAll();
	}


	public Product createProduct(Product product) {		
		return productRepo.save(product);
	}

	
}
