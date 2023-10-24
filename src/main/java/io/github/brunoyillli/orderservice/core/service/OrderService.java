package io.github.brunoyillli.orderservice.core.service;

import io.github.brunoyillli.orderservice.core.document.Event;
import io.github.brunoyillli.orderservice.core.document.Order;
import io.github.brunoyillli.orderservice.core.dto.OrderRequest;
import io.github.brunoyillli.orderservice.core.producer.SagaProducer;
import io.github.brunoyillli.orderservice.core.repository.OrderRepository;
import io.github.brunoyillli.orderservice.core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {

    private static final String TRANSACTION_ID_PATTER = "%s_%s";

    private final OrderRepository repository;
    private final EventService eventService;
    private final SagaProducer producer;
    private final JsonUtil jsonUtil;

    public Order createOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .products(orderRequest.getProducts())
                .createdAt(LocalDateTime.now())
                .transactionId(
                        String.format(TRANSACTION_ID_PATTER, Instant.now().toEpochMilli(), UUID.randomUUID())
                )
                .build();
        repository.save(order);
        Event event = createPayload(order);
        producer.sendEvent(jsonUtil.toJson(event));
        return order;
    }

    private Event createPayload(Order order) {
        Event event = Event.builder()
                .orderId(order.getId())
                .transactionId(order.getTransactionId())
                .payload(order)
                .createdAt(LocalDateTime.now())
                .build();
        eventService.save(event);
        return event;
    }
}
