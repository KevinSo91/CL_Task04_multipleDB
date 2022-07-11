package de.mainPackage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.mainPackage.model.order.Order;
import de.mainPackage.repository.order.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	
//	public OrderService(OrderRepository orderRepo) {
//		this.orderRepo = orderRepo;
//	}
	
	
	public Optional<Order> getOrder(int id) {
		return orderRepo.findById(id);
	}
	
	public List<Order> getAllOrders(){
		return orderRepo.findAll();
	}
	
	public Order createOrder(Order order) {
		return orderRepo.save(order);
	}
	
}
