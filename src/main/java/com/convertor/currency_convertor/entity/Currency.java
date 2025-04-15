package com.convertor.currency_convertor.entity;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "currency")
public class Currency {

    @Indexed(unique = true, name = "currency_ref_id_idx", sparse = true)
    private String refId;
    private String base;
    private Date lastUpdated;
    private Map<String, Double> rates;

    public Currency(String base, Map<String, Double> rates) {
        this.base = base;
        this.rates = rates;
    }
}
