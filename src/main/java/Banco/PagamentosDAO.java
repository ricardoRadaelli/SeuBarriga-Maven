/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Banco;
import Atributos.Clientes;
import Atributos.Comissoes;
import Atributos.Devolucoes;
import Atributos.Negociacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Atributos.Pagamentos;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class PagamentosDAO {
    private Connection conexao; 
    
    public PagamentosDAO(){
        conexao = ConexaoBD.getConexaoMySQL();
    }
    
    public void inserePagamento(Pagamentos p){
        String sql = "insert into pagamentos (dataPagamento, formaPagamento, valorPago, valorParcela, idNegociacao, dataVencimento, pago, encargos) values (?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(p.getDataPagamento()));
            stmt.setString(2, p.getFormaPagamento());
            stmt.setDouble(3, p.getValorPago());
            stmt.setDouble(4, p.getValorParcela());
            stmt.setInt(5, p.getIdNegociacao());
            stmt.setDate(6, java.sql.Date.valueOf(p.getDataVencimento()));
            stmt.setBoolean(7, p.getPago());
            stmt.setDouble(8, p.getEncargos());
            
            stmt.execute();
            
        } catch (SQLException ex) {
            System.out.println("Erro ao criar pagamentos!"+ex);
        }
    }
    
    public void efetuarPagamento(Pagamentos p){
        String sql = "update pagamentos set dataPagamento = ?, valorPago = ?, pago = 1, encargos =? where idPagamento = ?";
        
        try {
            conexao.setAutoCommit(false);
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(p.getDataPagamento()));
            stmt.setDouble(2, p.getValorPago());
            stmt.setDouble(3, p.getEncargos());
            stmt.setInt(4, p.getIdPagamento());
            
            stmt.executeUpdate();
            
            NegociacaoDAO ndao = new NegociacaoDAO();
            ndao.atualizaValorPago(conexao, p.getValorPago(), p.getIdNegociacao());
            ndao.baixaNegociacao(conexao, p.getIdNegociacao());
            
            SaldosDAO sdao = new SaldosDAO();
            sdao.calculaSaldoDevedorNegociacao(0);
            
            Negociacao neg = ndao.buscaNegociacoesPorIdNegociacao(p.getIdNegociacao());
            sdao.calculaSaldoDevedor_Devedor_Para_Cliente(neg.getIdDevedor(), neg.getIdCliente(), p.getValorParcela(), "sub", conexao);
            
            ClientesDAO cdao = new ClientesDAO();
            Clientes cliente = cdao.buscaClientesPorId(neg.getIdCliente());
            
            ComissoesDAO comDAO = new ComissoesDAO();
            DevolucoesDAO devoDAO = new DevolucoesDAO();
            
            Comissoes comi = calculaValorComissao(p, cliente.getComissao());
            Devolucoes devo = calculaValorDevolucao(p, cliente.getDiaReembolso(), cliente.getComissao());
            
            if((comi.getValorComissao()+devo.getValorDevolvido()) == p.getValorPago()){
                comDAO.registraComissao(comi, conexao);
                devoDAO.insereDevolucao(devo , conexao);
                
                conexao.commit();
            }else
                JOptionPane.showMessageDialog(null, "Diferença de valores de comissao e devolucao com valor pago pelo devedor!");
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar saldo devedor!"+ex);
            if(conexao != null){
                try{
                    conexao.rollback();
                }catch(SQLException e){
                    System.out.println("Erro ao criar pagamentos!"+e);
                }
            }
        }
    }
    
    private Devolucoes calculaValorDevolucao(Pagamentos p, int diaReembolso, double taxaComissao){
        LocalDate dataDevolucao = p.getDataPagamento().plusMonths(1);
        dataDevolucao = dataDevolucao.withDayOfMonth(diaReembolso);
        
        Devolucoes devolucao = new Devolucoes();
        devolucao.setDataDevolucao(dataDevolucao);
        devolucao.setDevolvido(false);
        devolucao.setIdPagamento(p.getIdPagamento());
        
        
        if(!p.getDataPagamento().isAfter(p.getDataVencimento())){
            
            devolucao.setValorDevolvido(p.getValorPago()-((taxaComissao/100)*p.getValorPago()));
        }else{
            devolucao.setValorDevolvido(p.getValorPago()-p.getEncargos()-((taxaComissao/100)*(p.getValorPago()-p.getEncargos())));
        }
        
        return devolucao;
    }
    
    private Comissoes calculaValorComissao(Pagamentos p, double taxaComissao){
        Comissoes com = new Comissoes();
        com.setIdPagamento(p.getIdPagamento());
        
        if(!p.getDataPagamento().isAfter(p.getDataVencimento())){
            com.setValorComissao((taxaComissao/100)*p.getValorPago());
            
        }else{
            com.setValorComissao((taxaComissao/100)*(p.getValorPago()-p.getEncargos())+p.getEncargos());
        }
                
        return com;
    }
    
    public void desfazerPagamento(Pagamentos p){
        String sql = "update pagamentos set dataPagamento = ?, valorPago = ?, pago = 0, encargos =? where idPagamento = ?";
        
        try {
            conexao.setAutoCommit(false);
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf("0000-00-00"));
            stmt.setDouble(2, 0.0);
            stmt.setDouble(3, 0.0);
            stmt.setInt(4, p.getIdPagamento());
            
            stmt.executeUpdate();
            
            NegociacaoDAO ndao = new NegociacaoDAO();

            //------------------------------------------------------------------------------------------------------------------------------------
                    
            String sql2 = "select * from negociacao as n, pagamentos as p where n.idNegociacao = ? and n.idNegociacao = p.idNegociacao;";
        
            Double saldoDevedor=0.0;

            PreparedStatement stmt2 = conexao.prepareStatement(sql2);
            stmt2.setInt(1, p.getIdNegociacao());
            ResultSet rs2 = stmt2.executeQuery();

            rs2.next();
            Double valorPago = rs2.getDouble("valorPago")-p.getValorPago();
            saldoDevedor = rs2.getDouble("saldoDevedor")+rs2.getDouble("valorParcela");


            sql = "update negociacao set valorPago = ?, saldoDevedor = ?, pago = 0 where idNegociacao = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, valorPago);
            stmt.setDouble(2, saldoDevedor);
            stmt.setInt(3, p.getIdNegociacao());

            stmt.executeUpdate();
          
            //------------------------------------------------------------------------------------------------------------------------------------
            
            conexao.commit();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar saldo devedor!"+ex);
            if(conexao != null){
                try{
                    conexao.rollback();
                }catch(SQLException e){
                    System.out.println("Erro ao criar pagamentos!"+e);
                }
            }
        }
    }
    
    public ArrayList<Pagamentos> buscaPagamentosPorNegociacao(int idNegociacao){
        ArrayList<Pagamentos> listaPagamentos = new ArrayList<Pagamentos>();
        String sql = "select * from pagamentos where idNegociacao = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idNegociacao);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Pagamentos p = new Pagamentos();
                p.setDataPagamento((rs.getDate("dataPagamento")).toLocalDate());
                p.setDataVencimento((rs.getDate("dataVencimento")).toLocalDate());
                p.setFormaPagamento(rs.getString("formaPagamento"));
                p.setIdNegociacao(rs.getInt("idNegociacao"));
                p.setIdPagamento(rs.getInt("idPagamento"));
                p.setPago(rs.getBoolean("pago"));
                p.setValorPago(rs.getDouble("valorPago"));
                p.setValorParcela(rs.getDouble("valorParcela"));
                p.setEncargos(rs.getDouble("encargos"));
                
                listaPagamentos.add(p);
                
            }
            return listaPagamentos;
        } catch (SQLException ex) {
            Logger.getLogger(PagamentosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaPagamentos;
    }
    
    public ArrayList<Pagamentos> buscaPagamentosPorDevedor(int idDevedor, LocalDate dataInicial, LocalDate dataFinal){
        ArrayList<Pagamentos> listaPagamentos = new ArrayList<Pagamentos>();
        String sql = "SELECT * FROM pagamentos as p JOIN negociacao as n on p.idNegociacao = n.idNegociacao where n.idDevedor= ? and p.dataPagamento BETWEEN ? and ? and p.pago =1";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idDevedor);
            stmt.setDate(2, java.sql.Date.valueOf(dataInicial));
            stmt.setDate(3, java.sql.Date.valueOf(dataFinal));
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Pagamentos p = new Pagamentos();
                p.setDataPagamento((rs.getDate("dataPagamento")).toLocalDate());
                p.setDataVencimento((rs.getDate("dataVencimento")).toLocalDate());
                p.setFormaPagamento(rs.getString("formaPagamento"));
                p.setIdNegociacao(rs.getInt("idNegociacao"));
                p.setIdPagamento(rs.getInt("idPagamento"));
                p.setPago(rs.getBoolean("pago"));
                p.setValorPago(rs.getDouble("valorPago"));
                p.setValorParcela(rs.getDouble("valorParcela"));
                p.setEncargos(rs.getDouble("encargos"));
                
                NegociacaoDAO ndao = new NegociacaoDAO();
                p.setNegociacao(ndao.buscaNegociacoesPorIdNegociacao(p.getIdNegociacao()));
                
                listaPagamentos.add(p);
                
            }
            return listaPagamentos;
        } catch (SQLException ex) {
            Logger.getLogger(PagamentosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaPagamentos;
    }
    
    public ArrayList<Pagamentos> buscaPagamentosEmAbertoPorDevedor(int idDevedor){
        ArrayList<Pagamentos> listaPagamentos = new ArrayList<Pagamentos>();
        String sql = "SELECT * FROM pagamentos as p JOIN negociacao as n on p.idNegociacao = n.idNegociacao where n.idDevedor= ? and p.pago =0";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idDevedor);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Pagamentos p = new Pagamentos();
                p.setDataPagamento((rs.getDate("dataPagamento")).toLocalDate());
                p.setDataVencimento((rs.getDate("dataVencimento")).toLocalDate());
                p.setFormaPagamento(rs.getString("formaPagamento"));
                p.setIdNegociacao(rs.getInt("idNegociacao"));
                p.setIdPagamento(rs.getInt("idPagamento"));
                p.setPago(rs.getBoolean("pago"));
                p.setValorPago(rs.getDouble("valorPago"));
                p.setValorParcela(rs.getDouble("valorParcela"));
                p.setEncargos(rs.getDouble("encargos"));
                
                NegociacaoDAO ndao = new NegociacaoDAO();
                p.setNegociacao(ndao.buscaNegociacoesPorIdNegociacao(p.getIdNegociacao()));
                
                listaPagamentos.add(p);
                
            }
            return listaPagamentos;
        } catch (SQLException ex) {
            Logger.getLogger(PagamentosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaPagamentos;
    }
    
    public ArrayList<Pagamentos> buscaPagamentosEmAtrasoPorDevedor(int idDevedor){
        ArrayList<Pagamentos> listaPagamentos = new ArrayList<Pagamentos>();
        String sql = "SELECT * FROM pagamentos as p JOIN negociacao as n on p.idNegociacao = n.idNegociacao where n.idDevedor= ? and p.pago =0 and dataVencimento < CURDATE()";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idDevedor);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Pagamentos p = new Pagamentos();
                p.setDataPagamento((rs.getDate("dataPagamento")).toLocalDate());
                p.setDataVencimento((rs.getDate("dataVencimento")).toLocalDate());
                p.setFormaPagamento(rs.getString("formaPagamento"));
                p.setIdNegociacao(rs.getInt("idNegociacao"));
                p.setIdPagamento(rs.getInt("idPagamento"));
                p.setPago(rs.getBoolean("pago"));
                p.setValorPago(rs.getDouble("valorPago"));
                p.setValorParcela(rs.getDouble("valorParcela"));
                p.setEncargos(rs.getDouble("encargos"));
                
                NegociacaoDAO ndao = new NegociacaoDAO();
                p.setNegociacao(ndao.buscaNegociacoesPorIdNegociacao(p.getIdNegociacao()));
                
                listaPagamentos.add(p);
                
            }
            return listaPagamentos;
        } catch (SQLException ex) {
            Logger.getLogger(PagamentosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaPagamentos;
    }
    
    public Pagamentos getPagamento(int idPagamento){
        Pagamentos pag = new Pagamentos();
        String sql = "select * from pagamentos where idPagamento = ?";
        
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idPagamento);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            
            pag.setDataPagamento(rs.getDate("dataPagamento").toLocalDate());
            pag.setDataVencimento(rs.getDate("dataVencimento").toLocalDate());
            pag.setFormaPagamento(rs.getString("formaPagamento"));
            pag.setIdNegociacao(rs.getInt("idNegociacao"));
            pag.setIdPagamento(rs.getInt("idPagamento"));
            pag.setPago(rs.getBoolean("pago"));
            pag.setValorPago(rs.getDouble("valorPago"));
            pag.setValorParcela(rs.getDouble("valorParcela"));
            pag.setEncargos(rs.getDouble("encargos"));
            
            NegociacaoDAO ndao = new NegociacaoDAO();
            pag.setNegociacao(ndao.buscaNegociacoesPorIdNegociacao(pag.getIdNegociacao()));
            
            return pag;
            
        } catch (SQLException ex) {
            Logger.getLogger(PagamentosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return pag;
    }
    
    public void atualizaPagamento(Pagamentos p){
        String sql = "update pagamentos set dataPagamento=?, formaPagamento=?, valorPago=?, valorParcela=?, dataVencimento=?, encargos=? where idPagamento=?";
        
        try {
            
            conexao.setAutoCommit(false);
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setDate(1, java.sql.Date.valueOf(p.getDataPagamento()));
            stmt.setString(2, p.getFormaPagamento());
            stmt.setDouble(3, p.getValorPago());
            stmt.setDouble(4, p.getValorParcela());
            stmt.setDate(5, java.sql.Date.valueOf(p.getDataVencimento()));
            stmt.setDouble(6, p.getEncargos());
            stmt.setInt(7, p.getIdPagamento());
            
            stmt.executeUpdate();
            
            
            confereValorNegociacao(p.getIdNegociacao());
            
            conexao.commit();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar saldo devedor!"+ex);
            if(conexao != null){
                try{
                    conexao.rollback();
                }catch(SQLException e){
                    System.out.println("Erro ao criar pagamentos!"+e);
                }
            }
        }
    }
    
    private void confereValorNegociacao(int idNegociacao){
        //Construir lógica para validar o valor dos demais boletos, porém, tem que considerar que o devedor pode quitar a dívida com desconto, então tem que ser possível, através de senha ou algo, finalizar a dívida por um valor menor que o negociado inicialmente
        
    }
    
    public boolean terminouPagamentos(int idNegociacao){
        boolean pago = true;
        
        String sql = "select * from pagamentos where idNegociacao = ?";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idNegociacao);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                if(rs.getBoolean("pago") == false)
                    pago = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PagamentosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pago;
    }
    
    public double getEncargos(int idPagamento){
        String sql = "select encargos from pagamentos where idPagamento =?";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idPagamento);
            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            
            return rs.getDouble("encargos");
            
        } catch (SQLException ex) {
            Logger.getLogger(PagamentosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0;
    }
}
