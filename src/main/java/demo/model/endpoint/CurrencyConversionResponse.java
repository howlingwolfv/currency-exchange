package demo.model.endpoint;

import java.io.Serializable;
import java.math.BigDecimal;

public class CurrencyConversionResponse implements Serializable {
    private Long id;
    private String from;
    private String to;
    private BigDecimal quote;
    private BigDecimal quantity;
    private BigDecimal totalCalculatedAmount;

    public CurrencyConversionResponse() {

    }

    public CurrencyConversionResponse(Long id, String from, String to, BigDecimal quote, BigDecimal quantity,
                                      BigDecimal totalCalculatedAmount) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.quote = quote;
        this.quantity = quantity;
        this.totalCalculatedAmount = totalCalculatedAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getQuote() {
        return quote;
    }

    public void setQuote(BigDecimal quote) {
        this.quote = quote;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalCalculatedAmount() {
        return totalCalculatedAmount;
    }

    public void setTotalCalculatedAmount(BigDecimal totalCalculatedAmount) {
        this.totalCalculatedAmount = totalCalculatedAmount;
    }

}
