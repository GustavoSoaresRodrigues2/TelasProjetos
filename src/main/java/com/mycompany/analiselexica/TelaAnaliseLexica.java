/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.analiselexica;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

/** 
 *
 * @author 823133821
 */
public class TelaAnaliseLexica extends javax.swing.JFrame {
    
    /**
     * Creates new form TelaAnaliseLexica
     */
    public TelaAnaliseLexica() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Escreva o codigo que queira analisar:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel1)
                        .addGap(0, 99, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaAnaliseLexica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAnaliseLexica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAnaliseLexica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAnaliseLexica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAnaliseLexica().setVisible(true);
            }
        });

        String codigoJava = jTextArea1.getText();

        ArrayList<Token> tokens = analisar(codigoJava);

        String msg = "";
        for (Token token : tokens) {
            msg = msg + " " + token;
        }

        JOptionPane.showMessageDialog(
            null, 
            "tokens", 
            "Lista de Tokens: " + msg, 
            ABORT
        );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
    
    public static ArrayList<Token> analisar(String codigo) {
        ArrayList<Token> tokens = new ArrayList<>();
        
        Pattern comentarioPattern = Pattern.compile("//.*|/\\*(?:.|[\\n\\r])*?\\*/");
        codigo = comentarioPattern.matcher(codigo).replaceAll("");
        
        Pattern padrao = Pattern.compile("\\b(args|System|public|private|static|class|void|int|double|String|if|else|else if|switch|while|do|for)\\b|\\{|\\}|\\(|\\)|\\[|\\]|\\;|\\=|\\\"(?:[^\\\"]|\\\"{2})*\\\"|\\d+(\\.\\d+)?|\\b(main|printf|println|print|out|[a-zA-Z_]\\w*)\\b|==|!=|>=|<=|[-+*/<>]");
    
        Matcher matcher = padrao.matcher(codigo);
        int posicaoInicio = 0;
        while (matcher.find()) {
            int posicaoFim = matcher.start();
            if (posicaoInicio != posicaoFim) {
                String parte = codigo.substring(posicaoInicio, posicaoFim);
                tokens.addAll(analisarParte(parte, padrao));
            }
            String token = matcher.group();
            TokenType tipo = null;
            if (token.matches("\\b(args|static|main|printf|println|print|out)\\b")) {
                tipo = TokenType.IDENTIFICADOR;
            } else if (token.matches("\\b(System|if|else|else if|switch|while|do|for|public|private|class|void|int|double|String)\\b")) {
                tipo = TokenType.PALAVRA_RESERVADA;
            } else if (token.matches("\\b[a-zA-Z_]\\w*\\b")) {
                tipo = TokenType.ID;
            } else {
                switch (token) {
                    case "{":
                    case "}":
                    case "[":
                    case "]":
                    case "(":
                    case ")":
                        tipo = TokenType.CARACTER_ESPECIAL;
                        break;
                    case "*":
                    case "/":
                    case "-":
                    case "+":
                        tipo = TokenType.ARITMETICOS;
                        break;
                    case "&":
                    case "!":
                    case "|":
                        tipo = TokenType.LOGICOS;
                        break;
                    case ",":
                    case ";":
                        tipo = TokenType.DELIMITADORES;
                        break;
                    case "<":
                    case ">":
                    case ">=":
                    case "<=":
                    case "==":
                    case "!=":
                        tipo = TokenType.RELACIONAIS;
                        break;
                    case "=":
                        tipo = TokenType.ATRIBUICAO;
                        break;
                    default:
                        if (token.matches("\\d+(\\.\\d+)?")) {
                            tipo = TokenType.NUMERO;
                        } else if (token.matches("\\\"(?:[^\\\"]|\\\"{2})*\\\"")) {
                            tipo = TokenType.STRING;
                        } else {
                            break;
                        }
                }
            }
            tokens.add(new Token(token, tipo));
            posicaoInicio = matcher.end();
        }
        if (posicaoInicio < codigo.length()) {
            String parte = codigo.substring(posicaoInicio);
            tokens.addAll(analisarParte(parte, padrao));
        }
        return tokens;
    }  

    public static ArrayList<Token> analisarParte(String parte, Pattern padrao) {
        ArrayList<Token> tokens = new ArrayList<>();
        Matcher matcher = padrao.matcher(parte.trim());
        while (matcher.find()) {
            String token = matcher.group();
            TokenType tipo = null;
            switch (token) {
                case "\\\"(?:[^\\\"]|\\\"{2})*\\\"":
                    tipo = TokenType.STRING;
                break;
                default:
                break; 
            }
            tokens.add(new Token(token, tipo));
        }
        return tokens;
    }
}

class Token {
    String lexema;
    TokenType tipo;

    public Token(String lexema, TokenType tipo) {
        this.lexema = lexema;
        this.tipo = tipo;
    }

    public String toString() {
        return "< " + lexema + " , " + tipo + " >";
    }
}

enum TokenType {
    PALAVRA_RESERVADA,
    NUMERO,
    STRING,
    CARACTER_ESPECIAL,
    ATRIBUICAO,
    ARITMETICOS,
    LOGICOS,
    DELIMITADORES,
    RELACIONAIS,
    IDENTIFICADOR,
    ID
}

