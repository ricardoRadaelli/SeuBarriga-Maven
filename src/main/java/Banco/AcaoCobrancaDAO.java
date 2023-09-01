/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Banco;
import Atributos.AcaoCobranca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author User
 */
public class AcaoCobrancaDAO {
    
        Connection conexao;
    public AcaoCobrancaDAO(){
        conexao = ConexaoBD.getConexaoMySQL();
    }
    
    public void insereAcao(AcaoCobranca acao){
        String sql = "insert into acao_cobranca (idCliente, idUsuario, tipoAcao, descricaoAcao, dataAcao, idDevedor) values (?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, acao.getIdCliente());
            stmt.setInt(2, acao.getIdUsuario());
            stmt.setString(3, acao.getTipoCobranca());
            stmt.setString(4, acao.getTextoCobranca());
            stmt.setDate(5, java.sql.Date.valueOf(acao.getDataCobranca()));
            stmt.setInt(6, acao.getIdDevedor());
            
            stmt.execute();
        }catch(SQLException ex){
            Logger.getLogger(AcaoCobrancaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<AcaoCobranca> buscaAcoesPorDevedorECliente(int idCliente, int idDevedor, LocalDate dataInicial, LocalDate dataFinal){
        String sql = "select * from acao_cobranca where idCliente = ? and idDevedor=? and dataAcao between ? and ?";
        PreparedStatement stmt;
            try {
                stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, idCliente);
                stmt.setInt(2, idDevedor);
                stmt.setDate(3, java.sql.Date.valueOf(dataInicial));
                stmt.setDate(4, java.sql.Date.valueOf(dataFinal));
                
                ResultSet rs = stmt.executeQuery();
                ArrayList<AcaoCobranca> lista = new ArrayList<AcaoCobranca>();
                
                while(rs.next()){
                    AcaoCobranca acao = new AcaoCobranca();
                    acao.setIdAcao(rs.getInt("idAcao"));
                    acao.setIdCliente(rs.getInt("idCliente"));
                    acao.setIdDevedor(rs.getInt("idDevedor"));
                    acao.setIdUsuario(rs.getInt("idUsuario"));
                    acao.setDataCobranca(rs.getDate("dataAcao").toLocalDate());
                    acao.setTipoCobranca(rs.getString("tipoAcao"));
                    acao.setTextoCobranca(rs.getString("descricaoAcao"));
                    
                    ClientesDAO cdao = new ClientesDAO();
                    DevedoresDAO ddao = new DevedoresDAO();
                    
                    acao.setCliente(cdao.buscaClientesPorId(idCliente));
                    acao.setDevedor(ddao.buscaDevedorPorIdClienteEIdDevedor(idDevedor, idCliente));
                    
                    lista.add(acao);
                }
                return lista;
            } catch (SQLException ex) {
                Logger.getLogger(AcaoCobrancaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
       return null;
    }
    
    public ArrayList<AcaoCobranca> buscaAcoesPorDevedor(int idDevedor, LocalDate dataInicial, LocalDate dataFinal){
        String sql = "select * from acao_cobranca where idDevedor=? and dataAcao between ? and ?";
        PreparedStatement stmt;
            try {
                stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, idDevedor);
                stmt.setDate(2, java.sql.Date.valueOf(dataInicial));
                stmt.setDate(3, java.sql.Date.valueOf(dataFinal));
                
                ResultSet rs = stmt.executeQuery();
                ArrayList<AcaoCobranca> lista = new ArrayList<AcaoCobranca>();
                
                while(rs.next()){
                    AcaoCobranca acao = new AcaoCobranca();
                    acao.setIdAcao(rs.getInt("idAcao"));
                    acao.setIdCliente(rs.getInt("idCliente"));
                    acao.setIdDevedor(rs.getInt("idDevedor"));
                    acao.setIdUsuario(rs.getInt("idUsuario"));
                    acao.setDataCobranca(rs.getDate("dataAcao").toLocalDate());
                    acao.setTipoCobranca(rs.getString("tipoAcao"));
                    acao.setTextoCobranca(rs.getString("descricaoAcao"));
                    
                    ClientesDAO cdao = new ClientesDAO();
                    DevedoresDAO ddao = new DevedoresDAO();
                    
                    acao.setCliente(cdao.buscaClientesPorId(rs.getInt("idCliente")));
                    acao.setDevedor(ddao.buscaDevedorPorIdClienteEIdDevedor(idDevedor, rs.getInt("idCliente")));
                    
                    lista.add(acao);
                }
                return lista;
            } catch (SQLException ex) {
                Logger.getLogger(AcaoCobrancaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
       return null;
    }
    
    public void atualizaAcao(AcaoCobranca acao){
        String sql = "update acao_cobranca set tipoAcao=?, descricaoAcao=?, dataAcao=? where idAcao =?";
        
            try {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setString(1, acao.getTipoCobranca());
                stmt.setString(2, acao.getTextoCobranca());
                stmt.setDate(3, java.sql.Date.valueOf(acao.getDataCobranca()));
                stmt.setInt(4, acao.getIdAcao());
                
                stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(AcaoCobrancaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
}
