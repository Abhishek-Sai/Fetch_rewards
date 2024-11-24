package com.fetchproject.fetch_project.service;

import com.fetchproject.fetch_project.model.Item;
import com.fetchproject.fetch_project.model.Receipt;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportServiceTest {
    private final ReceiptService receiptService = new ReceiptService();

    @Test
    void testProcessReceipt() {
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

        Receipt processed = receiptService.processReceipt(receipt);
        assertEquals(109, processed.getPoints());
    }
}
