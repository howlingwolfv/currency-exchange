package demo.service;

import demo.model.ExchangeValue;
import demo.model.endpoint.CurrencyConversionResponse;
import demo.repository.ExchangeValueRepository;
import io.reactivex.Completable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeValueServiceImpl implements ExchangeValueService {

    @Autowired
    private ExchangeValueRepository repository;

    @Override
    public Single<ExchangeValue> getExchangeValue(String from, String to) {
        return Single.create(singleSubscriber -> {
            Optional<ExchangeValue> _exchangeValue =
                    repository.findByFromAndTo(from, to);
            if (!_exchangeValue.isPresent())
                singleSubscriber.onError(new EntityNotFoundException());
            else {
                ExchangeValue exchangeValue = _exchangeValue.get();
                singleSubscriber.onSuccess(exchangeValue);
            }
        });
    }

    @Override
    public Single<CurrencyConversionResponse> convertCurrency(String from, String to, BigDecimal quantity) {
        return Single.create(singleSubscriber -> {
            Optional<ExchangeValue> _exchangeValue =
                    repository.findByFromAndTo(from, to);
            if (!_exchangeValue.isPresent())
                singleSubscriber.onError(new EntityNotFoundException());
            else {
                ExchangeValue exchangeValue = _exchangeValue.get();
                singleSubscriber.onSuccess(new CurrencyConversionResponse(exchangeValue.getId(), from, to, exchangeValue.getQuote(), quantity,
                        quantity.multiply(exchangeValue.getQuote())));
            }
        });
    }

    @Override
    public Single<List<ExchangeValue>> getAllExchangesValues() {
        return Single.create(singleSubscriber -> {
            List<ExchangeValue> exchangeValues = repository.findAll();
            singleSubscriber.onSuccess(exchangeValues);
        });
    }

    @Override
    public Single<ExchangeValue> getExchangeValueById(long id) {
        return Single.create(singleSubscriber -> {
            Optional<ExchangeValue> _exchangeValue = repository.findById(id);
            if (!_exchangeValue.isPresent())
                singleSubscriber.onError(new EntityNotFoundException());
            else {
                ExchangeValue exchangeValue = _exchangeValue.get();
                singleSubscriber.onSuccess(exchangeValue);
            }
        });
    }

    @Override
    public Single<ExchangeValue> createExchangeValue(ExchangeValue exchangeValue) {
        return Single.create(singleSubscriber -> {
            ExchangeValue _exchangeValue = repository
                    .save(new ExchangeValue(exchangeValue.getFrom(), exchangeValue.getTo(),exchangeValue.getQuote()));
            singleSubscriber.onSuccess(_exchangeValue);
        });
    }

    @Override
    public Completable updateExchangeValue(long id, ExchangeValue exchangeValue) {
        return Completable.create(completableSubscriber -> {
            Optional<ExchangeValue> _exchangeValueData = repository.findById(id);
            if (!_exchangeValueData.isPresent())
                completableSubscriber.onError(new EntityNotFoundException());
            else {
                ExchangeValue _exchangeValue = _exchangeValueData.get();
                _exchangeValue.setFrom(exchangeValue.getFrom());
                _exchangeValue.setTo(exchangeValue.getTo());
                _exchangeValue.setQuote(exchangeValue.getQuote());
                repository.save(_exchangeValue);
                completableSubscriber.onComplete();
            }
        });
    }

    @Override
    public Completable deleteExchangeValue(long id) {
        return Completable.create(completableSubscriber -> {
            Optional<ExchangeValue> _exchangeValue = repository.findById(id);
            if (!_exchangeValue.isPresent())
                completableSubscriber.onError(new EntityNotFoundException());
            else {
                repository.delete(_exchangeValue.get());
                completableSubscriber.onComplete();
            }
        });
    }
}
