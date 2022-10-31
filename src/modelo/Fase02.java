package modelo;

public class Fase02 {
    private String linea;
    private String estado;
    public Fase02(String linea){
        this.linea = linea;
        this.estado = "";
        validarSegmentoPila(this.linea);
    }


    public void validarSegmentoPila(String linea){
        if(linea.toLowerCase().equals(".stack segment")){
            this.estado ="\tCorrecto";
        }
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
