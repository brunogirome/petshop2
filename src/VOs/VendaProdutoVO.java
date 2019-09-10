/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VOs;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author LNPC
 */
public class VendaProdutoVO {

    private Timestamp DataHora;
    private String Produtos;
    private double Total;
    private int LinhasProdutos;

    public VendaProdutoVO() {
        this.Produtos = "";
        this.LinhasProdutos = 0;
    }

    public VendaProdutoVO(Timestamp datahora, String produtos, double total, int linhasProdutos) {
        this.DataHora = datahora;
        this.Produtos = produtos;
        this.Total = total;
        this.LinhasProdutos = linhasProdutos;
    }

    public Timestamp getDataHora() {
        return DataHora;
    }

    public void setDataHora(Timestamp DataHora) {
        this.DataHora = DataHora;
    }

    public String getProdutos() {
        return Produtos;
    }

    public void setProdutos(String Produtos) {
        this.Produtos += Produtos;
    }

    public double getTotal() {
        return Total;

    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public void incrementarLinhas() {
        this.LinhasProdutos++;
    }

    public int getLinhasProduto() {
        return this.LinhasProdutos;
    }

}
