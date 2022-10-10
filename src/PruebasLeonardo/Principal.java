package PruebasLeonardo;

import javax.swing.*;
import java.io.File;

public class Principal {
    public static void main(String[] args) {
        Ventana v1 = new Ventana(); // Objeto para la clase ventana
        File archivoSeleccionado;
        JFileChooser seleccionarArchivo;
        seleccionarArchivo = new JFileChooser();
        seleccionarArchivo.showOpenDialog(null);
        archivoSeleccionado = seleccionarArchivo.getSelectedFile();
        v1.setVisible(true); // muestra la ventana
    }
}
