package com.fetchproject.fetch_project.service;

import com.fetchproject.fetch_project.model.Item;
import com.fetchproject.fetch_project.model.Receipt;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportServiceTest {
    private final ReceiptService receiptService = new ReceiptService();

    @Test
    void testCalculatePointsForRetailer() {
        Receipt receipt = new Receipt();
        receipt.setRetailer("BestBuy-123");
        Receipt processed = receiptService.processReceipt(receipt);
        assertEquals(10, processed.getPoints());
    }

    @Test
    void testCalculatePointsForTotalAmountNoPoints() {
        Receipt receipt = new Receipt();
        receipt.setTotal(25.34);
        Receipt processed = receiptService.processReceipt(receipt);
        assertEquals(0, receipt.getPoints());
    }

    @Test
    void testCalculatePointsForTotalAmountWithPoints() {
        Receipt receipt = new Receipt();
        receipt.setTotal(9.00);
        Receipt processed = receiptService.processReceipt(receipt);
        assertEquals(75, receipt.getPoints());
    }

    @Test
    void testCalculatePointForNumberOfItemsNoPrice() {
        Receipt receipt = new Receipt();
        receipt.setItems(Arrays.asList(
                new Item("Item 1", 0.0),
                new Item("Item 2", 0.0),
                new Item ("Item 3", 0.0)
        ));
        Receipt processed = receiptService.processReceipt(receipt);
        assertEquals(5, processed.getPoints());
    }

    @Test
    void testCalculatePointForLengthOfItemsDesc() {
        Receipt receipt = new Receipt();
        receipt.setItems(Arrays.asList(
                new Item("Item 1", 10.0),
                new Item("Item 2", 10.0),
                new Item ("Item 3", 10.0)
        ));
        Receipt processed = receiptService.processReceipt(receipt);
        assertEquals(11, processed.getPoints());
    }

    @Test
    void testCalculatePointsForPurchaseDateOddDay() {
        Receipt receipt = new Receipt();
        receipt.setPurchaseDate("2024-11-23");
        Receipt processed = receiptService.processReceipt(receipt);
        assertEquals(6, processed.getPoints());
    }

    @Test
    void testCalculatePointsForPurchaseDateEvenDay() {
        Receipt receipt = new Receipt();
        receipt.setPurchaseDate("2024-11-08");
        Receipt processed = receiptService.processReceipt(receipt);
        assertEquals(0, processed.getPoints());
    }

    @Test
    void testCalculatePointsForPurchaseTimeBetween2And4() {
        Receipt receipt = new Receipt();
        receipt.setPurchaseTime("15:00"); // Between 2 PM and 4 PM
        Receipt processed = receiptService.processReceipt(receipt);
        assertEquals(10, processed.getPoints());
    }

    @Test
    void testCalculatePointsForPurchaseTimeNotBetween2And4() {
        Receipt receipt = new Receipt();
        receipt.setPurchaseTime("9:00"); // Between 2 PM and 4 PM
        Receipt processed = receiptService.processReceipt(receipt);
        assertEquals(0, processed.getPoints());
    }

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
