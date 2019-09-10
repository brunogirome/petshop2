package DAO;

import VOs.CategoriasVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CategoriasDAO {

    public static void inserirCategoria(String descricao) {
        Connection con = ConexaoDAO.iniciarConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO categorias (descricao) VALUES (?)");
            stmt.setString(1, descricao);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "CATEGORIA " + descricao + " REGISTRADA COM SUCESSO!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO REGISTRAR CATEGORIA:\n" + ex);
        }

    }

    public static ArrayList<CategoriasVO> selectCategorias() {

        Connection con = ConexaoDAO.iniciarConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        CategoriasVO objCategoria;
        ArrayList<CategoriasVO> listaRertorno = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM categorias");
            rs = stmt.executeQuery();

            while (rs.next()) {
                objCategoria = new CategoriasVO();
                objCategoria.setCod_catego(rs.getInt("cod_catego"));
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

    public static String[] carregaSelecao() {

        ArrayList<CategoriasVO> listaCatego = selectCategorias();
        String returnLista[] = new String[listaCatego.size()];

        for (int i = 0; i < returnLista.length; i++) {
            returnLista[i] = listaCatego.get(i).getDescricao();
        }
        return returnLista;
    }

}
