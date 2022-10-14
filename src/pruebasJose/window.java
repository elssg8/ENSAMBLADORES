package pruebasJose;

//import PruebasLeonardo.Separar;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import PruebasLeonardo.*;
import modelo.Analizador;

public class window extends JFrame {
    private JPanel panel;
    private JLabel lblASM;
    private JLabel lblSeparar;
    private JLabel lblIden;
    private JLabel lblPagActual;
    private JLabel lbl_pagina_elementos;
    private JTextArea txtArchivoASM;
    private JTextArea txtIndentificacion;
    private JTextArea txtSeparacion;
    private JButton btnSelectFile;

    private JButton btnSeparar;
    private JButton btnAtrasSeparar;
    private JButton btnSiguienteSeparar;


    private String nombre_archivo;
    private String ruta_archivo;
    private String archivo;

    private static int total_paginas;
    private static int pagina_actual;

    int tamaño;
    private byte imprimir = 15;

    private boolean abrio_correcto = false;
    Separar clase_separar = new Separar(this);


    public static void main(String[] args) {
        window wn = new window();
        wn.setVisible(true);
    }

    public window() {
        super("Análisis lexicográfico");
        setSize(1500, 700);
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
        //initTextAreaIndentifiacion();
        btnSelectFile();
        btnSeparar();
        btnSiguienteSeparar();
        btnAtrasSeparar();
        btnPaginaAnterior();
        btnPaginaSiguiente();
  
    }

    public void initPanel() {
        panel = new JPanel();
        panel.setLayout(null);

        this.getContentPane().add(panel).setBackground(Color.pink);
    }

    public void initLabels() {
        Font titulos = new Font("Times New Rome", Font.BOLD, 18);

        lblASM = new JLabel();
        lblASM.setText("Codigo Fuente .ASM");
        lblASM.setBounds(180, 10, 200, 50);
        lblASM.setFont(titulos);

        lblSeparar = new JLabel();
        lblSeparar.setText("Separacion de Elementos");
        lblSeparar.setBounds(600, 10, 250, 50);
        lblSeparar.setFont(titulos);

        lblIden = new JLabel();
        lblIden.setText("Indentifiacion de Elementos");
        lblIden.setBounds(1100, 10, 280, 50);
        lblIden.setFont(titulos);

        lblPagActual = new JLabel();
        lblPagActual.setBounds(350,370, 150, 30);

        lbl_pagina_elementos = new JLabel();
        lbl_pagina_elementos.setBounds(850, 650, 150, 30);

        panel.add(lblASM);
        panel.add(lblSeparar);
        panel.add(lblIden);
        panel.add(lblPagActual); // CHECAR
        panel.add(lbl_pagina_elementos); // CHECAR
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

   /* public void initTextAreaIndentifiacion() {
        txtIndentificacion = new JTextArea();
        txtIndentificacion.setBounds(1000, 60, 400, 300);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        txtIndentificacion.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        panel.add(txtIndentificacion);
    }*/


    public void armarArchivo() {
        int cont = 1;
        total_paginas = 1;
        tamaño = archivo.length();

        for (int i = 0; i < tamaño; i++) {
            if (archivo.charAt(i) == '\n') {
                if (cont == imprimir) {
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
        clase_separar.archivo = archivo;

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
                if (cont == imprimir){
                    renglon_buscador++;
                    cont = 1;
                }else {
                    cont++;
                }
            }
            i++;
        }


        while (renglon_buscador <= renglon_objetivo && i < tamaño){
            pagina += archivo.charAt(i);
            if (archivo.charAt(i) == '\n'){
                if (cont == imprimir){
                    renglon_buscador++;
                    cont = 1;
                }else {
                    cont++;
                }
            }
            i++;
        }
        txtArchivoASM.setText(pagina);
        //mostrarASM(pagina);
    }

    public void mostrarASM(String dato){//Metodo que nos sirve para ser utilizado en otras clases y mostrar texto en el textArea
        txtArchivoASM.setText(dato);
    }
    private void btnSelectFile(){
        JButton btnSelectFile = new JButton("Seleccionar archivo");
        btnSelectFile.setBounds(40,400,150,30);
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
                        analizar = new Analizador(txtArchivoASM.getText());
                        txtSeparacion.setText(analizar.analizaArchivo());
                    } else {
                        JOptionPane.showMessageDialog(null,"Aun no haz Seleccionado  ningun archivo");
                    }
                }
            }
        };
        btnSeparar.addActionListener(accionBoton);
    }


    private void btnSiguienteSeparar() {
        JButton btn_siguiente = new JButton("→");
        btn_siguiente.setFont(new Font("", Font.PLAIN, 25));
        btn_siguiente.setBounds(830,400,70,30);
        panel.add(btn_siguiente);
    }

    private void btnAtrasSeparar(){
        JButton btn_atras = new JButton("←");
        btn_atras.setFont(new Font("", Font.PLAIN, 25));
        btn_atras.setBounds(750,400,70,30);
        panel.add(btn_atras);

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



}
