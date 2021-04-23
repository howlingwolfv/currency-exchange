package demo.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class ExchangeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency_from")
    private String from;

    @Column(name = "currency_to")
    private String to;

    private BigDecimal quote;

    public ExchangeValue() {

    }

    public ExchangeValue(String from, String to, BigDecimal quote) {
        super();
        this.from = from;
        this.to = to;
        this.quote = quote;
    }

    public ExchangeValue(Long id, String from, String to, BigDecimal quote) {
        super();
        this.id = id;
        this.from = from;
        this.to = to;
        this.quote = quote;
    }

    public Long getId() {
        return id;
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
}