package com.titoufu.lista_palestras;

public class News {

   // String heading;
    String anoPalestra, dataPalestra, oradorPalestra,temaPalestra,semanaPalestra;
   // int titleImage;

    public News() {
    }
   public News(String anoPalestra, String dataPalestra, String oradorPalestra, String temaPalestra,String semanaPalestra) {
        this.anoPalestra = anoPalestra;
        this.dataPalestra = dataPalestra;
        this.oradorPalestra = oradorPalestra;
        this.temaPalestra = temaPalestra;
        this.semanaPalestra = semanaPalestra;
    }
}
