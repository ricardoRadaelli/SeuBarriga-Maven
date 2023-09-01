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
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author User
 */
public class GravarNegociacao extends javax.swing.JFrame {

    DefaultTableModel modelDebitos = new DefaultTableModel();
    
    int idCliente, idDevedor;
    ArrayList<Debitos> listaDebitos;
    
    Clientes cliente;
    Devedores devedor;
    Debitos debito;
    
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    
    public GravarNegociacao() {
        initComponents();
    }
    
    public GravarNegociacao(int idCliente, int idDevedor, ArrayList<Debitos> listaDebitos) {
        initComponents();
        
        this.idCliente = idCliente;
        this.idDevedor = idDevedor;
        this.listaDebitos = listaDebitos;
        
        preencheCampos();
    }
    
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
    
    private void preencheCampos(){
        ClientesDAO cdao = new ClientesDAO();
        DevedoresDAO devdao = new DevedoresDAO();
        DebitosDAO debdao = new DebitosDAO();
        
        cliente = cdao.buscaClientesPorId(idCliente);
        devedor = devdao.buscaIncompletoDevedorPorId(idDevedor);
        
        jTCliente.setText(cliente.getNomeCliente());
        jTDevedor.setText(devedor.getNomeDevedor());
        jTValorNegociacao.setText(somaValorNegociacao(0.055));
        
        preencheTabelaDebitos(listaDebitos);
    }
    
    private String somaValorNegociacao(double juros){
        double valor = 0.0, taxa=0.0;
        int mesesAtraso=0;
        ArrayList<Double> valores = new ArrayList<Double>();
        
        DevedoresDAO ddao = new DevedoresDAO();
        Devedores d = ddao.buscaDevedorPorCPFCNPJ(devedor.getCpfcnpj(), cliente.getIdCliente());
        
        for (int i = 0; i < listaDebitos.size(); i++) {
            if(juros == 0.055){
                taxa = d.getTaxaJuros()/100;
             
                LocalDate hoje = LocalDate.now();
                Period periodo = Period.between(listaDebitos.get(i).getDataCompra(), hoje);
                mesesAtraso = periodo.getMonths();
                
                jTFJurosNeg.setText(String.valueOf(listaDebitos.get(i).getValor()*taxa*mesesAtraso));
                
                valores.add((listaDebitos.get(i).getValor()*taxa*mesesAtraso)+(listaDebitos.get(i).getValor()));
            }else{
                valores.add(juros+(listaDebitos.get(i).getValor()));
            }
        }
        
        for (int i = 0; i < valores.size(); i++) {
            valor += valores.get(i);
        }
        
        return String.valueOf(valor);
    }
    
