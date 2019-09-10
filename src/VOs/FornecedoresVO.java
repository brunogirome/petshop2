/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VOs;

/**
 *
 * @author 28264088
 */
public class FornecedoresVO {
    
/*cod_fornec
nome
detalhes
cnpj
email
tel
cel*/
    
    private int Cod_fornec;
    private String Nome;
    private String Detalhes;
    private String Cnpj;
    private String Email;
    private String Tel;
    private String Cel;

    public int getCod_fornec() {
        return Cod_fornec;
    }

    public void setCod_fornec(int Cod_fornec) {
        this.Cod_fornec = Cod_fornec;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getDetalhes() {
        return Detalhes;
    }

    public void setDetalhes(String Detalhes) {
        this.Detalhes = Detalhes;
    }

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String Cnpj) {
        this.Cnpj = Cnpj;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
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
    
    
    
}
