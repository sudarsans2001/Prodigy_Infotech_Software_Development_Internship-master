import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGameGUI extends JFrame implements ActionListener {

    private int secretNumber;
    private int attempts;
    private JTextField guessInput;
    private JTextArea resultArea;

    public GuessingGameGUI() {
        setTitle("Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initGame();
        initUI();
    }

    private void initGame() {
        Random rand = new Random();
        secretNumber = rand.nextInt(5) + 1;
        attempts = 0;
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter your guess:");
        guessInput = new JTextField(10);
        JButton guessButton = new JButton("Guess");
        resultArea = new JTextArea(6, 30);
        resultArea.setEditable(false);

        guessButton.addActionListener(this);

        panel.add(label);
        panel.add(guessInput);
        panel.add(guessButton);
        panel.add(resultArea);

        getContentPane().setBackground(new Color(240, 240, 240));

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            int guess;
            try {
                guess = Integer.parseInt(guessInput.getText());
            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid input.");
                return;
            }

            attempts++;

            if (guess < secretNumber) {
                resultArea.setText("Too low! Try again.");
            } else if (guess > secretNumber) {
                resultArea.setText("Too high! Try again.");
            } else {
                resultArea.setText("Congratulations! You guessed the number " + secretNumber +
                        " in " + attempts + " attempts.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GuessingGameGUI game = new GuessingGameGUI();
            game.setVisible(true);
        });
    }
}