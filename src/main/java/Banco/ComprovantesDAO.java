/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Banco;

import Atributos.Comprovantes;
import java.io.File;
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
public class ComprovantesDAO {
    private Connection conexao=null;
    
    public ComprovantesDAO(){
        conexao = ConexaoBD.getConexaoMySQL();
    }
    
    public void insereComprovante(Comprovantes comprovante, Connection con){
        String sql = "insert into comprovantes (idDevedor, idCliente, idDebito, arquivo, dataInclusao) values (?,?,?,?,?)";
        
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, comprovante.getIdDevedor());
            stmt.setInt(2, comprovante.getIdCliente());
            stmt.setInt(3, comprovante.getIdCompra());
            stmt.setString(4, comprovante.getArquivo());
            stmt.setDate(5, java.sql.Date.valueOf(comprovante.getDataInclusao()));
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ComprovantesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atualizaComprovante(Comprovantes c, Connection con){
        String sql = "update comprovantes set arquivo=?, dataInclusao=? where id=?";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, c.getArquivo());
            stmt.setDate(2, java.sql.Date.valueOf(c.getDataInclusao()));
            stmt.setInt(3, c.getIdComprovante());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ComprovantesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void apagaArquivoComprovante(String nomeArquivo){
        File file = new File("C:\\xampp\\htdocs\\Cobranca\\php\\uploads\\"+nomeArquivo);
        file.delete();
    }
    
    public int getIdComprovante(String nomeArquivo){
        String sql = "select id from comprovantes where arquivo like ?";
        
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nomeArquivo);
            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            return rs.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(ComprovantesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
   
}
