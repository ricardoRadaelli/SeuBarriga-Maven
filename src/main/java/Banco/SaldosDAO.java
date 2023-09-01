/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Banco;

import Atributos.Clientes;
import Atributos.Devedores;
import java.sql.Connection;
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
public class SaldosDAO {
    
    private Connection conexao;
    
    public SaldosDAO(){
        conexao = ConexaoBD.getConexaoMySQL();
    }
    
    public double calculaSaldoDevedorNegociacao(int idNegociacao){
        double saldoDevedor=0;
        
        String sql = "";
        
        
        return saldoDevedor;
    }
    
    public double calculaSaldoDevedor_Devedor_Para_Cliente(int idDevedor, int idCliente, double saldo, String op, Connection con){
        double saldoDevedor=0;
        
        String sqlDevedores;
        ResultSet rs;
        try{
            sqlDevedores = "SELECT * FROM devedores left join `devedor-cliente` on devedores.idDevedor = `devedor-cliente`.idDevedor where devedores.idDevedor = ? and `devedor-cliente`.idCliente = ?";
            PreparedStatement stmt = con.prepareStatement(sqlDevedores);
            stmt.setInt(1, idDevedor);
            stmt.setInt(2, idCliente);

            rs = stmt.executeQuery();

            rs.next();
            Devedores devedor = new Devedores();
            devedor.setSaldoDevedor(rs.getDouble("saldoDevedor"));
            devedor.setTaxaJuros(rs.getDouble("taxaJuros"));
            
            if(op.equals("soma"))
                saldoDevedor =  (devedor.getSaldoDevedor()+saldo);
            if(op.equals("sub"))
                saldoDevedor = (devedor.getSaldoDevedor()-saldo);
            
            sqlDevedores = "update `devedor-cliente` set saldoDevedor=? where `devedor-cliente`.idDevedor = ? and `devedor-cliente`.idCliente = ?";
            stmt = conexao.prepareStatement(sqlDevedores);
            stmt.setDouble(1, saldoDevedor);
            stmt.setInt(2, idDevedor);
            stmt.setInt(3, idCliente);

            stmt.executeUpdate();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return saldoDevedor;
    }
    
    public double calculaValorEmCobranca_doCliente(int idCliente, double valor, String op, Connection con){
        double valorEmCobranca=0;
        
        String sql = "Select * from cliente where idCliente =?";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            
            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            
            Clientes cli = new Clientes();
            cli.setValorEmCobranca(rs.getDouble("valorEmCobranca"));
            
            
            if(op.equals("soma"))
                valorEmCobranca = cli.getValorEmCobranca()+valor;
            if(op.equals("sub"))
                valorEmCobranca = cli.getValorEmCobranca()-valor;
            
            sql = "update cliente set valorEmCobranca=? where idCliente=?";
            stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, valorEmCobranca);
            stmt.setInt(2, idCliente);
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SaldosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return valorEmCobranca;
    }
}
