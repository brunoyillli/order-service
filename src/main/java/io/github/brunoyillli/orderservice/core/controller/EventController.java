package io.github.brunoyillli.orderservice.core.controller;

import io.github.brunoyillli.orderservice.core.document.Event;
import io.github.brunoyillli.orderservice.core.dto.EventFilters;
import io.github.brunoyillli.orderservice.core.service.EventService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/event")
public class EventController {

    private final EventService service;

    @GetMapping
    public Event findByFilters(EventFilters filters) {
        return service.findByFilters(filters);
    }

    @GetMapping("all")
    public List<Event> findAll() {
        return service.findAll();
    }

}
