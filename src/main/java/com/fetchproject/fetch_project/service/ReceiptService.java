package com.fetchproject.fetch_project.service;

import com.fetchproject.fetch_project.model.Item;
import com.fetchproject.fetch_project.model.Receipt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {

    public Receipt processReceipt(Receipt receipt) {
        int points = 0;

        points += calculatePointsForRetailer(receipt);
        points += calculatePointsForTotalAmount(receipt);
        points += calculatePointsForNumberOfItems(receipt);
        points += calculatePointsForLengthOfItemDesc(receipt);
        points += calculatePointsForPurchaseDate(receipt);
        points += calculatePointsForPurchaseTime(receipt);

        receipt.setPoints(points);

        return receipt;
    }

    private int calculatePointsForRetailer(Receipt receipt) {
        String retailerName = receipt.getRetailer();
        if(retailerName != null)
            return retailerName.replaceAll("[^a-zA-Z0-9]", "").length();
        return 0;
    }

    private int calculatePointsForTotalAmount(Receipt receipt) {
        double total = receipt.getTotal();
        int points = 0;
        if(total > 0.0){
            if(total == Math.floor(total)) {
                points += 50;
            }
            if(total % 0.25 == 0) {
                points += 25;
            }
        }
        return points;
    }

    private int calculatePointsForNumberOfItems(Receipt receipt) {
        List<Item> itemsList = receipt.getItems();
        if(itemsList != null) {
            int length = (int) Math.floor((double) itemsList.size() / 2);
            return length * 5;
        }
        return 0;
    }

    private int calculatePointsForLengthOfItemDesc(Receipt receipt) {
        List<Item> itemsList = receipt.getItems();
        int points = 0;
        if(itemsList != null) {
            for(Item item: itemsList) {
                String shortDescription = item.getShortDescription().trim();
                if(shortDescription.length() % 3 == 0) {
                    double price = item.getPrice();
                    points += (int) Math.ceil(price * 0.2);
                }
            }
        }
        return points;
    }

    private int calculatePointsForPurchaseDate(Receipt receipt) {
        String date = receipt.getPurchaseDate();
        if(date != null){
            List<String> dateList = List.of(date.split("-"));
            /* Ideally should log as an error in date format.
            For the purpose of this assignment returning as zero. */
            if(dateList.size() < 3)
                return 0;
            int day = Integer.parseInt(dateList.get(2));
            if(day % 2 != 0) return 6;
        }
        return 0;
    }

    private int calculatePointsForPurchaseTime(Receipt receipt) {
        String purchaseTime = receipt.getPurchaseTime();
        if(purchaseTime != null) {
            List<String> purchaseTimeList = List.of(purchaseTime.split(":"));
            int purchaseHour = Integer.parseInt(purchaseTimeList.getFirst());
            if(purchaseHour >= 14 && purchaseHour <= 16) {
                return 10;
            }
        }
        return 0;
    }
}
