import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class SchedulerGUI extends JFrame {
    private JTextField idField, priorityField, arrivalField, burstField;
    private JTextArea outputArea;
    private List<Process> processes;

    public SchedulerGUI() {
        setTitle("Priority Preemptive Scheduling");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        processes = new ArrayList<>();

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Process ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Priority:"));
        priorityField = new JTextField();
        inputPanel.add(priorityField);

        inputPanel.add(new JLabel("Arrival Time:"));
        arrivalField = new JTextField();
        inputPanel.add(arrivalField);

        inputPanel.add(new JLabel("Burst Time:"));
        burstField = new JTextField();
        inputPanel.add(burstField);

        JButton addButton = new JButton("Add Process");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProcess();
            }
        });
        inputPanel.add(addButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBackButton();
            }
        });

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleClearButton();
            }
        });

        JButton scheduleButton = new JButton("Schedule");
        scheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scheduleProcesses();
            }
        });

        buttonPanel.add(backButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(scheduleButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addProcess() {
        try {
            String id = idField.getText();
            int priority = Integer.parseInt(priorityField.getText());
            int arrival = Integer.parseInt(arrivalField.getText());
            int burst = Integer.parseInt(burstField.getText());

            Process process = new Process(id, priority, arrival, burst);
            processes.add(process);

            outputArea.append("Process " + id + " added\n");

            idField.setText("");
            priorityField.setText("");
            arrivalField.setText("");
            burstField.setText("");
        } catch (NumberFormatException e) {
            outputArea.append("Invalid input. Please enter valid numbers.\n");
        }
    }

    private void scheduleProcesses() {
        if (processes.isEmpty()) {
            outputArea.append("No processes to schedule.\n");
            return;
        }

        PriorityScheduler scheduler = new PriorityScheduler(processes);
        scheduler.schedule();
    }

    private void handleBackButton() {
        outputArea.append("Back button pressed. Navigation logic should be implemented.\n");
        dispose();
    }

    private void handleClearButton() {
        outputArea.setText("");
        idField.setText("");
        priorityField.setText("");
        arrivalField.setText("");
        burstField.setText("");
        processes.clear();
        outputArea.append("All fields and processes cleared.\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SchedulerGUI().setVisible(true);
            }
        });
    }
}
