package com.myorg.kernel.domain.util;

public enum GenericResponseCodes {

    TRANSACCION_EXITOSA("0000", "Transacción exitosa."),
    ERROR_INTERNO_SERVIDOR("0001", "Error interno del servidor."),
    PERMISO_DENEGADO("0002", "Permiso denegado."),
    YA_EXISTE_RECURSO("0003", "Ya existe el recurso."),
    RECURSO_NO_EXISTE("0004", "Recurso no existente."),
    ERROR_AUTENTICACION("0005", "Error de autenticación."),
    INCONSISTENCIA_DATOS("0006", "Inconsistencia en datos de la transaccion."),
    ESTADO_ERRADO("0007", "Estado errado del recurso."),
    OTP_INVALIDO("0008", "OTP invalido."),
    CREDENCIALES_INVALIDAS("0009", "Credenciales de autenticación invalidas."),
    TRANSACCION_FALLIDA("0010", "Transacción fallida."),
    UNSOPORTED_MEDIA_TYPE("0011", "Unsupported Media Type"),
    FIELD_ID_NOT_NULL("0012", "The id in URL cannot be null or empty");
    private String value;
    private String description;

    GenericResponseCodes(String value, String description) {

        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

}
