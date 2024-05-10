import javax.swing.*;
import java.awt.*;

public class View {
    private JFrame frame;
    private JLabel textLabel;
    private JPanel textPanel;
    private JPanel boardPanel;
    private JButton[][] buttons;


    public View() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setSize(600, 650);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textLabel = new JLabel("Tic-Tac-Toe", SwingConstants.CENTER);
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setOpaque(true);

        textPanel = new JPanel(new BorderLayout());
        textPanel.add(textLabel, BorderLayout.CENTER);

        boardPanel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[3][3];

        initializeButtons();

        frame.add(textPanel, BorderLayout.NORTH);
        frame.add(boardPanel);
        frame.setVisible(true);
    }


    private void initializeButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 120));
                boardPanel.add(buttons[row][col]);
            }
        }
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public void setText(String text) {
        textLabel.setText(text);
    }
}
