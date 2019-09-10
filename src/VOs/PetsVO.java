/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VOs;

/**
 *
 * @author LNPC
 */

public class PetsVO {
    
    private int Cod_pet;
    private String Nome;
    private String Raca;
    private String Cor;
    private float Peso;
    private String Sexo;
    private String TipoAnimal;
    private String Idade;
    private String Porte;
    private String Observacoes;
    private String Foto;
    private String FkDono;

    public int getCod_pet() {
        return Cod_pet;
    }

    public void setCod_pet(int Cod_pet) {
        this.Cod_pet = Cod_pet;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getRaca() {
        return Raca;
    }

    public void setRaca(String Raca) {
        this.Raca = Raca;
    }

    public String getCor() {
        return Cor;
    }

    public void setCor(String Cor) {
        this.Cor = Cor;
    }

    public float getPeso() {
        return Peso;
    }

    public void setPeso(float Peso) {
        this.Peso = Peso;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public String getTipoAnimal() {
        return TipoAnimal;
    }

    public void setTipoAnimal(String TipoAnimal) {
        this.TipoAnimal = TipoAnimal;
    }

    public String getIdade() {
        return Idade;
    }

    public void setIdade(String Idade) {
        this.Idade = Idade;
    }

    public String getPorte() {
        return Porte;
    }

    public void setPorte(String Porte) {
        this.Porte = Porte;
    }

    public String getObservacoes() {
        return Observacoes;
    }

    public void setObservacoes(String Observacoes) {
        this.Observacoes = Observacoes;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String Foto) {
        this.Foto = Foto;
    }

    public String getFkDono() {
        return FkDono;
    }

    public void setFkDono(String FkDono) {
        this.FkDono = FkDono;
    }
    
    
    
}
