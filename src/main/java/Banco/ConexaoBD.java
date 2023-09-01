/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Banco;

import Atributos.Configuracoes;
import Telas.ConfiguracoesSistema;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;






/**
 *
 * @author User
 */
public class ConexaoBD {
    public static String status = "Não conectou...";
    
    
    //Método Construtor da Classe//
    public ConexaoBD() {
    }

    //Método de Conexão//
    public static java.sql.Connection getConexaoMySQL() {

        ConfiguracoesSistema conf = new ConfiguracoesSistema();
        Configuracoes config = conf.lerArquivo();
        
        Connection connection = null;          //atributo do tipo Connection
        try {
            // Carregando o JDBC Driver padrão
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            // Configurando a nossa conexão com um banco de dados//
            String serverName = config.getEndBD();    //caminho do servidor do BD
            String mydatabase = config.getNomeBD();        //nome do seu banco de dados
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String username = config.getUserBD();        //nome de um usuário de seu BD
            String password = "";      //sua senha de acesso
            connection = DriverManager.getConnection(url, username, password);

            //Testa sua conexão//
            if (connection != null) {
                status = ("STATUS--->Conectado com sucesso!");
            } else {
                status = ("STATUS--->Não foi possivel realizar conexão");
            }

            return connection;

        } catch (ClassNotFoundException e) {  //Driver não encontrado
            System.out.println("O driver expecificado nao foi encontrado.");
            return null;
        } catch (SQLException e) {
            //Não conseguindo se conectar ao banco
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            return null;
        }catch (InstantiationException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    //Método que retorna o status da sua conexão//
    public static String statusConection() {

        return status;
    }

   //Método que fecha sua conexão//
    public static boolean FecharConexao() {

        try {
            ConexaoBD.getConexaoMySQL().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

   //Método que reinicia sua conexão//
    public static java.sql.Connection ReiniciarConexao() {
        FecharConexao();
        return ConexaoBD.getConexaoMySQL();
    }
}
