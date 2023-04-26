package com.work.metalautomate.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Entity
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String itemName;
    private String itemDescribe;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "item_detail",
            joinColumns = { @JoinColumn(name = "item_id") },
            inverseJoinColumns = { @JoinColumn(name = "detail_id") }
    )
    private Map<Integer, Detail> detailList;

    public Item(String itemName, String itemDescribe, Map<Integer, Detail> detailList) {
        this.itemName = itemName;
        this.itemDescribe = itemDescribe;
        this.detailList = detailList;
    }

    @Override
    public String toString() {
        return String.format("""
                Item name: %s;
                Item describe: %s;
                Detail's list: {
                %s
                };
                """, getItemName(),
                getItemDescribe(),
                getDetailList().toString()
        );
    }
}
