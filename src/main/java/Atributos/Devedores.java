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
public class Devedores {
    private int idDevedor;
    private int idCliente;
    private String cpfcnpj;
    private String nomeDevedor;
    private double saldoDevedor;
    private double taxaJuros;
    private LocalDate dataFinalizacaoCobranca;
    private LocalDate dataCadastro;
    private String razao;
    private Boolean Ativado;

    public Boolean getAtivado() {
        return Ativado;
    }

    public void setAtivado(Boolean Ativado) {
        this.Ativado = Ativado;
    }

    public LocalDate getDataFinalizacaoCobranca() {
        return dataFinalizacaoCobranca;
    }

    public void setDataFinalizacaoCobranca(LocalDate dataFinalizacaoCobranca) {
        this.dataFinalizacaoCobranca = dataFinalizacaoCobranca;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }
    
    public double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public int getIdDevedor() {
        return idDevedor;
    }

    public void setIdDevedor(int idDevedor) {
        this.idDevedor = idDevedor;
    }

    public double getSaldoDevedor() {
        return saldoDevedor;
    }

    public void setSaldoDevedor(double saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }

    public String getNomeDevedor() {
        return nomeDevedor;
    }

    public void setNomeDevedor(String nome) {
        this.nomeDevedor = nome;
    }
    
    
}
