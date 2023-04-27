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

    public Detail(String detailName, String detailDescribe) {
        this.detailName = detailName;
        this.detailDescribe = detailDescribe;
    }

    @Override
    public String toString() {
        return String.format("Detail's name: %s, Detail's description: %s",
                getDetailName(),
                getDetailDescribe()
        );
    }
}
