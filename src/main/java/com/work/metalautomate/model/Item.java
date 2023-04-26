package com.work.metalautomate.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String itemName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "item_detail",
            joinColumns = { @JoinColumn(name = "item_id") },
            inverseJoinColumns = { @JoinColumn(name = "detail_id") }
    )
    private List<Detail> detailList;

    public Item(String itemName, List<Detail> detailList) {
        this.itemName = itemName;
        this.detailList = detailList;
    }

    @Override
    public String toString() {
        return String.format("""
                Item name: %s;
                Detail's list: {
                %s
                };
                """, getItemName(),
                getDetailList().toString()
        );
    }
}
