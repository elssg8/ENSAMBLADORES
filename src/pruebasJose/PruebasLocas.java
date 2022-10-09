package pruebasJose;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
        String validando = "NO VALIDO";
        boolean aux = false;

        if(cadena.toLowerCase().charAt(0)=='0' && cadena.toLowerCase().charAt(cadena.length()-1)=='h'){
            aux = true;
            aux = esPar(cadena.length() - 2);
        }

        if(aux){
            validando = "VALIDO :))";
        }

        return validando;
    }



    public static void main(String[] args) {
        String prueba = "0FFH";
        PruebasLocas OBJ = new PruebasLocas();
        System.out.println(OBJ.validarHexadecimal(prueba));
    }

}
