package de.mainPackage.model.product;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import de.mainPackage.model.order.Order;


@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;

    private String name;

    private double price;
    
    @ManyToMany(mappedBy = "enrolledProducts")
    private Set<Order> orders = new HashSet<>();
        
    
    
    public Product(String name, double price) {
    	this.name = name;
    	this.price = price;
    }
    
    
    public Product() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return product_id;
	}

	public void setId(int id) {
		this.product_id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

//	public void enrollOrder(Order order) {
//		this.orders.add(order);
//	}

	
}
