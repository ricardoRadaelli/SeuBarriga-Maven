/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Telas.Relatorios;

import Atributos.Clientes;
import Atributos.Devedores;
import Relatorios.Relatorios;
import Telas.PesquisaClientes;
import Telas.PesquisaDevedores;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author User
 */
public class Devedor_Debitos extends javax.swing.JFrame implements PesquisaClientes.InterfaceRepassaDadosClientes, PesquisaDevedores.InterfaceRepassaDadosDevedores{

    /**
     * Creates new form Devedor_Debitos
     */
    public Devedor_Debitos() {
        initComponents();
        itemChangeRadioButtons();
        
        
        
    }

    Clientes cliente;
    Devedores devedor;
    
    private void itemChangeRadioButtons(){
        ItemListener itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                JRadioButton selectedRadioButton = (JRadioButton) e.getSource();
                if (selectedRadioButton.isSelected()) {
                    // Realize as ações desejadas quando o RadioButton for selecionado
                    limpaCampos();
                    desabilitaCampos();
                    
                    if(selectedRadioButton.getText().equals("Débitos")){
                        jBGerar.setEnabled(true);
                    }else if(selectedRadioButton.getText().equals("Débitos por cliente")){
                        jTFCliente.setEnabled(true);
                        jBPesquisarCliente.setEnabled(true);
                    }else if(selectedRadioButton.getText().equals("Saldo devedor")){
                        jBGerar.setEnabled(true);
                    }else if(selectedRadioButton.getText().equals("Negociações")){
                        jBGerar.setEnabled(true);
                    }else if(selectedRadioButton.getText().equals("Negociações por cliente")){
                        jTFCliente.setEnabled(true);
                        jBPesquisarCliente.setEnabled(true);
                        jFTFDataFinal.setEnabled(true);
                        jFTFDataInicial.setEnabled(true);
                    }else if(selectedRadioButton.getText().equals("Negociações por data")){
                        jTFCliente.setEnabled(true);
                        jBPesquisarCliente.setEnabled(true);
                    }else if(selectedRadioButton.getText().equals("Pagamentos efetuados")){
                        jBGerar.setEnabled(true);
                        jFTFDataFinal.setEnabled(true);
                        jFTFDataInicial.setEnabled(true);
                    }else if(selectedRadioButton.getText().equals("Pagamentos em aberto")){
                        jBGerar.setEnabled(true);
                    }else if(selectedRadioButton.getText().equals("Ações de cobrança")){
                        jFTFDataFinal.setEnabled(true);
                        jFTFDataInicial.setEnabled(true);
                        jBGerar.setEnabled(true);
                    }else if(selectedRadioButton.getText().equals("Ações de cobrança por cliente")){
                        jTFCliente.setEnabled(true);
                        jBPesquisarCliente.setEnabled(true);
                        jFTFDataFinal.setEnabled(true);
                        jFTFDataInicial.setEnabled(true);
                    }
                }
            }
        };
        jRBDebitosPorCliente.addItemListener(itemListener);
        jRBDebitos.addItemListener(itemListener);
        jRBSaldoDevedor.addItemListener(itemListener);
        jRBNegociacoes.addItemListener(itemListener);
        jRBNegociacoesPorCliente.addItemListener(itemListener);
        jRBNegociacoesPorData.addItemListener(itemListener);
        jRBPagamentosEfetuados.addItemListener(itemListener);
        jRBPagamentosEmAberto.addItemListener(itemListener);
        jRBAcoesCobranca.addItemListener(itemListener);
        jRBAcoesPorCliente.addItemListener(itemListener);
    }
    
    private void limpaCampos(){
        jFTFDataFinal.setText("");
        jFTFDataInicial.setText("");
        jTFCliente.setText("");
        cliente = new Clientes();
    }
    
    private void habilitaCampos(){
        jFTFDataFinal.setEnabled(true);
        jFTFDataInicial.setEnabled(true);
        jTFCliente.setEnabled(true);
        jBPesquisarCliente.setEnabled(true);
        jBGerar.setEnabled(true);
    }
    
    private void desabilitaCampos(){
        jFTFDataFinal.setEnabled(false);
        jFTFDataInicial.setEnabled(false);
        jTFCliente.setEnabled(false);
        jBPesquisarCliente.setEnabled(false);
        jBGerar.setEnabled(false);
    }
    
    private boolean validaCampos(){
        boolean retorno = true;
        
        if(jFTFDataFinal.isEnabled() && jFTFDataFinal.getText().isEmpty())
            retorno = false;
        if(jFTFDataInicial.isEnabled() && jFTFDataInicial.getText().isEmpty())
            retorno = false;
        if(jTFCliente.isEnabled() && jTFCliente.getText().isEmpty())
            retorno = false;
        if(devedor.getNomeDevedor().isEmpty())
            retorno = false;
        
        
        return retorno;
    }
    
    private LocalDate converteData(String data){
        String dateString = data; // String contendo a data

        // Define o formato da data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Converte a string para um objeto LocalDate
        LocalDate date = LocalDate.parse(dateString, formatter);
        
        return date;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jRBDebitosPorCliente = new javax.swing.JRadioButton();
        jRBDebitos = new javax.swing.JRadioButton();
        jRBSaldoDevedor = new javax.swing.JRadioButton();
        jRBPagamentosEfetuados = new javax.swing.JRadioButton();
        jRBPagamentosEmAberto = new javax.swing.JRadioButton();
        jRBNegociacoes = new javax.swing.JRadioButton();
        jRBNegociacoesPorData = new javax.swing.JRadioButton();
        jRBNegociacoesPorCliente = new javax.swing.JRadioButton();
        jRBAcoesCobranca = new javax.swing.JRadioButton();
        jRBAcoesPorCliente = new javax.swing.JRadioButton();
        jRBPagamentosEmAtraso = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTFCliente = new javax.swing.JTextField();
        jBPesquisarCliente = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jFTFDataInicial = new javax.swing.JFormattedTextField();
        jFTFDataFinal = new javax.swing.JFormattedTextField();
        jBGerar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTFDevedor = new javax.swing.JTextField();
        jBPesquisarDevedor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório de Devedores");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipos de relatório"));

        buttonGroup1.add(jRBDebitosPorCliente);
        jRBDebitosPorCliente.setText("Débitos por cliente");
        jRBDebitosPorCliente.setEnabled(false);

        buttonGroup1.add(jRBDebitos);
        jRBDebitos.setText("Débitos");
        jRBDebitos.setEnabled(false);

        buttonGroup1.add(jRBSaldoDevedor);
        jRBSaldoDevedor.setText("Saldo devedor");
        jRBSaldoDevedor.setEnabled(false);

        buttonGroup1.add(jRBPagamentosEfetuados);
        jRBPagamentosEfetuados.setText("Pagamentos efetuados");

        buttonGroup1.add(jRBPagamentosEmAberto);
        jRBPagamentosEmAberto.setText("Pagamentos em aberto");

        buttonGroup1.add(jRBNegociacoes);
        jRBNegociacoes.setText("Negociações");
        jRBNegociacoes.setEnabled(false);

        buttonGroup1.add(jRBNegociacoesPorData);
        jRBNegociacoesPorData.setText("Negociações por data");
        jRBNegociacoesPorData.setEnabled(false);

        buttonGroup1.add(jRBNegociacoesPorCliente);
        jRBNegociacoesPorCliente.setText("Negociações por cliente");
        jRBNegociacoesPorCliente.setEnabled(false);

        buttonGroup1.add(jRBAcoesCobranca);
        jRBAcoesCobranca.setText("Ações de cobrança");

        buttonGroup1.add(jRBAcoesPorCliente);
        jRBAcoesPorCliente.setText("Ações de cobrança por cliente");

        buttonGroup1.add(jRBPagamentosEmAtraso);
        jRBPagamentosEmAtraso.setText("Pagamentos em atraso");
        jRBPagamentosEmAtraso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBPagamentosEmAtrasoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRBDebitos)
                    .addComponent(jRBDebitosPorCliente)
                    .addComponent(jRBSaldoDevedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRBNegociacoes)
                    .addComponent(jRBNegociacoesPorCliente)
                    .addComponent(jRBNegociacoesPorData))
                .addGap(150, 150, 150)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRBPagamentosEmAberto)
                            .addComponent(jRBPagamentosEfetuados))
                        .addGap(120, 120, 120)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRBAcoesPorCliente)
                            .addComponent(jRBAcoesCobranca)))
                    .addComponent(jRBPagamentosEmAtraso))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRBDebitos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRBDebitosPorCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRBSaldoDevedor))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jRBNegociacoesPorData))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRBNegociacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRBNegociacoesPorCliente))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRBPagamentosEfetuados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRBPagamentosEmAberto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRBPagamentosEmAtraso))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRBAcoesCobranca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRBAcoesPorCliente))))
        );

        jLabel1.setText("Cliente:");

        jTFCliente.setEnabled(false);

        jBPesquisarCliente.setText("Pesquisar");
        jBPesquisarCliente.setEnabled(false);
        jBPesquisarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPesquisarClienteActionPerformed(evt);
            }
        });

        jLabel2.setText("Data inicial:");

        jLabel3.setText("Data final:");

        jFTFDataInicial.setEnabled(false);

        jFTFDataFinal.setEnabled(false);

        jBGerar.setText("Gerar");
        jBGerar.setEnabled(false);
        jBGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGerarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jFTFDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jFTFDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTFCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBPesquisarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBGerar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBPesquisarCliente))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jFTFDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jFTFDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBGerar)))))
        );

        jLabel4.setText("Devedor:");

        jBPesquisarDevedor.setText("Pesquisar");
        jBPesquisarDevedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPesquisarDevedorActionPerformed(evt);
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
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jTFDevedor, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBPesquisarDevedor)
                        .addGap(220, 220, 220))))
            .addGroup(layout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTFDevedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBPesquisarDevedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBPesquisarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPesquisarClienteActionPerformed
        PesquisaClientes pesqcli = new PesquisaClientes(jTFCliente.getText());
        pesqcli.setListenerClientes(this);
        pesqcli.setVisible(true);
    }//GEN-LAST:event_jBPesquisarClienteActionPerformed

    private void jBGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGerarActionPerformed
        if(validaCampos()){
            if(jRBAcoesCobranca.isSelected()){
                Relatorios rel = new Relatorios();
                HashMap<String, Object> parametros = new HashMap<>();
                parametros.put("aa", "bb");
                rel.imprimeRelatorio("acoes", parametros, rel.relatorioAcoesPorDevedor(devedor.getIdDevedor(), converteData(jFTFDataInicial.getText()), converteData(jFTFDataFinal.getText())));
            }else if(jRBAcoesPorCliente.isSelected()){
                Relatorios rel = new Relatorios();
                HashMap<String, Object> parametros = new HashMap<>();
                parametros.put("aa", "bb");
                rel.imprimeRelatorio("acoes", parametros, rel.relatorioAcoesPorDevedorECliente(cliente.getIdCliente(), devedor.getIdDevedor(), converteData(jFTFDataInicial.getText()), converteData(jFTFDataFinal.getText())));
            }else if(jRBDebitos.isSelected()){
            }else if(jRBDebitosPorCliente.isSelected()){
            }else if(jRBNegociacoes.isSelected()){
            }else if(jRBNegociacoesPorCliente.isSelected()){
            }else if(jRBNegociacoesPorData.isSelected()){
            }else if(jRBPagamentosEfetuados.isSelected()){
                Relatorios rel = new Relatorios();
                HashMap<String, Object> parametros = new HashMap<>();
                parametros.put("aa", "bb");
                rel.imprimeRelatorio("pagamentosEfetuados", parametros, rel.relatorioPagamentosEfetuados(cliente.getIdCliente(), devedor.getIdDevedor(), converteData(jFTFDataInicial.getText()), converteData(jFTFDataFinal.getText())));
            }else if(jRBPagamentosEmAberto.isSelected()){
                Relatorios rel = new Relatorios();
                HashMap<String, Object> parametros = new HashMap<>();
                parametros.put("aa", "bb");
                rel.imprimeRelatorio("pagamentosEmAberto", parametros, rel.relatorioPagamentosEmAberto(devedor.getIdDevedor()));
            }else if(jRBPagamentosEmAtraso.isSelected()){
                Relatorios rel = new Relatorios();
                HashMap<String, Object> parametros = new HashMap<>();
                parametros.put("aa", "bb");
                rel.imprimeRelatorio("pagamentosEmAtraso", parametros, rel.relatorioPagamentosEmAtraso(devedor.getIdDevedor()));
            }else if(jRBSaldoDevedor.isSelected()){
            }
        
        }else
            JOptionPane.showMessageDialog(null, "Preencha todos os campos disponíveis!");
    }//GEN-LAST:event_jBGerarActionPerformed

    private void jBPesquisarDevedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPesquisarDevedorActionPerformed
        PesquisaDevedores pesqdev = new PesquisaDevedores(jTFDevedor.getText());
        pesqdev.setListenerDevedor(this);
        pesqdev.setVisible(true);
    }//GEN-LAST:event_jBPesquisarDevedorActionPerformed

    private void jRBPagamentosEmAtrasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBPagamentosEmAtrasoActionPerformed
        jBGerar.setEnabled(true);
    }//GEN-LAST:event_jRBPagamentosEmAtrasoActionPerformed

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
            java.util.logging.Logger.getLogger(Devedor_Debitos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Devedor_Debitos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Devedor_Debitos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Devedor_Debitos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Devedor_Debitos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBGerar;
    private javax.swing.JButton jBPesquisarCliente;
    private javax.swing.JButton jBPesquisarDevedor;
    private javax.swing.JFormattedTextField jFTFDataFinal;
    private javax.swing.JFormattedTextField jFTFDataInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRBAcoesCobranca;
    private javax.swing.JRadioButton jRBAcoesPorCliente;
    private javax.swing.JRadioButton jRBDebitos;
    private javax.swing.JRadioButton jRBDebitosPorCliente;
    private javax.swing.JRadioButton jRBNegociacoes;
    private javax.swing.JRadioButton jRBNegociacoesPorCliente;
    private javax.swing.JRadioButton jRBNegociacoesPorData;
    private javax.swing.JRadioButton jRBPagamentosEfetuados;
    private javax.swing.JRadioButton jRBPagamentosEmAberto;
    private javax.swing.JRadioButton jRBPagamentosEmAtraso;
    private javax.swing.JRadioButton jRBSaldoDevedor;
    private javax.swing.JTextField jTFCliente;
    private javax.swing.JTextField jTFDevedor;
    // End of variables declaration//GEN-END:variables

    @Override
    public void clienteSelecionado(Clientes cliente) {
        this.cliente = cliente;
        jTFCliente.setText(cliente.getNomeCliente());
        jBGerar.setEnabled(true);
    }

    @Override
    public void devedorSelecionado(Devedores devedor) {
        this.devedor = devedor;
        jTFDevedor.setText(devedor.getNomeDevedor());
    }
}
