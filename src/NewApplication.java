import javax.swing.*;
import java.awt.*;

public class NewApplication extends JFrame {

    JLabel applicationTitle;
    JPanel containerPane, fieldElements;
    JButton saveButton;

    NewApplication() {

        applicationTitle = new JLabel("Offline Data Operations Platform");
        applicationTitle.setHorizontalAlignment(SwingConstants.CENTER);

        containerPane = new JPanel(new BorderLayout());
        fieldElements =new JPanel();

        saveButton = new JButton("Save");

        addElementsToContainerPane();

        showApplication();
    }

    void addElementsToContainerPane() {

        containerPane.add(applicationTitle, BorderLayout.PAGE_START);
        containerPane.add(fieldElements, BorderLayout.CENTER);
        containerPane.add(saveButton, BorderLayout.PAGE_END);

    }

    void showApplication() {

        add(containerPane);
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        new NewApplication();

    }

}
