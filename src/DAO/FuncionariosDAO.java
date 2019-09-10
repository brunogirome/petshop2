/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VOs.CategoriasVO;
import VOs.FuncionariosVO;
import VOs.PetsVO;
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
public class FuncionariosDAO {

    public static ArrayList<FuncionariosVO> selectFuncionarios() {

        Connection con = ConexaoDAO.iniciarConexao();

        PreparedStatement stmt = null;

        ResultSet rs = null;

        FuncionariosVO objFuncionario;

        ArrayList<FuncionariosVO> returnLista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT funcionarios.cod_func, funcionarios.nome, funcionarios.cpf, funcionarios.rg, funcionarios.data_nasc, funcionarios.tel, funcionarios.cel, funcionarios.email, funcionarios.endereco, funcionarios.numero, funcionarios.compl, funcionarios.cep, funcionarios.cidade, funcionarios.bairro, funcionarios.uf, funcionarios.conta_corrente, funcionarios.agencia, funcionarios.banco, funcionarios.cidade_conta, funcionarios.estado_conta, funcionarios.PIS, funcionarios.salario, cargos.descricao from funcionarios inner join cargos on funcionarios.cargo = cargos.cod_cargo");

            rs = stmt.executeQuery();

            while (rs.next()) {
                objFuncionario = new FuncionariosVO();
                objFuncionario.setCod_clit(rs.getInt("funcionarios.cod_func"));
                objFuncionario.setNome(rs.getString("funcionarios.nome"));
                objFuncionario.setCpf(rs.getString("funcionarios.cpf"));
                objFuncionario.setRg(rs.getString("funcionarios.rg"));
                objFuncionario.setData_nasc(rs.getDate("funcionarios.data_nasc"));
                objFuncionario.setTel(rs.getString("funcionarios.tel"));
                objFuncionario.setCel(rs.getString("funcionarios.cel"));
                objFuncionario.setEmail(rs.getString("funcionarios.email"));
                objFuncionario.setEndereco(rs.getString("funcionarios.endereco"));
                objFuncionario.setNumero(rs.getString("funcionarios.numero"));
                objFuncionario.setComplemento(rs.getString("funcionarios.compl"));
                objFuncionario.setCep(rs.getString("funcionarios.cep"));
                objFuncionario.setCidade(rs.getString("funcionarios.cidade"));
                objFuncionario.setBairro(rs.getString("funcionarios.bairro"));
                objFuncionario.setUf(rs.getString("funcionarios.uf"));
                objFuncionario.setContaCorrente(rs.getString("funcionarios.conta_corrente"));
                objFuncionario.setAgencia(rs.getString("funcionarios.agencia"));
                objFuncionario.setBanco(rs.getString("funcionarios.banco"));
                objFuncionario.setCidadeConta(rs.getString("funcionarios.cidade_conta"));
                objFuncionario.setEstadoConta(rs.getString("funcionarios.estado_conta"));
                objFuncionario.setPis(rs.getString("funcionarios.PIS"));
                objFuncionario.setSalario(rs.getFloat("funcionarios.salario"));
                objFuncionario.setFkCargo(rs.getString("cargos.descricao"));
                returnLista.add(objFuncionario);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados: " + ex);
        } finally {
            ConexaoDAO.fecharConexao(con, stmt, rs);
        }

        return returnLista;

    }

    public static boolean inserirFuncionarios(FuncionariosVO objFuncionario) {

        Connection con = ConexaoDAO.iniciarConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO funcionarios (nome, cpf, rg, data_nasc, tel, cel, email, endereco, numero, compl, cep, cidade, bairro, uf, conta_corrente, agencia, banco, cidade_conta, estado_conta, PIS, salario, cargo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, objFuncionario.getNome());
            stmt.setString(2, objFuncionario.getCpf());
            stmt.setString(3, objFuncionario.getRg());
            stmt.setDate(4, objFuncionario.getData_nasc());
            stmt.setString(5, objFuncionario.getTel());
            stmt.setString(6, objFuncionario.getCel());
            stmt.setString(7, objFuncionario.getEmail());
            stmt.setString(8, objFuncionario.getEndereco());
            stmt.setString(9, objFuncionario.getNumero());
            stmt.setString(10, objFuncionario.getComplemento());
            stmt.setString(11, objFuncionario.getCep());
            stmt.setString(12, objFuncionario.getCidade());
            stmt.setString(13, objFuncionario.getBairro());
            stmt.setString(14, objFuncionario.getUf());
            stmt.setString(15, objFuncionario.getContaCorrente());
            stmt.setString(16, objFuncionario.getAgencia());
            stmt.setString(17, objFuncionario.getBanco());
            stmt.setString(18, objFuncionario.getCidadeConta());
            stmt.setString(19, objFuncionario.getEstadoConta());
            stmt.setString(20, objFuncionario.getPis());
            stmt.setFloat(21, objFuncionario.getSalario());
            stmt.setString(22, objFuncionario.getFkCargo());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Funcionário " + objFuncionario.getNome() + " registrado com sucesso.");
            
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha no registro:\n" + e);
            
            return false;
        } finally {
            ConexaoDAO.fecharConexao(con, stmt);
        }

    }

    public static String[] carregaSelecao() {

        Connection con = ConexaoDAO.iniciarConexao();

        PreparedStatement stmt = null;

        ResultSet rs = null;

        FuncionariosVO objCategoria;

        ArrayList<FuncionariosVO> listaCatego = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT cod_func, nome FROM funcionarios");
            rs = stmt.executeQuery();

            while (rs.next()) {
                objCategoria = new FuncionariosVO();
                objCategoria.setCod_clit(rs.getInt("cod_func"));
                objCategoria.setNome(rs.getString("nome"));
                listaCatego.add(objCategoria);
            }

            String returnLista[] = new String[listaCatego.size()];

            for (int i = 0; i < returnLista.length; i++) {
                returnLista[i] = listaCatego.get(i).getNome();
            }

            return returnLista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados:; " + ex);
            return null;
        } finally {
            ConexaoDAO.fecharConexao(con, stmt, rs);
        }

    }

}
