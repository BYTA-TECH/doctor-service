package com.bytatech.ayoos.doctor.domain.search;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;



import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A PaymentSettings.
 */


public class PaymentSettings {

   


    private Long id;

    
    private Boolean isPaymentEnabled;


    private Double amount;

    
    private String paymentMethod;

 
    private String currency;

   
    private String intent;

    private String noteToPayer;

 
    private String paymentGatewayProvider;

 
    private String paymentGatewayCredentials;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsPaymentEnabled() {
        return isPaymentEnabled;
    }

    public PaymentSettings isPaymentEnabled(Boolean isPaymentEnabled) {
        this.isPaymentEnabled = isPaymentEnabled;
        return this;
    }

    public void setIsPaymentEnabled(Boolean isPaymentEnabled) {
        this.isPaymentEnabled = isPaymentEnabled;
    }

    public Double getAmount() {
        return amount;
    }

    public PaymentSettings amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentSettings paymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCurrency() {
        return currency;
    }

    public PaymentSettings currency(String currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIntent() {
        return intent;
    }

    public PaymentSettings intent(String intent) {
        this.intent = intent;
        return this;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getNoteToPayer() {
        return noteToPayer;
    }

    public PaymentSettings noteToPayer(String noteToPayer) {
        this.noteToPayer = noteToPayer;
        return this;
    }

    public void setNoteToPayer(String noteToPayer) {
        this.noteToPayer = noteToPayer;
    }

    public String getPaymentGatewayProvider() {
        return paymentGatewayProvider;
    }

    public PaymentSettings paymentGatewayProvider(String paymentGatewayProvider) {
        this.paymentGatewayProvider = paymentGatewayProvider;
        return this;
    }

    public void setPaymentGatewayProvider(String paymentGatewayProvider) {
        this.paymentGatewayProvider = paymentGatewayProvider;
    }

    public String getPaymentGatewayCredentials() {
        return paymentGatewayCredentials;
    }

    public PaymentSettings paymentGatewayCredentials(String paymentGatewayCredentials) {
        this.paymentGatewayCredentials = paymentGatewayCredentials;
        return this;
    }

    public void setPaymentGatewayCredentials(String paymentGatewayCredentials) {
        this.paymentGatewayCredentials = paymentGatewayCredentials;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentSettings)) {
            return false;
        }
        return id != null && id.equals(((PaymentSettings) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PaymentSettings{" +
            "id=" + getId() +
            ", isPaymentEnabled='" + isIsPaymentEnabled() + "'" +
            ", amount=" + getAmount() +
            ", paymentMethod='" + getPaymentMethod() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", intent='" + getIntent() + "'" +
            ", noteToPayer='" + getNoteToPayer() + "'" +
            ", paymentGatewayProvider='" + getPaymentGatewayProvider() + "'" +
            ", paymentGatewayCredentials='" + getPaymentGatewayCredentials() + "'" +
            "}";
    }
}
