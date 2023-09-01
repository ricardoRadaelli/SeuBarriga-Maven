/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Atributos;

import java.time.LocalDate;

/**
 *
 * @author User
 */
public class Telefones {
    private int idTelefone;
    private String telefone;
    private int idDevedor;
    private int idCliente;
    private LocalDate dataInclusao;
    private LocalDate dataUltimoContato;
    private String obs;
    private String email;

    public LocalDate getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDate dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public LocalDate getDataUltimoContato() {
        return dataUltimoContato;
    }

    public void setDataUltimoContato(LocalDate dataUltimoContato) {
        this.dataUltimoContato = dataUltimoContato;
    }

    public int getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(int idTelefone) {
        this.idTelefone = idTelefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdDevedor() {
        return idDevedor;
    }

    public void setIdDevedor(int idDevedor) {
        this.idDevedor = idDevedor;
    }
    
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
