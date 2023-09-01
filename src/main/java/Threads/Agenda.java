/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Threads;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author User
 */
public class Agenda {
    
    private static final int INTERVALO_MINUTOS = 1; // Intervalo de minutos para verificar os lembretes
    private static final String DATA_HORA_LEMBRETE = "2023-07-25 13:49"; // Data e hora do lembrete
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        agendarLembrete();
    }

    private static void agendarLembrete() {
        Timer timer = new Timer();

        // Defina a tarefa para verificar os lembretes
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                LocalDateTime dataHoraAtual = LocalDateTime.now();
                LocalDateTime dataHoraLembrete = LocalDateTime.parse(DATA_HORA_LEMBRETE, FORMATTER);

                if (dataHoraAtual.isAfter(dataHoraLembrete)) {
                    // Exiba o lembrete
                    exibirLembrete();

                    // Cancelar a tarefa após exibir o lembrete
                    timer.cancel();
                }
            }
        };

        // Defina o período de verificação dos lembretes
        long intervaloMilissegundos = INTERVALO_MINUTOS * 60 * 1000;
        timer.scheduleAtFixedRate(tarefa, 0, intervaloMilissegundos);
    }

    private static void exibirLembrete() {
        JFrame frame = new JFrame("Lembrete!");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);

        JLabel label = new JLabel("Lembrete: É hora do evento!");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(label);

        // Tarefa para fazer a tela piscar
        TimerTask tarefaPiscar = new TimerTask() {
            private boolean piscar = false;

            @Override
            public void run() {
                piscar = !piscar;
                frame.setVisible(piscar);
            }
        };

        // Defina o período de piscagem (500 ms)
        Timer timerPiscar = new Timer();
        timerPiscar.scheduleAtFixedRate(tarefaPiscar, 0, 500);

        // Tarefa para emitir o som
        TimerTask tarefaSom = new TimerTask() {
            @Override
            public void run() {
                Toolkit.getDefaultToolkit().beep();
            }
        };

        // Defina o atraso para emitir o som (2 segundos)
        Timer timerSom = new Timer();
        timerSom.schedule(tarefaSom, 2000);

        frame.setVisible(true);
    }

}
