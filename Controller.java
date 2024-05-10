import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        model.setView(view);
        initializeButtons();
        updateText();
    }

    private void initializeButtons() {
        JButton[][] buttons = view.getButtons();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = buttons[row][col];
                final int r = row;
                final int c = col;
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!model.isGameOver() && button.getText().isEmpty()) {
                            button.setText(model.getCurrentPlayer());
                            model.updateBoard(r, c);
                            model.checkWinner();
                            if (!model.isGameOver()) {
                                model.switchPlayer();
                                updateText();
                            }
                        }
                    }
                });
            }
        }
    }

    private void updateText() {
        view.setText(model.getCurrentPlayer() + "'s turn.");
    }


    private void setWinner(String player) {
        model.setWinner(player);
    }

    private void setTie() {
        model.setTie();
    }
}

