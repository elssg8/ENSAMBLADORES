package main;

import  control.*;
import vista.*;


public class Principal {

    public static void main(String[] args) {
        //VARIABLES NECESARIAS
        ControlPrincipal CP; //Control principal

        Ventana Vent;
        ControlVPrincipal CV; //VENTANA Y SU CONTROL

        //CREACION DE LOS OBJETOS
        CV = new ControlVPrincipal();
        Vent = new Ventana();

        CP = new ControlPrincipal(CV, Vent);//VENTANA Y CONTROL EN EL PRINCIPAL


        //CONTROL DE LA VENTANA
        //Vent.setControl(CV);



        //INICIA EL PROGRAMA
        CP.ejecutaComando(Comandos.INICIA, null, null);


    }

}
