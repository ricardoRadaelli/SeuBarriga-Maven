/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Banco;

import Atributos.Enderecos;
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
public class EnderecosDAO {
    private Connection conexao=null;
    
    public EnderecosDAO(){
        conexao = ConexaoBD.getConexaoMySQL();
    }
    
    public void insereEndCliente(Enderecos end){
        String sql = "insert into enderecos (idCliente, cep, rua, numero, complemento, bairro, cidade, estado, referencia, dataInclusao, dataUltimoContato) "
                + "values (?,?,?,?,?,?,?,?,?,?,?)";
        
        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, end.getIdCliente());
            stmt.setString(2, end.getCep());
            stmt.setString(3, end.getRua());
            stmt.setString(4, end.getNumero());
            stmt.setString(5, end.getComplemento());
            stmt.setString(6, end.getBairro());
            stmt.setString(7, end.getCidade());
            stmt.setString(8, end.getEstado());
            stmt.setString(9, end.getReferencia());
            stmt.setDate(10, Date.valueOf(end.getDataInclusao()));
            stmt.setDate(11, Date.valueOf(end.getDataUltimoContato()));

            
            stmt.execute();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void insereEndCliente(Enderecos end, Connection con){
        String sql = "insert into enderecos (idCliente, cep, rua, numero, complemento, bairro, cidade, estado, referencia, dataInclusao, dataUltimoContato) "
                + "values (?,?,?,?,?,?,?,?,?,?,?)";
        
        try{
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, end.getIdCliente());
            stmt.setString(2, end.getCep());
            stmt.setString(3, end.getRua());
            stmt.setString(4, end.getNumero());
            stmt.setString(5, end.getComplemento());
            stmt.setString(6, end.getBairro());
            stmt.setString(7, end.getCidade());
            stmt.setString(8, end.getEstado());
            stmt.setString(9, end.getReferencia());
            stmt.setDate(10, Date.valueOf(end.getDataInclusao()));
            stmt.setDate(11, Date.valueOf(end.getDataUltimoContato()));

            
            stmt.execute();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void insereEndDevedor(Enderecos end){
        String sql = "insert into enderecos (idCliente, idDevedor, cep, rua, numero, complemento, bairro, cidade, estado, referencia, dataInclusao, dataUltimoContato) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, end.getIdCliente());
            stmt.setInt(2, end.getIdDevedor());
            stmt.setString(3, end.getCep());
            stmt.setString(4, end.getRua());
            stmt.setString(5, end.getNumero());
            stmt.setString(6, end.getComplemento());
            stmt.setString(7, end.getBairro());
            stmt.setString(8, end.getCidade());
            stmt.setString(9, end.getEstado());
            stmt.setString(10, end.getReferencia());
            stmt.setDate(11, Date.valueOf(end.getDataInclusao()));
            stmt.setDate(12, Date.valueOf(end.getDataUltimoContato()));

            
            stmt.execute();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public ArrayList<Enderecos> buscaEndCliente(int idCliente){
        ArrayList<Enderecos> listaTel = new ArrayList<Enderecos>();
        String sqlTel;
        ResultSet rs;
        
        try{
            sqlTel = "select * from enderecos where idCliente = ? and idDevedor is null";
            PreparedStatement stmt = conexao.prepareStatement(sqlTel);
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();

            while(rs.next()){
                Enderecos end = new Enderecos();
                end.setId(rs.getInt("id"));
                end.setIdCliente(rs.getInt("idCliente"));
                end.setIdDevedor(0);
                end.setCep(rs.getString("cep"));
                end.setRua(rs.getString("rua"));
                end.setNumero(rs.getString("numero"));
                end.setComplemento(rs.getString("complemento"));
                end.setBairro(rs.getString("bairro"));
                end.setCidade(rs.getString("cidade"));
                end.setEstado(rs.getString("estado"));
                end.setReferencia(rs.getString("referencia"));
                end.setDataInclusao(rs.getDate("dataInclusao").toLocalDate());
                end.setDataUltimoContato(rs.getDate("dataUltimoContato").toLocalDate());
                
                listaTel.add(end);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return listaTel;
    }
    
    public ArrayList<Enderecos> buscaEndDevedor(int idDevedor){
        ArrayList<Enderecos> listaTel = new ArrayList<Enderecos>();
        String sqlTel;
        PreparedStatement stmt = null;
        ResultSet rs;
        
        try{
            sqlTel = "select * from enderecos where idDevedor = ?";
            stmt = conexao.prepareStatement(sqlTel);
            stmt.setInt(1, idDevedor);
            rs = stmt.executeQuery();

            while(rs.next()){
                Enderecos end = new Enderecos();
                end.setId(rs.getInt("id"));
                end.setIdCliente(rs.getInt("idCliente"));
                end.setIdDevedor(rs.getInt("idDevedor"));
                end.setCep(rs.getString("cep"));
                end.setRua(rs.getString("rua"));
                end.setNumero(rs.getString("numero"));
                end.setComplemento(rs.getString("complemento"));
                end.setBairro(rs.getString("bairro"));
                end.setCidade(rs.getString("cidade"));
                end.setEstado(rs.getString("estado"));
                end.setReferencia(rs.getString("referencia"));
                end.setDataInclusao(rs.getDate("dataInclusao").toLocalDate());
                end.setDataUltimoContato(rs.getDate("dataUltimoContato").toLocalDate());
                
                listaTel.add(end);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return listaTel;
    }
    
    public void atualizaEndDevedor(Enderecos end){
        String sql = "update enderecos set cep = ?, rua = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, estado = ?, referencia = ? where id = ?";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, end.getCep());
            stmt.setString(2, end.getRua());
            stmt.setString(3, end.getNumero());
            stmt.setString(4, end.getComplemento());
            stmt.setString(5, end.getBairro());
            stmt.setString(6, end.getCidade());
            stmt.setString(7, end.getEstado());
            stmt.setString(8, end.getReferencia());
            stmt.setInt(9, end.getId());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EnderecosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void excluir(int idEndereco){
        String sql = "delete from enderecos where id = ?";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idEndereco);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EnderecosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
