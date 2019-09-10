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
public class ProdutosVO {

    private int Cod_prod;
    private String Nome;
    private String Descricao;
    private int Qtd;
    private float Preco;
    private String FkCatego;
    private String FkFornec;
    private String Tipo_Pet;
    private String Idade_Pet;
    private String Tamanho_Pet;

    public ProdutosVO() {
        this.setCod_prod(Cod_prod);

    }

    public int getCod_prod() {
        return Cod_prod;
    }

    public void setCod_prod(int Cod_prod) {
        this.Cod_prod = Cod_prod;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public int getQtd() {
        return Qtd;
    }

    public void setQtd(int Qtd) {
        this.Qtd = Qtd;
    }

    public float getPreco() {
        return Preco;
    }

    public void setPreco(float Preco) {
        this.Preco = Preco;
    }

    public String getFkCatego() {
        return FkCatego;
    }

    public void setFkCatego(String FkCatego) {
        this.FkCatego = FkCatego;
    }

    public String getFkFornec() {
        return FkFornec;
    }

    public void setFkFornec(String FkFornec) {
        this.FkFornec = FkFornec;
    }

    public String getTipo_Pet() {
        return Tipo_Pet;
    }

    public void setTipo_Pet(String Tipo_Pet) {
        this.Tipo_Pet = Tipo_Pet;
    }

    public String getIdade_Pet() {
        return Idade_Pet;
    }

    public void setIdade_Pet(String Idade_Pet) {
        this.Idade_Pet = Idade_Pet;
    }

    public String getTamanho_Pet() {
        return Tamanho_Pet;
    }

    public void setTamanho_Pet(String Tamanho_Pet) {
        this.Tamanho_Pet = Tamanho_Pet;
    }

}
