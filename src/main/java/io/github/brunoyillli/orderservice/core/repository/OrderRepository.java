package io.github.brunoyillli.orderservice.core.repository;

import io.github.brunoyillli.orderservice.core.document.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
