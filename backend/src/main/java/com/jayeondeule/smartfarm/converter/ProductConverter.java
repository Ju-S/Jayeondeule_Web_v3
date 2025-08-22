package com.jayeondeule.smartfarm.converter;

import com.jayeondeule.smartfarm.enums.farm.Product;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true) // Entity에서 일일이 지정 안 해도 적용
public class ProductConverter implements AttributeConverter<Product, Integer> {

    // Enum -> DB 저장용
    @Override
    public Integer convertToDatabaseColumn(Product product) {
        if (product == null) return null;
        return product.getPrdt();
    }

    // DB -> Enum
    @Override
    public Product convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;
        return Product.fromPrdt(dbData);
    }
}
