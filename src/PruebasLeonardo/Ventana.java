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
     static JTextArea txt_area_separacion;
     static JTextArea txt_area_identificacion;
     static JLabel etiqueta_pagina_actual;
    static JLabel etiqueta_pagina_E;
    private String nombre_archivo;
    private String ruta_archivo;
    private String archivo;
    private static int total_paginas;
    private static int pagina_actual;
     int tamaño;
    private byte imprimir = 15;
    private boolean abrio_correcto = false;

    Separar clase_separar = new Separar(this);
    public Ventana(){
        super("Análisis lexicográfico");
        setSize(1500, 1000);
        setResizable(false);
        setLocationRelativeTo(null);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        iniciarComponentes();
    }

    private void iniciarComponentes(){
        initPanel();
        AreaArchivoASM();
        
        
        AreaIdentificacion();
        AreaSeparacion();
        btnSelectFile();
        btnSeparar();
        btnPaginaAnterior();
        btnPaginaSiguiente();
        btnAtrasSeparar();
        btnSiguienteSeparar();
        AreaArchivoASM();
        etiquetas();
    }


    public void initPanel() {
        panel = new JPanel();
        panel.setLayout(null);

        this.getContentPane().add(panel);
    }
    
    

    private void etiquetas(){
        JLabel etiquetaAASM = new JLabel();
        etiquetaAASM.setText("ARCHIVO ASM ORIGINAL");
        etiquetaAASM.setBounds(150,10,150,50);
        panel.add(etiquetaAASM);

        JLabel etiquetaASep = new JLabel();
        etiquetaASep.setText("SEPARACION DE ELEMENTOS");
        etiquetaASep.setBounds(690,10,180,50);
        panel.add(etiquetaASep);

        JLabel etiquetaAIden = new JLabel();
        etiquetaAIden.setText("IDENTIFICACION DE ELEMENTOS");
        etiquetaAIden.setBounds(1230,10,200,50);
        panel.add(etiquetaAIden);

        etiqueta_pagina_actual = new JLabel();
        etiqueta_pagina_actual.setBounds(450,650,150,30);
        panel.add(etiqueta_pagina_actual);

        etiqueta_pagina_E = new JLabel();
        etiqueta_pagina_E.setBounds(850,650,150,30);
        panel.add(etiqueta_pagina_E);



    }


    public static void mostrarASM(String dato){//Metodo que nos sirve para ser utilizado en otras clases y mostrar texto en el textArea
        txtA_archivoASM.setText(dato);
    }

    public static void mostrarSeparado(String dato){//Metodo que nos sirve para ser utilizado en otras clases y mostrar texto en el textArea
        txtA_archivoASM.setText(dato);
    }

    public static void mostrarLexema(String dato){//Metodo que nos sirve para ser utilizado en otras clases y mostrar texto en el textArea
        txtA_archivoASM.setText(dato);
    }

    private void btnSelectFile(){
        JButton btnSelectFile = new JButton("Seleccionar archivo");
        btnSelectFile.setBounds(50,650,150,30);
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
        JButton btn_separar = new JButton("Separar");
        btn_separar.setBounds(500,650,150,30);
        panel.add(btn_separar);

        ActionListener accionBoton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (abrio_correcto = true){
                    clase_separar.instruccionesEquipo2();
                    clase_separar.separarElementos();
                }
            }
        };
        btn_separar.addActionListener(accionBoton);
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
        etiqueta_pagina_actual.setText(pagina_actual + "/" + total_paginas);
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
        txtA_archivoASM.setText(pagina);
        //mostrarASM(pagina);
    }

    public void btnPaginaAnterior(){
        JButton btn_pagina_anterior = new JButton("Pagina Anterior");
        btn_pagina_anterior.setBounds(50,750,150,30);
        panel.add(btn_pagina_anterior);

        ActionListener btn_anterior = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pagina_actual > 1){
                    pagina_actual--;
                    mostrarArchivo();
                    etiqueta_pagina_actual.setText(pagina_actual + "/" + total_paginas);
                }
            }
        };
        btn_pagina_anterior.addActionListener(btn_anterior);
    }

    public void btnPaginaSiguiente(){
        JButton btn_pagina_siguiente = new JButton("Pagina Siguiente");
        btn_pagina_siguiente.setBounds(250,750,150,30);
        panel.add(btn_pagina_siguiente);

        ActionListener btn_siguiente = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pagina_actual < total_paginas){
                    pagina_actual++;
                    mostrarArchivo();
                    etiqueta_pagina_actual.setText(pagina_actual + "/" + total_paginas);
                }
            }
        };
        btn_pagina_siguiente.addActionListener(btn_siguiente);
    }
    private void AreaArchivoASM (){
        txtA_archivoASM= new JTextArea();
        txtA_archivoASM.setBounds(20,80,480,500);
        txtA_archivoASM.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
        txtA_archivoASM.setEditable(false);
        panel.add(txtA_archivoASM);
    }
    private void AreaSeparacion (){
        txt_area_separacion = new JTextArea();
        txt_area_separacion.setBounds(580,80,400,500);
        panel.add(txt_area_separacion);
    }

    private void AreaIdentificacion (){
        txt_area_identificacion = new JTextArea();
        txt_area_identificacion.setBounds(1120,80,400,500);
        panel.add(txt_area_identificacion);
    }

    private void btnSiguienteSeparar() {
        JButton btn_siguiente = new JButton("Pagina Siguente");
        btn_siguiente.setBounds(750,750,150,30);
        panel.add(btn_siguiente);

        ActionListener accionBoton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                if (Separar.paginaL < Separar.paginasL) {
                    Separar.paginaL++;
                    Separar.paginaE++;
                    Separar.mostrarElementos();
                    Separar.mostrarLexema();
                    etiqueta_pagina_E.setText(Separar.paginaE + "/" + Separar.paginasE);
                }
            }
        };
        btn_siguiente.addActionListener(accionBoton);
    }

    private void btnAtrasSeparar(){
        JButton btn_atras = new JButton("Pagina Anterior");
        btn_atras.setBounds(500,750,150,30);
        panel.add(btn_atras);

        ActionListener accionBoton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Separar.paginaL > 1) {
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
}
