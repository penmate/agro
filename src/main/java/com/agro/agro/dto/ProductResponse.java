package com.agro.agro.dto;

import com.agro.agro.model.AmountType;
import com.agro.agro.model.Availability;
import com.agro.agro.model.ProductCondition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String productName;
    private String description;
    private Long price;
    private Long discountPrice;
    private String location;
    private ProductCondition productCondition;
    private Long amount;
    private AmountType amountType;
    private Availability availability;
    private String userName;
    private Integer commentCount;
    private String duration;
}

