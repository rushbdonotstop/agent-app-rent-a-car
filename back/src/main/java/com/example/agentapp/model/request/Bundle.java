package com.example.agentapp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

@Entity
public class Bundle {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @JsonInclude()
    @Transient
    List<Request> requests;

    public Bundle(List<Request> requests) {
        this.requests = requests;
    }

    public Bundle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "Bundle{" +
                "id=" + id +
                ", requests=" + requests +
                '}';
    }
}
