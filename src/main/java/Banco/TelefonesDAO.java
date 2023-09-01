/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Banco;

import Atributos.Telefones;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class TelefonesDAO {
    private Connection conexao=null;
    
    public TelefonesDAO(){
        conexao = ConexaoBD.getConexaoMySQL();
    }
    
    public void insereTelDevedor(Telefones tel){
        String sql = "insert into telefones (telefone, idDevedor, idCliente, dataInclusao, email, dataUltimoContato, obs) values (?,?,?,?,?,?,?)";
        
        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, tel.getTelefone());
            stmt.setInt(2, tel.getIdDevedor());
            stmt.setInt(3, tel.getIdCliente());
            stmt.setDate(4, Date.valueOf(tel.getDataInclusao()));
            stmt.setString(5, tel.getEmail());
            stmt.setDate(6, Date.valueOf(tel.getDataUltimoContato()));
            stmt.setString(7, tel.getObs());
            
            stmt.execute();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void insereTelCliente(Telefones tel, Connection con){
        String sql = "insert into telefones (telefone, idCliente, dataInclusao, email, dataUltimoContato, obs) values (?,?,?,?,?,?)";
        
        try{
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, tel.getTelefone());
            stmt.setInt(2, tel.getIdCliente());
            stmt.setDate(3, Date.valueOf(tel.getDataInclusao()));
            stmt.setString(4, tel.getEmail());
            stmt.setDate(5, Date.valueOf(tel.getDataUltimoContato()));
            stmt.setString(6, tel.getObs());
            
            stmt.execute();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void atualizaTelefoneDevedor(Telefones tel){
        String sql = "update telefones set idDevedor=?, idCliente=?, obs=?, email=?, telefone=? where idTelefone = ?";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, tel.getIdDevedor());
            stmt.setInt(2, tel.getIdCliente());
            stmt.setString(3, tel.getObs());
            stmt.setString(4, tel.getEmail());
            stmt.setString(5, tel.getTelefone());
            stmt.setInt(6, tel.getIdTelefone());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TelefonesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Telefones> buscaTelefoneCliente(int idCliente){
        ArrayList<Telefones> listaTel = new ArrayList<Telefones>();
        String sqlTel;
        PreparedStatement stmt = null;
        ResultSet rs;
        
        try{
            sqlTel = "SELECT * FROM `telefones` WHERE idCliente = ? and idDevedor is null";
            stmt = conexao.prepareStatement(sqlTel);
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();

            while(rs.next()){
                Telefones tel = new Telefones();
                tel.setTelefone(rs.getString("telefone"));
                tel.setIdCliente(rs.getInt("idCliente"));
                tel.setIdDevedor(0);
                tel.setEmail(rs.getString("email"));
                tel.setDataInclusao(rs.getDate("dataInclusao").toLocalDate());
                tel.setDataUltimoContato(rs.getDate("dataUltimoContato").toLocalDate());
                tel.setObs(rs.getString("obs"));
                tel.setIdTelefone(rs.getInt("idTelefone"));

                listaTel.add(tel);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return listaTel;
    }
    
    public ArrayList<Telefones> buscaTelefoneDevedor(int idDevedor){
        ArrayList<Telefones> listaTel = new ArrayList<Telefones>();
        String sqlTel;
        PreparedStatement stmt = null;
        ResultSet rs;
        
        try{
            sqlTel = "select * from telefones where idDevedor = ?";
            stmt = conexao.prepareStatement(sqlTel);
            stmt.setInt(1, idDevedor);
            rs = stmt.executeQuery();

            while(rs.next()){
                Telefones tel = new Telefones();
                tel.setTelefone(rs.getString("telefone"));
                tel.setIdCliente(rs.getInt("idCliente"));
                tel.setIdDevedor(rs.getInt("idDevedor"));
                tel.setEmail(rs.getString("email"));
                tel.setDataInclusao(rs.getDate("dataInclusao").toLocalDate());
                tel.setDataUltimoContato(rs.getDate("dataUltimoContato").toLocalDate());
                tel.setObs(rs.getString("obs"));
                tel.setIdTelefone(rs.getInt("idTelefone"));

                listaTel.add(tel);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return listaTel;
    }
    
    
}
