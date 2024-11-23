package com.fetchproject.fetch_project.controller;

import com.fetchproject.fetch_project.model.PointsResponse;
import com.fetchproject.fetch_project.model.Receipt;
import com.fetchproject.fetch_project.model.ReceiptProcessResponse;
import com.fetchproject.fetch_project.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
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
        System.out.println("The id is " + id);
        receiptStore.put(id, receipt);
        receiptStore.put(id, receiptService.processReceipt(receiptStore, id));
        return ResponseEntity.ok(new ReceiptProcessResponse(id));
    }

    @GetMapping("/receipts/{id}/points")
    public ResponseEntity<PointsResponse> getPointsById(@PathVariable String id) {
       return ResponseEntity.ok(new PointsResponse(receiptStore.get(id).getPoints()));
    }

    @GetMapping("/test")
    public String test() {
        return "Testing";
    }

}
