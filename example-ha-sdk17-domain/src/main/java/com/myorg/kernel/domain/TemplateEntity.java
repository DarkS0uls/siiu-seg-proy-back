package com.myorg.kernel.domain;

public class TemplateEntity {

    private final TemplateVO templateVO;
    private String telefono;
    private String direccion;

    private TemplateEntity(TemplateVO templateVO, String telefono, String direccion){
        this.templateVO = templateVO;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // REQUIRED ATTRIBUTES CONSTRUCTOR
    private TemplateEntity(TemplateVO templateVO){
        this.templateVO = templateVO;
    }

    public static TemplateEntity builder(TemplateVO templateVO){
        return new TemplateEntity(templateVO);
    }

    public TemplateEntity telefono(String telefono){
        this.telefono = telefono;
        return this;
    }
    public TemplateEntity direccion(String direccion){
        this.direccion = direccion;
        return this;
    }

    public TemplateEntity build(){
        return new TemplateEntity(this.templateVO, this.telefono, this.direccion);
    }

    public TemplateVO getTemplateVO2() {
        return templateVO;
    }


}
