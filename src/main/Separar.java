package main;

import main.window;

public class Separar{

    public Ventana ventana;
    static window w;
    public String archivo;
    static String elementos = "";
    static String lexemas = "";
    boolean error = false;
    static int imprimir = 30;
    static int tamE;
    static int tamL;
    static int paginasE;
    static int paginaE;
    static int paginasL;
    static int paginaL;

    //Analizador lexico
    int a_i = 0;
    int a_a = 0;
    char[] linea;
    int filesize;
    boolean fin_archivo;
    int renglon = 1;
    int columna = 1;
    int COMIENZO = 0;
    int ESTADO = 0;
    int c;

    String lexema;
    String el_token;

    int renglon_anterior = 1;
    int contar_columna = 1;

    String pr[] = new String[24];

    public Separar(Ventana ventana) {
        iniciarComponentes();
        this.ventana = ventana;
    }

    public Separar(window w) {
        iniciarComponentes();
        this.w = w;
    }



    public void iniciarComponentes() {
        //btnSiguiente();
        //btnAtras();
    }

    public void instruccionesEquipo2() {
        pr[0] = "STD";
        pr[1] = "AAD";
        pr[2] = "CLD";
        pr[3] = "CWD";
        pr[4] = "IRET";
        pr[5] = "MOVSW";
        pr[6] = "DIV";
        pr[7] = "IMUL";
        pr[8] = "POP";
        pr[9] = "IDIV";
        pr[10] = "SHL";
        pr[11] = "XCHG";
        pr[12] = "ADD";
        pr[13] = "LDS";
        pr[14] = "JNS";
        pr[15] = "JS";
        pr[16] = "LOOPNE";
        pr[17] = "JAE";
        pr[18] = "JCXZ";
        pr[19] = "JL";
    }

    public void separarElementos() {
        a_i = 0;
        a_a = 0;
        renglon = 1;
        columna = 1;
        COMIENZO = 0;
        ESTADO = 0;
        renglon_anterior = 1;
        contar_columna = 1;
        elementos = "";
        lexemas = "";
        fin_archivo = false;
        error = false;
        filesize = ventana.tamaño;
        while (!(fin_archivo)) {
            renglon_anterior = renglon;
            columna = contar_columna;
            el_token = Token();
            if (!el_token.equals("NoSirve")) {
                lexemas += lexema + "\n\r";
                elementos += el_token + "\n\r";
                //elementos+=el_token+"\n\r"+lexema+"\n\r"+Ren+"\n\r"+Col+"\n\r";
                //creaEscribeArchivo(xArchivo(arg[1]+".cm1"), el_token+"\n\r"+lexema+"\n\r"+Ren+"\n\r"+Col+"\n\r");
            }
        }
        //txtElemento.setText(elementos);
        //txtLexema.setText(lexemas);
        if (error == false) {
            //*txtMensajes.setForeground(Color.blue);
            //*txtMensajes.setText("Analisis Lexico correcto");
            construirElementos();
            construirLexemas();
        }
    }

