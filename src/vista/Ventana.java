package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;


import control.*;
import modelo.*;
import modelo.Archivo;

@SuppressWarnings("serial")
public class Ventana extends VentanaAGeneral{

    JPanel PD, PI, PA, subP, subP2;
    JLabel CA, selecciona, analizado;
    JTextArea iz, der;
    JFileChooser buscador;
    JButton buscar, analizar;
    JScrollPane sI, sD;


    public Ventana() {
        super("Análisis léxico gráfico");

        //CREACION DE PANELES
        PA = new JPanel();
        PA.setBorder(new EmptyBorder(5,5,5,5));
        //PA.setBackground(Color.BLACK);

        PI = new JPanel(new GridLayout(2,1));
        PI.setBorder(new EmptyBorder(5,5,5,5));
        PI.setBackground(Color.BLUE);
        subP = new JPanel(new GridLayout(1,2));
        subP.setBorder(new EmptyBorder(60,5,60,5));

        PD = new JPanel(new GridLayout(2,1));
        PD.setBorder(new EmptyBorder(5,5,5,5));
        PD.setBackground(new Color(63,65,60));
        subP2 = new JPanel(new GridLayout(1,2));
        subP2.setBorder(new EmptyBorder(60,5,60,5));


        //ELEMENTOS PANEL ARRIBA
        CA = new JLabel("<html><center>Analizador léxico gráfico <br>Realizado por:<br> <ul>Ulises Becerril Valdés<br>"
                + "Rene Ramírez Vargas<br>"
                + "Diana Zepeda Martínez</ul></center></html>");
        PA.add(CA);

        //ELEMENTOS PANEL IZQUIERDA
        //SUBPANEL IZQUIERDO
        selecciona = new JLabel("Selecciona el archivo a analizar:");
        subP.add(selecciona);

        buscar = new JButton("Seleccionar archivo:");
        buscar.setActionCommand(Comandos.BUSCA);
        buscar.addActionListener(this);
        subP.add(buscar);

        //SCROLL IZQUIERDO
        iz = new JTextArea("", 1,37);
        iz.setEditable(false);

        sI = new JScrollPane(iz, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        PI.add(subP);
        PI.add(sI);

        //ELEMENTOS PANEL DERECHA
        //SUBPANEL DERECHO
        analizado = new JLabel("Texto analizado:");
        subP2.add(analizado);

        analizar = new JButton("Analizar archivo");
        analizar.setActionCommand(Comandos.ANALIZA);
        analizar.addActionListener(this);
        subP2.add(analizar);

        PD.add(subP2);

        //SCROLL DERECHO
        der = new JTextArea("", 1, 40);
        der.setEditable(false);

        sD = new JScrollPane(der, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        PD.add(sD);

        //COLOCAR LSO PANELES EN LA VENTANA
        this.add(PA, BorderLayout.NORTH);
        this.add(PI, BorderLayout.WEST);
        this.add(PD, BorderLayout.EAST);

        //this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Comandos.BUSCA:
                buscador = new JFileChooser();
                buscador.showOpenDialog(this);
                try {
                    String ruta = buscador.getSelectedFile().getAbsolutePath();
                    Archivo archivo = new Archivo(ruta);
                    iz.setText(archivo.toString());
                }catch(NullPointerException r){
                    JOptionPane.showMessageDialog(this, "Ningún archivo seleccionado");
                }

                break;

            case Comandos.ANALIZA:
                Analizador anl;

                if(this.iz.getText().compareTo("")!=0) {
                    anl = new Analizador(iz.getText());

                    der.setText(anl.analizaArchivo());
                }else {
                    JOptionPane.showMessageDialog(this, "Aún no haz seleccionado ningún archivo");
                }
                break;
        }
    }

}
