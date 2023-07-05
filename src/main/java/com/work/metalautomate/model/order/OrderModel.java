package com.work.metalautomate.model.order;

import com.work.metalautomate.model.manufacture.Construction;
import com.work.metalautomate.model.manufacture.Detail;
import com.work.metalautomate.model.manufacture.Item;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private Date creationDate = new Date();
    private String orderText;


    @ElementCollection(targetClass = OrderStatus.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "order_status", joinColumns = @JoinColumn(name = "order_id"))
    @Enumerated(EnumType.STRING)
    private Set<OrderStatus> orderStatus;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "order_const",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "const_id") }
    )
    private List<Construction> constList;
}
