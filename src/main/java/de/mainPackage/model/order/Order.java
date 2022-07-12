package de.mainPackage.model.order;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import de.mainPackage.model.product.Product;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_id;
	
	private int user_id;
	
	@ManyToMany
	@JoinTable(
			name = "product_enrolled",
			joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id")
			)
	private Set<Product> enrolledProducts = new HashSet<>();
	
	
	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public void enrollProduct(Product product) {
		enrolledProducts.add(product);
	}

}
