package DAO;

import VOs.VendaProdutoVO;
import VOs.VendasVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class VendasDAO {

    public static int pegarVenda() {
        Connection con = ConexaoDAO.iniciarConexao();

        PreparedStatement stmt = null;

        ResultSet rs = null;

        VendasVO objVenda;
        ArrayList<VendasVO> listaVendas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("INSERT INTO vendas (data_venda) VALUES (?)");
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.executeUpdate();

            stmt = con.prepareStatement("SELECT cod_venda FROM vendas");
            rs = stmt.executeQuery();
            while (rs.next()) {
                objVenda = new VendasVO();
                objVenda.setCod_venda(rs.getInt("cod_venda"));
                listaVendas.add(objVenda);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO NA CONEXÃO COM O BANCO DE DADOS:\n" + ex);
        } finally {
            ConexaoDAO.fecharConexao(con, stmt, rs);
        }

        return listaVendas.get(listaVendas.size() - 1).getCod_venda();
    }

    public static void inserirVenda(int codVenda, int codProd, int qtdProd) {
        Connection con = ConexaoDAO.iniciarConexao();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO venda_produtos (fk_cod_venda, fk_cod_produto, qtd_produto) VALUES (?, ?, ?)");
            stmt.setInt(1, codVenda);
            stmt.setInt(2, codProd);
            stmt.setInt(3, qtdProd);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO NA CONEXÃO COM O BANCO DE DADOS:\n" + ex);
        } finally {
            ConexaoDAO.fecharConexao(con, stmt);
        }
    }

    public static void inserirTotal(int codVenda, double total) {
        Connection con = ConexaoDAO.iniciarConexao();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE vendas SET total = " + total + " WHERE vendas.cod_venda = " + codVenda);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO NA CONEXÃO COM O BANCO DE DADOS:\n" + ex);
        } finally {
            ConexaoDAO.fecharConexao(con, stmt);
        }
    }

    public static void atualizarQuantidades(int codPro, int qtdCompra) {
        Connection con = ConexaoDAO.iniciarConexao();

        PreparedStatement stmt = null;

        ResultSet rs = null;

        int quantidadeTotal;

        try {
            stmt = con.prepareStatement("SELECT qtd FROM produtos WHERE cod_prod = " + codPro);
            rs = stmt.executeQuery();
            rs.next();
            quantidadeTotal = rs.getInt("qtd");

            if (qtdCompra <= quantidadeTotal) {
                quantidadeTotal -= qtdCompra;
            } else {
                quantidadeTotal = 0;
            }
            stmt = con.prepareStatement("UPDATE produtos SET qtd = " + quantidadeTotal + " WHERE produtos.cod_prod = " + codPro);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO NA CONEXÃO COM O BANCO DE DADOS:\n" + ex);
        } finally {
            ConexaoDAO.fecharConexao(con, stmt, rs);
        }
    }

    public static ArrayList<VendaProdutoVO> selecionarVendas() {

        Connection con = ConexaoDAO.iniciarConexao();

        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<Integer> codigosVenda = new ArrayList<>();

        VendaProdutoVO objVenda;

        ArrayList<VendaProdutoVO> listaAux = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT cod_venda FROM vendas ORDER BY cod_venda DESC");
            rs = stmt.executeQuery();

            while (rs.next()) {
                codigosVenda.add(rs.getInt("cod_venda"));
            }

            for (int i = 0; i < codigosVenda.size(); i++) {
                objVenda = new VendaProdutoVO();
                stmt = con.prepareStatement("SELECT vendas.data_venda, produtos.nome, venda_produtos.qtd_produto, vendas.total FROM venda_produtos INNER JOIN vendas ON vendas.cod_venda = venda_produtos.fk_cod_venda INNER JOIN produtos ON produtos.cod_prod = venda_produtos.fk_cod_produto WHERE vendas.cod_venda = " + codigosVenda.get(i));
                rs = stmt.executeQuery();
                while (rs.next()) {
                    objVenda.setDataHora(rs.getTimestamp("vendas.data_venda"));
                    objVenda.setProdutos("" + rs.getString("produtos.nome") + " Qtd:" + rs.getInt("venda_produtos.qtd_produto") + "<br>");
                    objVenda.setTotal(rs.getDouble("vendas.total"));
                    objVenda.incrementarLinhas();
                }
                
                if (objVenda.getLinhasProduto() != 0) {
                    listaAux.add(objVenda);
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO NA CONEXÃO COM O BANCO DE DADOS:\n" + ex);
        } finally {
            ConexaoDAO.fecharConexao(con, stmt, rs);
        }

        return listaAux;
    }

}
