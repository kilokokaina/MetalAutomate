package com.work.metalautomate.api.dto;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class OrderDTO {
    private String[] detailList;
    private String[] itemList;
    private String orderDetails;

    @Override
    public String toString() {
        return String.format("""
                \ndetailList: %s
                itemList: %s
                orderDetails: %s
                """, Arrays.toString(getDetailList()), Arrays.toString(getItemList()), getOrderDetails());
    }
}
