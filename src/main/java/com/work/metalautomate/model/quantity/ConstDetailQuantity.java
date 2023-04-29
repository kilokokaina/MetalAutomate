package com.work.metalautomate.model.quantity;

import com.work.metalautomate.model.Construction;
import com.work.metalautomate.model.Detail;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class ConstDetailQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Construction construction;

    @OneToOne
    private Detail detail;

    private int quantity;

    public ConstDetailQuantity(Construction construction, Detail detail, int quantity) {
        this.construction = construction;
        this.detail = detail;
        this.quantity = quantity;
    }
}
