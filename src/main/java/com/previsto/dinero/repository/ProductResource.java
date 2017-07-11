package com.previsto.dinero.repository;

import com.previsto.dinero.model.Product;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.client.RestTemplate;

public class ProductResource extends Resource<Product>{

    private static final String FIELDS = "ProductGuid,Name,Quantity,Unit,AccountNumber,BaseAmountValue,BaseAmountValueInclVat,TotalAmount,TotalAmountInclVat,CreatedAt,UpdatedAt,DeletedAt,ProductNumber";


    public ProductResource(RestTemplate restTemplate, String serviceUrl) {
        super(Product.class, "products", restTemplate, serviceUrl, FIELDS);
    }
    
}