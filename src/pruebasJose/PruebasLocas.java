package pruebasJose;

public class PruebasLocas {

    /*
        Comprobar que cualquier num HEX, tenga:
        0    al principio
        h    al final
        Y siempre debe ser par, ejemplo:
        0H = CORRECTO
        0FH= INCORRECTO

        Nota: recordar que tanto 0 como H no se cuentan
        ya que solo es un requisito. Es por ello que
        al la cadena se le resta 2.
        cad = 0FFH
        cad.length()-2  --> FF
     */
    public static boolean esPar(int numero){
        return numero % 2 == 0;
    }

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
            return valido = "VALIDO :))";
        }

        return  valido;
    }



    public static void main(String[] args) {
        String prueba = "02H";
        PruebasLocas OBJ = new PruebasLocas();
        System.out.println(OBJ.validarHexadecimal(prueba));
    }

}
