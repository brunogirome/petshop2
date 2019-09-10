/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VOs.ProdutosVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author LNPC
 */
public class ProdutosDAO {

    public static ProdutosVO produtoCaixa(int busca) {

        Connection con = ConexaoDAO.iniciarConexao();

        PreparedStatement stmt = null;

        ResultSet rs = null;

        ProdutosVO objProduto;

        try {
            stmt = con.prepareStatement("SELECT cod_prod, nome, preco, qtd FROM produtos WHERE cod_prod = " + busca);
            rs = stmt.executeQuery();

            objProduto = new ProdutosVO();
            
            rs.next();
            objProduto.setCod_prod(rs.getInt("cod_prod"));
            objProduto.setNome(rs.getString("nome"));
            objProduto.setPreco(rs.getFloat("preco"));
            objProduto.setQtd(rs.getInt("qtd"));

            return objProduto;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "PRODUTO NÃO CONTIDO NO BANCO:\nLOG DO ERRO:\n" + e, "ERRO AO ENCONTRAR PRODUTO:", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            ConexaoDAO.fecharConexao(con, stmt, rs);
        }

    }

    public static ArrayList<ProdutosVO> selectProdutos(String codProduto) {

        Connection con = ConexaoDAO.iniciarConexao();

        PreparedStatement stmt = null;

        ResultSet rs = null;

        ProdutosVO objProduto;

        ArrayList<ProdutosVO> returnLista = new ArrayList<>();

        try {
            if (codProduto == null) {
                stmt = con.prepareStatement("select "
                        + "produtos.cod_prod, "
                        + "produtos.nome, "
                        + "produtos.descricao, "
                        + "produtos.qtd, "
                        + "produtos.preco, "
                        + "categorias.descricao, "
                        + "fornecedores.nomeFornecedor, "
                        + "produtos.tipo_pet, "
                        + "produtos.idade_pet, "
                        + "produtos.tamanho_pet "
                        + "from produtos "
                        + "inner join categorias ON "
                        + "produtos.fk_categoria = categorias.cod_catego "
                        + "inner join fornecedores ON "
                        + "produtos.fk_fornec = fornecedores.cod_fornec");

            } else {
                stmt = con.prepareStatement("select "
                        + "produtos.cod_prod, "
                        + "produtos.nome, "
                        + "produtos.descricao, "
                        + "produtos.qtd, "
                        + "produtos.preco, "
                        + "categorias.descricao, "
                        + "fornecedores.nomeFornecedor, "
                        + "produtos.tipo_pet, "
                        + "produtos.idade_pet, "
                        + "produtos.tamanho_pet "
                        + "from produtos "
                        + "inner join categorias ON "
                        + "produtos.fk_categoria = categorias.cod_catego "
                        + "inner join fornecedores ON "
                        + "produtos.fk_fornec = fornecedores.cod_fornec where produtos.cod_prod " + codProduto);
            }

            //SELECT produtos.cod, produto.nome, categoria.nome, fornecedor.nome FROM produtos INNER JOIN categoria ON produtos.fkCatego = categoria.cod INNER JOIN fornecedor ON produtos.fkFornec = forncedores.id
            rs = stmt.executeQuery();

            while (rs.next()) {
                objProduto = new ProdutosVO();
                objProduto.setCod_prod(rs.getInt("produtos.cod_prod"));
                objProduto.setNome(rs.getString("produtos.nome"));
                objProduto.setDescricao(rs.getString("produtos.descricao"));
                objProduto.setQtd(rs.getInt("produtos.qtd"));
                objProduto.setPreco(rs.getFloat("produtos.preco"));
                objProduto.setFkCatego(rs.getString("categorias.descricao"));
                objProduto.setFkFornec(rs.getString("fornecedores.nomeFornecedor"));
                objProduto.setTipo_Pet(rs.getString("produtos.tipo_pet"));
                objProduto.setIdade_Pet(rs.getString("produtos.idade_pet"));
                objProduto.setTamanho_Pet(rs.getString("produtos.tamanho_pet"));
                returnLista.add(objProduto);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados: " + ex);
        } finally {
            ConexaoDAO.fecharConexao(con, stmt, rs);
        }

        return returnLista;

    }

    public static boolean inserirProduto(ProdutosVO objCliente) {
        Connection con = ConexaoDAO.iniciarConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO produtos (nome, descricao, qtd, preco, fk_categoria, fk_fornec, tipo_pet, idade_pet, tamanho_pet) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, objCliente.getNome());
            stmt.setString(2, objCliente.getDescricao());
            stmt.setInt(3, objCliente.getQtd());
            stmt.setFloat(4, objCliente.getPreco());
            stmt.setString(5, objCliente.getFkCatego());
            stmt.setString(6, objCliente.getFkFornec());
            stmt.setString(7, objCliente.getTipo_Pet());
            stmt.setString(8, objCliente.getIdade_Pet());
            stmt.setString(9, objCliente.getTamanho_Pet());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto " + objCliente.getNome() + " registrado com sucesso.");
            
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha no registro:\n" + e);
            
            return false;
        } finally {
            ConexaoDAO.fecharConexao(con, stmt);
        }
    }

}
