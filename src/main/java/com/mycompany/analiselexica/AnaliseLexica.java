package com.mycompany.analiselexica;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnaliseLexica {

    

    public static void main(String[] args) {
        String codigoJava = "public class test {\n" +
            "public static void main(String[] args) {\n" +
                "/* testando comentario */\n" +
                "int idade = 30.5;\n" +
                "idade += 2;\n" +
                "double soma = 98.6 + 45 - 12 / 78 * 3;\n" +
                "for (int i = 0; i <= idade; i++) {\n" +
                    "System.out.println(\"Contagem de Idade: \" + i);\n" +
                "}\n" +
            "}\n" +
        "}";

        ArrayList<Token> tokens = analisar(codigoJava);

        System.out.println("Tokens:");
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

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
