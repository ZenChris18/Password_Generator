import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PasswordGenerator extends JFrame {
    private JSpinner lengthSpinner;
    private JCheckBox upperCheck, numberCheck, symbolCheck;
    private JTextField outputField;

    public PasswordGenerator() {
        super("Password Generator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel options = new JPanel(new FlowLayout(FlowLayout.LEFT));
        options.add(new JLabel("Length:"));
        lengthSpinner = new JSpinner(new SpinnerNumberModel(12, 4, 32, 1));
        options.add(lengthSpinner);

        upperCheck  = new JCheckBox("Uppercase", true);
        numberCheck = new JCheckBox("Numbers",   true);
        symbolCheck = new JCheckBox("Symbols",   false);
        options.add(upperCheck);
        options.add(numberCheck);
        options.add(symbolCheck);

        JPanel output = new JPanel(new FlowLayout(FlowLayout.LEFT));
        output.add(new JLabel("Password:"));
        outputField = new JTextField(20);
        outputField.setEditable(false);
        output.add(outputField);

        JButton generateBtn = new JButton("Generate");
        generateBtn.addActionListener(e -> {
            int length = (Integer) lengthSpinner.getValue();
            String pwd = generatePassword(
                    length,
                    upperCheck.isSelected(),
                    numberCheck.isSelected(),
                    symbolCheck.isSelected()
            );
            outputField.setText(pwd);
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(generateBtn);

        // 4) Single layout + add
        setLayout(new BorderLayout(5, 5));
        add(options,     BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(output,      BorderLayout.SOUTH);
    }

    private String generatePassword(int length, boolean useUpper, boolean useNumbers, boolean useSymbols) {
        String lower   = "abcdefghijklmnopqrstuvwxyz";
        String upper   = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*()-_=+[]{};:'\",.<>/?";

        String chars = lower
                + (useUpper   ? upper   : "")
                + (useNumbers ? numbers : "")
                + (useSymbols ? symbols : "");

        Random rand = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PasswordGenerator().setVisible(true);
        });
    }
}
