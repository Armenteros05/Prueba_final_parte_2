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
    private static JPanel simulationPanel;
    private static List<Experimento> experimentos = new ArrayList<>();
    private static Experimento experimentoActual;
    private static int[][] bacteriaPositions;
    private static int[][] bacteriaMovement;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                experimentoActual = new Experimento();
                simulationPanel = new JPanel();
                createAndShowGUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void createAndShowGUI() {
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
        // Inicializar bacteriaPositions y bacteriaMovement
        bacteriaPositions = new int[20][20];
        bacteriaMovement = new int[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                bacteriaPositions[i][j] = 1; // Todas las casillas empiezan en verde
                bacteriaMovement[i][j] = 0; // Todas las bacterias empiezan en la posición (0, 0)
            }
        }

        // Las bacterias comienzan en el centro de la matriz
        int totalBacteriaCount = 0;
        for (Bacteria bacteria : experimentoActual.getBacteriaPopulations()) {
            totalBacteriaCount += bacteria.getInitialBacteriaCount();
        }
        bacteriaMovement[10][10] = totalBacteriaCount;

        // Crear la ventana de visualización
        JFrame simulationFrame = new JFrame("Simulación de Montecarlo");
        simulationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        simulationFrame.setSize(800, 800);
        simulationFrame.setLayout(new BorderLayout());

        // Crear el menú desplegable para seleccionar el experimento
        if (experimentos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay experimentos guardados");
            return;
        }

        Experimento[] experimentosArray = experimentos.toArray(new Experimento[0]);
        JComboBox<Experimento> experimentComboBox = new JComboBox<>(experimentosArray);
        experimentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                experimentoActual = (Experimento) experimentComboBox.getSelectedItem();
                if (experimentoActual != null) {
                    // Rellenar bacteriaPositions con la población de bacterias del experimento
                    for (Bacteria bacteria : experimentoActual.getBacteriaPopulations()) {
                        // Suponiendo que las bacterias están distribuidas uniformemente en la matriz
                        int bacteriaPerPosition = bacteria.getInitialBacteriaCount() / (20 * 20);
                        for (int i = 0; i < 20; i++) {
                            for (int j = 0; j < 20; j++) {
                                bacteriaPositions[i][j] += bacteriaPerPosition;
                            }
                        }
                    }
                }
            }
        });

        simulationFrame.add(experimentComboBox, BorderLayout.NORTH);
        simulationFrame.add(simulationPanel, BorderLayout.CENTER);

        JButton startButton = new JButton("Iniciar simulación");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí va el código que quieres que se ejecute cuando se haga clic en el botón "Iniciar simulación"
                // Por ejemplo, podrías llamar a un método que inicie la simulación del experimento
                startSimulation();
            }
        });
        simulationFrame.add(startButton, BorderLayout.SOUTH);
        simulationFrame.setVisible(true);
    }

    private static void startSimulation() {
        // Aquí va el código para iniciar la simulación del experimento
        // Por ejemplo, podrías inicializar las variables del experimento y comenzar la simulación

        // Crear una nueva ventana para la simulación
        JFrame simulationWindow = new JFrame("Simulación de " + experimentoActual.getName());
        simulationWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        simulationWindow.setSize(800, 800);
        simulationWindow.setLayout(new BorderLayout());

        // Crear el panel de simulación
        JPanel simulationPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int squareSize = Math.min(getWidth() / 20, getHeight() / 20);
                for (int i = 0; i < 20; i++) {
                    for (int j = 0; j < 20; j++) {
                        // Dibujar las casillas de acuerdo a su estado
                        switch (bacteriaPositions[i][j]) {
                            case 1:
                                g.setColor(Color.GREEN);
                                break;
                            case 2:
                                g.setColor(Color.YELLOW);
                                break;
                            case 3:
                                g.setColor(Color.RED);
                                break;
                            case 4:
                                g.setColor(Color.GRAY);
                                break;
                        }
                        g.fillRect(i * squareSize, j * squareSize, squareSize, squareSize);
                    }
                }
            }
        };
        simulationPanel.setBackground(Color.WHITE);
        simulationWindow.add(simulationPanel, BorderLayout.CENTER);

        // Simular el crecimiento de las bacterias
        new Thread(() -> {
            try {
                for (int t = 0; t < 100; t++) { // Simular durante 100 pasos de tiempo
                    if (bacteriaPositions != null) { // Asegúrate de que bacteriaPositions está inicializado
                        for (int i = 0; i < 20; i++) {
                            for (int j = 0; j < 20; j++) {
                                // Para cada bacteria en la posición actual
                                for (int b = 0; b < bacteriaPositions[i][j]; b++) {
                                    // Obtener una nueva posición aleatoria para la bacteria
                                    int[] newPosition = getRandomPosition();
                                    // Mover la bacteria a la nueva posición
                                    bacteriaPositions[newPosition[0]][newPosition[1]] += 1;
                                    // Reducir el número de bacterias en la posición actual
                                    bacteriaPositions[i][j] -= 1;
                                }
                            }
                        }

                        // Actualizar la visualización de la simulación
                        simulationPanel.repaint();
                    }

                    // Pausar por un corto período de tiempo para visualizar el movimiento de las bacterias
                    try {
                        Thread.sleep(500); // Pausar por 500 milisegundos
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // Hacer visible la ventana de simulación
        simulationWindow.setVisible(true);
    }
    private static int[] getRandomPosition() {
        int x = (int) (Math.random() * 20);
        int y = (int) (Math.random() * 20);
        return new int[]{x, y};
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
        JTextField foodSupplyPatternField = new JTextField();
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
        createFrame.add(new JLabel("Patrón de suministro de comida:"));
        createFrame.add(foodSupplyPatternField);
        createFrame.add(new JLabel("Dosis de comida (microgramos):"));
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