/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VOs.AgendamentosVO;
import VOs.CargosVO;
import VOs.ClientesVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author LNPC
 */
public class AgendamentosDAO {

    public static ArrayList<AgendamentosVO> selectAgendamentos() {

        Connection con = ConexaoDAO.iniciarConexao();

        PreparedStatement stmt = null;

        ResultSet rs = null;

        AgendamentosVO objAgendamento;

        ArrayList<AgendamentosVO> returnLista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT agendamentos.cod_agend, servicos.descricao, funcionarios.nome, agendamentos.data_agend, agendamentos.horario, agendamentos.obs, agendamentos.status, pets.nome, clientes.nome FROM agendamentos INNER JOIN servicos ON agendamentos.fk_tipo_serv = servicos.cod_serv INNER JOIN funcionarios ON agendamentos.fk_func = funcionarios.cod_func INNER JOIN pets ON agendamentos.fk_pet = pets.cod_pet INNER JOIN clientes ON agendamentos.fk_dono = clientes.cod_cli");

            rs = stmt.executeQuery();

            while (rs.next()) {
                objAgendamento = new AgendamentosVO();
                objAgendamento.setCod_agend(rs.getInt("agendamentos.cod_agend"));
                objAgendamento.setFkTipoServ(rs.getString("servicos.descricao"));
                objAgendamento.setFkFunc(rs.getString("funcionarios.nome"));
                objAgendamento.setDataAgend(rs.getDate("agendamentos.data_agend"));
                objAgendamento.setHorario(rs.getString("agendamentos.horario"));
                objAgendamento.setObs(rs.getString("agendamentos.obs"));
                objAgendamento.setStatus(rs.getBoolean("agendamentos.status"));
                objAgendamento.setFkPet(rs.getString("pets.nome"));
                objAgendamento.setFkDono(rs.getString("clientes.nome"));
                returnLista.add(objAgendamento);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados: " + ex);
        } finally {
            ConexaoDAO.fecharConexao(con, stmt, rs);
        }

        return returnLista;

    }

    public static ArrayList<AgendamentosVO> selectAgendamentoDiario() {

        Connection con = ConexaoDAO.iniciarConexao();

        PreparedStatement stmt = null;

        ResultSet rs = null;

        AgendamentosVO objAgendamento;

        ArrayList<AgendamentosVO> returnLista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT agendamentos.cod_agend, servicos.descricao, funcionarios.nome, agendamentos.data_agend, agendamentos.horario, agendamentos.obs, agendamentos.status, pets.nome, clientes.nome FROM agendamentos INNER JOIN servicos ON agendamentos.fk_tipo_serv = servicos.cod_serv INNER JOIN funcionarios ON agendamentos.fk_func = funcionarios.cod_func INNER JOIN pets ON agendamentos.fk_pet = pets.cod_pet INNER JOIN clientes ON agendamentos.fk_dono = clientes.cod_cli");

            rs = stmt.executeQuery();

            while (rs.next()) {
                objAgendamento = new AgendamentosVO();
                objAgendamento.setCod_agend(rs.getInt("agendamentos.cod_agend"));
                objAgendamento.setFkTipoServ(rs.getString("servicos.descricao"));
                objAgendamento.setFkFunc(rs.getString("funcionarios.nome"));
                objAgendamento.setDataAgend(rs.getDate("agendamentos.data_agend"));
                objAgendamento.setHorario(rs.getString("agendamentos.horario"));
                objAgendamento.setObs(rs.getString("agendamentos.obs"));
                objAgendamento.setStatus(rs.getBoolean("agendamentos.status"));
                objAgendamento.setFkPet(rs.getString("pets.nome"));
                objAgendamento.setFkDono(rs.getString("clientes.nome"));
                if (objAgendamento.getDataAgend().toString().contains(LocalDate.now().toString())) {
                    returnLista.add(objAgendamento);
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados: " + ex);
        } finally {
            ConexaoDAO.fecharConexao(con, stmt, rs);
        }

        return returnLista;

    }

    public static boolean inserirAgendamento(AgendamentosVO objAgendamento) {
        Connection con = ConexaoDAO.iniciarConexao();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO agendamentos(fk_tipo_serv, fk_func, data_agend, horario, obs, status, fk_pet, fk_dono) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, Integer.parseInt(objAgendamento.getFkTipoServ()));
            stmt.setInt(2, Integer.parseInt(objAgendamento.getFkFunc()));
            stmt.setDate(3, objAgendamento.getDataAgend());
            stmt.setString(4, objAgendamento.getHorario());
            stmt.setString(5, objAgendamento.getObs());
            stmt.setBoolean(6, objAgendamento.isStatus());
            stmt.setInt(7, Integer.parseInt(objAgendamento.getFkPet()));
            stmt.setInt(8, Integer.parseInt(objAgendamento.getFkDono()));
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Agendamento realizado com sucesso!");
            
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro para inserir: " + e);
            
            return false;
        } finally {
            ConexaoDAO.fecharConexao(con, stmt);
        }

    }

    public static void autalizarStatus(int status, int cod) {
        Connection con = ConexaoDAO.iniciarConexao();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE agendamentos SET status = " + status + " WHERE agendamentos.`cod_agend` = " + cod);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "STATUS ATUALIZADO COM SUCESSO.");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO NA CONEXÃO COM O BANCO DE DADOS:\n" + ex);

        } finally {
            ConexaoDAO.fecharConexao(con, stmt);
        }
    }

}
