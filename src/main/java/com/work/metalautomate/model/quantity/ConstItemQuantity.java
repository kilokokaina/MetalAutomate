package com.work.metalautomate.model.quantity;

import com.work.metalautomate.model.Construction;
import com.work.metalautomate.model.Item;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class ConstItemQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Construction construction;

    @OneToOne
    private Item item;

    private int quantity;

    public ConstItemQuantity(Construction construction, Item item, int quantity) {
        this.construction = construction;
        this.item = item;
        this.quantity = quantity;
    }
}
