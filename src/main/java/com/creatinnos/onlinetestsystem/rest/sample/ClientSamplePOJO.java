package com.creatinnos.onlinetestsystem.rest.sample;
import java.util.HashSet;
import java.util.Set;

public class ClientSamplePOJO {

    private Long id;
    private String email;
    private String lang;

    public ClientSamplePOJO() {
    }

    public ClientSamplePOJO(Long id) {
    this.id = id;
    }

    public ClientSamplePOJO(Long id, String email, String lang) {
    this.id = id;
    this.email = email;
    this.lang = lang;
    }

    public Long getId() {
    return id;
    }

    public void setId(Long id) {
    this.id = id;
    }

    public String getEmail() {
    return email;
    }

    public void setEmail(String email) {
    this.email = email;
    }

    public String getLang() {
    return lang;
    }

    public void setLang(String lang) {
    this.lang = lang;
    }


    @Override
    public String toString() {
    return "Client [id=" + id + ", email=" + email + ", lang=" + lang + "]";
    }

}