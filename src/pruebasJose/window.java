package pruebasJose;

import PruebasLeonardo.Separar;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class window extends JFrame {
    private JPanel panel;
    private JLabel lblASM;
    private JLabel lblSeparar;
    private JLabel lblIden;
    private JLabel lblPagActual;
    private JLabel lblPagE;
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
        setSize(1500, 520);
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
        initTextAreaIndentifiacion();
        initButtons();
    }

    public void initPanel() {
        panel = new JPanel();
        panel.setLayout(null);

        this.getContentPane().add(panel);
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
        lblPagActual.setBounds(450, 650, 150, 30);

        lblPagE = new JLabel();
        lblPagE.setBounds(850, 650, 150, 30);

        panel.add(lblASM);
        panel.add(lblSeparar);
        panel.add(lblIden);
        panel.add(lblPagActual);
        panel.add(lblPagE);
    }

    public void initTextAreaArchivoASM() {
        txtArchivoASM = new JTextArea();
        txtArchivoASM.setBounds(40, 60, 400, 300);
        txtArchivoASM.setEditable(false);

        panel.add(txtArchivoASM);
    }

    public void initTextAreaSeparacion() {
        txtSeparacion = new JTextArea();
        txtSeparacion.setBounds(500, 60, 400, 300);

        panel.add(txtSeparacion);
    }

    public void initTextAreaIndentifiacion() {
        txtIndentificacion = new JTextArea();
        txtIndentificacion.setBounds(1000, 60, 400, 300);

        panel.add(txtIndentificacion);
    }

    public void initButtons() {
        btnSelectFile = new JButton("Open File");
        btnSelectFile.setBounds(200, 380, 100, 30);
        btnSelectFile.setFont(new Font("Times New Roman", Font.BOLD, 14));


        ActionListener listenerSelectFile = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser file_chooser = new JFileChooser();
                FileNameExtensionFilter filtro_archivo = new FileNameExtensionFilter("ASM", "asm");
                file_chooser.setFileFilter(filtro_archivo);

                int option = file_chooser.showOpenDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File archivoSeleccionado = file_chooser.getSelectedFile();
                    if (archivoSeleccionado.getName().endsWith(".asm")) {
                        nombre_archivo = file_chooser.getSelectedFile().getName();
                        ruta_archivo = file_chooser.getSelectedFile().toString();
                        try (FileReader fr = new FileReader(archivoSeleccionado)) {
                            archivo = "";
                            int valor = fr.read();
                            while (valor != -1) {
                                archivo = archivo + (char) valor;
                                valor = fr.read();
                            }
                            armarArchivo();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Error al abrir el archivo");
                        }
                    }
                }
            }
        };// Fin ActionListener

        btnSelectFile.addActionListener(listenerSelectFile);
        panel.add(btnSelectFile);


        // Boton de Atras
        btnAtrasSeparar = new JButton("Atras");
        btnAtrasSeparar.setBounds(500,400,150,30);

        ActionListener accionBoton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Separar > 1) {
                    Separar.paginaL--;
                    Separar.paginaE--;
                    Separar.mostrarElementos();
                    Separar.mostrarLexema();
                    etiqueta_pagina_E.setText(Separar.paginaE + "/" + Separar.paginasE);
                }
            }
        };
        btn_atras.addActionListener(accionBoton);






    }


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
    }






}
