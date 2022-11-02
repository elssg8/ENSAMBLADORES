package modelo;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class ValidarSintaxys {
    private String linea;
    private String validar;


    public  ValidarSintaxys(String linea){
        this.linea = linea;
        if(validarSegmentoPila(this.linea)){
            this.validar = "\tCorrecto";
        }else {
            this.validar = "\tIncorrecto";
        }



    }



    public boolean validarSegmentoPila(String linea){
        if(linea.toLowerCase().equals(".stack segment")){
             return true;
            // this.validar ="\tCorrecto";
        }
        return  false;
    }

    // Validar dw , db
    public boolean validarDatos(String linea){
        StringTokenizer st = new StringTokenizer(linea);
        ArrayList<String> analizar = new ArrayList<>();
        while (st != null){
            System.out.println(st.nextToken());
            analizar.add(st.nextToken());
        }

        boolean isVariable = (analizar.get(0).toLowerCase().matches("[a-z0-9]{6}"));
        boolean isTypeDataDw = (analizar.get(1).toLowerCase().matches("dw"));
        boolean isTypeDataDb = (analizar.get(1).toLowerCase().matches("db"));


        if(isVariable && (isTypeDataDw || isTypeDataDb)){
            return true;
        }
        //boolean isWordType  = (linea.toLowerCase().matches("dw"));
        return  false;
    }


    public static void main(String[] args) {
        ValidarSintaxys vm = new ValidarSintaxys("");
        System.out.println(vm.validarDatos(" var4 dw 40 dup(' ')"));
    }
    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getValidar() {
        return validar;
    }

    public void setValidar(String validar) {
        this.validar = validar;
    }

    @Override
    public String toString() {
        return  this.linea +"\t" + this.validar + "\n";
    }
}
