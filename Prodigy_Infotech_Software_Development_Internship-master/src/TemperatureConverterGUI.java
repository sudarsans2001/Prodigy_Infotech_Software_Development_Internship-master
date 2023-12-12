import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterGUI extends JFrame implements ActionListener {

    private JTextField temperatureInput;
    private JButton convertButton;
    private JTextArea resultArea;

    public TemperatureConverterGUI() {
        setTitle("Temperature Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter temperature value:");
        temperatureInput = new JTextField(10);
        convertButton = new JButton("Convert");
        resultArea = new JTextArea(6, 30);
        resultArea.setEditable(false);

        convertButton.addActionListener(this);

        panel.add(label);
        panel.add(temperatureInput);
        panel.add(convertButton);
        panel.add(resultArea);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            double temperature;
            char unit;

            try {
                temperature = Double.parseDouble(temperatureInput.getText());
            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid temperature value.");
                return;
            }

            unit = JOptionPane.showInputDialog(
                    this,
                    "Select unit (C, F, K):"
            ).charAt(0);

            switch (unit) {
                case 'C':
                case 'c':
                    double fahrenheit = (temperature * 9/5) + 32;
                    double kelvin = temperature + 273.15;
                    resultArea.setText("Fahrenheit: " + fahrenheit + " °F\nKelvin: " + kelvin + " K");
                    break;

                case 'F':
                case 'f':
                    double celsius = (temperature - 32) * 5/9;
                    kelvin = (temperature + 459.67) * 5/9;
                    resultArea.setText("Celsius: " + celsius + " °C\nKelvin: " + kelvin + " K");
                    break;

                case 'K':
                case 'k':
                    celsius = temperature - 273.15;
                    fahrenheit = (temperature * 9/5) - 459.67;
                    resultArea.setText("Celsius: " + celsius + " °C\nFahrenheit: " + fahrenheit + " °F");
                    break;

                default:
                    resultArea.setText("Invalid unit entered.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TemperatureConverterGUI converter = new TemperatureConverterGUI();
            converter.setVisible(true);
        });
    }
}
