package control;
import modelo.Data;


public interface Control {

    public Data ejecutaComando(String c, Data d, Data d2);
    void setCP(Control c);
}