    public String Token() {
        ESTADO = 0;
        COMIENZO = 0;
        while (!error) {
            switch (ESTADO) {
                case 0:
                    c = lee_car();
                    if (c == 'd') {
                        ESTADO = 1;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 1:
                    c = lee_car();
                    if (c == 'a') {
                        ESTADO = 2;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 2:
                    c = lee_car();
                    if (c == 't') {
                        ESTADO = 3;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 3:
                    c = lee_car();
                    if (c == 'a') {
                        ESTADO = 4;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 4:
                    c = lee_car();
                    if (c == ' ') {
                        ESTADO = 5;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 5:
                    c = lee_car();
                    if (c == 's') {
                        ESTADO = 6;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 6:
                    c = lee_car();
                    if (c == 'e') {
                        ESTADO = 7;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 7:
                    c = lee_car();
                    if (c == 'g') {
                        ESTADO = 8;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 8:
                    c = lee_car();
                    if (c == 'm') {
                        ESTADO = 9;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 9:
                    c = lee_car();
                    if (c == 'e') {
                        ESTADO = 10;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 10:
                    c = lee_car();
                    if (c == 'n') {
                        ESTADO = 11;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 11:
                    c = lee_car();
                    if (c == 't') {
                        ESTADO = 12;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 12:
                    lexema = obten_lexema();
                    a_i = a_a;
                    return ("Pseudoinstruccion");
                case 13:
                    c = lee_car();
                    if (c == 's') {
                        ESTADO = 14;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 14:
                    c = lee_car();
                    if (c == 't') {
                        ESTADO = 15;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 15:
                    c = lee_car();
                    if (c == 'a') {
                        ESTADO = 16;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 16:
                    c = lee_car();
                    if (c == 'c') {
                        ESTADO = 17;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 17:
                    c = lee_car();
                    if (c == 'k') {
                        ESTADO = 18;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 18:
                    c = lee_car();
                    if (c == ' ') {
                        ESTADO = 19;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 19:
                    c = lee_car();
                    if (c == 's') {
                        ESTADO = 20;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 20:
                    c = lee_car();
                    if (c == 'e') {
                        ESTADO = 21;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 21:
                    c = lee_car();
                    if (c == 'g') {
                        ESTADO = 22;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 22:
                    c = lee_car();
                    if (c == 'm') {
                        ESTADO = 23;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 23:
                    c = lee_car();
                    if (c == 'e') {
                        ESTADO = 24;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 24:
                    c = lee_car();
                    if (c == 'n') {
                        ESTADO = 25;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 25:
                    c = lee_car();
                    if (c == 't') {
                        ESTADO = 26;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 26:
                    lexema = obten_lexema();
                    a_i = a_a;
                    return ("Pseudoinstruccion");
                case 27:
                    c = lee_car();
                    if (c == 'b') {
                        ESTADO = 28;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 28:
                    c = lee_car();
                    if (c == 'y') {
                        ESTADO = 29;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 29:
                    c = lee_car();
                    if (c == 't') {
                        ESTADO = 30;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 30:
                    c = lee_car();
                    if (c == 'e') {
                        ESTADO = 31;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 31:
                    c = lee_car();
                    if (c == ' ') {
                        ESTADO = 32;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 32:
                    c = lee_car();
                    if (c == 'p') {
                        ESTADO = 33;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 33:
                    c = lee_car();
                    if (c == 't') {
                        ESTADO = 34;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 34:
                    c = lee_car();
                    if (c == 'r') {
                        ESTADO = 35;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 35:
                    lexema = obten_lexema();
                    a_i = a_a;
                    return ("PseudoInstruccion");
                case 36:
                    c = lee_car();
                    if (c == 'w') {
                        ESTADO = 37;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 37:
                    c = lee_car();
                    if (c == 'o') {
                        ESTADO = 38;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 38:
                    c = lee_car();
                    if (c == 'r') {
                        ESTADO = 39;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 39:
                    c = lee_car();
                    if (c == 'd') {
                        ESTADO = 40;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 40:
                    c = lee_car();
                    if (c == ' ') {
                        ESTADO = 41;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 41:
                    c = lee_car();
                    if (c == 'p') {
                        ESTADO = 42;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 42:
                    c = lee_car();
                    if (c == 't') {
                        ESTADO = 43;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 43:
                    c = lee_car();
                    if (c == 'r') {
                        ESTADO = 44;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 44:
                    lexema = obten_lexema();
                    a_i = a_a;
                    return ("PseudoInstruccion");
                case 45:
                    c = lee_car();
                    if (c == 'd') {
                        ESTADO = 46;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 46:
                    c = lee_car();
                    if (c == 'u') {
                        ESTADO = 47;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 47:
                    c = lee_car();
                    if (c == 'p') {
                        ESTADO = 48;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 48:
                    c = lee_car();
                    if (c == '(') {
                        ESTADO = 49;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 49:
                    c = lee_car();
                    if (es_digito(c) || es_letra(c)) {
                        ESTADO = 49;
                    } else if (c == ')') {
                        ESTADO = 50;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 50:
                    lexema = obten_lexema();
                    a_i = a_a;
                    return ("PseudoInstruccion");
                case 51:
                    c = lee_car();
                    if (c == '[') {
                        ESTADO = 52;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 52:
                    c = lee_car();
                    if (es_digito(c) || es_letra(c)) {
                        ESTADO = 52;
                    } else if (c == ']') {
                        ESTADO = 53;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 53:
                    lexema = obten_lexema();
                    a_i = a_a;
                    return ("ElementoCompuesto");
                case 54:
                    c = lee_car();
                    if (c == '"') {
                        ESTADO = 55;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 55:
                    c = lee_car();
                    if (c != 9 && c != 10 && c != 13 && c !='"') {
                        ESTADO = 55;
                    } else if (c == '"') {
                        ESTADO = 56;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 56:
                    lexema = obten_lexema();
                    a_i = a_a;
                    return ("Constante");
                case 57:
                    c = lee_car();
                    if (c == 39) {
                        ESTADO = 58;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 58:
                    c = lee_car();
                    if (es_digito(c) || es_letra(c)) {
                        ESTADO = 58;
                    } else if (c == 39) {
                        ESTADO = 59;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 59:
                    lexema = obten_lexema();
                    a_i = a_a;
                    return ("Constante");
                case 60:
                    c = lee_car();
                    if (es_letra(c)) {
                        ESTADO = 61;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 61:
                    c = lee_car();
                    //es_letra(c) || es_digito(c)  esto va dentro del si
                    if (c != ' ' && c != 9 && c != 10 && c != 13 && c!=43 && c!=44 && c!= 58 && c!= 59) {
                        ESTADO = 61;
                    } else {
                        ESTADO = 62;
                    }
                    break;
                case 62:
                    a_a--;
                    contar_columna--;
                    //se tiene que saber si es una palabra reservada
                    lexema = obten_lexema();
                    a_i = a_a;
                    if (es_pr(lexema)) {
                        return ("Instruccion");
                    } else {
                        return es_Instruccion_Especial(lexema);
                    }
                case 63:
                    c = lee_car();
                    if (c == ' ' || c == 9 || c == 10 || c == 13) {
                        ESTADO = 64;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 64:
                    c = lee_car();
                    if (c == ' ' || c == 9 || c == 10 || c == 13) {
                        ESTADO = 64;
                    } else {
                        ESTADO = 65;
                    }
                    break;
                case 65:
                    a_a--;
                    contar_columna--;
                    lexema = obten_lexema();
                    a_i = a_a;
                    return ("NoSirve");
                case 66:
                    c=lee_car();
                    if (es_digito(c)) {
                        if (c==48) {
                            ESTADO = 79;
                        }else{
                            ESTADO = 67;
                        }
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 79:
                    c=lee_car();
                    if (es_digito(c)) {
                        ESTADO = 79;
                    }else{
                        ESTADO = 80;
                    }
                    break;
                case 80:
                    if (c == 'h' || c == 'H') {
                        lexema = obten_lexema();
                        a_i = a_a;
                        return ("Numero hexadecimal");
                    } else {
                        a_a--;
                        contar_columna--;
                        lexema = obten_lexema();
                        a_i = a_a;
                        return ("Numero");
                    }
                case 67:
                    c=lee_car();
                    if (es_digito(c)) {
                        ESTADO = 67;
                    }else{
                        ESTADO = 68;
                    }
                    break;
                case 68:
                    if (es_letra(c)) {
                        ESTADO = 81;
                    }else{
                        a_a--;
                        contar_columna--;
                        lexema = obten_lexema();
                        a_i = a_a;
                        return ("Numero");
                    }
                    break;
                case 81:
                    c=lee_car();
                    if (es_digito(c)||es_letra(c)) {
                        ESTADO = 81;
                    }else{
                        a_a--;
                        contar_columna--;
                        lexema = obten_lexema();
                        a_i = a_a;
                        return ("Simbolo");
                    }
                    break;
                case 69:
                    c = lee_car();
                    if (c == ';') {
                        ESTADO = 70;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 70:
                    c=lee_car();
                    if (c == 10 || a_a == ventana.tamaño) {
                        ESTADO = 71;
                    } else {
                        ESTADO = 70;
                    }
                    break;
                case 71:
                    lexema = obten_lexema();
                    a_i = a_a;
                    return ("NoSirve");
                case 72:
                    c = lee_car();
                    switch (c) {
                        case 40:
                            ESTADO = 73;
                            break;
                        case 41:
                            ESTADO = 74;
                            break;
                        case 43:
                            ESTADO = 75;
                            break;
                        case 44:
                            ESTADO = 76;
                            break;
                        case 46:
                            ESTADO = 77;
                            break;
                        case 58:
                            ESTADO = 78;
                            break;
                    }
                case 73:
                    lexema = obten_lexema();
                    a_i = a_a;
                    return ("NoSirve");
                case 74:
                    lexema = obten_lexema();
                    a_i = a_a;
                    return ("NoSirve");
                case 75:
                    lexema = obten_lexema();
                    a_i = a_a;
                    return ("NoSirve");
                case 76:
                    lexema = obten_lexema();
                    a_i = a_a;
                    return ("NoSirve");
                case 77:
                    lexema = obten_lexema();
                    a_i = a_a;
                    return ("NoSirve");
                case 78:
                    lexema = obten_lexema();
                    a_i = a_a;
                    return ("NoSirve");
                    /////////////
                case 82:
                    c = lee_car();
                    if (c == 'c') {
                        ESTADO = 83;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 83:
                    c = lee_car();
                    if (c == 'o') {
                        ESTADO = 84;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 84:
                    c = lee_car();
                    if (c == 'd') {
                        ESTADO = 85;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 85:
                    c = lee_car();
                    if (c == 'e') {
                        ESTADO = 86;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 86:
                    c = lee_car();
                    if (c == ' ') {
                        ESTADO = 87;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 87:
                    c = lee_car();
                    if (c == 's') {
                        ESTADO = 88;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 88:
                    c = lee_car();
                    if (c == 'e') {
                        ESTADO = 89;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 89:
                    c = lee_car();
                    if (c == 'g') {
                        ESTADO = 90;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 90:
                    c = lee_car();
                    if (c == 'm') {
                        ESTADO = 91;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 91:
                    c = lee_car();
                    if (c == 'e') {
                        ESTADO = 92;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 92:
                    c = lee_car();
                    if (c == 'n') {
                        ESTADO = 93;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 93:
                    c = lee_car();
                    if (c == 't') {
                        ESTADO = 94;
                    } else {
                        ESTADO = diagrama();
                    }
                    break;
                case 94:
                    lexema = obten_lexema();
                    a_i = a_a;
                    return ("Pseudoinstruccion");
            }
        }
        return ("\n\nError");
    }

    public void construirElementos() {
        int cont = 1;
        paginasE = 1;
        tamE = elementos.length();
        for (int i = 0; i < tamE; i++) {
            if (elementos.charAt(i) == '\n') {
                if (cont == imprimir) {
                    paginasE++;
                    cont = 1;
                } else {
                    cont++;
                }
            }
        }
        paginaE = 1;
        mostrarElementos();
        //mostrarLexema();

        ventana.etiqueta_pagina_E.setText(paginaE + "/" + paginasE);
        //System.out.println("Paginas: "+paginasA);
        //System.out.println("Pagina:"+paginaA);
    }

    void construirLexemas() {
        int cont = 1;
        paginasL = 1;
        tamL = lexemas.length();
        for (int i = 0; i < tamL; i++) {
            if (lexemas.charAt(i) == '\n') {
                if (cont == imprimir) {
                    paginasL++;
                    cont = 1;
                } else {
                    cont++;
                }
            }
        }
        paginaL = 1;
        //mostrarElementos();
        mostrarLexema();
        //lblPaginaE.setText(paginaE+"/"+paginasE);
        //System.out.println("Paginas: "+paginasA);
        //System.out.println("Pagina:"+paginaA);
    }

    public static void mostrarElementos() {
        int cont = 1;
        int renglonObjetivo = paginaE;
        int renglonBuscador = 1;
        String pag = "";
        int i = 0;
        while (renglonBuscador < renglonObjetivo) {
            if (elementos.charAt(i) == '\n') {
                if (cont == imprimir) {
                    renglonBuscador++;
                    cont = 1;
                } else {
                    cont++;
                }
            }
            i++;
        }
        while (renglonBuscador <= renglonObjetivo && i < tamE) {
            pag += elementos.charAt(i);
            if (elementos.charAt(i) == '\n') {
                if (cont == imprimir) {
                    renglonBuscador++;
                    cont = 1;
                } else {
                    cont++;
                }
            }
            i++;
        }
        ventana.txt_area_identificacion.setText(pag);
    }

    public static void mostrarLexema() {
        int cont = 1;
        int renglonObjetivo = paginaL;
        int renglonBuscador = 1;
        String pag = "";
        int i = 0;
        while (renglonBuscador < renglonObjetivo) {
            if (lexemas.charAt(i) == '\n') {
                if (cont == imprimir) {
                    renglonBuscador++;
                    cont = 1;
                } else {
                    cont++;
                }
            }
            i++;
        }
        while (renglonBuscador <= renglonObjetivo && i < tamL) {
            pag += lexemas.charAt(i);
            if (lexemas.charAt(i) == '\n') {
                if (cont == imprimir) {
                    renglonBuscador++;
                    cont = 1;
                } else {
                    cont++;
                }
            }
            i++;
        }
        ventana.txt_area_separacion.setText(pag);
    }

    public boolean es_simbolo(int x){
        if( x==40 || x ==41 || x==43 || x == 44 || x == 46 || x == 58 || x == 59){
            return true;
        }else{
            return false;
        }
    }

    public boolean es_letra(int x) {
        if ((x >= 65 && x <= 90) || (x>=97 && x<= 122) ) {
            return true;
        } else {
            return false;
        }
    }
    public boolean es_digito(int x) {
        if ((x >= 48 && x <= 57)) {
            return true;
        } else {
            return false;
        }
    }
    public String obten_lexema() {
        String lex = "";
        for (int i = a_i; i < a_a; i++) {
            lex = lex + archivo.charAt(i);
        }
        return lex;
    }

    public int lee_car() {
        if (a_a <= filesize - 1) {
            if (archivo.charAt(a_a) == 10) {
                contar_columna = 1;
                renglon++;
            } else {
                contar_columna++;
            }
            return archivo.charAt(a_a++);  //DEVUELVE SOLO EL CARACTER E INCREMENTA EL APUNTADOR DE AVANCE
        } else {
            fin_archivo = true;
            return 225;
        }
    }

    public int diagrama() {
        renglon = renglon_anterior;
        contar_columna = columna;
        a_a = a_i;
        switch (COMIENZO) {
            case 0:
                COMIENZO = 13;
                break;
            case 13:
                COMIENZO = 27;
                break;
            case 27:
                COMIENZO = 36;
                break;
            case 36:
                COMIENZO = 45;
                break;
            case 45:
                COMIENZO = 51;
                break;
            case 51:
                COMIENZO = 54;
                break;
            case 54:
                COMIENZO = 57;
                break;
            case 57:
                COMIENZO = 60;
                break;
            case 60:
                COMIENZO = 63;
                break;
            case 63:
                COMIENZO = 66;
                break;
            case 66:
                COMIENZO = 69;
                break;
            case 69:
                COMIENZO = 72;
                break;
            case 72:
                ruterror();
                break;
        }
        return COMIENZO;
    }
    public String es_Instruccion_Especial(String simbolo){
        if (simbolo.equals("ends")||simbolo.equals("ENDS")) {
            return "PseudoInstruccion";
        }else if (simbolo.equals("db")||simbolo.equals("DB")) {
            return "PseudoInstruccion";
        }else if (simbolo.equals("dw")||simbolo.equals("DW")) {
            return "PseudoInstruccion";
        }else if (simbolo.equals("equ")||simbolo.equals("EQU")) {
            return "PseudoInstruccion";
        }else if (simbolo.equals("dub")||simbolo.equals("DUB")) {
            return "PseudoInstruccion";
        }else if (simbolo.equals("macro")||simbolo.equals("MACRO")) {
            return "PseudoInstruccion";
        }else if (simbolo.equals("endm")||simbolo.equals("ENDM")) {
            return "PseudoInstruccion";
        }else if (simbolo.equals("proc")||simbolo.equals("PROC")) {
            return "PseudoInstruccion";
        }else if (simbolo.equals("endp")||simbolo.equals("ENDP")) {
            return "PseudoInstruccion";
        }else if (simbolo.equals("ax")||simbolo.equals("AX")) {
            return "Registro";
        }else if (simbolo.equals("ah")||simbolo.equals("AH")) {
            return "Registro";
        }else if (simbolo.equals("al")||simbolo.equals("AL")) {
            return "Registro";
        }else if (simbolo.equals("bx")||simbolo.equals("BX")) {
            return "Registro";
        }else if (simbolo.equals("bh")||simbolo.equals("BH")) {
            return "Registro";
        }else if (simbolo.equals("bl")||simbolo.equals("BL")) {
            return "Registro";
        }else if (simbolo.equals("cx")||simbolo.equals("CX")) {
            return "Registro";
        }else if (simbolo.equals("ch")||simbolo.equals("CH")) {
            return "Registro";
        }else if (simbolo.equals("cl")||simbolo.equals("cl")) {
            return "Registro";
        }else if (simbolo.equals("dx")||simbolo.equals("DX")) {
            return "Registro";
        }else if (simbolo.equals("dh")||simbolo.equals("DH")) {
            return "Registro";
        }else if (simbolo.equals("dl")||simbolo.equals("DL")) {
            return "Registro";
        }else if (simbolo.equals("si")||simbolo.equals("SI")) {
            return "Registro";
        }else if (simbolo.equals("DI")||simbolo.equals("DI")) {
            return "Registro";
        }else if (simbolo.equals("sp")||simbolo.equals("SP")) {
            return "Registro";
        }else if (simbolo.equals("bp")||simbolo.equals("BP")) {
            return "Registro";
        }else if (simbolo.equals("ss")||simbolo.equals("SS")) {
            return "Registro";
        }else if (simbolo.equals("cs")||simbolo.equals("CS")) {
            return "Registro";
        }else if (simbolo.equals("ds")||simbolo.equals("DS")) {
            return "Registro";
        }else if (simbolo.equals("es")||simbolo.equals("ES")) {
            return "Registro";
        } else{
            return "Simbolo";
        }
    }

    public void ruterror() {
        char b = (char) c;
        String mensaje = " ERROR Lexico:\nCARACTER LEIDO[ " + b + " ]\n(" + renglon + "," + columna + ")";
        //JOptionPane.showMessageDialog(null, mensaje);
        lexema=b+"";
        fin_archivo=true;
        error=true;
        //*txtMensajes.setForeground(Color.red);
        //*txtMensajes.setText(mensaje);
    }

    public boolean es_pr(String x) {
        for (int i = 0; i <=23; i++) {
            if (x.equals(pr[i])) {
                return true;
            }
        }
        return false;
    }


}