package modelo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarT {

    private  String palabra;
    private  String tipo;
    private String instruccionesE02 [] = {"std","aad","cld","cwd","iret","movsw","div","imul","pop","idiv","shl","xchg","add","lds","jns","js","loopne","jae","jcxz","jl"};
    private String registros [] = {"ah","al","ax","bh","bl","bx","ch","cl","cx","dh","dl","dx","sp","bp","si","di","cs","ds", "es", "ss", "ip"};

    public ValidarT(String palabra){
        this.palabra = palabra;
        this.tipo = "";

        validarPseudoInstrucciones(this.palabra);
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
                this.tipo = "Elemento no valido";
            }else {
                this.tipo = "Simbolo";
            }

        }


    }// Fin constructor

//stack
   public void validarPseudoInstrucciones(String palabra){
            if(palabra.equalsIgnoreCase("stack segment") || palabra.equalsIgnoreCase(".stack")
                    || palabra.equalsIgnoreCase(".stack segment") || palabra.equalsIgnoreCase("data segment")
                    || palabra.equalsIgnoreCase(".data") || palabra.equalsIgnoreCase(".data segment")
                    || palabra.equalsIgnoreCase("code segment") || palabra.equalsIgnoreCase(".code")
                    || palabra.equalsIgnoreCase(".code segment") || palabra.equalsIgnoreCase("dw")
                    || palabra.equalsIgnoreCase("db") || palabra.equalsIgnoreCase("equ")
                    || (palabra.startsWith("dup") && palabra.endsWith(")")) || palabra.equalsIgnoreCase("macro")
                    || palabra.equalsIgnoreCase("endm") || palabra.equalsIgnoreCase("proc")
                    || palabra.equalsIgnoreCase("endp") || palabra.equalsIgnoreCase("ends")
                    || (palabra.startsWith("DUP") && palabra.endsWith(")")) || palabra.equalsIgnoreCase("byte ptr")
                    || palabra.equalsIgnoreCase("word ptr")){
                this.tipo = "\tPseudoinstruccion";
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
        /*
        if(cadena.toCharArray()[0]=='0' && cadena.toLowerCase().toCharArray()[cadena.length()-1]=='h'){
            validarHex = cadena.toLowerCase().matches("[0-9a-fh]+");
        }

        validarHex = esPar(cadena.length()-2);
        if(validarHex){
            setTipo("\tHexadecimal");
        }

         */

        if(palabra.toCharArray()[0]== '0' && (palabra.toCharArray()[palabra.length()-1] == 'H' || palabra.toCharArray()[palabra.length()-1] == 'h')) {
            for (int i = 0; i < palabra.length(); i++) {
                if(palabra.toLowerCase().toCharArray()[i] == '0'|| palabra.toLowerCase().toCharArray()[i] == '1' || palabra.toLowerCase().toCharArray()[i]=='2'|| palabra.toLowerCase().toCharArray()[i]=='3'
                || palabra.toLowerCase().toCharArray()[i]=='4' || palabra.toLowerCase().toCharArray()[i]=='5'||palabra.toLowerCase().toCharArray()[i]=='6'||palabra.toLowerCase().toCharArray()[i]=='7'||palabra.toLowerCase().toCharArray()[i]=='8'
                || palabra.toLowerCase().toCharArray()[i]=='9' || palabra.toLowerCase().toCharArray()[i]=='a'|| palabra.toLowerCase().toCharArray()[i]=='b'|| palabra.toLowerCase().toCharArray()[i]=='c'|| palabra.toLowerCase().toCharArray()[i]=='d'
                || palabra.toLowerCase().toCharArray()[i]=='e'|| palabra.toLowerCase().toCharArray()[i]=='f'){
                    validarHex = esPar(cadena.length()-2);
                }
            }
        }

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
