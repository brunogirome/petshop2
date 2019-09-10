/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VOs.CategoriasVO;
import VOs.ClientesVO;
import VOs.FornecedoresVO;
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
public class FornecedoresDAO {

    public static ArrayList<FornecedoresVO> selectFornecedores() {

        Connection con = ConexaoDAO.iniciarConexao();

        PreparedStatement stmt = null;

        ResultSet rs = null;

        FornecedoresVO objFornecedor;

        ArrayList<FornecedoresVO> returnLista = new ArrayList<>();

        try {

            stmt = con.prepareStatement("SELECT * FROM fornecedores");

            rs = stmt.executeQuery();

            while (rs.next()) {
                objFornecedor = new FornecedoresVO();
                objFornecedor.setCod_fornec(rs.getInt("cod_fornec"));
                objFornecedor.setNome(rs.getString("nomeFornecedor"));
                objFornecedor.setDetalhes(rs.getString("detalhes"));
                objFornecedor.setCnpj(rs.getString("cnpj"));
                objFornecedor.setEmail(rs.getString("email"));
                objFornecedor.setTel(rs.getString("tel"));
                objFornecedor.setCel(rs.getString("cel"));
                returnLista.add(objFornecedor);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados\n:" + ex);
        } finally {
            ConexaoDAO.fecharConexao(con, stmt, rs);
        }

        return returnLista;

    }

    public static String[] carregaSelecao() {

        Connection con = ConexaoDAO.iniciarConexao();

        PreparedStatement stmt = null;

        ResultSet rs = null;

        FornecedoresVO objFornecedor;

        ArrayList<FornecedoresVO> listaCatego = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM fornecedores");
            rs = stmt.executeQuery();

            while (rs.next()) {
                objFornecedor = new FornecedoresVO();
                objFornecedor.setCod_fornec(rs.getInt("cod_fornec"));
                objFornecedor.setNome(rs.getString("nomeFornecedor"));
                objFornecedor.setDetalhes(rs.getString("detalhes"));
                objFornecedor.setCnpj(rs.getString("cnpj"));
                objFornecedor.setEmail(rs.getString("email"));
                objFornecedor.setTel(rs.getString("tel"));
                objFornecedor.setCel(rs.getString("cel"));
                listaCatego.add(objFornecedor);
            }

            String returnLista[] = new String[listaCatego.size()];

            for (int i = 0; i < returnLista.length; i++) {
                returnLista[i] = listaCatego.get(i).getNome();
            }

            return returnLista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados\n: " + ex);
            return null;
        } finally {
            ConexaoDAO.fecharConexao(con, stmt, rs);
        }

    }

    public static boolean inserirForncedores(FornecedoresVO objFornecedor) {
        Connection con = ConexaoDAO.iniciarConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO fornecedores (nomeFornecedor, detalhes, cnpj, email, tel, cel) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, objFornecedor.getNome());
            stmt.setString(2, objFornecedor.getDetalhes());
            stmt.setString(3, objFornecedor.getCnpj());
            stmt.setString(4, objFornecedor.getEmail());
            stmt.setString(5, objFornecedor.getTel());
            stmt.setString(6, objFornecedor.getCel());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Forncedor " + objFornecedor.getNome() + " registrado com sucesso.");
            
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha no registro:\n" + e);
            
            return false;
        } finally {
            ConexaoDAO.fecharConexao(con, stmt);
        }

    }

}
