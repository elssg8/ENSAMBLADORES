package PruebasLeonardo;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Ventana extends JFrame{
    public JPanel panel;
    private static JTextArea txtA_archivoASM;
    private String nombre_archivo;
    private String ruta_archivo;
    private String archivo;
    private int total_paginas;
    private int pagina_actual;
    private int tamaño;
    private byte imprimir = 15;
    private boolean abrio_correcto = false;
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
        txtA_archivoASM= new JTextArea();
        txtA_archivoASM.setBounds(20,80,400,500);
        txtA_archivoASM.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        panel.add(txtA_archivoASM);
    }

    public static void mostrarASM(String dato){//Metodo que nos sirve para ser utilizado en otras clases y mostrar texto en el textArea
        txtA_archivoASM.append(dato);
    }

    private void Btn_Select_File (){
        JButton btnSelectFile = new JButton("Seleccionar archivo");
        btnSelectFile.setBounds(150,650,150,30);
        panel.add(btnSelectFile);

        // Agregamos el evento que va a abrir la ventana de selección de archivos
        ActionListener accionBoton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser file_chooser = new JFileChooser();
                FileNameExtensionFilter filtro_archivo = new FileNameExtensionFilter("ASM", "asm");
                file_chooser.setFileFilter(filtro_archivo);

                int option = file_chooser.showOpenDialog(null);
                if(option == JFileChooser.APPROVE_OPTION){
                    File archivoSeleccionado = file_chooser.getSelectedFile();
                    if (archivoSeleccionado.getName().endsWith(".asm")){
                        nombre_archivo = file_chooser.getSelectedFile().getName();
                        ruta_archivo = file_chooser.getSelectedFile().toString();
                        try(FileReader fr = new FileReader(archivoSeleccionado)){
                            archivo = "";
                            int valor = fr.read();
                            while (valor != -1){
                                archivo = archivo + (char) valor;
                                valor = fr.read();
                            }
                            armarArchivo();
                        }  catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Error al abrir el archivo");
                        }
                    }
                }
            }
        };
        btnSelectFile.addActionListener(accionBoton);
    }

    public void armarArchivo(){
        int cont = 1;
        total_paginas = 1;
        tamaño = archivo.length();

        for (int i = 0; i < tamaño; i++){
            if (archivo.charAt(i) == '\n'){
                if (cont == imprimir){
                    total_paginas++;
                    cont = 1;
                }else {
                    cont++;
                }
            }
        }

        pagina_actual = 1;
        mostrarArchivo();

    }

    public void mostrarArchivo(){
        int cont = 1;
        int renglon_objetivo = pagina_actual;
        int renglon_buscado = 1;
        String pagina = "";
        int i = 0;

        while (renglon_buscado < renglon_objetivo){
            if (archivo.charAt(i) == '\n'){
                if (cont == imprimir){
                    renglon_buscado++;
                    cont = 1;
                }else {
                    cont++;
                }
            }
            i++;
        }

        while (renglon_buscado <= renglon_objetivo && i < tamaño){
            pagina += archivo.charAt(i);
            if (archivo.charAt(i) == '\n'){
                if (cont == imprimir){
                    renglon_buscado++;
                    cont = 1;
                }else {
                    cont++;
                }
            }
            i++;
        }
        mostrarASM(pagina);
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
