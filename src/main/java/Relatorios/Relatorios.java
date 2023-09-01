/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Relatorios;

import Atributos.AcaoCobranca;
import Atributos.Comissoes;
import Atributos.Negociacao;
import Atributos.Pagamentos;
import Banco.AcaoCobrancaDAO;
import Banco.ComissoesDAO;
import Banco.NegociacaoDAO;
import Banco.PagamentosDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author User
 */
public class Relatorios {
    
    public List<Comissoes> relatorioModelo(){
        List<Comissoes> lista = new ArrayList<Comissoes>();

        Comissoes c = new Comissoes();
        Pagamentos p = new Pagamentos();
        Negociacao n = new Negociacao();
        
        NegociacaoDAO ndao = new NegociacaoDAO();
        PagamentosDAO pdao = new PagamentosDAO();
        ComissoesDAO cdao = new ComissoesDAO();
        
        lista = cdao.getComissoesPorMes(9, 2023);
        
        return lista;
    }
    
    
    public List<Comissoes> relatorioComissoes(int mes, int ano){
        List<Comissoes> lista = new ArrayList<Comissoes>();

        Comissoes c = new Comissoes();
        Pagamentos p = new Pagamentos();
        Negociacao n = new Negociacao();
        
        NegociacaoDAO ndao = new NegociacaoDAO();
        PagamentosDAO pdao = new PagamentosDAO();
        ComissoesDAO cdao = new ComissoesDAO();
        
        lista = cdao.getComissoesPorMes(mes, ano);
        
        return lista;
    }
    
    public List<AcaoCobranca> relatorioAcoesPorDevedorECliente(int idCliente, int idDevedor, LocalDate dataInicial, LocalDate dataFinal){
        List<AcaoCobranca> relatorioAcoes = new ArrayList<AcaoCobranca>();
        
        AcaoCobrancaDAO adao = new AcaoCobrancaDAO();
        relatorioAcoes = (adao.buscaAcoesPorDevedorECliente(idCliente, idDevedor, dataInicial, dataFinal));
        
        
        return relatorioAcoes;
    }
    
    public List<AcaoCobranca> relatorioAcoesPorDevedor(int idDevedor, LocalDate dataInicial, LocalDate dataFinal){
        List<AcaoCobranca> relatorioAcoes = new ArrayList<AcaoCobranca>();
        
        AcaoCobrancaDAO adao = new AcaoCobrancaDAO();
        relatorioAcoes = (adao.buscaAcoesPorDevedor(idDevedor, dataInicial, dataFinal));
        
        
        return relatorioAcoes;
    }
    
    public List<Pagamentos> relatorioPagamentosEfetuados(int idCliente, int idDevedor, LocalDate dataInicial, LocalDate dataFinal){
        List<Pagamentos> relPagamentos = new ArrayList<Pagamentos>();
        PagamentosDAO pdao = new PagamentosDAO();
        
        relPagamentos = pdao.buscaPagamentosPorDevedor(idDevedor, dataInicial, dataFinal);
        
        return relPagamentos;
    }
    
    public List<Pagamentos> relatorioPagamentosEmAberto(int idDevedor){
        List<Pagamentos> relPagamentos = new ArrayList<Pagamentos>();
        PagamentosDAO pdao = new PagamentosDAO();
        
        relPagamentos = pdao.buscaPagamentosEmAbertoPorDevedor(idDevedor);
        
        return relPagamentos;
    }
    
    public List<Pagamentos> relatorioPagamentosEmAtraso(int idDevedor){
        List<Pagamentos> relPagamentos = new ArrayList<Pagamentos>();
        PagamentosDAO pdao = new PagamentosDAO();
        
        relPagamentos = pdao.buscaPagamentosEmAtrasoPorDevedor(idDevedor);
        
        return relPagamentos;
    }
        
    public void imprimeRelatorio(String relatorio, HashMap parametros, List lista){
        
        if(relatorio.equals("acoes")){
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
            try {
                JasperPrint jasperPrint = JasperFillManager.fillReport("Relatorios/Relatorio_AcoesCobranca.jasper", parametros, dataSource);
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE); // Defina o comportamento de fechamento da janela do relatório
                jasperViewer.setVisible(true);
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }else if(relatorio.equals("comissoes")){
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
            try {
                JasperPrint jasperPrint = JasperFillManager.fillReport("Relatorios/relatorioComissoes.jasper", parametros, dataSource);
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE); // Defina o comportamento de fechamento da janela do relatório
                jasperViewer.setVisible(true);
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }else if(relatorio.equals("pagamentosEfetuados")){
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
            try {
                JasperPrint jasperPrint = JasperFillManager.fillReport("Relatorios/Relatorio_Pagamentos_Efetuados.jasper", parametros, dataSource);
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE); // Defina o comportamento de fechamento da janela do relatório
                jasperViewer.setVisible(true);
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }else if(relatorio.equals("pagamentosEmAberto")){
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
            try {
                JasperPrint jasperPrint = JasperFillManager.fillReport("Relatorios/Pagamentos_Em_Aberto.jasper", parametros, dataSource);
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE); // Defina o comportamento de fechamento da janela do relatório
                jasperViewer.setVisible(true);
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }else if(relatorio.equals("pagamentosEmAtraso")){
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
            try {
                JasperPrint jasperPrint = JasperFillManager.fillReport("Relatorios/Pagamentos_Em_Aberto.jasper", parametros, dataSource);
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE); // Defina o comportamento de fechamento da janela do relatório
                jasperViewer.setVisible(true);
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        
    }
    
    
}
