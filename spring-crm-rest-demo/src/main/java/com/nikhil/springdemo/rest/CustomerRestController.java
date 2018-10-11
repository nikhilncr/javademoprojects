package com.nikhil.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.springdemo.entity.Customer;
import com.nikhil.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	// autowire the customer service
	@Autowired
	public CustomerService customerService;

	// add mapping for GET /customers
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}

	// add mapping to GET /customers/{customerId}

	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		Customer customer = customerService.getCustomer(customerId);

		if (customer == null) {
			throw new CustomerNotFoundException("Customer not found -" + customerId);
		}
		return customer;
	}

	// add post mapping /customers to add customer

	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer) {

		// force set id to 0, => equivalent to null will force DAO to do insert
		// operation
		customer.setId(0);
		customerService.saveCustomer(customer);

		return customer;
	}

	// add a put mapping /customer

	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		return customer;
	}

	// add mapping /customers/{customerId}

	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {

		if (customerService.getCustomer(customerId) == null) {
			throw new CustomerNotFoundException("Customer not found with id - " + customerId);
		}

		customerService.deleteCustomer(customerId);

		return "Deleted Customer id - " + customerId;
	}

}
