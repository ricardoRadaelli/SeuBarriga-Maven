/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Atributos;


/**
 *
 * @author User
 */
public class Clientes {
    private String cpfcnpj; 
    private String nomeCliente;
    private int idCliente;
    private double valorEmCobranca;
    private double comissao;
    private int diaReembolso;
    private double custoDevedorNaoPago;
    private String razao;
    private String responsavel;
    private String informacoesAdicionais;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getValorEmCobranca() {
        return valorEmCobranca;
    }

    public void setValorEmCobranca(double valorEmCobranca) {
        this.valorEmCobranca = valorEmCobranca;
    }
    
    public double getCustoDevedorNaoPago() {
        return custoDevedorNaoPago;
    }

    public void setCustoDevedorNaoPago(double custoDevedorNaoPago) {
        this.custoDevedorNaoPago = custoDevedorNaoPago;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(String informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public int getDiaReembolso() {
        return diaReembolso;
    }

    public void setDiaReembolso(int diaReembolso) {
        this.diaReembolso = diaReembolso;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nome) {
        this.nomeCliente = nome;
    }
}
