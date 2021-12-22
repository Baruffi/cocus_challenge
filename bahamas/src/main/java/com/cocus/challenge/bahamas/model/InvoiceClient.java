package com.cocus.challenge.bahamas.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InvoiceClient {

    @Id
    private Long invoiceId;

    private String fiscalId;
    private String name;
    private String email;

    protected InvoiceClient() {
    }

    public InvoiceClient(Long invoiceId, String fiscal_id, String name, String email) {
        this.invoiceId = invoiceId;
        this.fiscalId = fiscal_id;
        this.name = name;
        this.email = email;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public String getFiscal_id() {
        return fiscalId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("InvoiceClient [email=%s, fiscal_id=%s, invoiceId=%s, name=%s]", email, fiscalId,
                invoiceId, name);
    }
}
