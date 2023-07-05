package com.work.metalautomate.model.quantity;

import com.work.metalautomate.model.manufacture.Detail;
import com.work.metalautomate.model.manufacture.Item;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class ItemDetailQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Item item;

    @OneToOne
    private Detail detail;

    private int quantity;

    public ItemDetailQuantity(Item item, Detail detail, int quantity) {
        this.item = item;
        this.detail = detail;
        this.quantity = quantity;
    }
}
