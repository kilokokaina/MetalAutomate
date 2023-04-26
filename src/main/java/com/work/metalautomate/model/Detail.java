package com.work.metalautomate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String detailName;
    private String detailDescribe;

    private int detailQuantity;

    public Detail(String detailName, String detailDescribe, int detailQuantity) {
        this.detailName = detailName;
        this.detailDescribe = detailDescribe;
        this.detailQuantity = detailQuantity;
    }

    @Override
    public String toString() {
        return String.format("""
                Detail name: %s;
                Detail description: %s;
                Detail quantity: %d;
                """, getDetailName(),
                getDetailDescribe(),
                getDetailQuantity()
        );
    }
}
