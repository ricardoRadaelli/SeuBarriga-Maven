/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Telas;

import Atributos.Clientes;
import Atributos.Debitos;
import Atributos.Devedores;
import Atributos.Negociacao;
import Atributos.Pagamentos;
import Banco.ClientesDAO;
import Banco.DebitosDAO;
import Banco.DevedoresDAO;
import Banco.NegociacaoDAO;
import Banco.PagamentosDAO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author User
 */
public class ListarNegociacoes extends javax.swing.JFrame implements PesquisaClientes.InterfaceRepassaDadosClientes, PesquisaDevedores.InterfaceRepassaDadosDevedores {

    /**
     * Creates new form CadastrarNegociacao
     */
    public ListarNegociacoes() {
        initComponents();
        desativaCampos();
    }

    Clientes cliente;
    Devedores devedor;

    DefaultTableModel modelNegociacoes = new DefaultTableModel();
    DefaultTableModel modelPagamentos = new DefaultTableModel();
    DefaultTableModel modelDebitos = new DefaultTableModel();
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTFPesquisarCliente = new javax.swing.JTextField();
        jBPesquisarCliente = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTFCPFCliente = new javax.swing.JTextField();
        jTFRazaoCliente = new javax.swing.JTextField();
        jTFNomeCliente = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jTFPesquisarDevedor = new javax.swing.JTextField();
        jBPesquisarDevedor = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTFCPFDevedor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTFNomeDevedor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTFSaldoDevedor = new javax.swing.JTextField();
        jTFDataFinalizacao = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTFTaxaJuros = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTNegociacoes = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTDebitos = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTPagamentos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        jLabel1.setText("Pesquisar cliente:");

