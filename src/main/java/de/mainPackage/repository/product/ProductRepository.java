package de.mainPackage.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.mainPackage.model.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
