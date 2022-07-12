package de.mainPackage.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.mainPackage.model.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
