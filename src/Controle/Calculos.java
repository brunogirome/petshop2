/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.awt.Container;
import javax.swing.JPanel;

/**
 *
 * @author LNPC
 */
public class Calculos {

    public static int porcentagemObjeto(double porcentagem, JPanel painel) {
        return (int) (painel.getWidth() * porcentagem);
    }

    public static int porcentagemLargura(double porcentagem, Container container) {
        return (int) (container.getWidth() * porcentagem);
    }

    public static int porcentagemAltura(double porcetagem, Container container) {
        return (int) (container.getHeight() * porcetagem);
    }

    public static int centralizarPaineis(int indice, int tamX, int qtd, JPanel painel) {
        return (int) (((painel.getWidth() - (tamX * qtd)) / (qtd + 1)) + (indice * (tamX + ((painel.getWidth() - (tamX * qtd)) / (qtd + 1)))));
    }

    public static int centralizarPaineis(int indice, int tamX, int qtd, Container container) {
        return (int) (((container.getWidth() - (tamX * qtd)) / (qtd + 1)) + (indice * (tamX + ((container.getWidth() - (tamX * qtd)) / (qtd + 1)))));
    }
    
    public static int centralizarPaineis(int indice, int tamX, int qtd, int largura) {
        return (int) (((largura - (tamX * qtd)) / (qtd + 1)) + (indice * (tamX + ((largura - (tamX * qtd)) / (qtd + 1)))));
    }


}
