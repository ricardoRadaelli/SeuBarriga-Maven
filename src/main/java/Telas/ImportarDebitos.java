/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Telas;

import Atributos.Clientes;
import Atributos.Comprovantes;
import Atributos.Devedores;
import Atributos.Debitos;
import Banco.ClientesDAO;
import Banco.ComprovantesDAO;
import Banco.DevedoresDAO;
import Banco.DebitosDAO;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author User
 */
public class ImportarDebitos extends javax.swing.JFrame {

    /**
     * Creates new form ImportarDebitos
     */
    public ImportarDebitos() {
        initComponents();
    }

    String caminhoXLS;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLarquivo = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("Carregar XLS de importação");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLarquivo.setText("Selecione um arquivo para carregar...");

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLarquivo, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLarquivo)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(102, 102, 102))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscaArquivo();
        jLarquivo.setText(caminhoXLS);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        lerXLS();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    
    private void buscaArquivo(){
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            caminhoXLS = selectedFile.getAbsolutePath();
        }
    }
    
    private void lerXLS(){
        String filePath = caminhoXLS;
        boolean flagNome=false, flagCPFCliente=false, flagCPFDevedor=false;
        
        ClientesDAO cliDAO = new ClientesDAO();
        DevedoresDAO creDAO = new DevedoresDAO();
        DebitosDAO dDAO = new DebitosDAO();
        ComprovantesDAO compDAO = new ComprovantesDAO();
        
        ArrayList<Debitos> listaDebitos = new ArrayList<Debitos>();
        
        int idCliente = 0;
        
        Comprovantes comp = new Comprovantes();
        Debitos debito = new Debitos();
        Clientes cliente = new Clientes();
        Devedores devedor = new Devedores();
        
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            BufferedInputStream bis = new BufferedInputStream(fileInputStream);
            
            Workbook workbook;
            if (filePath.endsWith(".xls")) {
                workbook = new HSSFWorkbook(bis); // Para arquivos XLS
            } else if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(bis); // Para arquivos XLSX
            } else {
                throw new IllegalArgumentException("Formato de arquivo inválido. Suportado apenas XLS e XLSX.");
            }

            Sheet sheet = workbook.getSheetAt(0); // Obter a primeira planilha
            
            Row linha = sheet.getRow(1);
            Cell celula = linha.getCell(2);
            if (celula.getCellType() == CellType.STRING) {
                cliente.setNomeCliente(celula.getStringCellValue());
            } else if(celula.getCellType() == CellType.NUMERIC) {
                cliente.setNomeCliente(String.valueOf(celula.getNumericCellValue()));
            }
            
            linha = sheet.getRow(2);
            celula = linha.getCell(2);
            
            if (celula.getCellType() == CellType.STRING) {
                cliente.setCpfcnpj(celula.getStringCellValue());
                cliente.setIdCliente(cliDAO.buscaIdClientePorCPF(cliente.getCpfcnpj()));
                idCliente = cliente.getIdCliente();
                
            } else if(celula.getCellType() == CellType.NUMERIC) {
                cliente.setCpfcnpj(String.valueOf(celula.getNumericCellValue()));
                cliente.setIdCliente(cliDAO.buscaIdClientePorCPF(cliente.getCpfcnpj()));
                idCliente = cliente.getIdCliente();
            }
            
            
            for (int i = 5; i < 49; i++) {
                linha = sheet.getRow(i);
                for (int j = 0; j < 5; j++) {
                    debito.setIdCliente(idCliente);
                    celula = linha.getCell(j);
                    CellType cellType = celula.getCellType();
                    if (j==0) {
                        if(cellType != CellType.BLANK){
                            if (celula.getCellType() == CellType.STRING) {
                                devedor = creDAO.buscaDevedorPorCPFCNPJ(celula.getStringCellValue(), cliente.getIdCliente());
                                debito.setIdDevedor(devedor.getIdDevedor());
                            } else if(celula.getCellType() == CellType.NUMERIC) {
                                devedor = creDAO.buscaDevedorPorCPFCNPJ(String.valueOf(celula.getNumericCellValue()), cliente.getIdCliente());
                                debito.setIdDevedor(devedor.getIdDevedor());
                            }
                        }
                    }else if(j==1){
                        if(cellType != CellType.BLANK){
                            debito.setDataCompra(converteDataParaBanco(celula.getDateCellValue().toString()));
                            debito.setDataInclusao(LocalDate.now());
                        }
                    }else if(j==2){
                        if(cellType != CellType.BLANK){
                            if (celula.getCellType() == CellType.STRING) {
                                //JOptionPane.showMessageDialog(null, "Letra encontrada no campo valor", "Erro na importação", JOptionPane.WARNING_MESSAGE);
                            } else if(celula.getCellType() == CellType.NUMERIC) {
                                debito.setValor(celula.getNumericCellValue());
                            }
                        }
                    }else if(j==3){
                        if(cellType != CellType.BLANK){
                            if (celula.getCellType() == CellType.STRING) {
                                debito.setDescricao(celula.getStringCellValue());
                            } else if(celula.getCellType() == CellType.NUMERIC) {
                                debito.setDescricao(String.valueOf(celula.getNumericCellValue()));
                            }
                        }
                    }else if(j==4){
                        comp.setDataInclusao(LocalDate.now());
                        comp.setIdCliente(cliente.getIdCliente());
                        comp.setIdDevedor(devedor.getIdDevedor());

                        if(cellType != CellType.BLANK){
                            if (celula.getCellType() == CellType.STRING) {
                                comp.setArquivo(celula.getStringCellValue());
                            } else if(celula.getCellType() == CellType.NUMERIC) {
                                comp.setArquivo(String.valueOf(celula.getNumericCellValue()));
                            }
                        }else{
                            comp.setArquivo("SemComprovante");
                        }



                        if(cliente.getIdCliente()== 0){
                            JOptionPane.showMessageDialog(null, "Cliente não encontrado, abortando importação!", "Erro", JOptionPane.WARNING_MESSAGE);
                        }else if(devedor.getIdDevedor() == 0){
                            JOptionPane.showMessageDialog(null, "Devedor não encontrado, linha "+(i+1)+" não importada!", "Erro", JOptionPane.WARNING_MESSAGE);
                        }else {
                            //Tem que colocar a comparação para saber se a data é nula ou não...
                            if(debito.getValor() == 0 && debito.getGetCompraNull() && debito.getIdCliente()==0 && debito.getDescricao().equals("")){
                                //Não faz nada
                            }else if(debito.getValor() == 0 || debito.getDataCompra().equals("") || debito.getIdCliente()==0 || debito.getDescricao().equals("")){
                                JOptionPane.showMessageDialog(null, "Campo obrigatório está em branco, linha "+(i+1)+" não importada!", "Erro", JOptionPane.WARNING_MESSAGE);
                            }else{
                                dDAO.insereDebitos(debito, comp);
                                debito = new Debitos();
                                comp = new Comprovantes();
                            }
                        }
                    }          
                }
            }
            System.out.println("Leitura do arquivo terminou");

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            java.util.logging.Logger.getLogger(ImportarDebitos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImportarDebitos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImportarDebitos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImportarDebitos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImportarDebitos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLarquivo;
    // End of variables declaration//GEN-END:variables
}
