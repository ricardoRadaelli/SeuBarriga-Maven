/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Banco;
import Atributos.Negociacao;
import Atributos.Pagamentos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class NegociacaoDAO {
    private Connection conexao;
    
    public NegociacaoDAO(){
        conexao = ConexaoBD.getConexaoMySQL();
    }
    
    public void insereNegociacao(Negociacao n){
        String sql = "insert into negociacao (idCliente, idDevedor, dataNegociacao, dataPrimeiraParcela, formaPagamento, valorTotal, nParcelas, valorPago, saldoDevedor, valorOriginal, juros) values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, n.getIdCliente());
            stmt.setInt(2, n.getIdDevedor());
            stmt.setDate(3, converteData(n.getDataNegociacao()));
            stmt.setDate(4, converteData(n.getDataPrimeiraParcela()));
            stmt.setString(5, n.getFormaPagamento());
            stmt.setDouble(6, n.getValorTotal());
            stmt.setInt(7, n.getnParcelas());
            stmt.setDouble(8, 0);
            stmt.setDouble(9, n.getValorTotal());
            stmt.setDouble(10, n.getValorOriginal());
            stmt.setDouble(11, n.getJuros());
            
            int linhasAfetadas = stmt.executeUpdate();
            
            if(linhasAfetadas > 0){
                try{
                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if(generatedKeys.next()){
                        int generatedId = generatedKeys.getInt(1);
                        
                        String sql2 = "insert into negociacao_debitos (idNegociacao, idDebito) values (?,?)";
                        
                        for (int i = 0; i < n.getListaDebitos().size(); i++) {
                            PreparedStatement stmt2 = conexao.prepareStatement(sql2);
                            stmt2.setInt(1, generatedId);
                            stmt2.setInt(2, n.getListaDebitos().get(i).getIdCompra());
                            
                            stmt2.execute();
                        }
                        n.setIdNegociacao(generatedId);
                        geraPagamentos(n);
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Falha ao obter ID da compra");
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NegociacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void geraPagamentos(Negociacao neg){
        PagamentosDAO pdao = new PagamentosDAO();
        Pagamentos p = new Pagamentos();
        
        String data = "1900-01-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(data, formatter);
        
        for (int i = 0; i < neg.getnParcelas(); i++) {
            p.setDataPagamento(localDate);
            
            if(i==0)
                p.setDataVencimento(neg.getDataPrimeiraParcela());
            else
                p.setDataVencimento(neg.getDataPrimeiraParcela().plusMonths(i));
            
            p.setIdNegociacao(neg.getIdNegociacao());
            p.setPago(false);
            p.setValorPago(0);
            p.setValorParcela(neg.getValorTotal()/neg.getnParcelas());
            p.setFormaPagamento(neg.getFormaPagamento());
            
            pdao.inserePagamento(p);
        }
    }
    
    private java.sql.Date converteData(java.time.LocalDate data){
        java.sql.Date dataConvertida = null;
        
        dataConvertida = dataConvertida.valueOf(data);
        
        return dataConvertida;
    }
    
    public ArrayList<Negociacao> buscaNegociacoesPorCliente(int idCliente){
        String sql = "select * from negociacao where idCliente = ?";
        ArrayList<Negociacao> listaNegociacoes = new ArrayList<Negociacao>();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Negociacao negociacao = new Negociacao();
                negociacao.setDataNegociacao(rs.getDate("dataNegociacao").toLocalDate());
                negociacao.setDataPrimeiraParcela(rs.getDate("dataPrimeiraParcela").toLocalDate());
                negociacao.setFormaPagamento(rs.getString("formaPagamento"));
                negociacao.setIdCliente(rs.getInt("idCliente"));
                negociacao.setIdDevedor(rs.getInt("idDevedor"));
                negociacao.setIdNegociacao(rs.getInt("idNegociacao"));
                negociacao.setPago(rs.getBoolean("pago"));
                negociacao.setValorTotal(rs.getDouble("valorTotal"));
                negociacao.setnParcelas(rs.getInt("nParcelas"));
                negociacao.setValorPago(rs.getDouble("valorPago"));
                negociacao.setSaldoDevedor(rs.getDouble("saldoDevedor"));
                negociacao.setValorOriginal(rs.getDouble("valorOriginal"));
                negociacao.setJuros(rs.getDouble("juros"));
                
                
                DebitosDAO ddao = new DebitosDAO();
                negociacao.setListaDebitos(ddao.getDebitosPorNegociacao(rs.getInt("idNegociacao")));
                
                listaNegociacoes.add(negociacao);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NegociacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaNegociacoes;
    }
    
    public Negociacao buscaNegociacoesPorIdNegociacao(int idNegociacao){
        String sql = "select * from negociacao where idNegociacao = ?";
        Negociacao negociacao = new Negociacao();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idNegociacao);
            
            ResultSet rs = stmt.executeQuery();
            rs.next();
            
            negociacao.setDataNegociacao(rs.getDate("dataNegociacao").toLocalDate());
            negociacao.setDataPrimeiraParcela(rs.getDate("dataPrimeiraParcela").toLocalDate());
            negociacao.setFormaPagamento(rs.getString("formaPagamento"));
            negociacao.setIdCliente(rs.getInt("idCliente"));
            negociacao.setIdDevedor(rs.getInt("idDevedor"));
            negociacao.setIdNegociacao(rs.getInt("idNegociacao"));
            negociacao.setPago(rs.getBoolean("pago"));
            negociacao.setValorTotal(rs.getDouble("valorTotal"));
            negociacao.setnParcelas(rs.getInt("nParcelas"));
            negociacao.setValorPago(rs.getDouble("valorPago"));
            negociacao.setSaldoDevedor(rs.getDouble("saldoDevedor"));
            negociacao.setValorOriginal(rs.getDouble("valorOriginal"));
            negociacao.setJuros(rs.getDouble("juros"));
            

            DebitosDAO ddao = new DebitosDAO();
            negociacao.setListaDebitos(ddao.getDebitosPorNegociacao(rs.getInt("idNegociacao")));
            
            DevedoresDAO devdao = new DevedoresDAO();
            negociacao.setDevedor(devdao.buscaDevedorPorIdClienteEIdDevedor(negociacao.getIdDevedor(), negociacao.getIdCliente()));
            
            ClientesDAO cdao = new ClientesDAO();
            negociacao.setCliente(cdao.buscaClientesPorId(negociacao.getIdCliente()));
            
        } catch (SQLException ex) {
            Logger.getLogger(NegociacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return negociacao;
    }
    
    public ArrayList<Negociacao> buscaNegociacoesPorDevedor(int idDevedor){
        String sql = "select * from negociacao where idDevedor = ?";
        ArrayList<Negociacao> listaNegociacoes = new ArrayList<Negociacao>();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idDevedor);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Negociacao negociacao = new Negociacao();
                negociacao.setDataNegociacao(rs.getDate("dataNegociacao").toLocalDate());
                negociacao.setDataPrimeiraParcela(rs.getDate("dataPrimeiraParcela").toLocalDate());
                negociacao.setFormaPagamento(rs.getString("formaPagamento"));
                negociacao.setIdCliente(rs.getInt("idCliente"));
                negociacao.setIdDevedor(rs.getInt("idDevedor"));
                negociacao.setIdNegociacao(rs.getInt("idNegociacao"));
                negociacao.setPago(rs.getBoolean("pago"));
                negociacao.setValorTotal(rs.getDouble("valorTotal"));
                negociacao.setnParcelas(rs.getInt("nParcelas"));
                negociacao.setValorPago(rs.getDouble("valorPago"));
                negociacao.setSaldoDevedor(rs.getDouble("saldoDevedor"));
                negociacao.setValorOriginal(rs.getDouble("valorOriginal"));
                negociacao.setJuros(rs.getDouble("juros"));
                
                DebitosDAO ddao = new DebitosDAO();
                negociacao.setListaDebitos(ddao.getDebitosPorNegociacao(rs.getInt("idNegociacao")));
                
                listaNegociacoes.add(negociacao);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NegociacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaNegociacoes;
    }
    
    public ArrayList<Negociacao> buscaNegociacoesPorClienteEDevedor(int idCliente, int idDevedor){
        String sql = "select * from negociacao where idCliente = ? and idDevedor = ?";
        ArrayList<Negociacao> listaNegociacoes = new ArrayList<Negociacao>();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idDevedor);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Negociacao negociacao = new Negociacao();
                negociacao.setDataNegociacao(rs.getDate("dataNegociacao").toLocalDate());
                negociacao.setDataPrimeiraParcela(rs.getDate("dataPrimeiraParcela").toLocalDate());
                negociacao.setFormaPagamento(rs.getString("formaPagamento"));
                negociacao.setIdCliente(rs.getInt("idCliente"));
                negociacao.setIdDevedor(rs.getInt("idDevedor"));
                negociacao.setIdNegociacao(rs.getInt("idNegociacao"));
                negociacao.setPago(rs.getBoolean("pago"));
                negociacao.setValorTotal(rs.getDouble("valorTotal"));
                negociacao.setnParcelas(rs.getInt("nParcelas"));
                negociacao.setValorPago(rs.getDouble("valorPago"));
                negociacao.setSaldoDevedor(rs.getDouble("saldoDevedor"));
                negociacao.setValorOriginal(rs.getDouble("valorOriginal"));
                negociacao.setJuros(rs.getDouble("juros"));
                
                listaNegociacoes.add(negociacao);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NegociacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaNegociacoes;
    }
    
    public int getParcelasPagas(int idNegociacao){
        int parcelasPagas=0;
        
        PagamentosDAO pdao = new PagamentosDAO();
        Pagamentos pag = new Pagamentos();
        ArrayList<Pagamentos> listaPag = pdao.buscaPagamentosPorNegociacao(idNegociacao);
        System.out.println("Tamanho lista pagamentos->"+listaPag.size());
        for (int i = 0; i < listaPag.size(); i++) {
            if(listaPag.get(i).getPago() == true)
                parcelasPagas++;
        }
        return parcelasPagas;
    }
    
    public Double getSaldoDevedor(int idNegociacao){
        Double saldoDevedor = 0.0;
        
        String sql = "select saldoDevedor from negociacao where idNegociacao = ?";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idNegociacao);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            
            saldoDevedor = rs.getDouble("saldoDevedor");
            
        } catch (SQLException ex) {
            Logger.getLogger(NegociacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return saldoDevedor;
    }
    
    public Double getValorPago(int idNegociacao){
        Double valorPago = 0.0;
        
        PagamentosDAO pdao = new PagamentosDAO();
        Pagamentos pag = new Pagamentos();
        ArrayList<Pagamentos> listaPag = pdao.buscaPagamentosPorNegociacao(idNegociacao);
        
        for (int i = 0; i < listaPag.size(); i++) {
            if(listaPag.get(i).getPago()== true)
                valorPago += listaPag.get(i).getValorPago();
        }
        
        return valorPago;
    }
    
    public void atualizaValorPago(Connection con, double valorPago, int idNegociacao) throws SQLException{
        String sql = "select * from negociacao as n, pagamentos as p where n.idNegociacao = ? and n.idNegociacao = p.idNegociacao;";
        
        Double saldoDevedor=0.0;
        
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, idNegociacao);
        ResultSet rs = stmt.executeQuery();
        
        rs.next();
        valorPago += rs.getDouble("valorPago");
        saldoDevedor = rs.getDouble("saldoDevedor")-rs.getDouble("valorParcela");
        
        
        sql = "update negociacao set valorPago = ?, saldoDevedor = ? where idNegociacao = ?";
        stmt = con.prepareStatement(sql);
        stmt.setDouble(1, valorPago);
        stmt.setDouble(2, saldoDevedor);
        stmt.setInt(3, idNegociacao);
        
        stmt.executeUpdate();
    }
    
    public void baixaNegociacao(Connection con, int idNegociacao) throws SQLException{
        PagamentosDAO p = new PagamentosDAO();
        boolean pago = p.terminouPagamentos(idNegociacao);
        
        if(pago){
            String sql = "update negociacao set pago = 1 where idNegociacao = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idNegociacao);
            stmt.executeUpdate();
        }
    }
    
    public boolean debitoEmNegociacao(int idDebito){
        boolean negociacao=false;
        
        String sql = "SELECT EXISTS(SELECT idDebito FROM `negociacao_debitos` WHERE idDebito = ?) AS resultado;";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idDebito);
            
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getBoolean("resultado");
        } catch (SQLException ex) {
            Logger.getLogger(NegociacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return negociacao;
    }
    
}
