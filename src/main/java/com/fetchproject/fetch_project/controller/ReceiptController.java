package com.fetchproject.fetch_project.controller;

import com.fetchproject.fetch_project.model.PointsResponse;
import com.fetchproject.fetch_project.model.Receipt;
import com.fetchproject.fetch_project.model.ReceiptProcessResponse;
import com.fetchproject.fetch_project.service.ReceiptService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
public class ReceiptController {

    private final HashMap<String, Receipt> receiptStore = new HashMap<>();

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/receipts/process")
    public ResponseEntity<?> processReceipt(@Valid @RequestBody Receipt receipt, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The receipt is invalid. "
            + errors.getFieldErrors().getLast().getDefaultMessage());
        }
        String id = UUID.randomUUID().toString();
        receiptStore.put(id, receipt);
        receipt.setId(id);
        receiptStore.put(id, receiptService.processReceipt(receiptStore.get(id)));
        return ResponseEntity.ok(new ReceiptProcessResponse(id));
    }

    @GetMapping(value = "/receipts/{id}/points")
    public ResponseEntity<?> getPointsById(@PathVariable String id) {
        Receipt receipt = receiptStore.get(id);
        if (receipt == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No receipt found for the id " + id);
        }
        return ResponseEntity.ok(new PointsResponse(receiptStore.get(id).getPoints()));
    }
}
