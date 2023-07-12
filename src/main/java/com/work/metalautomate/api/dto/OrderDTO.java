package com.work.metalautomate.api.dto;

import com.work.metalautomate.model.manufacture.Detail;
import com.work.metalautomate.model.manufacture.Item;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class OrderDTO {
    private Detail[] detailList;
    private Item[] itemList;
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
