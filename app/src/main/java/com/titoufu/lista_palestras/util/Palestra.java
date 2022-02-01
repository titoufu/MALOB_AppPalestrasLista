package com.titoufu.lista_palestras.util;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Palestra {
    private String data, orador, tema, referencia, numero;

    public Palestra() {
    }

    public void salvarPush(String data) {
        String idData = Base64Custom.codificarBase64(data);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("palestra");
        myRef.child(idData).push().setValue(this);
    }

    public void salvar(Palestra palestra) {

        String idData = palestra.getNumero();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("palestra");

        myRef.child(idData).child("data").setValue(palestra.getData());
        myRef.child(idData).child("orador").setValue(palestra.getOrador());
        myRef.child(idData).child("tema").setValue(palestra.getTema());
        myRef.child(idData).child("referencia").setValue(palestra.getReferencia());
        myRef.child(idData).child("numero").setValue(palestra.getNumero());
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOrador() {
        return orador;
    }

    public void setOrador(String orador) {
        this.orador = orador;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNumero() {return numero; }

    public void setNumero(String numero) {this.numero = numero; }
}
