package focus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Focus extends JFrame { 

    // TASKS
    private JTextField taskField;

    private DefaultListModel<String> taskModel;
 
    private JList<String> taskList;

    // NOTES
    private JTextArea notesArea;

    // TIMER
    private JLabel timerLabel;

    private Timer timer;

    private int seconds = 25 * 60;

    // FOCUS SESSIONS
    private int sessions = 0;

    private JLabel sessionsLabel;



    public Focus() {

        setTitle("Focus App");

        setSize(1000, 650);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        setLayout(new BorderLayout(15, 15));



        // ================= TOP PANEL =================

        JPanel topPanel =
                new JPanel(new BorderLayout());

        topPanel.setPreferredSize(
                new Dimension(1000, 120));



        // LEFT SIDE -> SESSIONS
        JPanel sessionPanel = new JPanel();

        sessionsLabel =
                new JLabel("Focus Sessions: 0");

        sessionsLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        22));

        sessionPanel.add(sessionsLabel);



        // CENTER -> TIMER
        JPanel timerPanel = new JPanel();

        timerLabel = new JLabel("25:00");

        timerLabel.setFont(
                new Font("Monospaced",
                        Font.BOLD,
                        55));



        JButton startBtn =
                new JButton("Start");

        JButton pauseBtn =
                new JButton("Pause");

        JButton resetBtn =
                new JButton("Reset");



        // BUTTON COLORS
        startBtn.setBackground(Color.GREEN);

        pauseBtn.setBackground(Color.RED);

        resetBtn.setBackground(Color.ORANGE);



        startBtn.setForeground(Color.WHITE);

        pauseBtn.setForeground(Color.WHITE);

        resetBtn.setForeground(Color.WHITE);



        Dimension btnSize =
                new Dimension(110, 40);

        startBtn.setPreferredSize(btnSize);

        pauseBtn.setPreferredSize(btnSize);

        resetBtn.setPreferredSize(btnSize);



        timerPanel.add(timerLabel);

        timerPanel.add(startBtn);

        timerPanel.add(pauseBtn);

        timerPanel.add(resetBtn);



        topPanel.add(sessionPanel,
                BorderLayout.WEST);

        topPanel.add(timerPanel,
                BorderLayout.CENTER);



        add(topPanel, BorderLayout.NORTH);



        // ================= TASK PANEL =================

        JPanel leftPanel =
                new JPanel(new BorderLayout(10, 10));

        leftPanel.setPreferredSize(
                new Dimension(320, 600));



        leftPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Tasks"));



        JPanel inputPanel = new JPanel();

        taskField = new JTextField(15);



        JButton addBtn =
                new JButton("Add");

        JButton deleteBtn =
                new JButton("Delete");



        addBtn.setBackground(
                new Color(0, 120, 215));

        addBtn.setForeground(Color.WHITE);



        deleteBtn.setBackground(
                Color.DARK_GRAY);

        deleteBtn.setForeground(Color.WHITE);



        inputPanel.add(taskField);

        inputPanel.add(addBtn);

        inputPanel.add(deleteBtn);



        taskModel =
                new DefaultListModel<>();

        taskList =
                new JList<>(taskModel);



        leftPanel.add(inputPanel,
                BorderLayout.NORTH);

        leftPanel.add(
                new JScrollPane(taskList),
                BorderLayout.CENTER);



        add(leftPanel, BorderLayout.WEST);



        // ================= NOTES PANEL =================

        JPanel rightPanel =
                new JPanel(new BorderLayout());



        rightPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Study Notes"));



        notesArea = new JTextArea();

        notesArea.setFont(
                new Font("Arial",
                        Font.PLAIN,
                        17));

        notesArea.setLineWrap(true);

        notesArea.setWrapStyleWord(true);



        rightPanel.add(
                new JScrollPane(notesArea),
                BorderLayout.CENTER);



        add(rightPanel, BorderLayout.CENTER);



        // ================= ADD TASK =================

        addBtn.addActionListener(e -> {

            String task =
                    taskField.getText().trim();

            if (!task.isEmpty()) {

                taskModel.addElement(task);

                taskField.setText("");
            }
        });



        // ENTER TO ADD TASK
        taskField.addActionListener(e -> {

            addBtn.doClick();
        });



        // DELETE TASK
        deleteBtn.addActionListener(e -> {

            int index =
                    taskList.getSelectedIndex();

            if (index != -1) {

                taskModel.remove(index);
            }
        });



        // ================= TIMER =================

        timer = new Timer(1000, e -> {

            if (seconds > 0) {

                seconds--;

                int min = seconds / 60;

                int sec = seconds % 60;

                timerLabel.setText(

                        String.format(
                                "%02d:%02d",
                                min,
                                sec));
            }

            else {

                timer.stop();

                sessions++;

                sessionsLabel.setText(
                        "Focus Sessions: " + sessions);

                JOptionPane.showMessageDialog(
                        null,
                        "Focus Session Finished!");

                seconds = 25 * 60;

                timerLabel.setText("25:00");
            }
        });



        // START
        startBtn.addActionListener(e -> {

            timer.start();
        });



        // PAUSE
        pauseBtn.addActionListener(e -> {

            timer.stop();
        });



        // RESET
        resetBtn.addActionListener(e -> {

            timer.stop();

            seconds = 25 * 60;

            timerLabel.setText("25:00");
        });



        setLocationRelativeTo(null);

        setVisible(true);
    }



    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new Focus();
        });
    }
}