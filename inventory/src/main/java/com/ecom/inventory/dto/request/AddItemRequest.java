package com.ecom.inventory.dto.request;

import java.util.List;

public record AddItemRequest(
        String productId,
        String category,
        List<String> attributes,
        int quantity,
        String warehouseId
) {
}
