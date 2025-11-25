package com.noel.triviapp;

public class Pregunta {

    private String texto;
    private String correcta;
    private String[] respuestas;

    public Pregunta(String texto, String correcta, String[] respuestas) {
        this.texto = texto;
        this.correcta = correcta;
        this.respuestas = respuestas;
    }

    public String getTexto() {
        return texto;
    }

    public String getCorrecta() {
        return correcta;
    }

    public String[] getRespuestas() {
        return respuestas;
    }
}
