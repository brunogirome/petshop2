/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
public class PetsDAO {

    public static ArrayList<PetsVO> selectPets() {

        Connection con = ConexaoDAO.iniciarConexao();

        PreparedStatement stmt = null;

        ResultSet rs = null;

        PetsVO objPet;

        ArrayList<PetsVO> returnLista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT pets.cod_pet, pets.nome, pets.raca, pets.cor, pets.peso, pets.sexo, pets.tipo_animal, pets.tipo_animal, pets.idade, pets.porte, pets.obs, clientes.nome FROM pets INNER JOIN clientes ON pets.fk_dono = clientes.cod_cli");

            rs = stmt.executeQuery();

            while (rs.next()) {
                objPet = new PetsVO();
                objPet.setCod_pet(rs.getInt("pets.cod_pet"));
                objPet.setNome(rs.getString("pets.nome"));
                objPet.setRaca(rs.getString("pets.raca"));
                objPet.setCor(rs.getString("pets.cor"));
                objPet.setPeso(rs.getFloat("pets.peso"));
                objPet.setSexo(rs.getString("pets.sexo"));
                objPet.setTipoAnimal(rs.getString("pets.tipo_animal"));
                objPet.setIdade(rs.getString("pets.idade"));
                objPet.setPorte(rs.getString("pets.porte"));
                objPet.setObservacoes(rs.getString("pets.obs"));
                objPet.setFkDono(rs.getString("clientes.nome"));
                returnLista.add(objPet);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados: " + ex);
        } finally {
            ConexaoDAO.fecharConexao(con, stmt, rs);
        }

        return returnLista;

    }

    public static boolean inserirPets(PetsVO objPets) {

        Connection con = ConexaoDAO.iniciarConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO pets ( nome, raca, cor, peso, sexo, tipo_animal, idade, porte, obs, fk_dono) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, objPets.getNome());
            stmt.setString(2, objPets.getRaca());
            stmt.setString(3, objPets.getCor());
            stmt.setFloat(4, objPets.getPeso());
            stmt.setString(5, objPets.getSexo());
            stmt.setString(6, objPets.getTipoAnimal());
            stmt.setString(7, objPets.getIdade());
            stmt.setString(8, objPets.getPorte());
            stmt.setString(9, objPets.getObservacoes());
            stmt.setString(10, objPets.getFkDono());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Animal " + objPets.getNome() + " registrado com sucesso.");
            
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha no registro:\n" + e);
            
            return false;
        } finally {
            ConexaoDAO.fecharConexao(con, stmt);
        }

    }

}
