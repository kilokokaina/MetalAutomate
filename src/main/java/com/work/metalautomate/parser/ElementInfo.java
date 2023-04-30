package com.work.metalautomate.parser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElementInfo {
    private String elementName;
    private String elementDescribe;
    private int elementQuantity;
}
