package demo.service;

import demo.model.ExchangeValue;
import demo.model.endpoint.CurrencyConversionResponse;
import io.reactivex.Completable;
import io.reactivex.Single;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

public interface ExchangeValueService {

    Single<ExchangeValue> getExchangeValue(String from, String to);

    Single<CurrencyConversionResponse> convertCurrency(String from, String to, BigDecimal quantity);

    Single<List<ExchangeValue>> getAllExchangesValues();

    Single<ExchangeValue> getExchangeValueById(long id);

    Single<ExchangeValue> createExchangeValue(ExchangeValue exchangeValue);

    Completable updateExchangeValue(long id, ExchangeValue exchangeValue);

    Completable deleteExchangeValue(long id);

}
