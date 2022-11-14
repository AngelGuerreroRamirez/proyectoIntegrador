package org.utl.dsm42d_optik;


/**
 *
 * @author urieh
 */
public class Cliente {
    //datos idEmpleado, apellidoPaterno, apellidoMaterno, genero,rtc,telCasa, telMovil,numeroUnico,estutus
    private int idEmpleado;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String genero;
    private String rfc;
    private String telCasa;
    private String telMovil;
    private String numeroUnico;
    private String correo;
    private int estatus;

    public Cliente() {
    }

    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String genero, String rfc, String telCasa, String telMovil, int estatus,String correo) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.genero = genero;
        this.rfc = rfc;
        this.telCasa = telCasa;
        this.telMovil = telMovil;

        this.numeroUnico = numeroUnico;

        this.estatus = estatus;
        this.correo=correo;
    }

    public Cliente(int idEmpleado, String nombre, String apellidoPaterno, String apellidoMaterno, String genero, String rfc, String telCasa, String telMovil,  String numeroUnico, int estatus,String correo) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.genero = genero;
        this.rfc = rfc;
        this.telCasa = telCasa;
        this.telMovil = telMovil;

        this.numeroUnico = numeroUnico;

        this.estatus = estatus;
        this.correo=correo;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTelCasa() {
        return telCasa;
    }

    public void setTelCasa(String telCasa) {
        this.telCasa = telCasa;
    }

    public String getTelMovil() {
        return telMovil;
    }

    public void setTelMovil(String telMovil) {
        this.telMovil = telMovil;
    }



    public String getNumeroUnico() {
        return numeroUnico;
    }

    public void setNumeroUnico(String numeroUnico) {
        this.numeroUnico = numeroUnico;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "idEmpleado=" + idEmpleado +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", genero='" + genero + '\'' +
                ", rfc='" + rfc + '\'' +
                ", telCasa='" + telCasa + '\'' +
                ", telMovil='" + telMovil + '\'' +

                ", numeroUnico='" + numeroUnico + '\'' +

                ", estatus=" + estatus +
                '}';
    }
}
