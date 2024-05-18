import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Pruebafinal1.Bacteria;
import Pruebafinal1.Experimento;

public class Main {
    private static List<Experimento> experimentos = new ArrayList<>();
    private static Experimento experimentoActual;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        experimentoActual = new Experimento();

        JFrame frame = new JFrame("Gestión de Experimentos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(2, 1));

        JButton viewButton = new JButton("Consultar experimentos guardados");
        JButton createButton = new JButton("Crear nuevo experimento");

        viewButton.setFont(new Font("Arial", Font.BOLD, 14));
        viewButton.setBackground(Color.LIGHT_GRAY);
        viewButton.setForeground(Color.BLACK);

        createButton.setFont(new Font("Arial", Font.BOLD, 14));
        createButton.setBackground(Color.LIGHT_GRAY);
        createButton.setForeground(Color.BLACK);

        frame.add(viewButton);
        frame.add(createButton);

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showExperiments();
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createExperimentWindow();
            }
        });

        JButton simulateButton = new JButton("Simular experimento");
        simulateButton.setFont(new Font("Arial", Font.BOLD, 14));
        simulateButton.setBackground(Color.LIGHT_GRAY);
        simulateButton.setForeground(Color.BLACK);

        frame.add(simulateButton);

        simulateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulateExperiment();
            }
        });

        frame.setVisible(true);
    }

    private static void simulateExperiment() {
        if (experimentos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay experimentos guardados");
            return;
        }

        Experimento[] experimentosArray = experimentos.toArray(new Experimento[0]);
        Experimento selectedExperiment = (Experimento) JOptionPane.showInputDialog(
                null,
                "Seleccione un experimento para simular:",
                "Simulación de Montecarlo",
                JOptionPane.QUESTION_MESSAGE,
                null,
                experimentosArray,
                experimentosArray[0]
        );

        if (selectedExperiment != null) {
            for (Bacteria bacteria : selectedExperiment.getBacteriaPopulations()) {
                // Aquí es donde se implementaría la simulación de Montecarlo
            }
        }
    }

    private static void createExperimentWindow() {
        JFrame createFrame = new JFrame("Crear nuevo experimento");
        createFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createFrame.setSize(500, 500);
        createFrame.setLayout(new GridLayout(11, 2));

        JTextField experimentNameField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField startDateField = new JTextField();
        JTextField endDateField = new JTextField();
        JTextField initialBacteriaCountField = new JTextField();
        JTextField temperatureField = new JTextField();
        JTextField lightConditionField = new JTextField();
        JTextField foodDoseField = new JTextField();

        JButton addButton = new JButton("Añadir población de bacterias");
        JButton editButton = new JButton("Editar población de bacterias");
        JButton deleteButton = new JButton("Eliminar población de bacterias");
        JButton saveButton = new JButton("Guardar experimento");

        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(Color.LIGHT_GRAY);
        addButton.setForeground(Color.BLACK);

        editButton.setFont(new Font("Arial", Font.BOLD, 14));
        editButton.setBackground(Color.LIGHT_GRAY);
        editButton.setForeground(Color.BLACK);

        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteButton.setBackground(Color.LIGHT_GRAY);
        deleteButton.setForeground(Color.BLACK);

        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setBackground(Color.LIGHT_GRAY);
        saveButton.setForeground(Color.BLACK);

        createFrame.add(new JLabel("Nombre del experimento:"));
        createFrame.add(experimentNameField);
        createFrame.add(new JLabel("Nombre de la bacteria:"));
        createFrame.add(nameField);
        createFrame.add(new JLabel("Fecha de inicio (dd/MM/yyyy):"));
        createFrame.add(startDateField);
        createFrame.add(new JLabel("Fecha de fin (dd/MM/yyyy):"));
        createFrame.add(endDateField);
        createFrame.add(new JLabel("Número inicial de bacterias:"));
        createFrame.add(initialBacteriaCountField);
        createFrame.add(new JLabel("Temperatura:"));
        createFrame.add(temperatureField);
        createFrame.add(new JLabel("Condición de luz:"));
        createFrame.add(lightConditionField);
        createFrame.add(new JLabel("Dosis de comida:"));
        createFrame.add(foodDoseField);
        createFrame.add(addButton);
        createFrame.add(editButton);
        createFrame.add(deleteButton);
        createFrame.add(saveButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                Date startDate = null;
                Date endDate = null;
                try {
                    startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDateField.getText());
                    endDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDateField.getText());
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                int initialBacteriaCount = Integer.parseInt(initialBacteriaCountField.getText());
                double temperature = Double.parseDouble(temperatureField.getText());
                String lightCondition = lightConditionField.getText();
                int foodDose = Integer.parseInt(foodDoseField.getText());

                Bacteria bacteria = new Bacteria(name, startDate, endDate, initialBacteriaCount, temperature, lightCondition, foodDose);
                experimentoActual.addBacteriaPopulation(bacteria);

                JOptionPane.showMessageDialog(createFrame, "Población de bacterias añadida");
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (experimentoActual.getBacteriaPopulations().isEmpty()) {
                    JOptionPane.showMessageDialog(createFrame, "No hay poblaciones de bacterias para editar");
                    return;
                }

                Bacteria[] bacteriaArray = experimentoActual.getBacteriaPopulations().toArray(new Bacteria[0]);
                Bacteria selectedBacteria = (Bacteria) JOptionPane.showInputDialog(
                        null,
                        "Seleccione la población de bacterias a editar:",
                        "Editar población de bacterias",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        bacteriaArray,
                        bacteriaArray[0]
                );

                if (selectedBacteria != null) {
                    nameField.setText(selectedBacteria.getName());
                    startDateField.setText(new SimpleDateFormat("dd/MM/yyyy").format(selectedBacteria.getStartDate()));
                    endDateField.setText(new SimpleDateFormat("dd/MM/yyyy").format(selectedBacteria.getEndDate()));
                    initialBacteriaCountField.setText(String.valueOf(selectedBacteria.getInitialBacteriaCount()));
                    temperatureField.setText(String.valueOf(selectedBacteria.getTemperature()));
                    lightConditionField.setText(selectedBacteria.getLightCondition());
                    foodDoseField.setText(String.valueOf(selectedBacteria.getFoodDose()));

                    addButton.setText("Actualizar población de bacterias");
                    addButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String name = nameField.getText();
                            Date startDate = null;
                            Date endDate = null;
                            try {
                                startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDateField.getText());
                                endDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDateField.getText());
                            } catch (ParseException parseException) {
                                parseException.printStackTrace();
                            }
                            int initialBacteriaCount = Integer.parseInt(initialBacteriaCountField.getText());
                            double temperature = Double.parseDouble(temperatureField.getText());
                            String lightCondition = lightConditionField.getText();
                            int foodDose = Integer.parseInt(foodDoseField.getText());

                            selectedBacteria.setName(name);
                            selectedBacteria.setStartDate(startDate);
                            selectedBacteria.setEndDate(endDate);
                            selectedBacteria.setInitialBacteriaCount(initialBacteriaCount);
                            selectedBacteria.setTemperature(temperature);
                            selectedBacteria.setLightCondition(lightCondition);
                            selectedBacteria.setFoodDose(foodDose);

                            JOptionPane.showMessageDialog(createFrame, "Población de bacterias actualizada");
                        }
                    });
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (experimentoActual.getBacteriaPopulations().isEmpty()) {
                    JOptionPane.showMessageDialog(createFrame, "No hay poblaciones de bacterias para eliminar");
                    return;
                }

                Bacteria[] bacteriaArray = experimentoActual.getBacteriaPopulations().toArray(new Bacteria[0]);
                Bacteria selectedBacteria = (Bacteria) JOptionPane.showInputDialog(
                        null,
                        "Seleccione la población de bacterias a eliminar:",
                        "Eliminar población de bacterias",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        bacteriaArray,
                        bacteriaArray[0]
                );

                if (selectedBacteria != null) {
                    experimentoActual.getBacteriaPopulations().remove(selectedBacteria);
                    JOptionPane.showMessageDialog(createFrame, "Población de bacterias eliminada");
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                experimentos.add(experimentoActual);
                saveExperiment();
                String experimentName = experimentNameField.getText();
                experimentoActual = new Experimento(experimentName);
            }
        });

        createFrame.setVisible(true);
    }

    private static void saveExperiment() {
        try {
            FileOutputStream fileOut = new FileOutputStream("experimentos.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(experimentos);
            out.close();
            fileOut.close();
            JOptionPane.showMessageDialog(null, "Experimento guardado");

            loadExperiment();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private static void loadExperiment() {
        try {
            FileInputStream fileIn = new FileInputStream("experimentos.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            experimentos = (List<Experimento>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Clase Experimento no encontrada");
            c.printStackTrace();
        }
    }

    private static void showExperiments() {
        if (experimentos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay experimentos guardados");
            return;
        }

        Experimento[] experimentosArray = experimentos.toArray(new Experimento[0]);
        Experimento selectedExperiment = (Experimento) JOptionPane.showInputDialog(
                null,
                "Seleccione un experimento:",
                "Experimentos guardados",
                JOptionPane.QUESTION_MESSAGE,
                null,
                experimentosArray,
                experimentosArray[0]
        );

        if (selectedExperiment != null) {
            StringBuilder bacteriaDetails = new StringBuilder();
            int count = 1;
            for (Bacteria bacteria : selectedExperiment.getBacteriaPopulations()) {
                bacteriaDetails.append("Población de bacterias ").append(count++).append(":\n");
                bacteriaDetails.append("Nombre: ").append(bacteria.getName()).append("\n");
                bacteriaDetails.append("Fecha de inicio: ").append(bacteria.getStartDate()).append("\n");
                bacteriaDetails.append("Fecha de fin: ").append(bacteria.getEndDate()).append("\n");
                bacteriaDetails.append("Número inicial de bacterias: ").append(bacteria.getInitialBacteriaCount()).append("\n");
                bacteriaDetails.append("Temperatura: ").append(bacteria.getTemperature()).append("\n");
                bacteriaDetails.append("Condición de luz: ").append(bacteria.getLightCondition()).append("\n");
                bacteriaDetails.append("Dosis de comida diaria: ").append(bacteria.getFoodDose()).append("\n\n");
                bacteriaDetails.append("\n----------------------------------------\n\n");
            }

            JTextArea textArea = new JTextArea(bacteriaDetails.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(500, 500));
            JOptionPane.showMessageDialog(null, scrollPane, "Detalles del experimento", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}