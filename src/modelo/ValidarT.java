package modelo;

public class ValidarT {

    private  String palabra;
    private  String tipo;

    private String psudoInstrucciones [] = {".stack", ".code",".data","proc", "macros","end","ends","endm","endp","dup","db","dw","equ"};
    private String instruccionesE02 [] = {"std","aad","cld","cwd","iret","movsw","div","imul","pop","idiv","shl","xchg","add","lds","jns","js","loopne","jae","jcxz","jl"};
    private String registros [] = {"ah","al","ax","bh","bl","bx","ch","cl","cx","dh","dl","dx","sp","bp","si","di","cs","ds", "es", "ss", "ip"};

    public ValidarT(String palabra){
        this.palabra = palabra;
        this.tipo = "";
        validarPseudoInstrucciones(this.palabra);
        for (String b:instruccionesE02) {
            if(this.palabra.toLowerCase().equalsIgnoreCase(b)){
                this.tipo = "Instruccion";
            }
        }
        for (String c : registros) {
            if(this.palabra.toLowerCase().equalsIgnoreCase(c)){
                this.tipo = "Registro";
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


    public void validarPseudoInstrucciones(String palabra){
        for (String a : psudoInstrucciones) {
            if(palabra.toLowerCase().equalsIgnoreCase(a)){
                this.tipo = "pseudoinstruccion";
            }
        }
    }

    public void validarInstrucciones(String palabra){
        for (String a : instruccionesE02){
            if(palabra.toLowerCase().equalsIgnoreCase(a)){
                this.tipo = "instruccion";
            }
        }
    }

    public void validarRegistro(String palabra){
        for(String a: registros){
            if(palabra.toLowerCase().equalsIgnoreCase(a)){
                this.tipo = "registro";
            }
        }
    }

    public void validarNumeroDecimal(String palabra){
        if(palabra.toCharArray()[0]=='0'|| palabra.toCharArray()[0]=='1'|| palabra.toCharArray()[0]=='2'||palabra.toCharArray()[0]=='3'||
                palabra.toCharArray()[0]=='4'||palabra.toCharArray()[0]=='5'||palabra.toCharArray()[0]=='6'||palabra.toCharArray()[0]=='7'||
                palabra.toCharArray()[0]=='8'||palabra.toCharArray()[0]=='9') {
            setTipo("Numero Decimal");
        }

    }


    public static boolean esPar(int numero){
        return numero % 2 == 0;
    }


    public  void validarHexadecimal(String cadena){
        boolean validarHex = false;
        if(cadena.charAt(0) == '0'){
            for (int j = 0; j < cadena.length(); j++) {
                if (cadena.charAt(j) == '1' || cadena.charAt(j) == '2' || cadena.charAt(j) == '3' || cadena.charAt(j) == '4' || cadena.charAt(j) == '5' || cadena.charAt(j) == '6' || cadena.charAt(j) == '7' || cadena.charAt(j) == '8' || cadena.charAt(j) == '9' || cadena.charAt(j) == '0' || cadena.charAt(j) == 'A' || cadena.charAt(j) == 'B' || cadena.charAt(j) == 'C' || cadena.charAt(j) == 'D' || cadena.charAt(j) == 'E' || cadena.charAt(j) == 'F' || cadena.charAt(j) == 'H' || cadena.charAt(j)=='h' || cadena.charAt(j) == 'a' || cadena.charAt(j) == 'b' || cadena.charAt(j) == 'c' || cadena.charAt(j) == 'd' || cadena.charAt(j) == 'e' || cadena.charAt(j) == 'f') {
                    validarHex = esPar(cadena.length()-2);
                } else {
                    validarHex = false;
                    break;
                }

            } // Fin for
        }

        if(validarHex){
            setTipo("Hexadecimal");
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
            setTipo("Binario");
        }
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
