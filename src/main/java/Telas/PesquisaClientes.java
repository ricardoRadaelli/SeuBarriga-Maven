/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Telas;

import Atributos.Clientes;
import Banco.ClientesDAO;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class PesquisaClientes extends javax.swing.JFrame {

    public PesquisaClientes() {
        initComponents();
        listaClientes("%");
    }
    
    public PesquisaClientes(String pesquisa){
        initComponents();
        if(pesquisa.isEmpty())
            listaClientes("");
        else
            listaClientes(pesquisa);
    }
    
    public interface InterfaceRepassaDadosClientes {
        void clienteSelecionado(Clientes cliente);
    }
    
    public void setListenerClientes(InterfaceRepassaDadosClientes listener){
        this.listener = listener;
    }
    
    private InterfaceRepassaDadosClientes listener;
    int idCliente=0;
    DefaultTableModel model = new DefaultTableModel();
    Clientes clienteSelecionado;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTFNome = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTClientes = new javax.swing.JTable();
        jBPesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Pesquisar cliente:");

        jTClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID do cliente", "Nome", "Razão Social", "CPF/CNPJ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTClientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (jTClientes.getSelectedRow() != -1) {
                        idCliente = Integer.parseInt(jTClientes.getValueAt(jTClientes.getSelectedRow(), 0).toString());
                        clienteSelecionado(idCliente);
                    }
                }
            }
        });
        jTClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTClientes);

        jBPesquisar.setText("Pesquisar");
        jBPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTFNome, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBPesquisar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTClientesMouseClicked
        int selectedRow = jTClientes.getSelectedRow();
        if(evt.getClickCount() == 2){
            if (selectedRow != -1) {
                clienteSelecionado = clienteSelecionado(Integer.parseInt(jTClientes.getValueAt(jTClientes.getSelectedRow(), 0).toString()));
                listener.clienteSelecionado(clienteSelecionado);
                dispose();
            }
        }
    }//GEN-LAST:event_jTClientesMouseClicked

    private void jBPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPesquisarActionPerformed
        String nome = jTFNome.getText();
        listaClientes(nome);
    }//GEN-LAST:event_jBPesquisarActionPerformed

    private void listaClientes(String pesquisa){
        ClientesDAO cdao = new ClientesDAO();
        ArrayList<Clientes> lista = cdao.buscaClientesPorNome(pesquisa);
        model = (DefaultTableModel) jTClientes.getModel();
        model.setNumRows(0);
        for (int i = 0; i < lista.size(); i++) {
            model.addRow(new Object[]{
                lista.get(i).getIdCliente(),
                lista.get(i).getNomeCliente(),
                lista.get(i).getRazao(),
                lista.get(i).getCpfcnpj()
            });
        }
    }
    
    public Clientes clienteSelecionado(int idCliente){
        ClientesDAO cdao = new ClientesDAO();
        Clientes cliente = cdao.buscaClientesPorId(idCliente);

        cliente.setNomeCliente(cliente.getNomeCliente());
        cliente.setCpfcnpj(cliente.getCpfcnpj());
        cliente.setRazao(cliente.getRazao());
        cliente.setIdCliente(cliente.getIdCliente());
        return cliente;
    }
    
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PesquisaClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTClientes;
    private javax.swing.JTextField jTFNome;
    // End of variables declaration//GEN-END:variables
}
