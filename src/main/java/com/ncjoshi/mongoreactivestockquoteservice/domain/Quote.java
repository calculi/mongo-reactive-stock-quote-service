package com.ncjoshi.mongoreactivestockquoteservice.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Created by ncj on 04 Oct, 2020.
 */
@Data
@Document
public class Quote {

    @Id
    private String id;
    private String ticker;
    private BigDecimal price;
    private Instant instant;
}
