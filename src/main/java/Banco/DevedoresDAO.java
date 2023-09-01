/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Banco;

import Atributos.Devedores;
import Atributos.Negociacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class DevedoresDAO {
    private Connection conexao=null;
    
    public DevedoresDAO(){
        conexao = ConexaoBD.getConexaoMySQL();
    }
    
    public void insereDevedorIncompleto(Devedores devedor){
        String sql = "insert into devedores (cpfcnpj, razao, nome) values (?,?,?);";
        
        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, devedor.getCpfcnpj());
            stmt.setString(2, devedor.getRazao());
            stmt.setString(3, devedor.getNomeDevedor());
            
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro ao cadastrar devedor incompleto!"+e);
        }
    }
    
    public void insereDevedor(Devedores devedor){
        String sql = "insert into devedores (cpfcnpj, razao, nome) values (?,?,?);";
        
        try{
            conexao.setAutoCommit(false);
            PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, devedor.getCpfcnpj());
            stmt.setString(2, devedor.getRazao());
            stmt.setString(3, devedor.getNomeDevedor());
            
            int linhasAfetadas = stmt.executeUpdate();
            if(linhasAfetadas > 0){
                try{
                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if(generatedKeys.next()){
                        int generatedId = generatedKeys.getInt(1);
                        
                        sql = "insert into `devedor-cliente` (idDevedor, idCliente, dataFinalizacaoCobranca, taxaJuros, saldoDevedor, dataCadastro, ativado) values (?,?,?,?,?,?,?)";
                        stmt = conexao.prepareStatement(sql);
                        stmt.setInt(1, generatedId);
                        stmt.setInt(2, devedor.getIdCliente());
                        stmt.setDate(3, java.sql.Date.valueOf(devedor.getDataFinalizacaoCobranca()));
                        stmt.setDouble(4, devedor.getTaxaJuros());
                        stmt.setDouble(5, devedor.getSaldoDevedor());
                        stmt.setDate(6, java.sql.Date.valueOf(devedor.getDataCadastro()));
                        stmt.setBoolean(7, devedor.getAtivado());

                        stmt.executeUpdate();
                        conexao.commit();
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Falha ao obter ID da compra");
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Falha ao gravar devedor, verifique os dados e o vínculo devedor-cliente! "+e);
                }
            }
            
        }catch(SQLException e){
            System.out.println("Erro ao atualizar saldo devedor!"+e);
            if(conexao != null){
                try{
                    conexao.rollback();
                }catch(SQLException ex){
                    System.out.println("Erro ao criar pagamentos!"+ex);
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public void vinculaDevedorCliente(Devedores devedor){
        String sql = "insert into `devedor-cliente` (idDevedor, idCliente, dataFinalizacaoCobranca, taxaJuros, saldoDevedor, dataCadastro, ativado) values (?,?,?,?,?,?,?)";
        
        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, devedor.getIdDevedor());
            stmt.setInt(2, devedor.getIdCliente());
            stmt.setDate(3, java.sql.Date.valueOf(devedor.getDataFinalizacaoCobranca()));
            stmt.setDouble(4, devedor.getTaxaJuros());
            stmt.setDouble(5, devedor.getSaldoDevedor());
            stmt.setDate(6, java.sql.Date.valueOf(devedor.getDataCadastro()));
            stmt.setBoolean(7, devedor.getAtivado());

            stmt.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Erro ao vincular devedor-cliente! "+e);
        }
    }
    
    public void atualizaDevedor(Devedores devedor){
        String sql = "update devedores set cpfcnpj=?, razao=?, nome=? where idDevedor = ?";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, devedor.getCpfcnpj());
            stmt.setString(2, devedor.getRazao());
            stmt.setString(3, devedor.getNomeDevedor());
            stmt.setInt(4, devedor.getIdDevedor());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DevedoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int buscaIdDevedor(String cpfcnpj){
        int idDevedor=0;
        
        try {
            PreparedStatement stmt = conexao.prepareStatement("Select idDevedor from devedores where and cpfcnpj like ?");
            stmt.setString(1, cpfcnpj);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                idDevedor = rs.getInt("idDevedor");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DevedoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return idDevedor;
    }
    
    public Devedores buscaIncompletoDevedorPorId(int idDevedor){
        String sqlDevedores;
        ResultSet rs;
        Devedores devedor = new Devedores();
        try{
            sqlDevedores = "SELECT * FROM devedores where idDevedor = ?";
            PreparedStatement stmt = conexao.prepareStatement(sqlDevedores);
            stmt.setInt(1, idDevedor);

            rs = stmt.executeQuery();

            while(rs.next()){
                devedor.setCpfcnpj(rs.getString("cpfcnpj"));
                devedor.setIdDevedor(rs.getInt("idDevedor"));
                devedor.setNomeDevedor(rs.getString("nome"));
                devedor.setRazao(rs.getString("razao"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return devedor;
    }
    
    public ArrayList<Devedores> buscaDevedorPorId(int idDevedor){
        String sqlDevedores;
        ResultSet rs;
        ArrayList<Devedores> listaDevedores = new ArrayList<Devedores>();
        try{
            sqlDevedores = "SELECT * FROM devedores left join `devedor-cliente` on devedores.idDevedor = `devedor-cliente`.idDevedor where devedores.idDevedor = ?";
            PreparedStatement stmt = conexao.prepareStatement(sqlDevedores);
            stmt.setInt(1, idDevedor);

            rs = stmt.executeQuery();

            while(rs.next()){
                Devedores devedor = new Devedores();
                devedor.setCpfcnpj(rs.getString("cpfcnpj"));
                devedor.setIdCliente(rs.getInt("idCliente"));
                devedor.setIdDevedor(rs.getInt("idDevedor"));
                devedor.setNomeDevedor(rs.getString("nome"));
                devedor.setRazao(rs.getString("razao"));
                devedor.setDataFinalizacaoCobranca(rs.getDate("dataFinalizacaoCobranca").toLocalDate());
                devedor.setSaldoDevedor(rs.getDouble("saldoDevedor"));
                devedor.setTaxaJuros(rs.getDouble("taxaJuros"));
                devedor.setDataCadastro(rs.getDate("dataCadastro").toLocalDate());
                devedor.setAtivado(rs.getBoolean("ativado"));
                
                listaDevedores.add(devedor);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return listaDevedores;
    }
    
    public ArrayList<Devedores> buscaDevedorPorNome(String busca){
        ArrayList<Devedores> listaDevedores = new ArrayList<Devedores>();
        String sqlDevedores;
        PreparedStatement stmt = null;
        ResultSet rs;
        
        try{
            if(busca.isEmpty()){
                sqlDevedores = "SELECT * FROM devedores left join `devedor-cliente` on devedores.idDevedor = `devedor-cliente`.idDevedor";
                stmt = conexao.prepareStatement(sqlDevedores);
            }else{
                sqlDevedores = "SELECT * FROM devedores left join `devedor-cliente` on devedores.idDevedor = `devedor-cliente`.idDevedor where devedor.nome like ?";
                stmt = conexao.prepareStatement(sqlDevedores);
                stmt.setString(1, busca);
            }
            
            rs = stmt.executeQuery();

            while(rs.next()){
                Devedores devedor = new Devedores();
                devedor.setCpfcnpj(rs.getString("cpfcnpj"));
                devedor.setIdCliente(rs.getInt("idCliente"));
                devedor.setIdDevedor(rs.getInt("idDevedor"));
                devedor.setNomeDevedor(rs.getString("nome"));
                devedor.setRazao(rs.getString("razao"));
                devedor.setDataFinalizacaoCobranca(rs.getDate("dataFinalizacaoCobranca").toLocalDate());
                devedor.setSaldoDevedor(rs.getDouble("saldoDevedor"));
                devedor.setTaxaJuros(rs.getDouble("taxaJuros"));
                devedor.setDataCadastro(rs.getDate("dataCadastro").toLocalDate());
                devedor.setAtivado(rs.getBoolean("ativado"));
                
                listaDevedores.add(devedor);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return listaDevedores;
    }
    
    public ArrayList<Devedores> buscaDevedorIncompletoPorNome(String busca){
        ArrayList<Devedores> listaDevedores = new ArrayList<Devedores>();
        String sqlDevedores;
        PreparedStatement stmt = null;
        ResultSet rs;
        
        try{
            if(busca.isEmpty()){
                sqlDevedores = "SELECT * FROM devedores";
                stmt = conexao.prepareStatement(sqlDevedores);
            }else{
                sqlDevedores = "SELECT * FROM devedores where devedor.nome like ?";
                stmt = conexao.prepareStatement(sqlDevedores);
                stmt.setString(1, busca);
            }
            
            rs = stmt.executeQuery();

            while(rs.next()){
                Devedores devedor = new Devedores();
                devedor.setCpfcnpj(rs.getString("cpfcnpj"));
                devedor.setIdDevedor(rs.getInt("idDevedor"));
                devedor.setNomeDevedor(rs.getString("nome"));
                devedor.setRazao(rs.getString("razao"));
                
                listaDevedores.add(devedor);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return listaDevedores;
    }
    
    public Devedores buscaDevedorPorCPFCNPJ(String cpfcnpj, int idCliente){
        Devedores devedor = new Devedores();
        String sqlDevedores;
        PreparedStatement stmt = null;
        ResultSet rs;
        
        try{
            sqlDevedores = "SELECT * FROM devedores left join `devedor-cliente` on devedores.idDevedor = `devedor-cliente`.idDevedor where devedores.cpfcnpj like ? and `devedor-cliente`.idCliente = ?";
            stmt = conexao.prepareStatement(sqlDevedores);
            stmt.setString(1, cpfcnpj);
            stmt.setInt(2, idCliente);
            
            rs = stmt.executeQuery();

           while(rs.next()){
                devedor.setCpfcnpj(rs.getString("cpfcnpj"));
                devedor.setIdCliente(rs.getInt("idCliente"));
                devedor.setIdDevedor(rs.getInt("idDevedor"));
                devedor.setNomeDevedor(rs.getString("nome"));
                devedor.setRazao(rs.getString("razao"));
                devedor.setDataFinalizacaoCobranca(rs.getDate("dataFinalizacaoCobranca").toLocalDate());
                devedor.setSaldoDevedor(rs.getDouble("saldoDevedor"));
                devedor.setTaxaJuros(rs.getDouble("taxaJuros"));
                devedor.setDataCadastro(rs.getDate("dataCadastro").toLocalDate());
                devedor.setAtivado(rs.getBoolean("ativado"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return devedor;
    }
    
    public ArrayList<Devedores> buscaDevedoresPorCliente(int idCliente){
        ArrayList<Devedores> listaDevedores = new ArrayList<Devedores>();
        String sqlDevedores;
        PreparedStatement stmt = null;
        ResultSet rs;
        
        try{
            sqlDevedores = "SELECT * FROM devedores left join `devedor-cliente` on devedores.idDevedor = `devedor-cliente`.idDevedor where idCliente = ?";
            stmt = conexao.prepareStatement(sqlDevedores);
            stmt.setInt(1, idCliente);
                       
            rs = stmt.executeQuery();

            while(rs.next()){
                Devedores devedor = new Devedores();
                devedor.setCpfcnpj(rs.getString("cpfcnpj"));
                devedor.setIdCliente(rs.getInt("idCliente"));
                devedor.setIdDevedor(rs.getInt("idDevedor"));
                devedor.setNomeDevedor(rs.getString("nome"));
                devedor.setRazao(rs.getString("razao"));
                devedor.setDataFinalizacaoCobranca(rs.getDate("dataFinalizacaoCobranca").toLocalDate());
                devedor.setSaldoDevedor(rs.getDouble("saldoDevedor"));
                devedor.setTaxaJuros(rs.getDouble("taxaJuros"));
                devedor.setDataCadastro(rs.getDate("dataCadastro").toLocalDate());
                devedor.setAtivado(rs.getBoolean("ativado"));
                
                listaDevedores.add(devedor);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return listaDevedores;
    }
    
    public Devedores buscaDevedorPorIdClienteEIdDevedor(int idDevedor, int idCliente){
        String sqlDevedores;
        PreparedStatement stmt = null;
        ResultSet rs;
        
        Devedores devedor = new Devedores();
        try{
            sqlDevedores = "SELECT * FROM devedores left join `devedor-cliente` on devedores.idDevedor = `devedor-cliente`.idDevedor where idCliente = ? and devedores.idDevedor = ?";
            stmt = conexao.prepareStatement(sqlDevedores);
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idDevedor);
                       
            rs = stmt.executeQuery();

            rs.next();
            devedor.setCpfcnpj(rs.getString("cpfcnpj"));
            devedor.setIdCliente(rs.getInt("idCliente"));
            devedor.setIdDevedor(rs.getInt("idDevedor"));
            devedor.setNomeDevedor(rs.getString("nome"));
            devedor.setRazao(rs.getString("razao"));
            devedor.setDataFinalizacaoCobranca(rs.getDate("dataFinalizacaoCobranca").toLocalDate());
            devedor.setSaldoDevedor(rs.getDouble("saldoDevedor"));
            devedor.setTaxaJuros(rs.getDouble("taxaJuros"));
            devedor.setDataCadastro(rs.getDate("dataCadastro").toLocalDate());
            devedor.setAtivado(rs.getBoolean("ativado"));

            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return devedor;
    }
    
    public ArrayList<Devedores> getClientesDoDevedor(int idDevedor){
        ArrayList<Devedores> listaDevedores = new ArrayList<Devedores>();
        String sql = "select * from devedores as d, `devedor-cliente` as dc where d.idDevedor = ? and dc.idDevedor = ? ";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idDevedor);
            stmt.setInt(2, idDevedor);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Devedores devedor = new Devedores();
                devedor.setCpfcnpj(rs.getString("cpfcnpj"));
                devedor.setIdCliente(rs.getInt("idCliente"));
                devedor.setIdDevedor(rs.getInt("idDevedor"));
                devedor.setNomeDevedor(rs.getString("nome"));
                devedor.setRazao(rs.getString("razao"));
                devedor.setDataFinalizacaoCobranca(rs.getDate("dataFinalizacaoCobranca").toLocalDate());
                devedor.setSaldoDevedor(rs.getDouble("saldoDevedor"));
                devedor.setTaxaJuros(rs.getDouble("taxaJuros"));
                devedor.setDataCadastro(rs.getDate("dataCadastro").toLocalDate());
                devedor.setAtivado(rs.getBoolean("ativado"));
                
                listaDevedores.add(devedor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DevedoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return listaDevedores;
    }
    
    public boolean confereSaldoDevedorViaPagamentos(int idCliente, int idDevedor){
        boolean confereSaldo = false;
        Double valorParcelasPagas =0.0, valorPago=0.0, saldoDevedor=0.0, valorTotal = 0.0;
        
        NegociacaoDAO ndao = new NegociacaoDAO();
        ArrayList<Negociacao> listaNegociacao = ndao.buscaNegociacoesPorClienteEDevedor(idCliente, idDevedor);
            
        String sql = "select * from pagamentos where idNegociacao in (select idNegociacao from negociacao where idCliente = ? and idDevedor = ? and pago = 0)";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idDevedor);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                if(rs.getBoolean("pago") == true){
                    valorParcelasPagas += rs.getDouble("valorParcela");
                    valorPago += rs.getDouble("valorPago");
                }
            }
            
            for (int i = 0; i < listaNegociacao.size(); i++) {
                valorTotal += listaNegociacao.get(i).getValorTotal();
            }
            
            sql = "select saldoDevedor from `devedor-cliente` where idDevedor = ? and idCliente = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idDevedor);
            stmt.setInt(2, idCliente);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                saldoDevedor = rs.getDouble("saldoDevedor");
            }
            
            System.out.println("Saldo devedor no cliente: "+saldoDevedor);
            System.out.println("Saldo devedor via pagamentos: "+(valorTotal-valorParcelasPagas));
            System.out.println("Valor pago via pagamentos (com juros): "+valorPago);
            
        } catch (SQLException ex) {
            Logger.getLogger(DevedoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return confereSaldo;
    }
    
    public double getSaldoDevedorPorNegociacao(int idNegociacao){
        double saldoDevedor=0.0;
        String sql = "";
        
        return saldoDevedor;
    }
    
    public double getSaldoDevedorPorDevedor(int idDevedor, int idCliente){
        Double saldo= 0.0;
        
        String sql = "select saldoDevedor from `devedor-cliente` where idDevedor = ? and idCliente = ?";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idDevedor);
            stmt.setInt(2, idCliente);
            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            saldo = rs.getDouble("saldoDevedor");
            return saldo;
            
        } catch (SQLException ex) {
            Logger.getLogger(DevedoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return saldo;
    }
    
    public ArrayList<Devedores> buscaDevedoresDoClientePorNome(int idCliente, String nome){
        String sql = "SELECT * FROM devedores left join `devedor-cliente` on devedores.idDevedor = `devedor-cliente`.idDevedor where idCliente=? and nome like \"%?%\"";
        ArrayList<Devedores> listaDevedores = new ArrayList<Devedores>();
        
        if(nome.isEmpty())
            nome = "%";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.setString(2, nome);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Devedores devedor = new Devedores();
                devedor.setCpfcnpj(rs.getString("cpfcnpj"));
                devedor.setIdCliente(rs.getInt("idCliente"));
                devedor.setIdDevedor(rs.getInt("idDevedor"));
                devedor.setNomeDevedor(rs.getString("nome"));
                devedor.setRazao(rs.getString("razao"));
                devedor.setDataFinalizacaoCobranca(rs.getDate("dataFinalizacaoCobranca").toLocalDate());
                devedor.setSaldoDevedor(rs.getDouble("saldoDevedor"));
                devedor.setTaxaJuros(rs.getDouble("taxaJuros"));
                devedor.setDataCadastro(rs.getDate("dataCadastro").toLocalDate());
                devedor.setAtivado(rs.getBoolean("ativado"));
                
                listaDevedores.add(devedor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DevedoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaDevedores;
    }
    
    public String getNomeDevedor(int idDevedor){
        String sqlDevedores;
        PreparedStatement stmt = null;
        ResultSet rs;
        String nome = "";
        
        try{
            sqlDevedores = "select * from devedores where idDevedor = ?";
            stmt = conexao.prepareStatement(sqlDevedores);
            stmt.setInt(1, idDevedor);
            rs = stmt.executeQuery();

            while(rs.next()){
                nome = rs.getString("nome");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return nome;
    }
    
}
