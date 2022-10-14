package modelo;

import pruebasJose.window;

import java.util.ArrayList;

public class Analizador {

    static int imprimir=15;
    static int tamE;
    int tamL;
    static int paginasE;
    static int paginaE;
    int paginasL;
    int paginaL;
    String elementos="";
    String lexemas="";
    private String txt;
    private char txtCompleto [];
    private ArrayList<ValidarT> palabras;
    private ArrayList<String> lineas;
    private static String resultado;
    public Analizador(String texto) {
        this.txt = texto;
    }
    public void analizaArchivo() {

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
            System.out.println(resultado);
        }

        construirElementos();
    }

    public static void construirElementos(){
        int cont=1;
        paginasE=1;
        tamE=resultado.length();
        for (int i = 0; i < tamE; i++) {
            if (resultado.charAt(i) == '\n') {
                if (cont == imprimir) {
                    paginasE++;
                    cont=1;
                }else{
                    cont++;
                }
            }
        }
        paginaE=1;
        mostrarElementos();
    }

    public static void mostrarElementos(){
        int cont=1;
        int renglonObjetivo=paginaE;
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
        if(paginaE>1){
            paginaE--;
            mostrarElementos();
            //lblPaginaE.setText(paginaE+"/"+paginasE);
        }
    }

    public static void btnSiguiente() {
        if(paginaE<paginasE){
            paginaE++;
            mostrarElementos();
            //lblPaginaE.setText(paginaE+"/"+paginasE);
        }
    }

}
