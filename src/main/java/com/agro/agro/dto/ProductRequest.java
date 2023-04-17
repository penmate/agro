package com.agro.agro.dto;

import com.agro.agro.model.AmountType;
import com.agro.agro.model.Availability;
import com.agro.agro.model.ProductCondition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private Long productId;
    @NotBlank(message = "Product Name cannot be empty or Null")
    private String name;
    private String description;
    private Long price;
    private Long discountPrice;
    private String location;
    private ProductCondition productCondition;
    private Long amount;
    private AmountType amountType;
    private Availability availability;
}
