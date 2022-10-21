package modelo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarT {

    private  String palabra;
    private  String tipo;
    private String psudoInstrucciones [] = {".model",".stack", ".code",".data","proc", "macros","ends","endm","endp","dup","db","dw","equ"};
    private String pseudoInstruccionesCompletas [] = {".code segment", "code segment",".data segment", "data segment",".stack segment", "stack segment","byte ptr","word ptr","dup","[]","segment","ptr"};
    private String instruccionesE02 [] = {"std","aad","cld","cwd","iret","movsw","div","imul","pop","idiv","shl","xchg","add","lds","jns","js","loopne","jae","jcxz","jl"};
    private String registros [] = {"ah","al","ax","bh","bl","bx","ch","cl","cx","dh","dl","dx","sp","bp","si","di","cs","ds", "es", "ss", "ip"};

    public ValidarT(){

    }
    public ValidarT(String palabra){
        this.palabra = palabra;
        this.tipo = "";

        validarPseudoInstrucciones(this.palabra);
        validarPseudoCompletas(this.palabra);
        validarInstrucciones(this.palabra);
        validarRegistro(this.palabra);
        validarNumeroDecimal(this.palabra);
        validarHexadecimal(this.palabra);
        validarBinario(this.palabra);
        validarEtiquetas(this.palabra);
        valirdarConstanteChar(this.palabra);
        valirdarEspacios(this.palabra);
        validarComentarios(this.palabra);


        // Si todas las validaciones anteriores son falsas entonces No se reconoce
        if(this.tipo.equalsIgnoreCase("")) {
            if(palabra.length()>10 && palabra.toCharArray()[palabra.length()-1] != ']') {

                this.tipo = "Elemento no reconocido";
            }else {
                this.tipo = "Simbolo";
            }

        }


    }// Fin constructor


   public void validarPseudoInstrucciones(String palabra){
        for (String a : psudoInstrucciones) {
            if(palabra.toLowerCase().equalsIgnoreCase(a)){
                this.tipo = "\tPseudoinstruccion";
            }
        }
    }

    public void validarPseudoCompletas(String palabra){
        for (String a : pseudoInstruccionesCompletas) {
            if(palabra.toLowerCase().equalsIgnoreCase(a)){
                this.tipo ="\tPseudoinstruccion";
            }
        }
    }

    public void validarInstrucciones(String palabra){
        for (String a : instruccionesE02){
            if(palabra.toLowerCase().equalsIgnoreCase(a)){
                this.tipo = "\tInstruccion";
            }
        }
    }

    public void validarRegistro(String palabra){
        for(String a: registros){
            if(palabra.toLowerCase().equalsIgnoreCase(a)){
                this.tipo = "\tRegistro";
            }
        }
    }

    public void validarNumeroDecimal(String palabra){
        boolean isNumeric = (palabra != null && palabra.toLowerCase().matches("[0-9d]+"));

        if(isNumeric){
            setTipo("\tNumero Decimal");
        }
    }


    public static boolean esPar(int numero){
        return numero % 2 == 0;
    }

    public  void validarHexadecimal(String cadena){
        boolean validarHex = false;

        if(cadena.toCharArray()[0]=='0' && cadena.toLowerCase().toCharArray()[cadena.length()-1]=='h'){
            validarHex = cadena.toLowerCase().matches("[0-9a-fh]+");
        }

        validarHex = esPar(cadena.length()-2);
        if(validarHex){
            setTipo("\tHexadecimal");
        }
    }


    public void validarBinario(String cadena){
        boolean validarBinario = false;

        if(cadena.toLowerCase().toCharArray()[cadena.length()-1]=='b'){
            for (int i = 0; i < cadena.length(); i++) {
                if(cadena.charAt(i)=='0' || cadena.charAt(i) =='1' || cadena.toLowerCase().charAt(i) == 'b'){
                    validarBinario = esPar(cadena.length()-1);
                } else {
                    validarBinario = false;
                    break;
                }
            }
        }

        if(validarBinario){
            setTipo("\tBinario");
        }
    }

    public void validarEtiquetas(String cadena){
        if(cadena.toLowerCase().toCharArray()[cadena.length()-1] == ':'){
            setTipo("\tEtiqueta");
        }
    }

    public void valirdarConstanteChar(String cadena){
        //Constante de caracter
        if(palabra.toCharArray()[0]=='\'' || palabra.toCharArray()[0]=='\"' || palabra.toCharArray()[palabra.length()-1] == '\"') {
            this.tipo = "\tConstante caracter";
        }
    }

    public void valirdarEspacios(String cadena){
        //Espacios
        if(this.tipo.equalsIgnoreCase("")) {
            this.tipo = "\tSimbolo";
        }
    }

    public void validarComentarios(String cadena){
        // Ignoramos comentarios
        if (palabra.toCharArray()[0]==';'){
            this.tipo ="";
        }//ESTA MADRE NO HACE NADA

    }


    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra){
        this.palabra = palabra;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }


    public String toString() {
        return this.palabra + "\t" + this.tipo + "\n";
    }

}
