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
public class AgendamentosVO {

    private int Cod_agend;
    private String FkTipoServ;
    private String FkFunc;
    private Date DataAgend;
    private String Horario;
    private String Obs;
    private boolean Status;
    private String FkPet;
    private String FkDono;

    public int getCod_agend() {
        return Cod_agend;
    }

    public void setCod_agend(int Cod_agend) {
        this.Cod_agend = Cod_agend;
    }

    public String getFkTipoServ() {
        return FkTipoServ;
    }

    public void setFkTipoServ(String FkTipoServ) {
        this.FkTipoServ = FkTipoServ;
    }

    public String getFkFunc() {
        return FkFunc;
    }

    public void setFkFunc(String FkFunc) {
        this.FkFunc = FkFunc;
    }

    public Date getDataAgend() {
        return DataAgend;
    }

    public void setDataAgend(Date DataAgend) {
        this.DataAgend = DataAgend;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String Horario) {
        this.Horario = Horario;
    }

    public String getObs() {
        return Obs;
    }

    public void setObs(String Obs) {
        this.Obs = Obs;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public String getFkPet() {
        return FkPet;
    }

    public void setFkPet(String FkPet) {
        this.FkPet = FkPet;
    }

    public String getFkDono() {
        return FkDono;
    }

    public void setFkDono(String FkDono) {
        this.FkDono = FkDono;
    }

}
