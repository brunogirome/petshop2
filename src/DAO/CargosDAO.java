/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VOs.CargosVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author 28264088
 */
public class CargosDAO {

    public static ArrayList<CargosVO> selectCargos() {

        Connection con = ConexaoDAO.iniciarConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        CargosVO objCategoria;
        ArrayList<CargosVO> listaRertorno = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cargos");
            rs = stmt.executeQuery();

            while (rs.next()) {
                objCategoria = new CargosVO();
                objCategoria.setCod_cargo(rs.getInt("cod_cargo"));
                objCategoria.setDescricao(rs.getString("descricao"));
                listaRertorno.add(objCategoria);
            }

            return listaRertorno;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO NA CONEXÃO COM O BANCO DE DADOS:\n" + ex);
            return null;
        } finally {
            ConexaoDAO.fecharConexao(con, stmt, rs);
        }
    }

    public static String[] carregarCargos() {

        ArrayList<CargosVO> listaCatego = selectCargos();
        String returnLista[] = new String[listaCatego.size()];

        for (int i = 0; i < returnLista.length; i++) {
            returnLista[i] = listaCatego.get(i).getDescricao();
        }
        return returnLista;
    }

    public static void inserirCargo(String descricao) {
        Connection con = ConexaoDAO.iniciarConexao();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO cargos (descricao) VALUES (?)");
            stmt.setString(1, descricao);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "CARGO " + descricao + " REGISTRADO COM SUCESSO!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO NA CONEXÃO COM O BANCO DE DADOS:\n" + ex);
        } finally {
            ConexaoDAO.fecharConexao(con, stmt);
        }
    }
}
