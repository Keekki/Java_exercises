
package Java_exercises.Snake;

import mySnake.Arena;
import java.awt.EventQueue;
import javax.swing.JFrame;

// main activity
public class Snake extends JFrame {

    public Snake() {

        initUI();

    }

    private void initUI() {

        add(new Arena());

        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            JFrame ex = new Snake();
            ex.setVisible(true);
        });
    }
}
