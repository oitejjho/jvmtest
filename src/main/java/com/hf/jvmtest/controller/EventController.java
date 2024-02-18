package com.hf.jvmtest.controller;


import com.hf.jvmtest.dto.EventRequest;
import com.hf.jvmtest.dto.StatResponse;
import com.hf.jvmtest.service.EventService;
import com.hf.jvmtest.util.EventRequestMapper;
import com.hf.jvmtest.util.EventRequestValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "/event", method = RequestMethod.POST, consumes = "text/csv")
    public ResponseEntity processEvents(@RequestBody(required = false) final String payload) {
        List<EventRequest> events = EventRequestMapper.map(payload);
//        EventRequestValidator.validate(events);
        eventService.saveEvents(events);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/stats")
    public ResponseEntity<StatResponse> getEventStats() {
        StatResponse stats = eventService.getStatistics();
        return ResponseEntity.ok(stats);
    }
}
