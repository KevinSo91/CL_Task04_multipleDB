package de.mainPackage.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.mainPackage.model.product.Product;
import de.mainPackage.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/{product_id}")
	public Optional<Product> getProduct(@PathVariable int product_id){
		return this.productService.findProduct(product_id);
	}
	
	
	@GetMapping("/all")
	public List<Product> getAllProducts(){
		return this.productService.findAllProducts();
	}
	
	
}