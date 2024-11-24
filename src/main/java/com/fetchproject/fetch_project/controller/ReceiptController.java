package com.fetchproject.fetch_project.controller;

import com.fetchproject.fetch_project.model.PointsResponse;
import com.fetchproject.fetch_project.model.Receipt;
import com.fetchproject.fetch_project.model.ReceiptProcessResponse;
import com.fetchproject.fetch_project.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
public class ReceiptController {

    private final HashMap<String, Receipt> receiptStore = new HashMap<>();

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/receipts/process")
    public ResponseEntity<ReceiptProcessResponse> processReceipt(@RequestBody Receipt receipt) {
        String id = UUID.randomUUID().toString();
        receiptStore.put(id, receipt);
        receipt.setId(id);
        receiptStore.put(id, receiptService.processReceipt(receiptStore.get(id)));
        return ResponseEntity.ok(new ReceiptProcessResponse(id));
    }

    @GetMapping(value = "/receipts/{id}/points")
    public ResponseEntity<PointsResponse> getPointsById(@PathVariable String id) {
        Receipt receipt = receiptStore.get(id);
        if(receipt == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PointsResponse(0));
        }
        return ResponseEntity.ok(new PointsResponse(receiptStore.get(id).getPoints()));
    }
}
