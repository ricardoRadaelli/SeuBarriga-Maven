/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Banco;

import Atributos.Devolucoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class DevolucoesDAO {
    Connection conexao;
    
    public DevolucoesDAO(){
        conexao = ConexaoBD.getConexaoMySQL();
    }
    
    public void insereDevolucao(Devolucoes devolucao, Connection con){
        String sql = "insert into devolucoes (idPagamento, valorDevolvido, devolvido, dataDevolucao) values (?,?,?,?)";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, devolucao.getIdPagamento());
            stmt.setDouble(2, devolucao.getValorDevolvido());
            stmt.setBoolean(3, devolucao.isDevolvido());
            stmt.setDate(4, java.sql.Date.valueOf(devolucao.getDataDevolucao()));
            
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DevolucoesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
