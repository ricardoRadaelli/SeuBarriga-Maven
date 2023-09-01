/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Atributos;

/**
 *
 * @author User
 */
public class Comissoes {
    
    public Comissoes(){
    
    }
    
    
    private int idComissao;
    private int idPagamento;
    private double valorComissao;
    private Pagamentos pag;

    public Pagamentos getPag() {
        return pag;
    }

    public void setPag(Pagamentos pag) {
        this.pag = pag;
    }

    public int getIdComissao() {
        return idComissao;
    }

    public void setIdComissao(int idComissao) {
        this.idComissao = idComissao;
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public double getValorComissao() {
        return valorComissao;
    }

    public void setValorComissao(double valorComissao) {
        this.valorComissao = valorComissao;
    }
}
