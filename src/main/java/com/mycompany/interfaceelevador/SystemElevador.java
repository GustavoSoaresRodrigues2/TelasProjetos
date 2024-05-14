package com.mycompany.interfaceelevador;

import javax.swing.JOptionPane;

public class SystemElevador {
    
    public static int motor;
    public static int porta;
    public static int andar;

    public SystemElevador() {
        motor = 0;
        porta = 0;
        andar = 0;
    }

    public int getMotor() {
        return motor;
    }

    public void setMotor(int motorGet) {
        motor = motorGet;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int portaGet) {
        porta = portaGet;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andarGet) {
        andar = andarGet;
    }
    
    public void elevador(int input) {
        int andarAtual = getAndar();
        
        if (andarAtual < input) {
            setMotor(1);
            setPorta(1);
    
            JOptionPane.showMessageDialog(
                null, 
                "Fechando Porta.", 
                "Menu Elevador", 
                1
            );

            String verifPorta = "";
            if (getPorta() == 1) verifPorta = " (Fechada)";
            else verifPorta = " (Aberta)";

            String verifMotor = "";
            if (getMotor() == 1) verifMotor = " (Subindo)";
            else if (getMotor() == -1) verifMotor = " (Descendo)";

            String msg = "Porta: " + getPorta() + verifPorta + "\n" +
                         "Motor: " + getMotor() + verifMotor + "\n";
            for (int i = andarAtual + 1; i <= input; i++) {
                msg += "Passando pelo sensor do  " + i + "º Andar\n";
                if (i == input) {
                    msg += "Chegou no " + i + "º Andar\n";
                }
                setAndar(i);
            }
            JOptionPane.showMessageDialog(
                null, 
                msg, 
                "Menu Elevador", 
                1
            );

            setMotor(0);
            JOptionPane.showMessageDialog(
                null, 
                "Parando...", 
                "Menu Elevador", 
                1
            );

            setPorta(0);
            JOptionPane.showMessageDialog(
                null, 
                "Abrindo Porta.", 
                "Menu Elevador", 
                1
            );
        } else if (andarAtual > input) {
            setMotor(-1);
            setPorta(1);

            JOptionPane.showMessageDialog(
                null, 
                "Fechando Porta.", 
                "Menu Elevador", 
                1
            );

            String verifPorta = "";
            if (getPorta() == 1) verifPorta = " (Fechada)";
            else verifPorta = " (Aberta)";

            String verifMotor = "";
            if (getMotor() == 1) verifMotor = " (Subindo)";
            else if (getMotor() == -1) verifMotor = " (Descendo)";
    
            String msg = "Porta: " + getPorta() + verifPorta + "\n" +
                         "Motor: " + getMotor() + verifMotor + "\n";
            for (int i = andarAtual - 1; i >= input; i--) {
                if (i == 0) {
                    msg += "Passando pelo sensor do Térreo\n";
                    msg += "Chegou no Térreo\n";
                }                 
                else {
                    msg += "Passando pelo sensor do  " + i + "º Andar\n";
                }

                if (i == input) {
                    msg += "Chegou no " + i + "º Andar\n";
                }

                setAndar(i);
            }
            JOptionPane.showMessageDialog(
                null, 
                msg, 
                "Menu Elevador", 
                1
            );

            setMotor(0);
            JOptionPane.showMessageDialog(
                null, 
                "Parando...", 
                "Menu Elevador", 
                1
            );

            setPorta(0);
            JOptionPane.showMessageDialog(
                null, 
                "Abrindo Porta.", 
                "Menu Elevador", 
                1
            );
        } else {
            JOptionPane.showMessageDialog(
                null, 
                "Você já se encontra nesse andar.", 
                "Menu Elevador", 
                1
            );
        }
    }
}