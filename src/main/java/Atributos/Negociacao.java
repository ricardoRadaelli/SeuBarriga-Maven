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
public class Negociacao {
    private int idNegociacao;
    private int idCliente;
    private int idDevedor;
    private LocalDate dataNegociacao;
    private LocalDate dataPrimeiraParcela;
    private String formaPagamento;
    private double valorTotal;
    private double valorPago;
    private double valorOriginal;
    private double juros;
    private double saldoDevedor;
    private int nParcelas;
    private Boolean pago;
    private ArrayList<Debitos> listaDebitos;
    private Clientes cliente;
    private Devedores devedor;
    private ArrayList<Pagamentos> listaPagamentos;

    public ArrayList<Pagamentos> getListaPagamentos() {
        return listaPagamentos;
    }

    public void setListaPagamentos(ArrayList<Pagamentos> listaPagamentos) {
        this.listaPagamentos = listaPagamentos;
    }
    
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

    public double getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(double valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public double getSaldoDevedor() {
        return saldoDevedor;
    }

    public void setSaldoDevedor(double saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public ArrayList<Debitos> getListaDebitos() {
        return listaDebitos;
    }

    public void setListaDebitos(ArrayList<Debitos> listaDebitos) {
        this.listaDebitos = listaDebitos;
    }

    public int getIdNegociacao() {
        return idNegociacao;
    }

    public void setIdNegociacao(int idNegociacao) {
        this.idNegociacao = idNegociacao;
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

    public LocalDate getDataNegociacao() {
        return dataNegociacao;
    }

    public void setDataNegociacao(LocalDate dataNegociacao) {
        this.dataNegociacao = dataNegociacao;
    }

    public LocalDate getDataPrimeiraParcela() {
        return dataPrimeiraParcela;
    }

    public void setDataPrimeiraParcela(LocalDate dataPrimeiraParcela) {
        this.dataPrimeiraParcela = dataPrimeiraParcela;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getnParcelas() {
        return nParcelas;
    }

    public void setnParcelas(int nParcelas) {
        this.nParcelas = nParcelas;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }
}
