package org.app.demo.model;

public class RespuestaWs {
    private Object data;
    private String mensaje;
    private Boolean estado;

    public RespuestaWs() {

    }

    public RespuestaWs(Boolean estado, Object data, String mensaje) {
        this.estado = estado;
        this.data = data;
        this.mensaje = mensaje;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
