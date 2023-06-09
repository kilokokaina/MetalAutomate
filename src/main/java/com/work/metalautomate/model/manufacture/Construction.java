package com.work.metalautomate.model.manufacture;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Construction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String constName;
    private String constDescribe;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "const_detail",
            joinColumns = { @JoinColumn(name = "const_id") },
            inverseJoinColumns = { @JoinColumn(name = "detail_id") }
    )
    private List<Detail> detailList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "const_item",
            joinColumns = { @JoinColumn(name = "const_id") },
            inverseJoinColumns = { @JoinColumn(name = "item_id") }
    )
    private List<Item> itemList;

    public Construction(String constName, String constDescribe,
                        List<Detail> detailList, List<Item> itemList) {
        this.constName = constName;
        this.constDescribe = constDescribe;
        this.detailList = detailList;
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return String.format("Construction name: %s, Construction describe: %s, Item's list: { %s }, Detail's list: { %s }",
                getConstName(),
                getConstDescribe(),
                getItemList().toString(),
                getDetailList().toString()
        );
    }
}
