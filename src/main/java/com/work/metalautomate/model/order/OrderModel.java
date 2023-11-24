package com.work.metalautomate.model.order;

import com.work.metalautomate.model.manufacture.Detail;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Override
    public String toString() {
        return String.format("""
                \nDate: %s,
                Status: %s,
                Detail`s list: %s,
                Details: %s
                """, getCreationDate(), getOrderStatus(), getDetailList(), getOrderText());
    }
}
