package com.agro.agro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {
    private String name;
    private Long productId;
    private String type;
    @Lob
    private byte[] picByte;
}
