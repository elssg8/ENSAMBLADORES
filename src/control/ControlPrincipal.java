package control;

import vista.*;
import modelo.Data;


public class ControlPrincipal extends ControlAbs{

    /*
     * Controlador de la Ventana
     * Indica:
     *  Cuando Mostrarse
     *  Cuando Ocultarse
     */
    private Ventana myWindow;
    public ControlPrincipal(ControlVPrincipal CVP, Ventana myWindow) {
        this.myWindow = myWindow;
    }
    @Override
    public Data ejecutaComando(String c, Data d, Data d2) {
        switch(c) {
            case Comandos.INICIA:
                myWindow.setBounds(0, 0, 1000, 500);
                myWindow.setLocationRelativeTo(null);
                myWindow.setVisible(true);
                break;
        }
        return null;
    }
}
