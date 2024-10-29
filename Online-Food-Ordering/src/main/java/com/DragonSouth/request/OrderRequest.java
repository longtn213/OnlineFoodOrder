package com.DragonSouth.request;

import com.DragonSouth.model.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private long restaurantId;
    private Address deliveryAddress;

}