    private void preencheTabelaDebitos(ArrayList<Debitos> listaDebitos){
        DebitosDAO dDAO = new DebitosDAO();
        Debitos debitos = new Debitos();
        
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
        jTDebitos.getColumn("Comprovante").setCellRenderer(new GravarNegociacao.BotaoRenderer());

        // Configuração do editor de célula para a coluna do botão
        jTDebitos.getColumn("Comprovante").setCellEditor(new GravarNegociacao.BotaoEditor());
        jTDebitos.setRowSelectionInterval(0, 0);
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
        jLabel4 = new javax.swing.JLabel();
        jTDevedor = new javax.swing.JTextField();
        jTCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jCBPago = new javax.swing.JCheckBox();
        jTValorNegociacao = new javax.swing.JTextField();
        jTNParcelas = new javax.swing.JTextField();
        jBGravar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jCBForma = new javax.swing.JComboBox<>();
        jFTFDataNegociacao = new javax.swing.JFormattedTextField();
        jFTFDataPrimeiraParcela = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jTFJurosNeg = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTDebitos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Negociação"));

        jLabel1.setText("Devedor:");

        jLabel2.setText("Cliente:");

        jLabel3.setText("Data negociação:");

        jLabel4.setText("Data primeira parcela:");

        jTDevedor.setEditable(false);

        jTCliente.setEditable(false);

        jLabel5.setText("Valor da negociação:");

        jLabel6.setText("Número de parcelas:");

        jCBPago.setText("Negociação Paga");
        jCBPago.setEnabled(false);

        jTValorNegociacao.setHorizontalAlignment(JTextField.RIGHT);
        jTValorNegociacao.setText("0.00");

        jTValorNegociacao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!((c >= '0' && c <= '9') || c == '.' || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    evt.consume();
                }
            }
        });
        jTValorNegociacao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTValorNegociacaoFocusLost(evt);
            }
        });

        jBGravar.setText("Gravar");
        jBGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGravarActionPerformed(evt);
            }
        });

        jLabel7.setText("Forma de pagamento:");

        jCBForma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione...", "Dinheiro", "Pix/Transferência", "Boleto", "Cartão Crédito" }));

        try {
            jFTFDataNegociacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jFTFDataPrimeiraParcela.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel8.setText("Juros:");

        jTFJurosNeg.setHorizontalAlignment(JTextField.RIGHT);
        jTFJurosNeg.setText("0.00");

        jTFJurosNeg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!((c >= '0' && c <= '9') || c == '.' || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    evt.consume();
                }
            }
        });
        jTFJurosNeg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTFJurosNegFocusLost(evt);
            }
        });

        jLabel10.setText("R$");

        jLabel11.setText("R$");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTDevedor, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jCBPago))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3))
                                .addGap(20, 20, 20)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jFTFDataNegociacao, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTValorNegociacao, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(134, 134, 134)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10)
                                        .addGap(6, 6, 6))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jBGravar)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(32, 32, 32)
                                        .addComponent(jCBForma, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addGap(22, 22, 22)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTNParcelas, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jFTFDataPrimeiraParcela, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFJurosNeg, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(43, 43, 43)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTDevedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBPago))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jFTFDataNegociacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTValorNegociacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jFTFDataPrimeiraParcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTFJurosNeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jCBForma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTNParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jBGravar)
                .addGap(22, 22, 22))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTNParcelas, jTValorNegociacao});

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Debitos"));

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
        TableColumnModel columnModel = jTDebitos.getColumnModel();

        // Escondendo a coluna de índice 1 (Coluna 2)
        columnModel.removeColumn(columnModel.getColumn(0));
        jScrollPane1.setViewportView(jTDebitos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGravarActionPerformed
        NegociacaoDAO ndao = new NegociacaoDAO();
        Negociacao neg = new Negociacao();
        
        neg.setDataNegociacao(converteDataParaBanco(jFTFDataNegociacao.getText()));
        neg.setDataPrimeiraParcela(converteDataParaBanco(jFTFDataPrimeiraParcela.getText()));
        neg.setFormaPagamento(jCBForma.getSelectedItem().toString());
        neg.setIdCliente(idCliente);
        neg.setIdDevedor(idDevedor);
        neg.setValorTotal(Double.parseDouble(jTValorNegociacao.getText().replace(",", ".")));
        neg.setnParcelas(Integer.parseInt(jTNParcelas.getText()));
        neg.setListaDebitos(listaDebitos);
        
        ndao.insereNegociacao(neg);
        
        dispose();        
    }//GEN-LAST:event_jBGravarActionPerformed

    private void jTValorNegociacaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTValorNegociacaoFocusLost
        jTValorNegociacao.setText(decimalFormat.format(Double.parseDouble(jTValorNegociacao.getText().replaceAll(",", "."))));
    }//GEN-LAST:event_jTValorNegociacaoFocusLost

    private void jTFJurosNegFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFJurosNegFocusLost
        jTFJurosNeg.setText(decimalFormat.format(Double.parseDouble(jTFJurosNeg.getText().replaceAll(",", "."))));
        
        jTValorNegociacao.setText(somaValorNegociacao(Double.parseDouble(jTFJurosNeg.getText().replace(",", "."))));
    }//GEN-LAST:event_jTFJurosNegFocusLost

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
            java.util.logging.Logger.getLogger(GravarNegociacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GravarNegociacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GravarNegociacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GravarNegociacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GravarNegociacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBGravar;
    private javax.swing.JComboBox<String> jCBForma;
    private javax.swing.JCheckBox jCBPago;
    private javax.swing.JFormattedTextField jFTFDataNegociacao;
    private javax.swing.JFormattedTextField jFTFDataPrimeiraParcela;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTCliente;
    private javax.swing.JTable jTDebitos;
    private javax.swing.JTextField jTDevedor;
    private javax.swing.JTextField jTFJurosNeg;
    private javax.swing.JTextField jTNParcelas;
    private javax.swing.JTextField jTValorNegociacao;
    // End of variables declaration//GEN-END:variables
}
