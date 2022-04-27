package com.ctrlcutter.backend.dto;

import java.sql.Timestamp;

public class SessionDTO {

    private Long session_id;
    private String session_key;
    private CustomerDTO customer;
    private Timestamp creation_time;
    private Timestamp valid_until;

    public SessionDTO() {}

    public SessionDTO(String session_key, CustomerDTO customer, Timestamp creation_time, Timestamp valid_until) {
        this.session_key = session_key;
        this.customer = customer;
        this.creation_time = creation_time;
        this.valid_until = valid_until;
    }

    public Long getSession_id() {
        return this.session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    public String getSession_key() {
        return this.session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public CustomerDTO getCustomer() {
        return this.customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public Timestamp getCreation_time() {
        return this.creation_time;
    }

    public void setCreation_time(Timestamp creation_time) {
        this.creation_time = creation_time;
    }

    public Timestamp getValid_until() {
        return this.valid_until;
    }

    public void setValid_until(Timestamp valid_until) {
        this.valid_until = valid_until;
    }

    @Override
    public String toString() {
        return "Session{" + "session_id=" + this.session_id + ", session_key='" + this.session_key + '\'' + ", customer=" + this.customer + ", creation_time="
                + this.creation_time + ", valid_until=" + this.valid_until + '}';
    }
}
