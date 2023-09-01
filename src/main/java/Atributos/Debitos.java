/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Atributos;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Debitos {
    private int idCompra;
    private int idCliente;
    private int idDevedor;
    private double valor;
    private LocalDate dataCompra;
    private String descricao;
    private LocalDate dataInclusao;
    private ArrayList<Comprovantes> comprovantes;
    
    public Boolean getGetCompraNull() {
        if(dataCompra == null)
            return true;
        else
            return false;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public LocalDate getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDate dataInclusao) {
        this.dataInclusao = dataInclusao;
    }
    
    public ArrayList<Comprovantes> getComprovantes() {
        return comprovantes;
    }

    public void setComprovantes(ArrayList<Comprovantes> comprovantes) {
        this.comprovantes = comprovantes;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
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
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
