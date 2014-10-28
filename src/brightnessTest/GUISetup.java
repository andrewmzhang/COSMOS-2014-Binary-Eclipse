/*
 * Made by Louis Lu in COSMOS for simulating light curves in research of Eclipsing Binaries
 * There is some very interesting geometry in these four classes
 * 
 * This section has been revised and debugged by Andrew Zhang. 
 * Math has been corrected, UI improved, and bugs fixed!
 */
package brightnessTest;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;


@SuppressWarnings("serial")
public class GUISetup extends JFrame {


    //These are all some objects and variables...

    //This is the Icon of the program

    ImageIcon img = new ImageIcon(getClass().getResource("curve.png"));

    private JPanel inputPanel = new JPanel(new GridBagLayout());
    private JPanel imagePanel = new JPanel(new BorderLayout());
    private JPanel imageInfoPanel = new JPanel(new FlowLayout());

    public JLabel percentageLabel = new JLabel("0%");

    public JButton generateBtn = new JButton("Generate");

    GridBagConstraints c = new GridBagConstraints();

    private JLabel inputLabel = new JLabel("Input");
    private JLabel imageLabel = new JLabel("Light Curve Produced");
    private JLabel imageResLabel = new JLabel("Image Resolution:");

    private JTextField imageWidthText = new JTextField("1920", 10);
    private JTextField imageHeightText = new JTextField("1080", 10);

    private JTextArea imageDir = new JTextArea();
    private JLabel imageDest = new JLabel("Save the image to:");
    public JButton browseBtn = new JButton("Browse...");

    private JLabel creditLabel1 = new JLabel("Created by");
    private JLabel creditLabel2 = new JLabel("Louis Lu");

    private JLabel creditLabel3 = new JLabel("Debugged by");
    private JLabel creditLabel4 = new JLabel("Andrew Zhang");

    private JLabel creditLabel5 = new JLabel("in COSMOS");

    private static String OS = System.getProperty("os.name").toLowerCase();
    public static String filename = "Test.jpg";
    public static String dir = (String) System.getProperty("user.dir");

    private JLabel sliceLabel = new JLabel("Slices/Polygons:");
    private JTextField sliceNumText = new JTextField("1000", 10);

    //Star1
    private JLabel star1Label = new JLabel("Star 1 Information:");
    private JLabel star1TempLabel = new JLabel("Temperature (Kelvin):");
    private JTextField star1TempText = new JTextField(10);
    private JLabel star1RadiusLabel = new JLabel("Radius:");
    private JTextField star1RadiusText = new JTextField(10);

    //Star2
    private JLabel star2Label = new JLabel("Star 2 Information:");
    private JLabel star2TempLabel = new JLabel("Temperature (kelvin):");
    private JTextField star2TempText = new JTextField(10);
    private JLabel star2RadiusLabel = new JLabel("Radius:");
    private JTextField star2RadiusText = new JTextField(10);


    public double star1Temp = 0;
    public double star1Radius = 0;

    public double star2Temp = 0;
    public double star2Radius = 0;

    public int imageWidth = 0;
    public int imageHeight = 0;

    public int sliceNum = 0;


    BufferedImage lightCurve;

    JLabel picLabel;

    private JTextArea consoleOut;

