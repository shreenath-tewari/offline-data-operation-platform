import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.*;

public class DataStorage extends JFrame{

    protected JPanel headerPane, bodyPane, containerPane;

    protected JButton openFile, saveData;

    protected JScrollPane bodyScroll;

    protected JFileChooser jf;

    protected File file;

    protected ArrayList<JLabel> fieldLabel;
    protected ArrayList<JTextField> fieldArea;
    protected ArrayList<String> fieldType;

    DataStorage() {

        containerPane = new JPanel(new BorderLayout());

        headerPane = new JPanel(new GridLayout(2, 1));
        openFile = new JButton("Open New File");
        jf = new JFileChooser();

        bodyPane = new JPanel(new GridLayout(20, 2));

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
        }

        showData(file);

    }

    void setApplicationTitle(String fileName) {
        JLabel setTitle = new JLabel(fileName.substring(0, (fileName.length() - 4)));
        setTitle.setHorizontalAlignment(JLabel.CENTER);
        headerPane.add(setTitle);
        headerPane.validate();
    }

    void showData(File f) {

        int i, flag = 0, j = 0;
        char ch;
        String fName = "";
        String lines;

        BufferedReader br;

        setApplicationTitle(f.getName());

        try {
            br = new BufferedReader(new FileReader(f.getName()));

            while(flag == 0) {
                lines = br.readLine();
                for(i = 0; i < lines.length(); i++) {
                    ch = lines.charAt(i);
                    if(ch == '/') {
                        break;
                    }
                    else {
                        fName = fName + ch;
                    }
                }
                fieldLabel.add(new JLabel(fName));
                j++;
                if(lines.charAt(i + 1) == 'T') {
                    fieldArea.add(new JTextField());
                    fieldType.add("Text");
                }
                else {
                    fieldArea.add(new JTextField());
                    fieldType.add("Number");
                }

                if(lines == null)
                    flag = 1;

                fName = "";
            }

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
            bodyPane.validate();
        }
        System.out.println("Body Pane" + bodyPane);

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