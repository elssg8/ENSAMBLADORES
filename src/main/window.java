package main;

//import main.Separar;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import modelo.Analizador;

public class window extends JFrame {
    private JPanel panel;
    private JLabel lblASM;
    private JLabel lblSeparar;
    private JLabel lblPagActual;
    private JLabel lblSepararPagActual;
    private JTextArea txtArchivoASM;
    public static JTextArea txtSeparacion;
    private JButton btnSelectFile;
    private JButton btnSeparar;
    private JButton btnAtrasSeparar;
    private JButton btnSiguienteSeparar;
    private String fileName;
    private String path;
    private String archivo;
    private  int total_paginas;
    private  int pagina_actual;
    public int tamanio;
    private byte numLineasAImprimir = 15;
    private boolean abrio_correcto = false;
    ArrayList<String> lineas = new ArrayList<>();
    public static void main(String[] args) {
        window wn = new window();
        wn.setVisible(true);
    }

    public window() {
        super("Análisis lexicográfico");
        setSize(1000, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();
    }

    public void initComponents() {
        initPanel();
        initLabels();
        initTextAreaArchivoASM();
        initTextAreaSeparacion();
        btnSelectFile();
        btnSeparar();
        btnSiguienteSeparar();
        btnAtrasSeparar();
        btnPaginaAnterior();
        btnPaginaSiguiente();
        //buffer();

    }

    public void initPanel() {
        panel = new JPanel();
        panel.setLayout(null);

        this.getContentPane().add(panel).setBackground(Color.pink);
    }

    public void initLabels() {
        Font titulos = new Font("Times New Rome", Font.BOLD, 18);

        lblASM = new JLabel();
        lblASM.setText("Código Fuente .ASM");
        lblASM.setBounds(180, 10, 200, 50);
        lblASM.setFont(titulos);

        lblSeparar = new JLabel();
        lblSeparar.setText("Separación e identificación");
        lblSeparar.setBounds(600, 10, 300, 50);
        lblSeparar.setFont(titulos);

        lblPagActual = new JLabel();
        lblPagActual.setBounds(350,370, 150, 30);


        lblSepararPagActual = new JLabel();
        lblSepararPagActual.setBounds(700,370,150,30);


        panel.add(lblASM);
        panel.add(lblSeparar);
        panel.add(lblPagActual);
        panel.add(lblSepararPagActual);
    }

    public void initTextAreaArchivoASM() {
        txtArchivoASM = new JTextArea();
        txtArchivoASM.setBounds(40, 60, 400, 300);
        txtArchivoASM.setEditable(false);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        txtArchivoASM.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        panel.add(txtArchivoASM);
    }

    public void initTextAreaSeparacion() {
        txtSeparacion = new JTextArea();
        txtSeparacion.setBounds(500, 60, 400, 300);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        txtSeparacion.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        panel.add(txtSeparacion);
    }

    private void btnSelectFile(){
        btnSelectFile = new JButton("Open File");
        btnSelectFile.setBounds(40,400,150,30);
        btnSelectFile.setFont(new Font("Times New Roman",Font.BOLD,16));
        panel.add(btnSelectFile);

        ActionListener accionBoton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               /* JFileChooser file_chooser = new JFileChooser("C:/");
                // Indicamos la extension del archivo .asm
                FileNameExtensionFilter filtro_archivo = new FileNameExtensionFilter("ASM", "asm");
                file_chooser.setFileFilter(filtro_archivo);

                int option = file_chooser.showOpenDialog(null);
                if(option == JFileChooser.APPROVE_OPTION){
                    File archivoSeleccionado = file_chooser.getSelectedFile();

                    if (archivoSeleccionado.getName().endsWith(".asm")){
                        fileName = file_chooser.getSelectedFile().getName();
                        path = file_chooser.getSelectedFile().toString();

                        try(FileReader fileReader = new FileReader(archivoSeleccionado)){
                            archivo = "";
                            int valor = fileReader.read();
                            while (valor != -1){
                                archivo = archivo + (char) valor;
                                valor = fileReader.read();
                            }
                            ///////////////////////////////////

                            armarArchivo();
                        }  catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Error al abrir el archivo");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Archivo no valido");
                    }
                }*/
                buffer();
            }
        };
        btnSelectFile.addActionListener(accionBoton);
    }

    public void buffer(){
        JFileChooser file_chooser = new JFileChooser("C:/");
        // Indicamos la extension del archivo .asm
        FileNameExtensionFilter filtro_archivo = new FileNameExtensionFilter("ASM", "asm");
        file_chooser.setFileFilter(filtro_archivo);
        BufferedReader br = null;


        int option = file_chooser.showOpenDialog(null);
        if(option == JFileChooser.APPROVE_OPTION){
            File archivoSeleccionado = file_chooser.getSelectedFile();

            if (archivoSeleccionado.getName().endsWith(".asm")){
                fileName = file_chooser.getSelectedFile().getName();
                path = file_chooser.getSelectedFile().toString();

                try(FileReader fileReader = new FileReader(archivoSeleccionado)){
                    // Crear un objeto BufferedReader al que se le pasa
                    //   un objeto FileReader con el nombre del fichero
                    br = new BufferedReader(new FileReader(archivoSeleccionado));
                    // Leer la primera línea, guardando en un String
                    String texto = br.readLine();
                    // Repetir mientras no se llegue al final del fichero
                    while(texto != null) {
                        // Hacer lo que sea con la línea leída
                        // En este ejemplo sólo se muestra por consola
                        //System.out.println(texto);
                        lineas.add(texto);
                        // Leer la siguiente línea
                        texto = br.readLine();

                    }


                    archivo = "";
                    int valor = fileReader.read();
                    while (valor != -1){
                        archivo = archivo + (char) valor;
                        valor = fileReader.read();
                    }
                    ///////////////////////////////////

                    armarArchivo();
                }catch (FileNotFoundException ex) {
                    System.out.println("Error: Fichero no encontrado");
                    ex.printStackTrace();
                }
                catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al abrir el archivo");
                }
// Captura de cualquier otra excepción
                catch(Exception ex) {
                    System.out.println("Error de lectura del fichero");
                    ex.printStackTrace();
                }
// Asegurar el cierre del fichero en cualquier caso
                finally {
                    try {
                        // Cerrar el fichero si se ha podido abrir
                        if(br != null) {
                            br.close();
                        }
                    }
                    catch (Exception ex) {
                        System.out.println("Error al cerrar el fichero");
                        ex.printStackTrace();
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "Archivo no valido");
            }
        }
    }

    private void btnSeparar(){
        btnSeparar = new JButton("Separar");
        btnSeparar.setBounds(500,400,150,30);
        panel.add(btnSeparar);


        ActionListener accionBoton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (abrio_correcto = true){
                    Analizador analizar;
                    if(txtArchivoASM.getText().compareTo("") !=0){
                        analizar = new Analizador(lineas);
                        analizar.analizaArchivo();
                    } else {
                        JOptionPane.showMessageDialog(null,"Aun no haz Seleccionado  ningun archivo");
                    }
                }
            }
        };
        btnSeparar.addActionListener(accionBoton);
    }


    private void btnSiguienteSeparar() {
        btnSiguienteSeparar = new JButton("→");
        btnSiguienteSeparar.setFont(new Font("", Font.PLAIN, 25));
        btnSiguienteSeparar.setBounds(830,400,70,30);
        panel.add(btnSiguienteSeparar);
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Analizador.btnSiguiente();
            }
        };

        btnSiguienteSeparar.addActionListener(action);
    }

    private void btnAtrasSeparar(){
        btnAtrasSeparar = new JButton("←");
        btnAtrasSeparar.setFont(new Font("", Font.PLAIN, 25));
        btnAtrasSeparar.setBounds(750,400,70,30);
        panel.add(btnAtrasSeparar);

        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Analizador.btnAtras();
            }
        };

        btnAtrasSeparar.addActionListener(action);

    }

    public void btnPaginaAnterior(){
        JButton btn_pagina_anterior = new JButton("←");
        btn_pagina_anterior.setFont(new Font("", Font.PLAIN, 25));
        btn_pagina_anterior.setBounds(290,400,70,30);
        panel.add(btn_pagina_anterior);

        ActionListener btn_anterior = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pagina_actual > 1){
                    pagina_actual--;
                    mostrarArchivo();
                    lblPagActual.setText(pagina_actual + "/" + total_paginas);

                }
            }
        };
        btn_pagina_anterior.addActionListener(btn_anterior);
    }

    public void btnPaginaSiguiente(){
        JButton btn_pagina_siguiente = new JButton("→");
        btn_pagina_siguiente.setFont(new Font("", Font.PLAIN, 25));
        btn_pagina_siguiente.setBounds(370,400,70,30);
        panel.add(btn_pagina_siguiente);

        ActionListener btn_siguiente = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pagina_actual < total_paginas){
                    pagina_actual++;
                    mostrarArchivo();
                    lblPagActual.setText(pagina_actual + "/" + total_paginas);
                }
            }
        };
        btn_pagina_siguiente.addActionListener(btn_siguiente);
    }



    // Metodos

    public void armarArchivo() {
        int cont = 1;
        total_paginas = 1;
        tamanio = archivo.length();

        for (int i = 0; i < tamanio; i++) {
            if (archivo.charAt(i) == '\n') {
                if (cont == 15) {
                    total_paginas++;
                    cont = 1;
                } else {
                    cont++;
                }
            }
        }
        pagina_actual = 1;
        mostrarArchivo();
        lblPagActual.setText(pagina_actual + "/" + total_paginas);
        lblPagActual.setFont(new Font("Times New Roman", Font.BOLD,18));
        abrio_correcto = true;
    }
    public void mostrarArchivo(){
        int cont = 1;
        int renglon_objetivo = pagina_actual;
        int renglon_buscador = 1;
        String pagina = "";
        int i = 0;

        while (renglon_buscador < renglon_objetivo){
            if (archivo.charAt(i) == '\n'){
                if (cont == numLineasAImprimir){
                    renglon_buscador++;
                    cont = 1;
                }else {
                    cont++;
                }
            }
            i++;
        }


        while (renglon_buscador <= renglon_objetivo && i < tamanio){
            pagina += archivo.charAt(i);
            if (archivo.charAt(i) == '\n'){
                if (cont == numLineasAImprimir){
                    renglon_buscador++;
                    cont = 1;
                }else {
                    cont++;
                }
            }
            i++;
        }
        txtArchivoASM.setText(pagina);
    }

}
