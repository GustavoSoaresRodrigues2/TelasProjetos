/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.maquinadedoce;

import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class MaquinaDeDoce {

    public static int valor = 0;

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        MaquinaDeDoce.valor = valor;
    }

    public void somaComVerif(int indice) {
        switch (indice) {
            case 1:
                if (valor + 1 > 10) {
                    JOptionPane.showMessageDialog(
                            null,
                            "O saldo máximo possivel é de R$10.00",
                            "MÁXIMO VALOR ATINGIDO",
                            1);
                } else {
                    valor += 1;
                }
                break;
            case 2:
                if (valor + 2 > 10) {
                    JOptionPane.showMessageDialog(
                            null,
                            "O saldo máximo possivel é de R$10.00",
                            "MÁXIMO VALOR ATINGIDO",
                            1);
                } else {
                    valor += 2;
                }
                break;
            case 5:
                if (valor + 5 > 10) {
                    JOptionPane.showMessageDialog(
                            null,
                            "O saldo máximo possivel é de R$10.00",
                            "MÁXIMO VALOR ATINGIDO",
                            1);
                } else {
                    valor += 5;
                }
                break;
            default:
                break;
        }
    }

    public void pedirDoce(int indice) {
        switch (indice) {
            case 6:
                if (valor < 6) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Você não tem saldo para compra esse doce !",
                            "Saldo Insuficiente !",
                            1);
                } else {
                    valor -= 6;
                    JOptionPane.showMessageDialog(
                            null,
                            "BOMBOM entrege :D aproveite !",
                            "Pedido Feito !",
                            1);
                }
                break;
            case 7:
                if (valor < 7) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Você não tem saldo para compra esse doce !",
                            "Saldo Insuficiente !",
                            1);
                } else {
                    valor -= 7;
                    JOptionPane.showMessageDialog(
                            null,
                            "TRIDENT entrege :D aproveite !",
                            "Pedido Feito !",
                            1);
                }
                break;
            case 8:
                if (valor < 8) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Você não tem saldo para compra esse doce !",
                            "Saldo Insuficiente !",
                            1);
                } else {
                    valor -= 8;
                    JOptionPane.showMessageDialog(
                            null,
                            "ALFAJOR entrege :D aproveite !",
                            "Pedido Feito !",
                            1);
                }
                break;

            default:
                break;
        }
    }

    public void resgatarSaldo() {
        if (valor != 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "Você está regatando R$" + valor + " de saldo.",
                    "Resgate de Saldo",
                    1);
            valor = 0;
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Você não tem saldo a ser resgatado.",
                    "Resgate de Saldo",
                    1);
        }
    }
}

// LISTA CODIGO BACKUP

// if (MaquinaDeDoce.getValor() >= 6) {
// } else {
// }
 
// MaquinaDeDoce MaquinaDeDoce = new MaquinaDeDoce();
