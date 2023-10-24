package io.github.brunoyillli.orderservice.core.consumer;

import io.github.brunoyillli.orderservice.core.document.Event;
import io.github.brunoyillli.orderservice.core.service.EventService;
import io.github.brunoyillli.orderservice.core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class EventConsumer {

    private final JsonUtil jsonUtil;
    private final EventService service;
    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.notify-ending}"
    )
    public void consumerNotifyEndingEvent(String payload){
        log.info("Receiving ending notification event {} from notify-ending topic", payload);
        Event event = jsonUtil.toEvent(payload);
        service.notifyEnding(event);

    }
}
