import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.*;

public class DataStorage extends JFrame{

    JPanel headerPane, bodyPane, containerPane;

    JButton openFile, saveData;

    JScrollPane bodyScroll;

    JFileChooser jf;

    File file;

    ArrayList<JLabel> fieldLabel;
    ArrayList<JTextField> fieldArea;
    ArrayList<String> fieldType;

    DataStorage() {

        containerPane = new JPanel(new BorderLayout());

        headerPane = new JPanel(new GridLayout(2, 1));
        openFile = new JButton("Open New File");
        jf = new JFileChooser();

        bodyPane = new JPanel(new GridLayout(40, 2));

        bodyScroll = new JScrollPane(bodyPane);

        saveData = new JButton("Save Data");

        fieldLabel = new ArrayList<>();
        fieldArea = new ArrayList<>();
        fieldType = new ArrayList<>();

        headerPane.add(openFile);

        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chooseFile();
            }
        });

        addElementsToContainerPane();

        showApplication();

    }

    void chooseFile() {

        int i = jf.showOpenDialog(this);
        if(i == JFileChooser.APPROVE_OPTION) {
            file =jf.getSelectedFile();
            System.out.println(file.getName());
        }

        showData(file);

    }

    void showData(File f) {

        int i;
        char ch;
        String fName = "";

        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(f));
            while(br.readLine() != null) {
                System.out.println(br.readLine().length());
                for(i = 0; i < br.readLine().length(); i++) {
                    ch = br.readLine().charAt(i);
                    System.out.println(ch);
                    if(ch != '/') {
                        fName = fName + ch;
                    }
                    else {
                        break;
                    }
                }
                System.out.println(fName);
                fieldLabel.add(new JLabel(fName));
                if(br.readLine().charAt(i + 1) == 'T') {
                    fieldArea.add(new JTextField());
                    fieldType.add("Text");
                }
                else {
                    fieldArea.add(new JTextField());
                    fieldType.add("Number");
                }
            }

            System.out.println();

            br.close();
        }
        catch (Exception e) {};

        for(i = 0; i < fieldLabel.size(); i++) {
            bodyPane.add(fieldLabel.get(i));
            bodyPane.add(fieldArea.get(i));
            if(fieldType.get(i).equals("Text"))
                bodyPane.add(new JLabel("Please Enter Text Data"));
            else
                bodyPane.add(new JLabel("Please Enter Number Data"));
        }

    }

    void addElementsToContainerPane() {

        containerPane.add(headerPane, BorderLayout.PAGE_START);
        containerPane.add(bodyScroll, BorderLayout.CENTER);
        containerPane.add(saveData, BorderLayout.PAGE_END);

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

        new DataStorage();
    }
}
