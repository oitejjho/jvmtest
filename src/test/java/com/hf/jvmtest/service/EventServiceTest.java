package com.hf.jvmtest.service;

import com.hf.jvmtest.dto.EventRequest;
import com.hf.jvmtest.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService service;

    @Test
    void saveEvent() {
        when(eventRepository.insert(any())).thenReturn(true);
        EventRequest event = new EventRequest(System.currentTimeMillis() - 100, 1.0, 1L);
        service.saveEvents(new ArrayList<>(){{add(event);}});
        Mockito.verify(eventRepository,times(1)).insert(any());
    }
}
