package demo.controller;

import demo.model.ExchangeValue;
import demo.model.endpoint.CurrencyConversionResponse;
import demo.service.ExchangeValueService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/currency-exchanges")
public class ExchangeValueController {

    @Autowired
    private ExchangeValueService service;

    @GetMapping("/status")
    public String status() {
        return "Demo Application Started";
    }

    @GetMapping("/from/{from}/to/{to}")
    public Single<ResponseEntity<ExchangeValue>> getExchangeValue
            (@PathVariable String from, @PathVariable String to) {

        return service.getExchangeValue(from, to)
                .subscribeOn(Schedulers.io())
                .map(exchangeValue -> ResponseEntity.ok(exchangeValue));
    }

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public Single<ResponseEntity<CurrencyConversionResponse>> convertCurrency(@PathVariable String from, @PathVariable String to,
                                                                              @PathVariable BigDecimal quantity) {
        return service.convertCurrency(from, to, quantity)
                .subscribeOn(Schedulers.io())
                .map(currencyConversionResponse -> ResponseEntity.ok(currencyConversionResponse));
    }

    @GetMapping
    public Single<ResponseEntity<List<ExchangeValue>>> getAllExchangesValues() {
        return service.getAllExchangesValues()
                .subscribeOn(Schedulers.io())
                .map(exchangeValues -> ResponseEntity.ok(exchangeValues));
    }

    @GetMapping("/{id}")
    public Single<ResponseEntity<ExchangeValue>> getExchangeValueById(@PathVariable("id") long id) {
        return service.getExchangeValueById(id)
                .subscribeOn(Schedulers.io())
                .map(exchangeValue -> ResponseEntity.ok(exchangeValue));
    }

    @PostMapping
    public Single<ResponseEntity<ExchangeValue>> createExchangeValue(@RequestBody ExchangeValue exchangeValue) {
        return service.createExchangeValue(exchangeValue).subscribeOn(Schedulers.io()).map(
                _exchangeValue -> ResponseEntity.created(URI.create("/currency-exchanges/"))
                        .body(_exchangeValue));
    }

    @PutMapping("/{id}")
    public Single<ResponseEntity<ExchangeValue>> updateExchangeValue(@PathVariable("id") long id, @RequestBody ExchangeValue exchangeValue) {
        return service.updateExchangeValue(id, exchangeValue)
                .subscribeOn(Schedulers.io())
                .toSingle(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(null));
    }

    @DeleteMapping("/{id}")
    public Single<ResponseEntity<ExchangeValue>> deleteExchangeValue(@PathVariable("id") long id) {
        return service.deleteExchangeValue(id)
                .subscribeOn(Schedulers.io())
                .toSingle(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(null));
    }

}
