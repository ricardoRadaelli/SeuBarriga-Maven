/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Telas;

import Atributos.Devedores;
import Banco.DevedoresDAO;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
 
/**
 *
 * @author User
 */
public class PesquisaDevedores extends javax.swing.JFrame {

    private int idCliente;
    
    public PesquisaDevedores(int idCliente) {
        initComponents();
        this.idCliente = idCliente;
        listaDevedoresPorCliente(idCliente, "%");
    }
    
    public PesquisaDevedores(String nomeDevedor) {
        initComponents();
        listaDevedores(nomeDevedor);
    }
    
    public PesquisaDevedores(int idCliente, String pesquisa){
        initComponents();
        if(pesquisa.isEmpty()){
            this.idCliente = idCliente;
            listaDevedoresPorCliente(idCliente, "%");
        }else{
            this.idCliente = idCliente;
            listaDevedoresPorCliente(idCliente, pesquisa);
    
        }
    }
    
    public interface InterfaceRepassaDadosDevedores {
        void devedorSelecionado(Devedores devedor);
    }
    
    public void setListenerDevedor(InterfaceRepassaDadosDevedores listener){
        this.listener = listener;
    }
    
    private InterfaceRepassaDadosDevedores listener;
    int idDevedor=0;
    DefaultTableModel model = new DefaultTableModel();
    Devedores devedorSelecionado;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTFNome = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTDevedores = new javax.swing.JTable();
        jBPesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Pesquisar devedor:");

        jTDevedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID do devedor", "Nome", "CPF/CNPJ", "Razão Social"
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
        jTDevedores.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (jTDevedores.getSelectedRow() != -1) {
                        idDevedor = Integer.parseInt(jTDevedores.getValueAt(jTDevedores.getSelectedRow(), 0).toString());
                        devedorSelecionado(idDevedor);
                    }
                }
            }
        });
        jTDevedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTDevedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTDevedores);

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

    private void jTDevedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTDevedoresMouseClicked
        int selectedRow = jTDevedores.getSelectedRow();
        if(evt.getClickCount() == 2){
            if (selectedRow != -1) {
                devedorSelecionado = devedorSelecionado(Integer.parseInt(jTDevedores.getValueAt(jTDevedores.getSelectedRow(), 0).toString()));
                listener.devedorSelecionado(devedorSelecionado);
                dispose();
            }
        }
    }//GEN-LAST:event_jTDevedoresMouseClicked

    private void jBPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPesquisarActionPerformed
        String nome = jTFNome.getText();
        listaDevedoresPorCliente(idCliente, nome);
    }//GEN-LAST:event_jBPesquisarActionPerformed

    private void listaDevedores(String pesquisa){
        DevedoresDAO ddao = new DevedoresDAO();
        ArrayList<Devedores> lista = ddao.buscaDevedorIncompletoPorNome(pesquisa);
        model = (DefaultTableModel) jTDevedores.getModel();
        model.setNumRows(0);
        for (int i = 0; i < lista.size(); i++) {
            model.addRow(new Object[]{
                lista.get(i).getIdDevedor(),
                lista.get(i).getNomeDevedor(),
                lista.get(i).getCpfcnpj(),
                lista.get(i).getRazao(),
            });
        }
    }
    
    private void listaDevedoresPorCliente(int idCliente, String pesquisa){
        DevedoresDAO ddao = new DevedoresDAO();
        ArrayList<Devedores> lista = ddao.buscaDevedoresDoClientePorNome(idCliente, pesquisa);
        model = (DefaultTableModel) jTDevedores.getModel();
        model.setNumRows(0);
        for (int i = 0; i < lista.size(); i++) {
            model.addRow(new Object[]{
                lista.get(i).getIdDevedor(),
                lista.get(i).getNomeDevedor(),
                lista.get(i).getCpfcnpj(),
                lista.get(i).getDataFinalizacaoCobranca(),
            });
        }
    }
    
    public Devedores devedorSelecionado(int idDevedor){
        Devedores devedor = new Devedores();
        DevedoresDAO cdao = new DevedoresDAO();
        Devedores lista = cdao.buscaIncompletoDevedorPorId(idDevedor);
        
        devedor.setNomeDevedor(lista.getNomeDevedor());
        devedor.setRazao(lista.getRazao());
        devedor.setCpfcnpj(lista.getCpfcnpj());
        devedor.setIdDevedor(idDevedor);
        
        return devedor;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTDevedores;
    private javax.swing.JTextField jTFNome;
    // End of variables declaration//GEN-END:variables
}
