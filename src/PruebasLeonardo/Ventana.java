package PruebasLeonardo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        panel.setSize(900,900);
        this.getContentPane().add(panel); // Agrega el panel a la ventana


        AreaIdentificacion();
        etiquetaAreaIden();
        AreaSeparacion();
        etiquetaAreaSep();
        Btn_Select_File();
        AreaArchivoASM();
        etiquetaAreaAASM();
    }

    private void etiquetaAreaAASM(){
        JLabel etiquetaAASM = new JLabel();
        etiquetaAASM.setText("ARCHIVO ASM ORIGINAL");
        etiquetaAASM.setBounds(150,10,150,50);
        panel.add(etiquetaAASM);
    }
    private void AreaArchivoASM (){
        JTextArea archivoASM = new JTextArea();
        archivoASM.setBounds(20,80,400,500);
        panel.add(archivoASM);
    }

    private void Btn_Select_File (){
        JButton btnSelectFile = new JButton("Seleccionar archivo");
        btnSelectFile.setBounds(150,650,150,30);
        panel.add(btnSelectFile);

        // Agregamos el evento que va a abrir la ventana de selección de archivos
        ActionListener accionBoton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File archivoSeleccionado;
                JFileChooser seleccionarArchivo;
                seleccionarArchivo = new JFileChooser();
                seleccionarArchivo.showOpenDialog(null);
                archivoSeleccionado = seleccionarArchivo.getSelectedFile();
            }
        };
        btnSelectFile.addActionListener(accionBoton);
    }

    private void etiquetaAreaSep(){
        JLabel etiquetaASep = new JLabel();
        etiquetaASep.setText("SEPARACION DE ELEMENTOS");
        etiquetaASep.setBounds(690,10,180,50);
        panel.add(etiquetaASep);
    }

    private void AreaSeparacion (){
        JTextArea AreaSeparacion = new JTextArea();
        AreaSeparacion.setBounds(580,80,400,500);
        panel.add(AreaSeparacion);
    }

    private void etiquetaAreaIden(){
        JLabel etiquetaAIden = new JLabel();
        etiquetaAIden.setText("IDENTIFICACION DE ELEMENTOS");
        etiquetaAIden.setBounds(1230,10,200,50);
        panel.add(etiquetaAIden);
    }

    private void AreaIdentificacion (){
        JTextArea AreaIdentificacion = new JTextArea();
        AreaIdentificacion.setBounds(1120,80,400,500);
        panel.add(AreaIdentificacion);
    }
}
