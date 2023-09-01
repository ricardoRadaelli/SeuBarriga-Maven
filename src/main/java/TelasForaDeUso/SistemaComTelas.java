/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TelasForaDeUso;

/**
 *
 * @author User
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaComTelas extends JFrame {
    private JPanel painelCentral;
    private CardLayout cardLayout;

    public SistemaComTelas() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sistema com Telas");
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Crie o painel central e defina o CardLayout
        painelCentral = new JPanel();
        cardLayout = new CardLayout();
        painelCentral.setLayout(cardLayout);

        // Adicione as telas (JPanel) ao painel central com identificadores
        painelCentral.add(new Tela1(), "tela1");
        painelCentral.add(new Telas.CadastroDevedor(), "tela2");
        painelCentral.add(new Telas.CadastroCliente(), "tela3");

        // Crie um menu com opções para mostrar as telas
        JMenuBar menuBar = new JMenuBar();
        JMenu menuTelas = new JMenu("Telas");
        JMenuItem menuItemTela1 = new JMenuItem("Tela 1");
        JMenuItem menuItemTela2 = new JMenuItem("Tela 2");
        JMenuItem menuItemTela3 = new JMenuItem("Tela 3");

        menuItemTela1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(painelCentral, "tela1");
            }
        });

        menuItemTela2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(painelCentral, "tela2");
            }
        });

        menuItemTela3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(painelCentral, "tela3");
            }
        });

        menuTelas.add(menuItemTela1);
        menuTelas.add(menuItemTela2);
        menuTelas.add(menuItemTela3);
        menuBar.add(menuTelas);

        setJMenuBar(menuBar);
        add(painelCentral);

        // Inicialmente, exiba a primeira tela (opcional, você pode definir a tela inicial como desejar)
        cardLayout.show(painelCentral, "tela1");
    }

    private static class Tela1 extends JPanel {
        public Tela1() {
            add(new JLabel("Tela 1"));
        }
    }

    private static class Tela2 extends JPanel {
        public Tela2() {
            add(new JLabel("Tela 2"));
        }
    }

    private static class Tela3 extends JPanel {
        public Tela3() {
            add(new JLabel("Tela 3"));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemaComTelas sistema = new SistemaComTelas();
            sistema.setVisible(true);
        });
    }
}