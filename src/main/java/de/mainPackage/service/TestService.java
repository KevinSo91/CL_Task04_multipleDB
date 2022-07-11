package de.mainPackage.service;



import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.mainPackage.model.product.Product;
import de.mainPackage.model.user.User;
import de.mainPackage.repository.product.ProductRepository;
import de.mainPackage.repository.user.UserRepository;

@Service
public class TestService {
		
	@Autowired
    private UserRepository userRepository;

	@Autowired
    private ProductRepository productRepository;

    
	
    public List<User> getAllUsers() {
    	
    	return this.userRepository.findAll();
    }
    
    public List<Product> getAllProducts() {
    	
    	return this.productRepository.findAll();
    }
    
    
    @Test
    @Transactional("userTransactionManager")
    public void whenCreatingUser_thenCreated() {
        User user = new User();
        user.setName("John");
        user.setEmail("john@test.com");
        user.setAge(20);
        user = userRepository.save(user);

        assertNotNull(userRepository.findById(user.getId()));
    }

    @Test
    @Transactional("userTransactionManager")
    public void whenCreatingUsersWithSameEmail_thenRollback() {
        User user1 = new User();
        user1.setName("John");
        user1.setEmail("john@test.com");
        user1.setAge(20);
        user1 = userRepository.save(user1);
        assertNotNull(userRepository.findById(user1.getId()));

        User user2 = new User();
        user2.setName("Tom");
        user2.setEmail("john@test.com");
        user2.setAge(10);
        try {
            user2 = userRepository.save(user2);
        } catch (DataIntegrityViolationException e) {
        }

        assertNull(userRepository.findById(user2.getId()));
    }

    @Test
    @Transactional("productTransactionManager")
    public void whenCreatingProduct_thenCreated() {
        Product product = new Product();
        product.setName("Book");
        product.setId(2);
        product.setPrice(20);
        product = productRepository.save(product);

        assertNotNull(productRepository.findById(product.getId()));
    }

}
