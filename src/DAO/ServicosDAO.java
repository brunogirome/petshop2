package DAO;

import VOs.ServicosVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ServicosDAO {

    public static ArrayList<ServicosVO> selectServicos() {

        Connection con = ConexaoDAO.iniciarConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ServicosVO objCategoria;
        ArrayList<ServicosVO> listaRertorno = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM servicos");
            rs = stmt.executeQuery();

            while (rs.next()) {
                objCategoria = new ServicosVO();
                objCategoria.setCod_serv(rs.getInt("cod_serv"));
                objCategoria.setDescricao(rs.getString("descricao"));
                listaRertorno.add(objCategoria);
            }

            return listaRertorno;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados:; " + ex);
            return null;
        } finally {
            ConexaoDAO.fecharConexao(con, stmt, rs);
        }
    }

    public static String[] carregarServicos() {

        ArrayList<ServicosVO> listaCatego = selectServicos();
        String returnLista[] = new String[listaCatego.size()];

        for (int i = 0; i < returnLista.length; i++) {
            returnLista[i] = listaCatego.get(i).getDescricao();
        }
        return returnLista;
    }

}
