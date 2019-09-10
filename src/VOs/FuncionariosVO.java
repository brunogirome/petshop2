/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VOs;

import java.sql.Date;

/**
 *
 * @author LNPC
 */
public class FuncionariosVO {

    private int Cod_clit;
    private String Nome;
    private String Cpf;
    private String Rg;
    private Date Data_nasc;
    private String Tel;
    private String Cel;
    private String Email;
    private String Endereco;
    private String Numero;
    private String Complemento;
    private String Cep;
    private String Cidade;
    private String Bairro;
    private String Uf;
    private String ContaCorrente;
    private String Agencia;
    private String Banco;
    private String CidadeConta;
    private String EstadoConta;
    private String Pis;
    private float Salario;
    private String FkCargo;

    public int getCod_cli() {
        return Cod_clit;
    }

    public void setCod_clit(int Cod_clit) {
        this.Cod_clit = Cod_clit;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
    }

    public String getRg() {
        return Rg;
    }

    public void setRg(String Rg) {
        this.Rg = Rg;
    }

    public Date getData_nasc() {
        return Data_nasc;
    }

    public void setData_nasc(Date Data_nasc) {
        this.Data_nasc = Data_nasc;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public String getCel() {
        return Cel;
    }

    public void setCel(String Cel) {
        this.Cel = Cel;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public String getComplemento() {
        return Complemento;
    }

    public void setComplemento(String Complemento) {
        this.Complemento = Complemento;
    }

    public String getCep() {
        return Cep;
    }

    public void setCep(String Cep) {
        this.Cep = Cep;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    public String getUf() {
        return Uf;
    }

    public void setUf(String Uf) {
        this.Uf = Uf;
    }

    public String getContaCorrente() {
        return ContaCorrente;
    }

    public void setContaCorrente(String ContaCorrente) {
        this.ContaCorrente = ContaCorrente;
    }

    public String getAgencia() {
        return Agencia;
    }

    public void setAgencia(String Agencia) {
        this.Agencia = Agencia;
    }

    public String getBanco() {
        return Banco;
    }

    public void setBanco(String Banco) {
        this.Banco = Banco;
    }

    public String getCidadeConta() {
        return CidadeConta;
    }

    public void setCidadeConta(String CidadeConta) {
        this.CidadeConta = CidadeConta;
    }

    public String getEstadoConta() {
        return EstadoConta;
    }

    public void setEstadoConta(String EstadoConta) {
        this.EstadoConta = EstadoConta;
    }

    public String getPis() {
        return Pis;
    }

    public void setPis(String Pis) {
        this.Pis = Pis;
    }

    public float getSalario() {
        return Salario;
    }

    public void setSalario(float Salario) {
        this.Salario = Salario;
    }

    public String getFkCargo() {
        return FkCargo;
    }

    public void setFkCargo(String FkCargo) {
        this.FkCargo = FkCargo;
    }

}
