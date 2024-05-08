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
    
            String msg = "Subindo...\n" +
                         "Motor: " + getMotor() + "\n";
            for (int i = andarAtual + 1; i <= input; i++) {
                if (i == input) {
                    msg += "Chegou no " + i + "º Andar\n";
                } else {
                    msg += "Passando pelo " + i + "º Andar\n";
                }
                setAndar(i);
            }
            JOptionPane.showMessageDialog(
                null, 
                msg, 
                "Menu Elevador", 
                1
            );
    
            setPorta(0);
        } else if (andarAtual > input) {
            setMotor(-1);
            setPorta(1);
    
            String msg = "Descendo...\n" +
                         "Motor: " + getMotor() + "\n";
            for (int i = andarAtual - 1; i >= input; i--) {
                if (i == 0) {
                    msg += "Chegou no Térreo\n";
                } else if (i == input) {
                    msg += "Chegou no " + i + "º Andar\n";
                } else {
                    msg += "Passando pelo " + i + "º Andar\n";
                }
                setAndar(i);
            }
            JOptionPane.showMessageDialog(
                null, 
                msg, 
                "Menu Elevador", 
                1
            );
    
            setPorta(0);
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