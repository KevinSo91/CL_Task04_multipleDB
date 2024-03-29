package de.mainPackage.model.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int product_id;

    private String name;

    private double price;
    
    
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

	public Product(String name, double price) {
    	this.name = name;
    	this.price = price;
    }

	
}
