package io.github.brunoyillli.orderservice.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.brunoyillli.orderservice.core.document.Event;
import io.github.brunoyillli.orderservice.core.document.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public String toJson(Object object){
        try{
            return objectMapper.writeValueAsString(object);
        }catch (Exception exception){
            log.error(exception.getMessage());
            return "";
        }
    }


    public Event toEvent(String json){
        try{
            return objectMapper.readValue(json, Event.class);
        }catch (Exception exception){
            log.error(exception.getMessage());
            return null;
        }
    }
}
