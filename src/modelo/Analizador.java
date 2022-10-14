package modelo;

import java.util.ArrayList;

public class Analizador {
    private String txt;
    private char txtCompleto [];
    private ArrayList<ValidarT> palabras;
    private ArrayList<String> lineas;
    private String resultado;
    public Analizador(String texto) {
        this.txt = texto;
    }
    public String analizaArchivo() {

        txtCompleto = txt.toCharArray();

        String linea = "";
        this.lineas = new ArrayList<String>();

        String palabra;

        palabra = "";
        this.palabras =  new ArrayList<ValidarT>();

        char[] aux = {};

        //DIVIDIR EN LINEAS
        for(int i =0; i<txtCompleto.length; i++) {
            if(txtCompleto[i]=='\n') {
                lineas.add(linea);
                linea = "";
                continue;
            }else {
                linea = linea + txtCompleto[i];
            }
        }// Fin divide lineas

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


                        if(palabra.equalsIgnoreCase("STACK")) {
                            palabra = "STACK SEGMENT";
                            palabras.add(new ValidarT(palabra));
                            palabra ="";
                            break;
                        }

                        if(palabra.equalsIgnoreCase("DATA")) {
                            palabra = "DATA SEGMENT";
                            palabras.add(new ValidarT(palabra));
                            palabra ="";
                            break;
                        }

                        if(palabra.equalsIgnoreCase("CODE")) {
                            palabra = "CODE SEGMENT";
                            palabras.add(new ValidarT(palabra));
                            palabra ="";
                            break;
                        }

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
                            }							break;
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

        return resultado;
    }


}
