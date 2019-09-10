/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VOs.ClientesVO;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 28264088
 */
public class ClientesDAO {

    public static ArrayList<ClientesVO> selectClientes(String busca) {

        Connection con = ConexaoDAO.iniciarConexao();

        PreparedStatement stmt = null;

        ResultSet rs = null;

        ClientesVO objCliente;

        ArrayList<ClientesVO> returnLista = new ArrayList<>();

        try {
            if (busca == null) {
                stmt = con.prepareStatement("SELECT * FROM clientes");
            } else {
                stmt = con.prepareStatement("SELECT * FROM clientes WHERE nome LIKE '%" + busca + "%'");
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                objCliente = new ClientesVO();
                objCliente.setCod_clit(rs.getInt("cod_cli"));
                objCliente.setNome(rs.getString("nome"));
                objCliente.setCpf(rs.getString("cpf"));
                objCliente.setRg(rs.getString("rg"));
                objCliente.setData_nasc(rs.getDate("data_nasc"));
                objCliente.setTel(rs.getString("tel"));
                objCliente.setCel(rs.getString("cel"));
                objCliente.setEmail(rs.getString("email"));
                objCliente.setEndereco(rs.getString("endereco"));
                objCliente.setNumero(rs.getString("numero"));
                objCliente.setComplemento(rs.getString("compl"));
                objCliente.setCep(rs.getString("cep"));
                objCliente.setCidade(rs.getString("cidade"));
                objCliente.setBairro(rs.getString("bairro"));
                objCliente.setUf(rs.getString("uf"));
                returnLista.add(objCliente);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados:\n" + ex);
        } finally {
            ConexaoDAO.fecharConexao(con, stmt, rs);
        }

        return returnLista;

    }

    public static boolean inserirCliente(ClientesVO objCliente) {
        Connection con = ConexaoDAO.iniciarConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO clientes (nome, cpf, rg, data_nasc, tel, cel, email, endereco, numero, compl, cep, cidade, bairro, uf)VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            //stmt.setInt(1, objCliente.getCod_clit());
            stmt.setString(1, objCliente.getNome());
            stmt.setString(2, objCliente.getCpf());
            stmt.setString(3, objCliente.getRg());
            stmt.setDate(4, objCliente.getData_nasc());
            stmt.setString(5, objCliente.getTel());
            stmt.setString(6, objCliente.getCel());
            stmt.setString(7, objCliente.getEmail());
            stmt.setString(8, objCliente.getEndereco());
            stmt.setString(9, objCliente.getNumero());
            stmt.setString(10, objCliente.getComplemento());
            stmt.setString(11, objCliente.getCep());
            stmt.setString(12, objCliente.getCidade());
            stmt.setString(13, objCliente.getBairro());
            stmt.setString(14, objCliente.getUf());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente " + objCliente.getNome() + " registrado com sucesso.");
            
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha no registro:\n" + e);
            
            return false;
        } finally {
            ConexaoDAO.fecharConexao(con, stmt);
        }
    }

    public static ArrayList<ClientesVO> vetorClientes(String busca) {

        Connection con = ConexaoDAO.iniciarConexao();

        PreparedStatement stmt = null;

        ResultSet rs = null;

        ClientesVO objCliente;

        ArrayList<ClientesVO> arrayReturn = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT cod_cli, nome FROM clientes");

            rs = stmt.executeQuery();

            while (rs.next()) {
                objCliente = new ClientesVO();
                objCliente.setCod_clit(rs.getInt("cod_cli"));
                objCliente.setNome(rs.getString("nome"));
                arrayReturn.add(objCliente);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados: " + ex);
        } finally {
            ConexaoDAO.fecharConexao(con, stmt, rs);
        }

        return arrayReturn;

    }

}
