import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;



public class Clicks implements ActionListener {

    

    private int count = 0;
    private JLabel label;
    private JFrame frame;
    private JPanel panel;

    public Clicks() {
        frame = new JFrame();

        JButton button = new JButton("Clicar");
        button.addActionListener(this);

        label = new JLabel("Clicks:");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 15));
        panel.setLayout(new GridLayout(0,1));
        panel.add(button);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Contador de clicks");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        Clicks clicks = new Clicks();
       
        //Utilizando o timer para fazer uma contagem regressiva
        final ScheduledExecutorService tempo = Executors.newScheduledThreadPool(1);
        final Runnable runnable = new Runnable() {
            int startCount = 5;
            

            public void run() { 
                System.out.println(startCount);
                startCount--;

                if(startCount < 0) {
                    System.out.println("Acabou o tempo!!");
                    tempo.shutdown();
                    System.exit(0);
                }
                
            }   
        }; 
        tempo.scheduleAtFixedRate(runnable, 0, 1, SECONDS); 
        
          
        

        //Aqui eu quero mostrar a quantidade de clicks finais do usuario.
        
    }


    //Contabilizando a quantidade de clicks do usuario
    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        label.setText("clicks:" + count);
    }

    public int getCount() {
        return count;
    }
}
