/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Telas;

import TelasForaDeUso.Cobranca;
import Atributos.Clientes;
import Atributos.Devedores;
import Atributos.Debitos;
import Banco.AcaoCobrancaDAO;
import Banco.ClientesDAO;
import Banco.DevedoresDAO;
import Banco.DebitosDAO;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author User
 */
public class ListaAcoesCobranca extends JDialog {

    /**
     * Creates new form AcaoCobranca
     */
    public ListaAcoesCobranca() {
        initComponents();
    }
    
    DefaultTableModel modelDebitos = new DefaultTableModel();
    DefaultTableModel modelAcoes = new DefaultTableModel();
    int idCompra = 0;
    
    public ListaAcoesCobranca(int idCliente, int idDevedor, JDialog frame) {
        super(frame, "Listagem de ações de cobrança", true);
        initComponents();
        desativaCampos();
        
        Clientes cliente = buscaCliente(idCliente);
        jTFNome.setText(cliente.getNomeCliente());
        jTFCPFCNPJ.setText(cliente.getCpfcnpj());
        jTFsaldoCobranca.setText(String.valueOf(cliente.getValorEmCobranca()));
        
        jLCliente.setText(jTFNome.getText());
        
        Devedores devedor = buscaDevedor(idDevedor);
        jTFCPFCNPJDevedor.setText(devedor.getCpfcnpj());
        jTFNomeDevedor.setText(devedor.getNomeDevedor());
        jTFSaldoDevedor.setText(saldoDevedorPorCliente(idCliente, idDevedor));
        jTFSaldoDevedorTotal.setText(String.valueOf(devedor.getSaldoDevedor()));
        
        preencheTabelaDebitos(idDevedor);
        buscaAcoes(idCliente, idDevedor);
    }
    
    private void desativaCampos(){
        jTFCPFCNPJ.setEditable(false);
        jTFCPFCNPJDevedor.setEditable(false);
        jTFNome.setEditable(false);
        jTFNomeDevedor.setEditable(false);
        jTFSaldoDevedor.setEditable(false);
        jTFSaldoDevedorTotal.setEditable(false);
        jTFsaldoCobranca.setEditable(false);
    }
    
    private void preencheTabelaDebitos(int idDevedor){
        DebitosDAO debitosDAO = new DebitosDAO();
        ArrayList<Debitos> lista = debitosDAO.buscaDebitos(idDevedor);
        
        ClientesDAO clienteDAO = new ClientesDAO();
        modelDebitos = (DefaultTableModel) jTDebitos.getModel();
        for (int i = 0; i < lista.size(); i++) {
            modelDebitos.addRow(new Object[]{
                lista.get(i).getIdCompra(),
                lista.get(i).getDataCompra(),
                lista.get(i).getValor(),
                lista.get(i).getDescricao(),
                lista.get(i).getDataInclusao(),
                                
                lista.get(i).getComprovantes().get(0).getArquivo()
            });
        }
        // Configuração do renderizador personalizado para a coluna do botão
        jTDebitos.getColumn("Comprovantes").setCellRenderer(new ListaAcoesCobranca.BotaoRenderer());

        // Configuração do editor de célula para a coluna do botão
        jTDebitos.getColumn("Comprovantes").setCellEditor(new ListaAcoesCobranca.BotaoEditor());
    }

    private void preencheTabelaAcoes(ArrayList<Atributos.AcaoCobranca> lista){
        modelAcoes = (DefaultTableModel) jTAcoes.getModel();
        modelAcoes.setNumRows(0);
        for (int i = 0; i < lista.size(); i++) {
            modelAcoes.addRow(new Object[]{
                lista.get(i).getDataCobranca(),
                lista.get(i).getTipoCobranca(),
                lista.get(i).getTextoCobranca()
            });
        }
    }
    
    private void buscaAcoes(int idCliente, int idDevedor){
        AcaoCobrancaDAO aDAO = new AcaoCobrancaDAO();
        Atributos.AcaoCobranca acao = new Atributos.AcaoCobranca();
        
        ArrayList<Atributos.AcaoCobranca> listaAcoes = aDAO.buscaAcoesPorDevedorECliente(idCliente, idDevedor, java.time.LocalDate.parse("1900-01-01"), java.time.LocalDate.now());
        preencheTabelaAcoes(listaAcoes);        
    }
    
