package modelo;

import main.window;

import java.util.ArrayList;
public class Analizador {

    static int imprimir=15;
    static int tamE;
    int tamL;
    static int total_paginas;
    static int pagina_actual;
    private ArrayList<ValidarT> palabras;
    private ArrayList<String> lineas;
    private static String resultado;
    public Analizador(ArrayList<String> lineas) {
        this.lineas = lineas;
    }
    public void analizaArchivo() {

        String palabra = "";
        this.palabras =  new ArrayList<>();

        char[] aux = {};

        for(int i = 0; i<lineas.size();i++) {
            if(palabra !="") {
                palabras.add(new ValidarT(palabra));
                palabra = "";
            }


            aux = lineas.get(i).toCharArray();

            for(int j =0; j<aux.length; j++) {
                if(aux[j]==';') {
                    break;
                }else {
                    if(aux[j]!=' ' && aux[j]!=',' && aux[j]!='\n') {
                        palabra = palabra + aux[j];

                        if(palabra.equalsIgnoreCase(".STACK")) {
                            palabra = ".stack segment";
                            palabras.add(new ValidarT(palabra));
                            palabra ="";
                            break;
                        }

                        if(palabra.equalsIgnoreCase(".DATA")) {
                            palabra = ".data segment";
                            palabras.add(new ValidarT(palabra));
                            palabra ="";
                            break;
                        }

                        if(palabra.equalsIgnoreCase(".CODE")) {
                            palabra = ".code segment";
                            palabras.add(new ValidarT(palabra));
                            palabra ="";
                            break;
                        }
                        // Parte donde se separa por coma
                        if(aux[j]=='\'') {
                            for(int k = j; k<aux.length;k++) {
                                palabra = palabra + aux[k];
                                if(aux[k]==','|| aux[k]=='\n') {
                                    if(palabra !="") {
                                        palabras.add(new ValidarT(palabra));
                                        palabra = "";
                                    }
                                    continue;
                                }
                            }
                            if(palabra !="") {
                                palabras.add(new ValidarT(palabra));
                                palabra = "";
                            }
                            break;
                        }

                    }
                    if(aux[j]==' ' || aux[j]==','|| aux[j]=='\n') {
                        if(palabra !="") {
                            palabras.add(new ValidarT(palabra));
                            palabra = "";
                        }
                    }
                }
            }
        }

        resultado = "";
        for(int j =0; j<palabras.size(); j++) {
            resultado = resultado + palabras.get(j).toString();
        }

        construirElementos();
    }

    public static void construirElementos(){
        int cont=1;
        total_paginas =1;
        tamE=resultado.length();

        for (int i = 0; i < tamE; i++) {
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

        while(renglonBuscador<=renglonObjetivo&&i<tamE){
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
        window.txtSeparacion.setText(pag);

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
