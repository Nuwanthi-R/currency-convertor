package com.convertor.currency_convertor.repository;

import com.convertor.currency_convertor.entity.Currency;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends MongoRepository<Currency, String> {
  Currency findByBase(String base);

}
