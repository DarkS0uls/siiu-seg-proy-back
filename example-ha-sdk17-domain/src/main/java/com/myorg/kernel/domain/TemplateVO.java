package com.myorg.kernel.domain;

public class TemplateVO {

    private final String email;

    private TemplateVO(final String email){
        this.email = email;
    }

    public static TemplateVO of(String email){
        return new TemplateVO(email);
    }

    public String getEmail() {
        return email;
    }
}
