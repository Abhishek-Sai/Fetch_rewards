package com.fetchproject.fetch_project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fetchproject.fetch_project.model.Item;
import com.fetchproject.fetch_project.model.Receipt;
import com.fetchproject.fetch_project.service.ReceiptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.get;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReceiptController.class)
public class ReceiptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ReceiptService receiptService;

    @Test
    void testResponse() throws Exception {
        Receipt receipt = new Receipt();
        receipt.setRetailer("M&M Corner Market");
        receipt.setTotal(9.00);
        receipt.setPurchaseDate("2022-03-20");
        receipt.setPurchaseTime("14:33");
        receipt.setItems(Arrays.asList(
                new Item("Gatorade", 2.25),
                new Item("Gatorade", 2.25),
                new Item("Gatorade", 2.25),
                new Item("Gatorade", 2.25)
        ));

        mockMvc.perform(MockMvcRequestBuilders.post("/receipts/process")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(receipt)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    void testGetPointsByInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/receipts/invalid-id/points"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.points").value(0));
    }
}
