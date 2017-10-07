import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class NewApplication extends JFrame{

    protected int fieldCounter;

    protected JLabel applicationTitle, applicationNameLabel;

    protected JTextField applicationNameTextField;

    protected JPanel containerPane, fieldElements, footerPanel;

    protected JButton saveButton, addFieldButton;

    protected JScrollPane fieldElementsScrollPane;

    protected ArrayList<String> fieldName;
    protected ArrayList<String> fieldType;
    protected ArrayList<JLabel> fieldNameLabel;
    protected ArrayList<JLabel> fieldTypeLabel;
    protected ArrayList<JTextField> fieldNameTextField;
    protected ArrayList<JRadioButton> textField;
    protected ArrayList<JRadioButton> numberField;
    protected ArrayList<JRadioButton> listField;

    public NewApplication() {

        fieldCounter = -1;

        containerPane = new JPanel(new BorderLayout());

        footerPanel = new JPanel(new GridLayout(1, 2));

        applicationTitle = new JLabel("Offline Data Operations Platform - New Application");
        applicationTitle.setHorizontalAlignment(SwingConstants.CENTER);
        fieldElements =new JPanel();
        fieldElements.setLayout(new GridLayout(40,1));
        saveButton = new JButton("Save");

        fieldElementsScrollPane = new JScrollPane(fieldElements);

        applicationNameLabel = new JLabel("Enter Application Name");
        applicationNameTextField = new JTextField();

        fieldElements.add(applicationNameLabel);
        fieldElements.add(applicationNameTextField);

        fieldName = new ArrayList<>();
        fieldType = new ArrayList<>();

        fieldNameLabel = new ArrayList<>();
        fieldTypeLabel = new ArrayList<>();
        fieldNameTextField = new ArrayList<>();
        textField = new ArrayList<>();
        numberField = new ArrayList<>();
        listField = new ArrayList<>();
        addFieldButton = new JButton("Add Another Field");

        footerPanel.add(saveButton);
        footerPanel.add(addFieldButton);

        addElementsToFieldElementsPane();

        addElementsToContainerPane();

        showApplication();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                saveApplication();
            }
        });
    }

    void addElementsToFieldElementsPane() {

        fieldCounter++;
        //addNewFields(fieldCounter);

        addFieldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewFields(fieldCounter);
                fieldCounter++;
                //System.out.println(fieldCounter);
            }
        });
    }

    void addNewFields(int c) {
        fieldNameLabel.add(new JLabel("Enter Field Name"));
        fieldNameTextField.add(new JTextField());
        fieldTypeLabel.add(new JLabel("Enter Field Type"));
        textField.add(new JRadioButton("Text"));
        numberField.add(new JRadioButton("Number"));
        listField.add(new JRadioButton("List"));
        fieldElements.add(fieldNameLabel.get(c));
        fieldElements.add(fieldNameTextField.get(c));
        fieldElements.add(fieldTypeLabel.get(c));
        fieldElements.add(textField.get(c));
        fieldElements.add(numberField.get(c));
        System.out.println(fieldElements);
        setOptionChooser(c);

    }

    void setOptionChooser(int c) {
        textField.get(c).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fieldType.add("Text");
            }
        });
        numberField.get(c).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fieldType.add("Number");
            }
        });
    }

    void addElementsToContainerPane() {

        containerPane.add(applicationTitle, BorderLayout.PAGE_START);
        containerPane.add(fieldElementsScrollPane, BorderLayout.CENTER);
        containerPane.add(footerPanel, BorderLayout.PAGE_END);

    }

    void showApplication() {

        add(containerPane);
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    void saveApplication() {

        int i;
        String line, fileName;
        for(i = 0; i < fieldNameLabel.size(); i++) {
            fieldName.add(fieldNameTextField.get(i).getText());
        }

        fileName = applicationNameTextField.getText() + ".txt";

        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            System.out.println(fieldName.size());
            for(i = 0; i < fieldName.size(); i++) {
                line = fieldName.get(i) + "/" + fieldType.get(i) + "\n";
                bw.write(line);
            }

            for(i = 0; i < fieldName.size(); i++)
                System.out.println(fieldName.get(i));

            bw.close();
        }
        catch(IOException e){};
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