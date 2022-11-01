package modelo;

public class ValidarSintaxys {
    private String linea;
    private String validar;


    public  ValidarSintaxys(String linea){
        this.linea = linea;
        validarSegmentoPila(this.linea);

    }

    public void validarSegmentoPila(String linea){
        if(linea.toLowerCase().equals(".stack segment")){
            this.validar ="\tCorrecto";
        } else {
            this.validar ="\tNo valido";
        }
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
