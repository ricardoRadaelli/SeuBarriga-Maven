/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Telas;

import Atributos.Clientes;
import Atributos.Debitos;
import Atributos.Devedores;
import Atributos.Negociacao;
import Banco.ClientesDAO;
import Banco.DebitosDAO;
import Banco.DevedoresDAO;
import Banco.NegociacaoDAO;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author User
 */
public class CadastrarNegociacao extends javax.swing.JFrame implements PesquisaDevedores.InterfaceRepassaDadosDevedores {
    
    Devedores devedor;
    
    DefaultTableModel modelClientes = new DefaultTableModel();
    DefaultTableModel modelDebitos = new DefaultTableModel();
    TableColumnModel cmDebitos;
    
    MaskFormatter cpfMask;
    MaskFormatter cnpjMask;
    
    public CadastrarNegociacao() {
        initComponents();
        
        try {
            cpfMask = new MaskFormatter("###.###.###-##");
            cnpjMask = new MaskFormatter("##.###.###/####-##");
        } catch (ParseException ex) {
            Logger.getLogger(CadastroDevedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public CadastrarNegociacao(Devedores dev, Clientes cli) {
        initComponents();
        devedor = dev;
        
        jTFNomeDevedor.setText(devedor.getNomeDevedor());
        jFTFCPFDevedor.setText(devedor.getCpfcnpj());
        preencheTabelaClientes(devedor.getIdDevedor());
        selecionaCliente(cli.getIdCliente());
        
        try {
            cpfMask = new MaskFormatter("###.###.###-##");
            cnpjMask = new MaskFormatter("##.###.###/####-##");
        } catch (ParseException ex) {
            Logger.getLogger(CadastroDevedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jTFPesquisarDevedor = new javax.swing.JTextField();
        jBPesquisarDevedor = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTFNomeDevedor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTFSaldoDevedor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTFTaxaJuros = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jFTFDataFinalizacao = new javax.swing.JFormattedTextField();
        jFTFCPFDevedor = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTClientes = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTDebitos = new javax.swing.JTable();
        jBNegociacao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Devedor"));

        jBPesquisarDevedor.setText("Pesquisar");
        jBPesquisarDevedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPesquisarDevedorActionPerformed(evt);
            }
        });

        jLabel2.setText("Pesquisar devedor:");

        jLabel6.setText("Nome:");

        jTFNomeDevedor.setEditable(false);

        jLabel7.setText("CPF/CNPJ:");

        jLabel8.setText("Finalização de cobrança:");

        jLabel9.setText("Saldo devedor:");

        jLabel10.setText("R$");

        jTFSaldoDevedor.setEditable(false);

        jLabel11.setText("Taxa de juros:");

        jTFTaxaJuros.setEditable(false);

        jLabel12.setText("%");

        jFTFDataFinalizacao.setEditable(false);
        try {
            jFTFDataFinalizacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jFTFCPFDevedor.setEditable(false);
        jFTFCPFDevedor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFTFCPFDevedorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFTFCPFDevedorFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jTFNomeDevedor, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFTFDataFinalizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFTaxaJuros, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addGap(76, 76, 76)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFSaldoDevedor))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFTFCPFDevedor))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFPesquisarDevedor, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)))
                .addGap(17, 17, 17)
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
                    .addComponent(jTFNomeDevedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTFCPFDevedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jTFSaldoDevedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jTFTaxaJuros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jFTFDataFinalizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de clientes"));

        jTClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Cliente", "Nome", "CPF/CNPJ", "Valor devido"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableColumnModel columnModel = jTClientes.getColumnModel();

        // Escondendo a coluna de índice 1 (Coluna 2)
        columnModel.removeColumn(columnModel.getColumn(0));

        jTClientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (jTClientes.getSelectedRow() != -1) {
                        DevedoresDAO ddao = new DevedoresDAO();
                        devedor = ddao.buscaDevedorPorCPFCNPJ(devedor.getCpfcnpj(), Integer.parseInt(jTClientes.getModel().getValueAt(jTClientes.getSelectedRow(), 0).toString()));

                        jFTFDataFinalizacao.setText(converteDataParaApresentacao(devedor.getDataFinalizacaoCobranca()));
                        jTFTaxaJuros.setText(String.valueOf(devedor.getTaxaJuros()));
                        jTFSaldoDevedor.setText(String.valueOf(devedor.getSaldoDevedor()));
                        preencheTabelaDebitos(Integer.parseInt(jTClientes.getModel().getValueAt(jTClientes.getSelectedRow(), 0).toString()));
                    }
                }
            }
        });
        jScrollPane1.setViewportView(jTClientes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de débitos"));

        jTDebitos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Debito", "Valor", "Data da compra", "Descrição", "Comprovante"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cmDebitos = jTDebitos.getColumnModel();

        // Escondendo a coluna de índice 1 (Coluna 2)
        cmDebitos.removeColumn(cmDebitos.getColumn(0));
        jScrollPane2.setViewportView(jTDebitos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jBNegociacao.setText("Criar nova negociação");
        jBNegociacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBNegociacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBNegociacao)
                .addGap(369, 369, 369))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBNegociacao)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBPesquisarDevedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPesquisarDevedorActionPerformed
        PesquisaDevedores pesquisa = new PesquisaDevedores(jTFPesquisarDevedor.getText());
        pesquisa.setListenerDevedor(this);
        pesquisa.setVisible(true);
    }//GEN-LAST:event_jBPesquisarDevedorActionPerformed

    private void jBNegociacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNegociacaoActionPerformed
        DevedoresDAO ddao = new DevedoresDAO();
        ClientesDAO cdao = new ClientesDAO();
        DebitosDAO debdao = new DebitosDAO();
        
        int idDevedor = devedor.getIdDevedor();     //ddao.buscaIdDevedor(jTFCPFDevedor.getText(), (Integer.parseInt(jTClientes.getModel().getValueAt(jTClientes.getSelectedRow(), 0).toString())));
        
        int debitos[] = jTDebitos.getSelectedRows(); 
        ArrayList<Debitos> debitosSelecionados = new ArrayList<Debitos>();
        for (int i = 0; i < debitos.length; i++) {
            Debitos deb =  debdao.buscaDebitosPorIdCompra((Integer)(jTDebitos.getModel().getValueAt(debitos[i], 0)));
            debitosSelecionados.add(deb);
        }
        
        int idCliente = (Integer) jTClientes.getModel().getValueAt(jTClientes.getSelectedRow(), 0);
        
        GravarNegociacao tela = new GravarNegociacao(idCliente, idDevedor, debitosSelecionados);
        tela.setVisible(true);
        dispose();
    }//GEN-LAST:event_jBNegociacaoActionPerformed

    private void jFTFCPFDevedorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTFCPFDevedorFocusGained
        jFTFCPFDevedor.setFormatterFactory(new DefaultFormatterFactory());
    }//GEN-LAST:event_jFTFCPFDevedorFocusGained

    private void jFTFCPFDevedorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTFCPFDevedorFocusLost
        String texto = jFTFCPFDevedor.getText();
            if(jFTFCPFDevedor.getText().length() == 11){
                jFTFCPFDevedor.setFormatterFactory(new DefaultFormatterFactory(cpfMask));
                cpfMask.install(jFTFCPFDevedor);
                jFTFCPFDevedor.setText(texto);
            }else if(jFTFCPFDevedor.getText().length() == 14){
                jFTFCPFDevedor.setFormatterFactory(new DefaultFormatterFactory(cnpjMask));
                cnpjMask.install(jFTFCPFDevedor);
                jFTFCPFDevedor.setText(texto);
            }else
                jFTFCPFDevedor.setText("");
    }//GEN-LAST:event_jFTFCPFDevedorFocusLost

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
    
    @Override
    public void devedorSelecionado(Devedores devedor) {
        this.devedor = devedor;
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        jTFNomeDevedor.setText(devedor.getNomeDevedor());
        jFTFCPFDevedor.setText(devedor.getCpfcnpj());
        preencheTabelaClientes(devedor.getIdDevedor());
    }
    
    private void preencheTabelaClientes(int idDevedor){   //Deve receber uma lista com o id e nome do cliente e o valor devido a este cliente pelo devedor...
        NegociacaoDAO negDAO = new NegociacaoDAO();
        Negociacao negociacao = new Negociacao();
        
        ClientesDAO cdao = new ClientesDAO();
        Clientes cli = new Clientes();
        
        DevedoresDAO ddao = new DevedoresDAO();
        Devedores dev = new Devedores();

        modelClientes = (DefaultTableModel) jTClientes.getModel();
        modelClientes.setNumRows(0);
        modelDebitos.setRowCount(0);
        
        ArrayList<Devedores> lista = ddao.getClientesDoDevedor(idDevedor);
        
        if(lista.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhum cliente encontrado para este devedor, verifique o cadastro do Devedor!");
        }else{
            for (int i = 0; i < lista.size(); i++) {
                modelClientes.addRow(new Object[]{
                    lista.get(i).getIdCliente(),
                    cdao.buscaNomeCliente(lista.get(i).getIdCliente()),
                    cdao.buscaCPFCliente(lista.get(i).getIdCliente()),
                    lista.get(i).getSaldoDevedor(),           
                });
            }
            //jTClientes.setRowSelectionInterval(0, 0);
            //preencheTabelaDebitos(Integer.parseInt(jTClientes.getModel().getValueAt(jTClientes.getSelectedRow(), 0).toString()));
        }
    }
    
    private void preencheTabelaDebitos(int idCliente){
        DebitosDAO dDAO = new DebitosDAO();
        Debitos debitos = new Debitos();
        
        ArrayList<Debitos> listaDebitos = dDAO.getDebitosSemNegociacao(idCliente, devedor.getIdDevedor());
        modelDebitos = (DefaultTableModel) jTDebitos.getModel();
        modelDebitos.setNumRows(0);
        if(listaDebitos.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhum debito encontrado, verifique a negociação selecionada!");
        }else{
            for (int i = 0; i < listaDebitos.size(); i++) {
                modelDebitos.addRow(new Object[]{
                    listaDebitos.get(i).getIdCompra(),
                    listaDebitos.get(i).getValor(),
                    converteDataParaApresentacao(listaDebitos.get(i).getDataCompra()),
                    listaDebitos.get(i).getDescricao(),
                    listaDebitos.get(i).getComprovantes().get(0).getArquivo()
                });
            }
        }
        // Configuração do renderizador personalizado para a coluna do botão
        jTDebitos.getColumn("Comprovante").setCellRenderer(new CadastrarNegociacao.BotaoRenderer());

        // Configuração do editor de célula para a coluna do botão
        jTDebitos.getColumn("Comprovante").setCellEditor(new CadastrarNegociacao.BotaoEditor());
        //jTDebitos.setRowSelectionInterval(0, 0);
    }
            
    private void selecionaCliente(int idCliente){ //Quando vem do cadastro de devedores, já deve bloquear a seleção de outro cliente e deixar este selecionado...
        for (int i = 0; i < jTClientes.getRowCount(); i++) {
            if(Integer.parseInt(jTClientes.getModel().getValueAt(i, 0).toString()) == idCliente){
                jTClientes.setRowSelectionInterval(i, i);
            }
        }
        jTClientes.setEnabled(false);
        jTClientes.setRowSelectionAllowed(false);
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
        Object valorCelula;
        
        public BotaoEditor() {
            button = new JButton();
            button.setOpaque(true);

            // Adiciona um ActionListener ao botão
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String caminhoArquivo = "teste";
                    try{
                        valorCelula = jTDebitos.getValueAt(jTDebitos.getSelectedRow(), 3);
                        if(jTDebitos.getValueAt(jTDebitos.getSelectedRow(), 3) != null){
                            if(!jTDebitos.getValueAt(jTDebitos.getSelectedRow(), 3).toString().isEmpty()){
                                caminhoArquivo = jTDebitos.getValueAt(jTDebitos.getSelectedRow(), 3).toString();
                                abrirArquivo(caminhoArquivo);
                            }else{
                                JOptionPane.showMessageDialog(null, "Não existe comprovante para esta compra!");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Não existe comprovante para esta compra!!!");
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
            return valorCelula;
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
            java.util.logging.Logger.getLogger(CadastrarNegociacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarNegociacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarNegociacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarNegociacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastrarNegociacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBNegociacao;
    private javax.swing.JButton jBPesquisarDevedor;
    private javax.swing.JFormattedTextField jFTFCPFDevedor;
    private javax.swing.JFormattedTextField jFTFDataFinalizacao;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTClientes;
    private javax.swing.JTable jTDebitos;
    private javax.swing.JTextField jTFNomeDevedor;
    private javax.swing.JTextField jTFPesquisarDevedor;
    private javax.swing.JTextField jTFSaldoDevedor;
    private javax.swing.JTextField jTFTaxaJuros;
    // End of variables declaration//GEN-END:variables

    
}
