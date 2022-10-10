package PruebasLeonardo;
import javax.swing.*;
import java.io.*;

public class Ventana extends JFrame{
    public JPanel panel;
    public Ventana(){
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Establece la ventana completa
        //this.setSize(500,500); // Establece el tamaño de la ventana
        setTitle("Análisis lexicográfico"); // Establecer título a la ventana
        setLocationRelativeTo(null); // Establecemos posición inicial de la ventana, en el centro

        iniciarComponentes(); // llamada al método iniciarComponentes
        setDefaultCloseOperation(EXIT_ON_CLOSE); // termina la ejecución del programa
    }

    private void iniciarComponentes(){
        panel = new JPanel(); // Creacion de un panel
        panel.setLayout(null); // Desactivamos los diseños predeterminados del panel
        this.getContentPane().add(panel); // Agrega el panel a la ventana

        AreaIdentificacion();
        AreaSeparacion();
        AreaArchivoASM();
    }

    private void AreaArchivoASM (){
        JTextArea archivoASM = new JTextArea();
        archivoASM.setBounds(20,20,400,500);
        panel.add(archivoASM);
    }

    private void AreaSeparacion (){
        JTextArea AreaSeparacion = new JTextArea();
        AreaSeparacion.setBounds(500,20,400,500);
        panel.add(AreaSeparacion);
    }

    private void AreaIdentificacion (){
        JTextArea AreaIdentificacion = new JTextArea();
        AreaIdentificacion.setBounds(1000,20,400,500);
        panel.add(AreaIdentificacion);
    }
}