    // Método para abrir o arquivo usando o Desktop
    private void abrirArquivo(String caminhoArquivo) {
        try {
            File arquivo = new File("C:\\xampp\\htdocs\\Cobranca\\php\\uploads\\"+caminhoArquivo);
            Desktop.getDesktop().open(arquivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Editor de célula personalizado para lidar com o evento de clique do botão
    class BotaoEditor extends AbstractCellEditor implements TableCellEditor {
        private JButton button;

        public BotaoEditor() {
            button = new JButton();
            button.setOpaque(true);

            // Adiciona um ActionListener ao botão
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!jTDebitos.getValueAt(jTDebitos.getSelectedRow(), 5).toString().isEmpty()){
                        String caminhoArquivo = jTDebitos.getValueAt(jTDebitos.getSelectedRow(), 5).toString();
                        abrirArquivo(caminhoArquivo);
                    }else{
                        JOptionPane.showMessageDialog(null, "Não existe comprovante para esta compra!");
                    }
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }    
    }
    
    // Renderizador personalizado para exibir o botão na célula da tabela
    class BotaoRenderer extends JButton implements TableCellRenderer {
        public BotaoRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText("Abrir");
            return this;
        }
    }
    
    private String saldoDevedorPorCliente(int idCliente, int idDevedor){
        double saldo=-1;
        ClientesDAO cdao = new ClientesDAO();
        
        saldo = cdao.getValorEmCobranca(idCliente);
        
        return String.valueOf(saldo);
    }
    
    private Devedores buscaDevedor(int idDevedor){
        Devedores devedor = new Devedores();
        DevedoresDAO cDAO = new DevedoresDAO();
        Devedores lista = cDAO.buscaIncompletoDevedorPorId(idDevedor);
        
        devedor.setCpfcnpj(lista.getCpfcnpj());
        devedor.setIdCliente(lista.getIdCliente());
        //devedor.setSaldoDevedor(lista.getSaldoDevedor());
        devedor.setNomeDevedor(lista.getNomeDevedor());

        return devedor;
    }
    
    private Clientes buscaCliente(int idCliente){
        ClientesDAO cDAO = new ClientesDAO();
        Clientes cliente = cDAO.buscaClientesPorId(idCliente);
        
        cliente.setCpfcnpj(cliente.getCpfcnpj());
        cliente.setNomeCliente(cliente.getNomeCliente());
        cliente.setValorEmCobranca(cliente.getValorEmCobranca());
        cliente.setIdCliente(cliente.getIdCliente());
        
        return cliente;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTFNome = new javax.swing.JTextField();
        jTFsaldoCobranca = new javax.swing.JTextField();
        jTFCPFCNPJ = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLCliente = new javax.swing.JLabel();
        jTFSaldoDevedor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTFSaldoDevedorTotal = new javax.swing.JTextField();
        jTFNomeDevedor = new javax.swing.JTextField();
        jTFCPFCNPJDevedor = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTAcoes = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTDebitos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Cliente"));

        jLabel1.setText("Nome:");

        jLabel2.setText("CPF/CNPJ:");

        jLabel3.setText("Saldo em cobrança:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFNome, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFCPFCNPJ)))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFsaldoCobranca, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTFsaldoCobranca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTFCPFCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Devedor"));

        jLabel4.setText("Nome:");

        jLabel5.setText("CPF/CNPJ:");

        jLabel6.setText("Saldo devedor para");

        jLCliente.setText("cliente");

        jLabel7.setText("Saldo devedor Total:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFCPFCNPJDevedor))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFNomeDevedor)))
                .addGap(114, 114, 114)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTFSaldoDevedor, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jTFSaldoDevedorTotal)))
                .addGap(15, 15, 15))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLCliente)
                    .addComponent(jTFSaldoDevedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFNomeDevedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTFCPFCNPJDevedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTFSaldoDevedorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Ações de Cobrança"));

        jButton1.setText("Telefonema");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("WhatsApp");

        jButton3.setText("Carta");

        jButton4.setText("Resposta");

        jButton5.setText("Pagamento Recebido");

        jTAcoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data da Ação", "Tipo da Ação", "Descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTAcoes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Débitos"));

        jTDebitos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Data Compra", "Valor", "Descrição", "Data Sistema", "Comprovantes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTDebitos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (jTDebitos.getSelectedRow() != -1) {
                        idCompra = Integer.parseInt(jTDebitos.getValueAt(jTDebitos.getSelectedRow(), 0).toString());
                    }
                }
            }
        });
        jScrollPane1.setViewportView(jTDebitos);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(idCompra != 0){
            System.out.println("Id Compra ->"+idCompra);
            Cobranca tela = new Cobranca(0, idCompra, this);
            tela.setVisible(true);
        }else
            JOptionPane.showMessageDialog(null, "Selecione um débito na tabela para ser cobrado!");
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ListaAcoesCobranca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaAcoesCobranca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaAcoesCobranca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaAcoesCobranca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaAcoesCobranca().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTAcoes;
    private javax.swing.JTable jTDebitos;
    private javax.swing.JTextField jTFCPFCNPJ;
    private javax.swing.JTextField jTFCPFCNPJDevedor;
    private javax.swing.JTextField jTFNome;
    private javax.swing.JTextField jTFNomeDevedor;
    private javax.swing.JTextField jTFSaldoDevedor;
    private javax.swing.JTextField jTFSaldoDevedorTotal;
    private javax.swing.JTextField jTFsaldoCobranca;
    // End of variables declaration//GEN-END:variables
}
