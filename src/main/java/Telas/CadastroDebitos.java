/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Telas;

import Atributos.Clientes;
import Atributos.Comprovantes;
import Atributos.Devedores;
import Atributos.Debitos;
import Banco.DebitosDAO;
import Banco.SaldosDAO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;

/**
 *
 * @author User
 */
public class CadastroDebitos extends javax.swing.JFrame implements PesquisaClientes.InterfaceRepassaDadosClientes, PesquisaDevedores.InterfaceRepassaDadosDevedores{

    /**
     * Creates new form CadastroDebitosasdsad
     */
    public CadastroDebitos() {
        initComponents();
        desativaCampos();
    }
    
    public CadastroDebitos(Devedores devedor, Clientes cliente){
        initComponents();
        //desativaCampos();
        
        devedorSelecionado(devedor);
        clienteSelecionado(cliente);
        
        jBPesquisarDevedor.setEnabled(true);
        jTFPesquisarDevedor.setEditable(true);
    }
    
    File arquivo;
    Devedores devedor;
    Clientes cliente;

    private void desativaCampos(){
        jTADescricao.setEditable(false);
        jTFCPFDevedor.setEditable(false);
        jTFCPFCliente.setEditable(false);
        jTFCliente.setEditable(false);
        jFTFDataCompra.setEditable(false);
        jTFNomeDevedor.setEditable(false);
        jTFValor.setEditable(false);
        jBComprovante.setEnabled(false);
        jTFPesquisarDevedor.setEditable(false);
        arquivo = null;
        jBPesquisarDevedor.setEnabled(false);
    }
    
