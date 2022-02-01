package com.titoufu.lista_palestras.util;

import android.icu.text.SimpleDateFormat;

import java.time.DayOfWeek;
import java.time.LocalDate;


public class DateCustom {

    public static String dataAtual() {

        long data = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataString = simpleDateFormat.format(data);
        return dataString;
    }

    public static String dataAtualLong() {
        long data = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String dataString = simpleDateFormat.format(data);
        return dataString;
    }

    public static String mesAnoDataEscolhida(String data) {

        String retornoData[] = data.split("/");
        String dia = retornoData[0];
        String mes = retornoData[1];
        String ano = retornoData[2];
        String mesAno = mes + ano;
        return mesAno;
    }

    public static String diaDaSemana(String datain) {

        String Dia[] = {" ", "SEGUNDA", "TERÇA", "QUARTA", "QUINTA", "SEXTA", "SÁBADO", "DOMINGO"};
        String retornoData[] = datain.split("/");
        int dia = (int) Long.parseLong(retornoData[0]);
        int mes = (int) Long.parseLong(retornoData[1]);
        int ano = (int) Long.parseLong(retornoData[2]);
        LocalDate date = LocalDate.of(ano, mes, dia);
        DayOfWeek day = date.getDayOfWeek();
        int i = date.getDayOfWeek().getValue();
        return Dia[day.getValue()];
    }
}
