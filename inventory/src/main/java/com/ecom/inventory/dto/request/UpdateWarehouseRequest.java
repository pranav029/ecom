package com.ecom.inventory.dto.request;

public record UpdateWarehouseRequest(
        String warehouseId,
        String name,
        String location
) {
}