    private void limpaCampos(){
        arquivo = null;
        jTADescricao.setText("");
        jTFCPFDevedor.setText("");
        jTFCPFCliente.setText("");
        jTFCliente.setText("");
        jFTFDataCompra.setText("");
        jTFNomeDevedor.setText("");
        jTFValor.setText("");
        jBComprovante.setEnabled(false);
        jTFPesquisarDevedor.setText("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTFPesquisarCliente = new javax.swing.JTextField();
        jBPesquisarCliente = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTFCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTFCPFCliente = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTFPesquisarDevedor = new javax.swing.JTextField();
        jBPesquisarDevedor = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTFNomeDevedor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTFCPFDevedor = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTFValor = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTADescricao = new javax.swing.JTextArea();
        jBComprovante = new javax.swing.JButton();
        jFTFDataCompra = new javax.swing.JFormattedTextField();
        jBSalvar = new javax.swing.JButton();
        jBLimpar = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        jLabel1.setText("Busque o cliente:");

        jBPesquisarCliente.setText("Pesquisar");
        jBPesquisarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPesquisarClienteActionPerformed(evt);
            }
        });

        jLabel3.setText("Cliente:");

        jTFCliente.setEditable(false);

        jLabel5.setText("CPF/CNPJ:");

        jTFCPFCliente.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFPesquisarCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBPesquisarCliente))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFCPFCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFPesquisarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBPesquisarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTFCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTFCPFCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Devedor"));

        jLabel2.setText("Busque o devedor:");

        jBPesquisarDevedor.setText("Pesquisar");
        jBPesquisarDevedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPesquisarDevedorActionPerformed(evt);
            }
        });

        jLabel4.setText("Nome:");

        jTFNomeDevedor.setEditable(false);

        jLabel6.setText("CPF/CNPJ:");

        jTFCPFDevedor.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFPesquisarDevedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBPesquisarDevedor))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFNomeDevedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFCPFDevedor, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTFPesquisarDevedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBPesquisarDevedor))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTFNomeDevedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jTFCPFDevedor)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Débito"));

        jLabel7.setText("Data da compra:");

        jLabel9.setText("Valor da compra:");

        jLabel8.setText("R$");

        jLabel10.setText("Descrição da compra:");

        jTADescricao.setColumns(20);
        jTADescricao.setRows(5);
        jScrollPane1.setViewportView(jTADescricao);

        jBComprovante.setText("Anexar comprovante");
        jBComprovante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBComprovanteActionPerformed(evt);
            }
        });

        try {
            jFTFDataCompra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTFDataCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFValor, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(jBComprovante)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jTFValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTFDataCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBComprovante)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jBSalvar.setText("Salvar");
        jBSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalvarActionPerformed(evt);
            }
        });

        jBLimpar.setText("Limpar");
        jBLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLimparActionPerformed(evt);
            }
        });

        jBCancelar.setText("Cancelar");
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(jBSalvar)
                .addGap(18, 18, 18)
                .addComponent(jBLimpar)
                .addGap(18, 18, 18)
                .addComponent(jBCancelar)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBSalvar)
                    .addComponent(jBLimpar)
                    .addComponent(jBCancelar))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBComprovanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBComprovanteActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            arquivo = selectedFile;
        }
    }//GEN-LAST:event_jBComprovanteActionPerformed

    @Override
    public void devedorSelecionado(Devedores devedor) {
        this.devedor = devedor;
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        jTFNomeDevedor.setText(devedor.getNomeDevedor());
        jTFCPFDevedor.setText(devedor.getCpfcnpj());
    }
    
    @Override
    public void clienteSelecionado(Clientes cliente) {
        this.cliente = cliente;
        
        jTFCliente.setText(cliente.getNomeCliente());
        jTFCPFCliente.setText(cliente.getCpfcnpj());
    }
        
    private void jBPesquisarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPesquisarClienteActionPerformed
        PesquisaClientes pesquisa = new PesquisaClientes(jTFPesquisarCliente.getText());
        pesquisa.setListenerClientes(this);
        pesquisa.setVisible(true);
        
        jBPesquisarDevedor.setEnabled(true);
        jTFPesquisarDevedor.setEditable(true);
        
        /*
        if(!jTFPesquisarCliente.getText().isBlank()){
            ClientesDAO cliDAO = new ClientesDAO();
            ArrayList<Clientes> lista = cliDAO.buscaClientes(jTFPesquisarCliente.getText());
            
            if(lista.size()<1){
                JOptionPane.showMessageDialog(null, "Cliente não encontrado! Verifique se o CPF/CNPJ não está incorreto.");
            }else{
                jTFCliente.setText(lista.get(0).getNome());
                jTFCPFCliente.setText(lista.get(0).getCpfcnpj());
                idCliente = lista.get(0).getId();
                jTFPesquisarDevedor.setEditable(true);
            }
        }*/
    }//GEN-LAST:event_jBPesquisarClienteActionPerformed

    private void jBPesquisarDevedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPesquisarDevedorActionPerformed
        PesquisaDevedores pesquisa = new PesquisaDevedores(cliente.getIdCliente(), jTFPesquisarCliente.getText());
        pesquisa.setListenerDevedor(this);
        pesquisa.setVisible(true);
        
        /*if(!jTFPesquisarDevedor.getText().isBlank()){
            DevedoresDAO creDAO = new DevedoresDAO();
            ArrayList<Devedores> lista = new ArrayList<Devedores>();
            lista.add(creDAO.buscaDevedorPorCPFCNPJ(jTFPesquisarDevedor.getText(), idCliente));
            
            if(lista.size()<1){
                JOptionPane.showMessageDialog(null, "Devedor não encontrado! Verifique se o CPF/CNPJ não está incorreto.");
            }else{
                jTFNomeDevedor.setText(lista.get(0).getNome());
                jTFCPFDevedor.setText(lista.get(0).getCpfcnpj());
                idDevedor = lista.get(0).getIdDevedor();
                
                jFTFDataCompra.setEditable(true);
                jTFValor.setEditable(true);
                jTADescricao.setEditable(true);
                jBComprovante.setEnabled(true);
            }
        }*/
    }//GEN-LAST:event_jBPesquisarDevedorActionPerformed

    private void jBLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLimparActionPerformed
        desativaCampos();
        limpaCampos();
    }//GEN-LAST:event_jBLimparActionPerformed

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void jBSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalvarActionPerformed
        Debitos debito = new Debitos();
        DebitosDAO dDAO = new DebitosDAO();
        
        debito.setDataCompra(converteDataParaBanco(jFTFDataCompra.getText()));
        debito.setDataInclusao(LocalDate.now());
        debito.setDescricao(jTADescricao.getText());
        debito.setValor(Double.parseDouble(jTFValor.getText()));
        debito.setIdCliente(cliente.getIdCliente());
        debito.setIdDevedor(devedor.getIdDevedor());
        
        Comprovantes comprovante = new Comprovantes();
        comprovante.setArquivo(arquivo.getName());
        comprovante.setDataInclusao(LocalDate.now());
        comprovante.setIdCliente(cliente.getIdCliente());
        comprovante.setIdDevedor(devedor.getIdDevedor());
        
        dDAO.insereDebitos(debito, comprovante);
        
        // Lógica para salvar o arquivo no servidor
        File serverDirectory = new File("C:\\xampp\\htdocs\\Cobranca\\php\\uploads\\");
        File destinationFile = new File(serverDirectory, arquivo.getName());

        try {
            Files.copy(arquivo.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Arquivo salvo com sucesso no servidor.");
        } catch (IOException ex) {
            System.out.println("Erro ao salvar o arquivo no servidor: " + ex.getMessage());
        }
        
        limpaCampos();
        desativaCampos();     
        dispose();
    }//GEN-LAST:event_jBSalvarActionPerformed
    
    private String converteDataParaApresentacao(LocalDate data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = data.format(formatter);

        return formattedDate;
    }
    
    private LocalDate converteDataParaBanco(String data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(data, formatter);

        return localDate;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroDebitos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroDebitos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroDebitos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroDebitos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroDebitos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBComprovante;
    private javax.swing.JButton jBLimpar;
    private javax.swing.JButton jBPesquisarCliente;
    private javax.swing.JButton jBPesquisarDevedor;
    private javax.swing.JButton jBSalvar;
    private javax.swing.JFormattedTextField jFTFDataCompra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTADescricao;
    private javax.swing.JTextField jTFCPFCliente;
    private javax.swing.JTextField jTFCPFDevedor;
    private javax.swing.JTextField jTFCliente;
    private javax.swing.JTextField jTFNomeDevedor;
    private javax.swing.JTextField jTFPesquisarCliente;
    private javax.swing.JTextField jTFPesquisarDevedor;
    private javax.swing.JTextField jTFValor;
    // End of variables declaration//GEN-END:variables
}