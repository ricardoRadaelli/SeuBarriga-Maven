/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Banco;

import Atributos.Comissoes;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author User
 */
public class ComissoesDAO {
    
    Connection conexao;
    
    public ComissoesDAO(){
        conexao = ConexaoBD.getConexaoMySQL();
    }
    
    public void registraComissao(Comissoes comissao, Connection con){
        String sql = "insert into comissoes (idPagamento, valorComissao) values (?,?)";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, comissao.getIdPagamento());
            stmt.setDouble(2, comissao.getValorComissao());
            
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ComissoesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Comissoes getComissoesPorPagamento(int idPagamento){
            Comissoes comissao = new Comissoes();
        
        String sql = "select * from comissoes where idPagamento = ?";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idPagamento);
            
            ResultSet rs = stmt.executeQuery();
            rs.next();
            
            comissao.setIdComissao(rs.getInt("idComissao"));
            comissao.setValorComissao(rs.getDouble("valorComissao"));
                        
            PagamentosDAO pdao = new PagamentosDAO();
            comissao.setPag(pdao.getPagamento(idPagamento));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ComissoesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return comissao;
    }
    
    public ArrayList<Comissoes> getComissoesPorMes(int mes, int ano){
        Comissoes comissao = new Comissoes();
        ArrayList<Comissoes> lista = new ArrayList<Comissoes>();
        
        String sql = "SELECT c.idPagamento, c.idComissao, c.valorComissao FROM comissoes as c JOIN pagamentos as p ON c.idPagamento = p.idPagamento WHERE MONTH(p.dataPagamento) = ? and YEAR(p.dataPagamento) = ?;";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, mes);
            stmt.setInt(2, ano);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){            
                comissao.setIdComissao(rs.getInt("idComissao"));
                comissao.setValorComissao(rs.getDouble("valorComissao"));
                comissao.setIdPagamento(rs.getInt("idPagamento"));
            
                PagamentosDAO pdao = new PagamentosDAO();
                comissao.setPag(pdao.getPagamento(rs.getInt("idPagamento")));
                lista.add(comissao);
           } 
        } catch (SQLException ex) {
            Logger.getLogger(ComissoesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
    
}
