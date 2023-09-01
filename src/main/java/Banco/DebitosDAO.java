/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Banco;

import Atributos.Comprovantes;
import Atributos.Debitos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class DebitosDAO {
    private Connection conexao=null;
    
    public DebitosDAO(){
        conexao = ConexaoBD.getConexaoMySQL();
    }
    
    public void insereDebitos(Debitos debito, Comprovantes comprovante){
        String sql = "insert into debitos (idCliente, idDevedor, valor, dataCompra, descricao, dataInclusao) values"
                + "(?,?,?,?,?,?)";
        
        try {
            conexao.setAutoCommit(false);
            PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, debito.getIdCliente());
            stmt.setInt(2, debito.getIdDevedor());
            stmt.setDouble(3, debito.getValor());
            stmt.setDate(4, java.sql.Date.valueOf(debito.getDataCompra()));
            stmt.setString(5, debito.getDescricao());
            stmt.setDate(6, java.sql.Date.valueOf(debito.getDataInclusao()));
            
            int linhasAfetadas = stmt.executeUpdate();
            
            if(linhasAfetadas > 0){
                try{
                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if(generatedKeys.next()){
                        int generatedId = generatedKeys.getInt(1);
                        ComprovantesDAO compDAO = new ComprovantesDAO();
                        comprovante.setIdCompra(generatedId);
                        compDAO.insereComprovante(comprovante, conexao);
                        
                        SaldosDAO sdao = new SaldosDAO();
                        sdao.calculaSaldoDevedor_Devedor_Para_Cliente(debito.getIdDevedor(), debito.getIdCliente(), debito.getValor(), "soma", conexao);
                        sdao.calculaValorEmCobranca_doCliente(debito.getIdCliente(), debito.getValor(), "soma", conexao);

                        conexao.commit();
                    }else{
                        JOptionPane.showMessageDialog(null, "Falha ao gravar comprovante");
                    }
                }catch(Exception e){
                
                }
            }
            
        }catch(SQLException e){
            System.out.println("Erro ao gravar débito!"+e);
            if(conexao != null){
                try{
                    conexao.rollback();
                }catch(SQLException ex){
                    System.out.println("Erro ao salvar débito!"+ex);
                    ex.printStackTrace();
                }
            }
        }
        
    }
    
    public void atualizaDebitos(Debitos debito, Comprovantes comprovante, String comprovanteAntigo){
        String sql = "update debitos set valor=?, dataCompra=?, descricao=? where idDebito =?";
        
        try {
            conexao.setAutoCommit(false);
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setDouble(1, debito.getValor());
            stmt.setDate(2, java.sql.Date.valueOf(debito.getDataCompra()));
            stmt.setString(3, debito.getDescricao());
            stmt.setInt(4, debito.getIdCompra());
            
            stmt.executeUpdate();
            
            ComprovantesDAO cdao = new ComprovantesDAO();
            cdao.atualizaComprovante(comprovante, conexao);
            
            Debitos debAntigo = buscaDebitosPorIdCompra(debito.getIdCompra());
            
            SaldosDAO sdao = new SaldosDAO();
            sdao.calculaSaldoDevedor_Devedor_Para_Cliente(debito.getIdDevedor(), debito.getIdCliente(), debAntigo.getValor(), "sub", conexao);
            sdao.calculaSaldoDevedor_Devedor_Para_Cliente(debito.getIdDevedor(), debito.getIdCliente(), debito.getValor(), "soma", conexao);

            sdao.calculaValorEmCobranca_doCliente(debito.getIdCliente(), debAntigo.getValor(), "sub", conexao);
            sdao.calculaValorEmCobranca_doCliente(debito.getIdCliente(), debito.getValor(), "soma", conexao);

            
            conexao.commit();
            
            if(!comprovanteAntigo.equals(""))
                cdao.apagaArquivoComprovante(comprovanteAntigo);
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar débito!"+ex);
            if(conexao != null){
                try{
                    conexao.rollback();
                }catch(SQLException e){
                    System.out.println("Erro ao atualizar débito!!"+e);
                    e.printStackTrace();
                }
            }
        }
    }
    
    public Debitos buscaDebitosPorIdCompra(int idDebito){
        String sqlDebitos;
        PreparedStatement stmt = null;
        ResultSet rs;
        Debitos debito = new Debitos();
        
        try{
            sqlDebitos = "select * from debitos where idDebito = ?";
            stmt = conexao.prepareStatement(sqlDebitos);
            stmt.setInt(1, idDebito);

            rs = stmt.executeQuery();

            while(rs.next()){
                debito.setIdCompra(rs.getInt("idDebito"));
                debito.setIdCliente(rs.getInt("idCliente"));
                debito.setIdDevedor(rs.getInt("idDevedor"));
                debito.setValor(rs.getDouble("valor"));
                debito.setDataCompra(rs.getDate("dataCompra").toLocalDate());
                debito.setDescricao(rs.getString("descricao"));
                debito.setDataInclusao(rs.getDate("dataInclusao").toLocalDate());
                
                debito.setComprovantes(buscaComprovantes(rs.getInt("idDebito")));
                
                return debito;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return debito;
    }
    
    public ArrayList<Debitos> buscaDebitos(Integer idDevedor){
        ArrayList<Debitos> listaDebitos=  new ArrayList<Debitos>();
        String sqlDebitos;
        PreparedStatement stmt = null;
        ResultSet rs;
        
        try{
            sqlDebitos = "select * from debitos where idDevedor = ?";
            stmt = conexao.prepareStatement(sqlDebitos);
            stmt.setInt(1, idDevedor);

            rs = stmt.executeQuery();

            while(rs.next()){
                Debitos debito = new Debitos();
                debito.setIdCompra(rs.getInt("idDebito"));
                debito.setIdCliente(rs.getInt("idCliente"));
                debito.setIdDevedor(rs.getInt("idDevedor"));
                debito.setValor(rs.getDouble("valor"));
                debito.setDataCompra(rs.getDate("dataCompra").toLocalDate());
                debito.setDescricao(rs.getString("descricao"));
                debito.setDataInclusao(rs.getDate("dataInclusao").toLocalDate());
                
                debito.setComprovantes(buscaComprovantes(rs.getInt("idDebito")));
                
                listaDebitos.add(debito);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return listaDebitos;
    }
    
    public ArrayList<Debitos> buscaDebitosPorCliente(Integer idCliente){
        ArrayList<Debitos> listaDebitos=  new ArrayList<Debitos>();
        String sqlDebitos;
        PreparedStatement stmt = null;
        ResultSet rs;
        
        try{
            sqlDebitos = "select * from debitos where idCliente = ?";
            stmt = conexao.prepareStatement(sqlDebitos);
            stmt.setInt(1, idCliente);

            rs = stmt.executeQuery();

            while(rs.next()){
                Debitos debito = new Debitos();
                debito.setIdCompra(rs.getInt("idDebito"));
                debito.setIdCliente(rs.getInt("idCliente"));
                debito.setIdDevedor(rs.getInt("idDevedor"));
                debito.setValor(rs.getDouble("valor"));
                debito.setDataCompra(rs.getDate("dataCompra").toLocalDate());
                debito.setDescricao(rs.getString("descricao"));
                debito.setDataInclusao(rs.getDate("dataInclusao").toLocalDate());
                
                debito.setComprovantes(buscaComprovantes(rs.getInt("idDebito")));
                
                listaDebitos.add(debito);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return listaDebitos;
    }
    
    public ArrayList<Debitos> buscaDebitosPorDevedorECliente(int idDevedor, int idCliente){
        ArrayList<Debitos> listaDebitos=  new ArrayList<Debitos>();
        String sqlDebitos;
        PreparedStatement stmt = null;
        ResultSet rs;
        
        try{
            sqlDebitos = "select * from debitos where idCliente = ? and idDevedor = ?";
            stmt = conexao.prepareStatement(sqlDebitos);
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idDevedor);

            rs = stmt.executeQuery();

            while(rs.next()){
                Debitos debito = new Debitos();
                debito.setIdCompra(rs.getInt("idDebito"));
                debito.setIdCliente(rs.getInt("idCliente"));
                debito.setIdDevedor(rs.getInt("idDevedor"));
                debito.setValor(rs.getDouble("valor"));
                debito.setDataCompra(rs.getDate("dataCompra").toLocalDate());
                debito.setDescricao(rs.getString("descricao"));
                debito.setDataInclusao(rs.getDate("dataInclusao").toLocalDate());
                
                debito.setComprovantes(buscaComprovantes(rs.getInt("idDebito")));
                
                listaDebitos.add(debito);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return listaDebitos;
    }
    
    public ArrayList<Comprovantes> buscaComprovantes(int idDebito){
        ArrayList<Comprovantes> listaComprovantes = new ArrayList<>();
        
        String sql = "select * from comprovantes where idDebito = ?";
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idDebito);;
            ResultSet rs = stmt.executeQuery();
            
            if(!rs.isBeforeFirst()){
                Comprovantes comp = new Comprovantes();
                    comp.setArquivo("");
                    comp.setDataInclusao(LocalDate.parse("1900-01-01"));
                    comp.setIdCliente(0);
                    comp.setIdCompra(idDebito);
                    comp.setIdComprovante(0);
                    comp.setIdDevedor(0);

                    listaComprovantes.add(comp);
            }else{
                while(rs.next()){
                    Comprovantes comp = new Comprovantes();
                    comp.setArquivo(rs.getString("arquivo"));
                    comp.setDataInclusao((rs.getDate("dataInclusao").toLocalDate()));
                    comp.setIdCliente(rs.getInt("idCliente"));
                    comp.setIdCompra(rs.getInt("idDebito"));
                    comp.setIdComprovante(rs.getInt("id"));
                    comp.setIdDevedor(rs.getInt("idDevedor"));

                    listaComprovantes.add(comp);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DebitosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return listaComprovantes;
    }
    
    public ArrayList<Debitos> getDebitosPorNegociacao(int idNegociacao){
        ArrayList<Debitos> listaDebitos = new ArrayList<Debitos>();
        String sql = "select * from debitos where idDebito in (select idDebito from negociacao_debitos where idNegociacao = ?)";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idNegociacao);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Debitos debito = new Debitos();
                debito.setDataCompra(rs.getDate("dataCompra").toLocalDate());
                debito.setDataInclusao(rs.getDate("dataInclusao").toLocalDate());
                debito.setDescricao(rs.getString("descricao"));
                debito.setIdCliente(rs.getInt("idCliente"));
                debito.setIdDevedor(rs.getInt("idDevedor"));
                debito.setIdCompra(rs.getInt("idDebito"));
                debito.setValor(rs.getDouble("valor"));
                debito.setComprovantes(buscaComprovantes(rs.getInt("idDebito")));

                listaDebitos.add(debito);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(NegociacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return listaDebitos;
    }
    
    public ArrayList<Debitos> getDebitosSemNegociacao(int idCliente, int idDevedor){
        ArrayList<Debitos> listaDebitos = new ArrayList<Debitos>();
        String sql = "SELECT * FROM debitos LEFT JOIN `negociacao_debitos` ON debitos.idDebito = `negociacao_debitos`.idDebito WHERE debitos.idCliente = ? and debitos.idDevedor = ? and `negociacao_debitos`.idDebito IS NULL";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idDevedor);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Debitos d = new Debitos();
                d.setDataCompra(rs.getDate("dataCompra").toLocalDate());
                d.setDataInclusao(rs.getDate("dataInclusao").toLocalDate());
                d.setDescricao(rs.getString("descricao"));
                d.setIdCliente(rs.getInt("idCliente"));
                d.setIdDevedor(rs.getInt("idDevedor"));
                d.setIdCompra(rs.getInt("idDebito"));
                d.setValor(rs.getDouble("valor"));
                d.setComprovantes(buscaComprovantes(rs.getInt("idDebito")));

                listaDebitos.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DebitosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaDebitos;
    }
    
    /* public ArrayList<Debitos> buscaDebitosPorNegociacao(int idNegociacao){
        ArrayList<Debitos> listaDebitos = new ArrayList<Debitos>();
        String sql = "SELECT * FROM `debitos` as d, `negociacao_debitos` as nd WHERE nd.idNegociacao = ? and d.idDebito = nd.idDebito;";
        
        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idNegociacao);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Debitos debito = new Debitos();
                debito.setDataCompra(rs.getDate("dataCompra"));
                debito.setDataInclusao(rs.getDate("dataInclusao"));
                debito.setDescricao(rs.getString("descricao"));
                debito.setIdCliente(rs.getInt("idCliente"));
                debito.setIdDevedor(rs.getInt("idDevedor"));
                debito.setIdCompra(rs.getInt("idDebito"));
                debito.setValor(rs.getDouble("valor"));
                debito.setComprovantes(buscaComprovantes(rs.getInt("idDebito")));

                listaDebitos.add(debito);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return listaDebitos;
    }
    */
}
