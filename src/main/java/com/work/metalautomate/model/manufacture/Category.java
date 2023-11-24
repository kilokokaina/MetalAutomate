package com.work.metalautomate.model.manufacture;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    private String categoryName;

    @OneToMany
    @JoinColumn(name = "category_id")
    private List<Detail> detailList;
}
