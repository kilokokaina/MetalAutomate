package com.work.metalautomate.model.manufacture;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Detail implements Element {
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
    public String getName() {
        return getDetailName();
    }

    @Override
    public String getDescribe() {
        return getDetailDescribe();
    }

    @Override
    public String toString() {
        return String.format("Detail's name: %s, Detail's description: %s",
                getDetailName(),
                getDetailDescribe()
        );
    }
}
