package pruebasJose;

public class PruebasLocas {

    /*
        --------------------- HEXADECIMAL
        Nota: recordar que tanto 0 como H no se cuentan
        ya que solo es un requisito. Es por ello que
        al la cadena se le resta 2.
        cad = 0FFH
        cad.length()-2  --> FF
        Es por ello que uso el metodo esPar()

        --------------------- BINARIO
        En este caso solo la B al final de la cadena
        es un requisito entonces solo le restamos 1
        al tamanio de la cadena y comprobamos que sea
        par.
     */
    public static boolean esPar(int numero){
        return numero % 2 == 0;
    }


    /*
        Comprobar que cualquier num HEX, tenga:
        0    al principio
        h    al final
        Y siempre debe ser par, ejemplo:
        0H = CORRECTO
        0FH= INCORRECTO
    */
    public  String validarHexadecimal(String cadena){
        String  valido  = "NO VALIDO :((";
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
             valido = " HEX VALIDO :))";
        }

        return  valido;
    }


    public String validarBinario(String cadena){
        String valido = "NO VALIDO";
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
           valido ="OK";
        }

        return  valido;
    }


    public static void main(String[] args) {

        PruebasLocas test = new PruebasLocas();
        // Prueba para validar un numero HEX
        String hexadecimal = "012H";
        System.out.println(test.validarHexadecimal(hexadecimal));


        // Prueba para validar un numero BINARIO
        String binario = "b1010b";
        System.out.println(test.validarBinario(binario));

    }

}
