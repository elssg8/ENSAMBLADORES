package modelo;

import javax.xml.crypto.Data;

public class Token  {

    private  String palabra;
    private  String tipo;

    private String psudoInstrucciones [] = {".stack", ".code",".data","proc", "macros","end","ends","endm","endp","dup","db","dw","equ"};
    private String instruccionesE02 [] = {"std","aad","cld","cwd","iret","movsw","div","imul","pop","idiv","shl","xchg","add","lds","jns","js","loopne","jae","jcxz","jl"};
    private String registros [] = {"ah","al","ax","bh","bl","bx","ch","cl","cx","dh","dl","dx","sp","bp","si","di","cs","ds", "es", "ss", "ip"};


    public Token(String palabra){
        this.palabra = palabra;
        this.tipo = "";

        // Validando pseudoInstrucciones
        for (String a : psudoInstrucciones) {
            if(this.palabra.toLowerCase().equalsIgnoreCase(a)){
                this.tipo = "PseudoInstruccion";
            }

        }
        // Validando instrucciones
        for (String b:instruccionesE02) {
            if(this.palabra.toLowerCase().equalsIgnoreCase(b)){
                this.tipo = "Instruccion";
            }
        }
        // Validando registros
        for (String c : registros) {
            if(this.palabra.toLowerCase().equalsIgnoreCase(c)){
                this.tipo = "Registro";
            }
        }

        // Valida constantes numericas
        if(palabra.toCharArray()[0]=='0'||palabra.toCharArray()[0]=='1'||palabra.toCharArray()[0]=='2'||palabra.toCharArray()[0]=='3'||
                palabra.toCharArray()[0]=='4'||palabra.toCharArray()[0]=='5'||palabra.toCharArray()[0]=='6'||palabra.toCharArray()[0]=='7'||
                palabra.toCharArray()[0]=='8'||palabra.toCharArray()[0]=='9') {
            tipo = "Constante numerica decimal";
            if(palabra.toCharArray()[palabra.length()-1] == 'H') {
                tipo = "Constante numerica hexadecimal";
            }

            if(palabra.toCharArray()[palabra.length()-1] == 'B') {
                tipo = "Constante numerica binaria";
            }

            if(palabra.toCharArray()[palabra.length()-1] == 'D') {
                tipo = "Constante numerica decimal";
            }
        }
        if(palabra.toCharArray()[0]=='0'||palabra.toCharArray()[0]=='1'||palabra.toCharArray()[0]=='2'||palabra.toCharArray()[0]=='3'||
                palabra.toCharArray()[0]=='4'||palabra.toCharArray()[0]=='5'||palabra.toCharArray()[0]=='6'||palabra.toCharArray()[0]=='7'||
                palabra.toCharArray()[0]=='8'||palabra.toCharArray()[0]=='9') {
            tipo = "Constante numerica decimal";
            if(palabra.toCharArray()[palabra.length()-1] == 'H') {
                tipo = "Constante numerica hexadecimal";
            }

            if(palabra.toCharArray()[palabra.length()-1] == 'B') {
                tipo = "Constante numerica binaria";
            }

            if(palabra.toCharArray()[palabra.length()-1] == 'D') {
                tipo = "Constante numerica decimal";
            }
        }
    }// Fin constructor



    public String toString() {
        return this.palabra + "\t" + this.tipo + "\n";
    }


    public String getPalabra() {
        return palabra;
    }
    public String getTipo() {
        return tipo;
    }



}
