package modelo;

import main.window;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Fase02 {
    static int imprimir=15;
    static int total_paginas;
    static int pagina_actual;
    private ArrayList<String> lineas;
    private ArrayList<ValidarSintaxys> palabras;
    private static String resultado;
    public Fase02(ArrayList<String> lineas){
        this.lineas = lineas;
    }

    public void analizaArchivoF02() {
        String palabra = "";
        this.palabras = new ArrayList<>();

        char[] aux = {};

        for (int i = 0; i < lineas.size(); i++) {
            if (palabra != "") {
                palabras.add(new ValidarSintaxys(palabra));
                palabra = "";
            }

            aux = lineas.get(i).toCharArray();


            for (int j = 0; j < aux.length; j++) {
                if(aux[j]==';'){
                    break;
                }else {
                    if(aux[j]!=' ' && aux[j]!=',' && aux[j]!='\n') {
                        palabra+=  aux[j];
                        if(palabra.equalsIgnoreCase(".STACK")) {
                            palabra = ".stack segment";
                            palabras.add(new ValidarSintaxys(palabra));
                            palabra = "";
                            break;
                        }

                    }
                }
            }
        }
        resultado = "";
        for (int i = 0; i < palabras.size(); i++) {
            resultado += palabras.get(i).toString();
        }
        construirElementos();

    }
    public static void construirElementos(){
        int cont=1;
        total_paginas = 1;

        for (int i = 0; i < resultado.length(); i++) {
            if (resultado.charAt(i) == '\n') {
                if (cont == 15) {
                    total_paginas++;
                    cont=1;
                }else{
                    cont++;
                }
            }
        }
        pagina_actual =1;
        mostrarElementos();
    }
    public static void mostrarElementos(){
        int cont=1;
        int renglonObjetivo= pagina_actual;
        int renglonBuscador=1;
        String pag="";
        int i=0;

        while(renglonBuscador<renglonObjetivo){
            if (resultado.charAt(i) == '\n') {
                if(cont==imprimir){
                    renglonBuscador++;
                    cont=1;
                }else{
                    cont++;
                }
            }
            i++;
        }

        while(renglonBuscador<=renglonObjetivo&&i<resultado.length()){
            pag += resultado.charAt(i);
            if (resultado.charAt(i) == '\n') {
                if (cont==imprimir) {
                    renglonBuscador++;
                    cont=1;
                }else{
                    cont++;
                }
            }
            i++;
        }
        window.txtSeparacion.setText("FASE02");
        window.txtSeparacion.setText(pag);
        //window.txtIdentificacion.setText(pag);
    }

    public static void btnAtras() {
        if(pagina_actual >1){
            pagina_actual--;
            mostrarElementos();
        }
    }

    public static void btnSiguiente() {
        if(pagina_actual < total_paginas){
            pagina_actual++;
            mostrarElementos();
        }
    }

}
