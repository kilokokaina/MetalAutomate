package com.work.metalautomate.model.order;

import com.work.metalautomate.model.manufacture.Detail;
import com.work.metalautomate.model.manufacture.Element;
import com.work.metalautomate.model.manufacture.Item;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @Lob
    private String orderText;
    private Date creationDate = new Date();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "order_detail",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "detail_id") }
    )
    private List<Detail> detailList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "order_item",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "item_id") }
    )
    private List<Item> itemList;

    public List<Element> getElementList() {
        List<Element> elementList = new ArrayList<>();

        if (getDetailList() != null) elementList.addAll(getDetailList());
        if (getItemList() != null) elementList.addAll(getItemList());

        return elementList;
    }

    @Override
    public String toString() {
        return String.format("""
                \nDate: %s,
                Status: %s,
                Detail`s list: %s,
                Item`s list: %s,
                Details: %s
                """, getCreationDate(), getOrderStatus(), getDetailList(), getItemList(), getOrderText());
    }
}
