/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VOs;

import java.sql.Timestamp;

/**
 *
 * @author 28264088
 */
public class VendasVO {

    private int cod_venda;
    private Timestamp data_venda;
    private double total;

    public int getCod_venda() {
        return cod_venda;
    }

    public void setCod_venda(int cod_venda) {
        this.cod_venda = cod_venda;
    }

    public Timestamp getData_venda() {
        return data_venda;
    }

    public void setData_venda(Timestamp data_venda) {
        this.data_venda = data_venda;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