        jBPesquisarCliente.setText("Pesquisar");
        jBPesquisarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPesquisarClienteActionPerformed(evt);
            }
        });

        jLabel3.setText("Nome:");

        jLabel4.setText("Razão Social:");

        jLabel5.setText("CPF/CNPJ:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTFPesquisarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTFRazaoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jTFNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFCPFCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBPesquisarCliente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFPesquisarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBPesquisarCliente))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jTFCPFCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTFRazaoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Devedor"));

        jBPesquisarDevedor.setText("Pesquisar");
        jBPesquisarDevedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPesquisarDevedorActionPerformed(evt);
            }
        });

        jLabel2.setText("Pesquisar devedor:");

        jLabel6.setText("Nome:");

        jLabel7.setText("CPF/CNPJ:");

        jLabel8.setText("Finalização de cobrança:");

        jLabel9.setText("Saldo devedor:");

        jLabel10.setText("R$");

        jLabel11.setText("Taxa de juros:");

        jLabel12.setText("%");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jTFNomeDevedor, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jTFDataFinalizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFTaxaJuros, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addGap(76, 76, 76)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFSaldoDevedor))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFCPFDevedor, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFPesquisarDevedor)))
                .addGap(20, 20, 20)
                .addComponent(jBPesquisarDevedor)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTFPesquisarDevedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBPesquisarDevedor))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jTFCPFDevedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFNomeDevedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jTFSaldoDevedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFDataFinalizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jTFTaxaJuros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Negociações ativas"));

        jTNegociacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Negociação", "Cliente", "Devedor", "Valor da dívida", "Valor Pago", "Parcelas pagas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableColumnModel cMNegociacoes = jTNegociacoes.getColumnModel();

        // Escondendo a coluna de índice 1 (Coluna 2)
        cMNegociacoes.removeColumn(cMNegociacoes.getColumn(0));

        jTNegociacoes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (jTNegociacoes.getSelectedRow() != -1) {
                        preencheTabelaPagamentos(Integer.parseInt(jTNegociacoes.getModel().getValueAt(jTNegociacoes.getSelectedRow(), 0).toString()));
                        preencheTabelaDebitos(Integer.parseInt(jTNegociacoes.getModel().getValueAt(jTNegociacoes.getSelectedRow(), 0).toString()));
                    }
                }
            }
        });
        jScrollPane1.setViewportView(jTNegociacoes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Débitos"));

        jTDebitos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Débito", "Valor", "Descrição", "Data da compra", "Comprovante"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableColumnModel cMDebitos = jTDebitos.getColumnModel();

        // Escondendo a coluna de índice 1 (Coluna 2)
        cMDebitos.removeColumn(cMDebitos.getColumn(0));
        jScrollPane2.setViewportView(jTDebitos);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(194, 194, 194))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Pagamentos"));

        jTPagamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pagamento", "Valor pago", "Data do pagamento", "Data de vencimento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTPagamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTPagamentosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTPagamentos);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void desativaCampos(){
        jTFCPFCliente.setEditable(false);
        jTFCPFDevedor.setEditable(false);
        jTFDataFinalizacao.setEditable(false);
        jTFNomeCliente.setEditable(false);
        jTFNomeDevedor.setEditable(false);
        jTFRazaoCliente.setEditable(false);
        jTFSaldoDevedor.setEditable(false);
        jTFTaxaJuros.setEditable(false);
        jTFPesquisarDevedor.setEditable(false);
        jBPesquisarDevedor.setEnabled(false);
    }
    
    private void limpaCampos(){
        jTFCPFCliente.setText("");
        jTFCPFDevedor.setText("");
        jTFDataFinalizacao.setText("");
        jTFNomeCliente.setText("");
        jTFNomeDevedor.setText("");
        jTFRazaoCliente.setText("");
        jTFSaldoDevedor.setText("");
        jTFTaxaJuros.setText("");
        jTFPesquisarDevedor.setText("");
        modelDebitos.setRowCount(0);
        modelNegociacoes.setRowCount(0);
        modelPagamentos.setRowCount(0);
    }
    
    private void jBPesquisarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPesquisarClienteActionPerformed
        PesquisaClientes pesquisa = new PesquisaClientes(jTFPesquisarCliente.getText());
        pesquisa.setListenerClientes(this);
        pesquisa.setVisible(true);
        
        limpaCampos();
        
        jTFPesquisarDevedor.setEditable(true);
        jBPesquisarDevedor.setEnabled(true);
    }//GEN-LAST:event_jBPesquisarClienteActionPerformed

    private void jBPesquisarDevedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPesquisarDevedorActionPerformed
        PesquisaDevedores pesquisa = new PesquisaDevedores(cliente.getIdCliente(), jTFPesquisarCliente.getText());
        pesquisa.setListenerDevedor(this);
        pesquisa.setVisible(true);
    }//GEN-LAST:event_jBPesquisarDevedorActionPerformed

    private void jTPagamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTPagamentosMouseClicked
        int selectedRow = jTPagamentos.getSelectedRow();
        if(evt.getClickCount() == 2){
            if (selectedRow != -1) {
                EfetuarPagamento epag = new EfetuarPagamento(Integer.parseInt(jTNegociacoes.getModel().getValueAt(jTNegociacoes.getSelectedRow(), 0).toString()), cliente.getIdCliente(), devedor.getIdDevedor(), jTPagamentos.getSelectedRow());
                epag.setVisible(true);
                jTNegociacoes.clearSelection();
                modelDebitos.setRowCount(0);
                modelPagamentos.setRowCount(0);
            }
        }
    }//GEN-LAST:event_jTPagamentosMouseClicked

    @Override
    public void devedorSelecionado(Devedores devedor) {
        this.devedor = devedor;
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        jTFNomeDevedor.setText(devedor.getNomeDevedor());
        jTFCPFDevedor.setText(devedor.getCpfcnpj());
        jTFDataFinalizacao.setText(String.valueOf(outputFormat.format(devedor.getDataFinalizacaoCobranca())));
        jTFTaxaJuros.setText(String.valueOf(devedor.getTaxaJuros()));
        jTFSaldoDevedor.setText(String.valueOf(devedor.getSaldoDevedor()));
        preencheTabelaNegociacoes(cliente.getIdCliente(), devedor.getIdDevedor());
    }
    
    @Override
    public void clienteSelecionado(Clientes cliente) {
        this.cliente = cliente;
        
        jTFNomeCliente.setText(cliente.getNomeCliente());
        jTFCPFCliente.setText(cliente.getCpfcnpj());
        jTFRazaoCliente.setText(cliente.getRazao());
    }
    
    private void preencheTabelaNegociacoes(int idCliente, int idDevedor){
        NegociacaoDAO negDAO = new NegociacaoDAO();
        Negociacao negociacao = new Negociacao();
        
        ClientesDAO cdao = new ClientesDAO();
        Clientes cli = new Clientes();
        
        DevedoresDAO ddao = new DevedoresDAO();
        Devedores dev = new Devedores();
        
        PagamentosDAO pdao = new PagamentosDAO();
        Pagamentos p = new Pagamentos();

        ArrayList<Negociacao> listaNegociacoes = negDAO.buscaNegociacoesPorClienteEDevedor(idCliente, idDevedor);
        modelNegociacoes = (DefaultTableModel) jTNegociacoes.getModel();
        modelNegociacoes.setNumRows(0);
        modelDebitos.setRowCount(0);
        modelPagamentos.setRowCount(0);
        if(listaNegociacoes.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhuma negociacao encontrada, verifique o Cliente e o Devedor selecionados!");
        }else{
            for (int i = 0; i < listaNegociacoes.size(); i++) {
                modelNegociacoes.addRow(new Object[]{
                    listaNegociacoes.get(i).getIdNegociacao(),
                    cdao.buscaNomeCliente(listaNegociacoes.get(i).getIdCliente()),
                    ddao.getNomeDevedor(idDevedor),
                    listaNegociacoes.get(i).getValorTotal(),
                    negDAO.getValorPago(listaNegociacoes.get(i).getIdNegociacao()),
                    negDAO.getParcelasPagas(listaNegociacoes.get(i).getIdNegociacao())+"/"+listaNegociacoes.get(i).getnParcelas()                    
                });
            }
        }
        
        ddao.confereSaldoDevedorViaPagamentos(idCliente, idDevedor);
    }
    
    private void preencheTabelaPagamentos(int idNegociacao){
        PagamentosDAO pagDAO = new PagamentosDAO();
        
        ArrayList<Pagamentos> listaPagamentos = pagDAO.buscaPagamentosPorNegociacao(idNegociacao);
        modelPagamentos = (DefaultTableModel) jTPagamentos.getModel();
        modelPagamentos.setNumRows(0);
        if(listaPagamentos.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhum pagamento encontrado, verifique a negociação selecionadas!");
        }else{
            for (int i = 0; i < listaPagamentos.size(); i++) {
                modelPagamentos.addRow(new Object[]{
                    listaPagamentos.get(i).getIdPagamento(),
                    listaPagamentos.get(i).getValorPago(),
                    confereData(listaPagamentos.get(i).getDataPagamento().toString()),
                    converteData(listaPagamentos.get(i).getDataVencimento())                                 
                });
                jTPagamentos.setDefaultRenderer(Object.class, new CustomCellRenderer());
                repaint();
            }
        }
    }
    
    private void preencheTabelaDebitos(int idNegociacao){
        DebitosDAO dDAO = new DebitosDAO();
        Debitos debitos = new Debitos();
        
        ArrayList<Debitos> listaDebitos = dDAO.getDebitosPorNegociacao(idNegociacao);
        modelDebitos = (DefaultTableModel) jTDebitos.getModel();
        modelDebitos.setNumRows(0);
        if(listaDebitos.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhum debito encontrado, verifique a negociação selecionada!");
        }else{
            for (int i = 0; i < listaDebitos.size(); i++) {
                modelDebitos.addRow(new Object[]{
                    listaDebitos.get(i).getIdCompra(),
                    listaDebitos.get(i).getValor(),
                    listaDebitos.get(i).getDescricao(),
                    listaDebitos.get(i).getDataCompra(),
                    listaDebitos.get(i).getComprovantes().get(0).getArquivo()
                });
            }
        }
        // Configuração do renderizador personalizado para a coluna do botão
        jTDebitos.getColumn("Comprovante").setCellRenderer(new ListarNegociacoes.BotaoRenderer());

        // Configuração do editor de célula para a coluna do botão
        jTDebitos.getColumn("Comprovante").setCellEditor(new ListarNegociacoes.BotaoEditor());
    }
    
    private void abrirArquivo(String caminhoArquivo) {
        try {
            File arquivo = new File("C:\\xampp\\htdocs\\Cobranca\\php\\uploads\\"+caminhoArquivo);
            Desktop.getDesktop().open(arquivo);
        }catch(java.lang.IllegalArgumentException erro){
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado! "+erro);
        }catch (Exception e) {
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
                    String caminhoArquivo = "teste";
                    try{
                        if(jTDebitos.getValueAt(jTDebitos.getSelectedRow(), 4) != null){
                            if(!jTDebitos.getValueAt(jTDebitos.getSelectedRow(), 4).toString().isEmpty()){
                                caminhoArquivo = jTDebitos.getValueAt(jTDebitos.getSelectedRow(), 4).toString();
                                abrirArquivo(caminhoArquivo);
                            }else{
                                JOptionPane.showMessageDialog(null, "Não existe comprovante para esta compra!");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Não existe comprovante para esta compra!");
                        }
                    }catch(java.lang.NullPointerException eNull){
                        System.out.println("erro-> "+eNull);
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
            setText("Abrir comprovante");
            return this;
        }
    }
    
    private String converteData(LocalDate data){
        DateTimeFormatter formatoFinal = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        String dataFinal = data.format(formatoFinal);
       
        return dataFinal;
    }
    
    private String confereData(String data){
        DateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formatoFinal = new SimpleDateFormat("dd/MM/yyyy");
        String dataP = null;

        if(!data.equals("1900-01-01")){
            try {
                Date dataOriginal = formatoOriginal.parse(data);
                dataP = formatoFinal.format(dataOriginal);
                
            } catch (ParseException ex) {
                Logger.getLogger(ListarNegociacoes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            dataP = "";
        }
        
        return dataP;
    }
    
    // Implementação personalizada do TableCellRenderer
    static class CustomCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String valorCelulaDataPagamento = table.getValueAt(row, 2).toString();
            String valorCelulaDataVencimento = table.getValueAt(row, 3).toString();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                
            // Converter a String em LocalDate
            LocalDate localDateVencimento = LocalDate.parse(valorCelulaDataVencimento, formatter);

            if(isSelected){
                Color corFundo = table.getSelectionBackground();
                component.setBackground(corFundo);
            }else{
                if(table.getValueAt(row, 2).toString().isEmpty()){
                    LocalDate hoje = LocalDate.now();
                    if(localDateVencimento.isBefore(hoje)){
                        component.setBackground(Color.RED);
                    }else
                        component.setBackground(Color.WHITE);
                }else{
                    LocalDate localDatePagamento = LocalDate.parse(valorCelulaDataPagamento, formatter);
                    if(localDatePagamento.isAfter(localDateVencimento))
                        component.setBackground(Color.RED);
                    else if(localDatePagamento.isBefore(localDateVencimento) || (localDatePagamento.isEqual(localDateVencimento)))
                        component.setBackground(Color.GREEN);
                }
            }
            return component;
        }
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
            java.util.logging.Logger.getLogger(ListarNegociacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListarNegociacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListarNegociacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListarNegociacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListarNegociacoes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBPesquisarCliente;
    private javax.swing.JButton jBPesquisarDevedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTDebitos;
    private javax.swing.JTextField jTFCPFCliente;
    private javax.swing.JTextField jTFCPFDevedor;
    private javax.swing.JTextField jTFDataFinalizacao;
    private javax.swing.JTextField jTFNomeCliente;
    private javax.swing.JTextField jTFNomeDevedor;
    private javax.swing.JTextField jTFPesquisarCliente;
    private javax.swing.JTextField jTFPesquisarDevedor;
    private javax.swing.JTextField jTFRazaoCliente;
    private javax.swing.JTextField jTFSaldoDevedor;
    private javax.swing.JTextField jTFTaxaJuros;
    private javax.swing.JTable jTNegociacoes;
    private javax.swing.JTable jTPagamentos;
    // End of variables declaration//GEN-END:variables
}
