package com.hf.jvmtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hf.jvmtest.dto.StatResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void saveEvents() throws Exception {
        String payload = "1708185404014,0.0004209936,2141960742\n" +
                "1708185390019,0.0419045016,2098651724\n" +
                "1708185453032,0.0054850322,1491439824\n" +
                "1708185408018,0.5199602246,1584048243\n" +
                "1708185401017,0.0963968411,1181759867\n" +
                "1708185450036,0.5691103339,1573478611\n" +
                "1708185437033,0.6585960984,1087687738\n" +
                "1708185380021,0.8198835850,1256206921\n" +
                "1708185436018,0.0113903880,2102054562\n" +
                "1708185399013,0.5191285014,1508166329\n" +
                "1708185446014,0.5046156049,1375217561\n" +
                "1708185389041,0.0799897313,1384487181";

        mockMvc.perform(post("/event")
                        .contentType("text/csv")
                        .content(payload))
                .andExpect(status().is(202));
    }

    @Test
    void getEventStats() throws Exception {
        String payload = "1708185460037,0.0705600008,1917837855\n" +
                "1708185457034,0.4580504894,1569910525\n" +
                "1708185457010,0.0053895931,1178465455\n" +
                "1708185400020,0.0269391239,2103336473\n" +
                "1708185399039,0.0038487113,1231147508\n" +
                "1708185399028,0.0190757401,1864590977\n" +
                "1708185399015,0.0045809909,1133979433";

        mockMvc.perform(post("/event")
                        .contentType("text/csv")
                        .content(payload))
                .andExpect(status().is(202));

        mockMvc.perform(get("/stats")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(result -> {
                    String contentAsString = result.getResponse().getContentAsString();

                    ObjectMapper objectMapper = new ObjectMapper();
                    StatResponse resp = objectMapper.readValue(contentAsString, StatResponse.class);

                    assertTrue(contentAsString.contains("count"));
                    assertTrue(contentAsString.contains("sumX"));
                    assertTrue(contentAsString.contains("averageX"));
                    assertTrue(contentAsString.contains("sumY"));
                    assertTrue(contentAsString.contains("averageY"));

                    assertEquals(4, resp.getCount());
                    assertEquals(0.5609392072, resp.getSumX());
                    assertEquals(0.1402348018, resp.getAverageX());
                    assertEquals(6769550308L, resp.getSumY());
                    assertEquals(1.692387577E9, resp.getAverageY());
                });

    }

}
