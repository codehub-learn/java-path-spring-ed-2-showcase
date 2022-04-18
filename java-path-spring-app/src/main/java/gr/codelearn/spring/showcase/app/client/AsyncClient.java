package gr.codelearn.spring.showcase.app.client;

import gr.codelearn.spring.showcase.app.domain.Customer;
import gr.codelearn.spring.showcase.app.domain.Order;
import gr.codelearn.spring.showcase.app.domain.Product;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AsyncClient {
	CompletableFuture<List<Order>> retrieveOrders() throws InterruptedException;

	CompletableFuture<List<Customer>> retrieveCustomers() throws InterruptedException;

	CompletableFuture<List<Product>> retrieveProducts() throws InterruptedException;
}
