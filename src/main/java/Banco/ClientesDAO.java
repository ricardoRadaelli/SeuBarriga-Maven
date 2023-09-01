/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Banco;
import Atributos.Clientes;
import Atributos.Enderecos;
import Atributos.Telefones;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ClientesDAO {
    private Connection conexao=null;
    
    public ClientesDAO(){
        conexao = ConexaoBD.getConexaoMySQL();
    }
    
    public void insere(Clientes cliente, Telefones tel, Enderecos end){
        String sql = "insert into cliente (nome, cpfcnpj, valorEmCobranca, comissao, diaReembolso, custoDevedorNaoPago, razao, responsavel, informacoesAdicionais)"
                + " values (?,?,?,?,?,?,?,?,?)";
        
        try {
            conexao.setAutoCommit(false);
            PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getCpfcnpj());
            stmt.setDouble(3, 0.0);
            stmt.setDouble(4, cliente.getComissao());
            stmt.setInt(5, cliente.getDiaReembolso());
            stmt.setDouble(6, cliente.getCustoDevedorNaoPago());
            stmt.setString(7, cliente.getRazao());
            stmt.setString(8, cliente.getResponsavel());
            stmt.setString(9, cliente.getInformacoesAdicionais());
            
            int linhasAfetadas = stmt.executeUpdate();
            
            if(linhasAfetadas > 0){
                try{
                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if(generatedKeys.next()){
                        int generatedId = generatedKeys.getInt(1);
                        tel.setIdCliente(generatedId);
                        end.setIdCliente(generatedId);
                        
                        EnderecosDAO enddao = new EnderecosDAO();
                        enddao.insereEndCliente(end, conexao);
                        
                        TelefonesDAO tdao = new TelefonesDAO();
                        tdao.insereTelCliente(tel, conexao);
                        
                        conexao.commit();
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Falha ao obter ID do cliente");
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String buscaCPFCliente(int idCliente){
        String cpfcnpj = "";
        String sql = "select cpfcnpj from cliente where idCliente = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                cpfcnpj = rs.getString("cpfcnpj");
            }
            return cpfcnpj;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cpfcnpj;
    }
    
    public String buscaNomeCliente(int idCliente){
        String nome = "";
        String sql = "select nome from cliente where idCliente = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                nome = rs.getString("nome");
            }
            return nome;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nome;
    }
    
    public ArrayList<Clientes> buscaClientesPorCPF(String cpfcnpj){
        ArrayList<Clientes> listaClientes = new ArrayList<>();
        PreparedStatement stmt;
        
        try {
            if(cpfcnpj.isEmpty()){
                String sql = "select * from cliente";
                stmt = conexao.prepareStatement(sql);
            }else{
                String sql = "select * from cliente where cpfcnpj like \"%?%\"";
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, cpfcnpj);
            }
        
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Clientes cliente = new Clientes();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNomeCliente(rs.getString("nome"));
                cliente.setCpfcnpj(rs.getString("cpfcnpj"));
                cliente.setValorEmCobranca(rs.getDouble("valorEmCobranca")); 
                cliente.setComissao(rs.getDouble("comissao"));
                cliente.setDiaReembolso(rs.getInt("diaReembolso"));
                cliente.setCustoDevedorNaoPago(rs.getDouble("custoDevedorNaoPago"));
                cliente.setRazao(rs.getString("razao"));
                cliente.setResponsavel(rs.getString("responsavel"));
                cliente.setInformacoesAdicionais(rs.getString("informacoesAdicionais"));

                listaClientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaClientes;
    }
    
    public ArrayList<Clientes> buscaClientesPorNome(String nome){
        ArrayList<Clientes> listaClientes = new ArrayList<>();
        PreparedStatement stmt;
        String sql;
        nome = "%"+nome+"%";
        
        try {
            if(nome.isEmpty()){
                sql = "select * from cliente";
                stmt = conexao.prepareStatement(sql);
            }else{
                sql = "select * from cliente where nome like ?";
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, nome);
            }
        
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Clientes cliente = new Clientes();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNomeCliente(rs.getString("nome"));
                cliente.setCpfcnpj(rs.getString("cpfcnpj"));
                cliente.setValorEmCobranca(rs.getDouble("valorEmCobranca")); 
                cliente.setComissao(rs.getDouble("comissao"));
                cliente.setDiaReembolso(rs.getInt("diaReembolso"));
                cliente.setCustoDevedorNaoPago(rs.getDouble("custoDevedorNaoPago"));
                cliente.setRazao(rs.getString("razao"));
                cliente.setResponsavel(rs.getString("responsavel"));
                cliente.setInformacoesAdicionais(rs.getString("informacoesAdicionais"));

                listaClientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaClientes;
    }
    
    public Clientes buscaClientesPorId(int id){
        Clientes cliente = new Clientes();
        PreparedStatement stmt;
        
        try {
            String sql = "select * from cliente where idCliente = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
        
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNomeCliente(rs.getString("nome"));
                cliente.setCpfcnpj(rs.getString("cpfcnpj"));
                cliente.setValorEmCobranca(rs.getDouble("valorEmCobranca")); 
                cliente.setComissao(rs.getDouble("comissao"));
                cliente.setDiaReembolso(rs.getInt("diaReembolso"));
                cliente.setCustoDevedorNaoPago(rs.getDouble("custoDevedorNaoPago"));
                cliente.setRazao(rs.getString("razao"));
                cliente.setResponsavel(rs.getString("responsavel"));
                cliente.setInformacoesAdicionais(rs.getString("informacoesAdicionais"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }
    
    public double getValorEmCobranca(int idCliente){
        String sql = "Select valorEmCobranca where idCliente = ?";
        Double valorEmCobranca = 0.0;
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                valorEmCobranca = rs.getDouble("valorEmCobranca");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return valorEmCobranca;
    }
    
    public double getComissao(int idCliente){
        String sql = "Select comissao where idCliente = ?";
        Double comissao = 0.0;
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                comissao = rs.getDouble("comissao");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return comissao;
    }
    
    public int buscaIdClientePorCPF(String cpfcnpj){
        int idCliente = 0;
        String sql = "select id from cliente where cpfcnpj like ?";
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cpfcnpj);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                idCliente = rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return idCliente;
    }
    
    public ArrayList<Clientes> getClientesPorDevedor(int idDevedor){
        ArrayList<Clientes> listaClientes = new ArrayList<Clientes>();
        String sql = "select * from cliente where id in (select idCliente from `devedor-cliente` where idDevedor in (select idDevedor from devedores where idDevedor = ?))";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idDevedor);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Clientes cliente = new Clientes();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNomeCliente(rs.getString("nome"));
                cliente.setCpfcnpj(rs.getString("cpfcnpj"));
                cliente.setValorEmCobranca(rs.getDouble("valorEmCobranca")); 
                cliente.setComissao(rs.getDouble("comissao"));
                cliente.setDiaReembolso(rs.getInt("diaReembolso"));
                cliente.setCustoDevedorNaoPago(rs.getDouble("custoDevedorNaoPago"));
                cliente.setRazao(rs.getString("razao"));
                cliente.setResponsavel(rs.getString("responsavel"));
                cliente.setInformacoesAdicionais(rs.getString("informacoesAdicionais"));

                listaClientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaClientes;
    }
}
