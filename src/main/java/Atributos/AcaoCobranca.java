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
public class AcaoCobranca {
    private int idAcao;
    private int idCliente;
    private int idDevedor;
    private int idUsuario;
    private LocalDate dataCobranca;
    private String tipoCobranca;
    private String textoCobranca;
    private Clientes cliente;
    private Devedores devedor;

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Devedores getDevedor() {
        return devedor;
    }

    public void setDevedor(Devedores devedor) {
        this.devedor = devedor;
    }

    public int getIdAcao() {
        return idAcao;
    }

    public void setIdAcao(int idAcao) {
        this.idAcao = idAcao;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdDevedor() {
        return idDevedor;
    }

    public void setIdDevedor(int idDevedor) {
        this.idDevedor = idDevedor;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getDataCobranca() {
        return dataCobranca;
    }

    public void setDataCobranca(LocalDate dataCobranca) {
        this.dataCobranca = dataCobranca;
    }

    public String getTipoCobranca() {
        return tipoCobranca;
    }

    public void setTipoCobranca(String tipoCobranca) {
        this.tipoCobranca = tipoCobranca;
    }

    public String getTextoCobranca() {
        return textoCobranca;
    }

    public void setTextoCobranca(String textoCobranca) {
        this.textoCobranca = textoCobranca;
    }
}