    public GUISetup() {


        super("Eclipsing Binaries Light Curve Generator");//Name of the program
        consoleOut = new JTextArea(11, 18);
        consoleOut.setEditable(false);
        PrintStream printStream = new PrintStream(new TextHandler(consoleOut));
        System.setOut(printStream);
        System.setErr(printStream);


        setLayout(new BorderLayout());
        //Adding some objects
        add(inputPanel, BorderLayout.WEST);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        c.gridx = 0;
        c.gridy = 0;
        inputPanel.add(inputLabel, c);
        inputLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        c.gridy++;
        inputPanel.add(star1Label, c);
        c.gridy++;
        inputPanel.add(star1TempLabel, c);
        c.gridy++;
        inputPanel.add(star1TempText, c);
        c.gridy++;
        inputPanel.add(star1RadiusLabel, c);
        c.gridy++;
        inputPanel.add(star1RadiusText, c);
        c.gridy++;
        inputPanel.add(star2Label, c);
        c.gridy++;
        inputPanel.add(star2TempLabel, c);
        c.gridy++;
        inputPanel.add(star2TempText, c);
        c.gridy++;
        inputPanel.add(star2RadiusLabel, c);
        c.gridy++;
        inputPanel.add(star2RadiusText, c);
        c.gridy++;
        inputPanel.add(generateBtn, c);
        c.gridy++;
        inputPanel.add(imageResLabel, c);
        c.gridy++;
        inputPanel.add(imageWidthText, c);
        c.gridy++;
        inputPanel.add(imageHeightText, c);
        c.gridy++;
        inputPanel.add(sliceLabel, c);
        c.gridy++;
        inputPanel.add(sliceNumText, c);
        percentageLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));
        percentageLabel.setForeground(Color.BLUE);
        c.gridy++;
        inputPanel.add(imageDest, c);
        c.gridy++;
        imageDir.append(dir + "/" + filename);
        imageDir.setLineWrap(true);
        imageDir.setEditable(false);
        inputPanel.add(imageDir, c);
        c.gridy++;
        inputPanel.add(browseBtn, c);
        browseBtn.addActionListener(new browseFolderBtn());//Button click to browse folder
        c.gridy++;
        inputPanel.add(creditLabel1, c);
        c.gridy++;
        inputPanel.add(creditLabel2, c);
        c.gridy++;
        inputPanel.add(creditLabel3, c);
        c.gridy++;
        inputPanel.add(creditLabel4, c);
        c.gridy++;
        inputPanel.add(creditLabel5, c);
        c.gridy++;
        inputPanel.add(new JScrollPane(consoleOut), c);

        generateBtn.addActionListener(new generateGraphBtn());//Click button to start generate graph

        add(imagePanel, BorderLayout.CENTER);
        imagePanel.add(imageInfoPanel, BorderLayout.PAGE_START);
        imageLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));
        imageInfoPanel.add(imageLabel);
        imageInfoPanel.add(percentageLabel);
    }

    public void loadImage() {
        /*
		 * This method loads image from whichever directory the user selects
		 */
        try {
            lightCurve = ImageIO.read(new File(dir + "/" + filename));
        } catch (IOException e) {
            System.err.println("Could not open image");
        }
        //draw the image
        JPanel subImagePanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(lightCurve, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };
        //load the image to the panel
        imagePanel.remove(subImagePanel);
        imagePanel.add(subImagePanel);
        //re-enable two buttons
        generateBtn.setEnabled(true);
        browseBtn.setEnabled(true);
    }

    public class generateGraphBtn implements ActionListener {
		/*
		 * This method takes in values and starts generating the image in a new thread
		 */


        public void actionPerformed(ActionEvent e) {
            // Clears Window
            try {
                consoleOut.getDocument().remove(0,
                        consoleOut.getDocument().getLength());
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }

            try {
                if (!star1TempText.getText().equals("")) {
                    star1Temp = Integer.parseInt(star1TempText.getText());
                }
                if (!star1RadiusText.getText().equals("")) {
                    star1Radius = Integer.parseInt(star1RadiusText.getText());
                }
                if (!star2TempText.getText().equals("")) {
                    star2Temp = Integer.parseInt(star2TempText.getText());
                }
                if (!star2RadiusText.getText().equals("")) {
                    star2Radius = Integer.parseInt(star2RadiusText.getText());
                }
                if (!imageWidthText.getText().equals("")) {
                    imageWidth = Integer.parseInt(imageWidthText.getText());
                }
                if (!imageHeightText.getText().equals("")) {
                    imageHeight = Integer.parseInt(imageHeightText.getText());
                }
                if (!sliceNumText.getText().equals("")) {
                    if (Integer.parseInt(sliceNumText.getText()) == 1) {
                        throw new NumberFormatException();
                    }
                    sliceNum = Integer.parseInt(sliceNumText.getText());
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Invalid Input");
            }
            //A new thread needs to be started to do this complicated calculation so it doesn't lag the GUI
            thread1 t1 = new thread1();
            //Those two button need to be disabled during calculations
            generateBtn.setEnabled(false);
            browseBtn.setEnabled(false);
            //start the thread
            t1.start();
        }
    }

    public class browseFolderBtn implements ActionListener {
        //Opens up a browsing window to select directory for the graph
        public void actionPerformed(ActionEvent arg0) {
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new FileNameExtensionFilter("Image Files", ".jpg"));
            int rVal = fc.showOpenDialog(GUISetup.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                //Find the directory and convert it to String
                dir = fc.getCurrentDirectory().toString();
                filename = fc.getSelectedFile().getName().toString();
                imageDir.setText(dir + "/" + filename);
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {

            }
        }

    }

    public class thread1 implements Runnable {
        //This is the thread that handles the heavy work
        private Thread t;

        public thread1() {
        }

        public void run() {
            BrightnessTest.generateGraph();

        }

        public void start() {
            if (t == null) {
                t = new Thread(this, "Thread1");
                t.start();
            }
        }
    }
}
