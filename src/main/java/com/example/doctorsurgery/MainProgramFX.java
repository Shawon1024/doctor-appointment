package com.example.doctorsurgery;

import javafx.application.Platform;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Pair;
import java.io.*;
import java.util.Optional;

public class MainProgramFX extends Application
{
    private static final DoctorList doctorList = new DoctorList();
    private static final PatientList patientList = new PatientList();
    private static final AppointmentList appointmentList = new AppointmentList();

    private static final File dataFile1 = new File("DoctorData.dat");
    private static final File dataFile2 = new File("PatientData.dat");
    private static final File dataFile3 = new File("AppointmentData.dat");

    public static void main(String[] args)
    {
        launch(args);
    }

    public static class IntegerField extends TextField
    {

        public IntegerField()
        {
            super();
        }

        @Override
        public void replaceText(int start, int end, String text)
        {
            if (text.matches("[0-9]*"))
            {
                super.replaceText(start, end, text);
            }
        }

        @Override
        public void replaceSelection(String text)
        {
            if (text.matches("[0-9]*"))
            {
                super.replaceSelection(text);
            }
        }

        public int getValue()
        {
            String text = getText();

            if (text.isEmpty())
            {
                return 0;
            }

            else
            {
                return Integer.parseInt(text);
            }
        }
    }

    @Override
    public void start(Stage primaryStage)
    {
        if (!dataFile1.exists()  && !dataFile2.exists() && !dataFile3.exists())
        {
            saveFile();
        }

        loadFile();

        Label titleLabel = new Label("Welcome to the Doctor's Surgery Management System");
        titleLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 24px; -fx-text-fill: #006DB0; -fx-font-weight: bold; -fx-background-color: white; -fx-border-color: white; -fx-border-width: 3px;");

        Label optionLabel = new Label(" Please Select an Option: ");
        optionLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-font-weight: bold;");

        Button addDoctorButton = new Button("Add Doctor");
        addDoctorButton.setOnAction(e -> addDoctor());
        addDoctorButton.setPrefSize(155, 30);

        Button displayDoctorButton = new Button("View All Doctor");
        displayDoctorButton.setOnAction(e -> displayDoctor());
        displayDoctorButton.setPrefSize(155, 30);

        Button updateDoctorButton = new Button("Update Doctor Info");
        updateDoctorButton.setOnAction(e -> updateDoctor());
        updateDoctorButton.setPrefSize(155, 30);

        Button deleteDoctorButton = new Button("Delete Doctor");
        deleteDoctorButton.setOnAction(e -> deleteDoctor());
        deleteDoctorButton.setPrefSize(155, 30);

        Button addPatientButton = new Button("Add Patient");
        addPatientButton.setOnAction(e -> addPatient());
        addPatientButton.setPrefSize(155, 30);

        Button displayPatientButton = new Button("View All Patient");
        displayPatientButton.setOnAction(e -> displayPatient());
        displayPatientButton.setPrefSize(155, 30);

        Button updatePatientButton = new Button("Update Patient Info");
        updatePatientButton.setOnAction(e -> updatePatient());
        updatePatientButton.setPrefSize(155, 30);

        Button deletePatientButton = new Button("Delete Patient");
        deletePatientButton.setOnAction(e -> deletePatient());
        deletePatientButton.setPrefSize(155, 30);

        Button addAppointmentButton = new Button("Add Appointment");
        addAppointmentButton.setOnAction(e -> addAppointment());
        addAppointmentButton.setPrefSize(155, 30);

        Button displayAppointmentButton = new Button("View All Appointment");
        displayAppointmentButton.setOnAction(e -> displayAppointment());
        displayAppointmentButton.setPrefSize(155, 30);

        Button updateAppointmentButton = new Button("Update Appointment Info");
        updateAppointmentButton.setOnAction(e -> updateAppointment());
        updateAppointmentButton.setPrefSize(155, 30);

        Button deleteAppointmentButton = new Button("Delete an Appointment");
        deleteAppointmentButton.setOnAction(e -> deleteAppointment());
        deleteAppointmentButton.setPrefSize(155, 30);

        Button saveExitButton = new Button("Save & Exit");
        saveExitButton.setOnAction(e ->
        {
            saveFile();
            primaryStage.close();
        });
        saveExitButton.setPrefSize(150, 50);

        GridPane root = new GridPane();

        root.setAlignment(Pos.CENTER);
        root.setHgap(20);
        root.setVgap(20);
        root.setPrefSize(200, 200);
        root.setStyle("-fx-background-color: #006DB0;");

        root.add(titleLabel, 0, 1, 4, 1);
        root.add(optionLabel, 0, 2, 4, 1);
        root.add(addDoctorButton, 0, 3);
        root.add(displayDoctorButton, 1, 3);
        root.add(updateDoctorButton, 2, 3);
        root.add(deleteDoctorButton, 3, 3);
        root.add(addPatientButton, 0, 4);
        root.add(displayPatientButton, 1, 4);
        root.add(updatePatientButton, 2, 4);
        root.add(deletePatientButton, 3, 4);
        root.add(addAppointmentButton, 0, 5);
        root.add(displayAppointmentButton, 1, 5);
        root.add(updateAppointmentButton, 2, 5);
        root.add(deleteAppointmentButton, 3, 5);

        root.add(saveExitButton, 3, 0, 1, 1);

        Scene scene = new Scene(root, 800, 400);

        primaryStage.setTitle("Doctor Surgery Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // METHOD FOR ADDING A NEW DOCTOR TO THE LIST
    public static void addDoctor()
    {
        Stage stage = new Stage();

        Label nameLabel = new Label("Enter Doctor Name:");
        TextField nameTextField = new TextField();

        Label specialityLabel = new Label("Enter Doctor Speciality:");
        TextField specialityTextField = new TextField();

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e ->
        {
            String name = nameTextField.getText().trim();
            String speciality = specialityTextField.getText().trim();

            if (name.matches("[a-z A-Z]+") && speciality.matches("[a-z A-Z]+"))
            {
                Doctor newDoctor = new Doctor(0, name, speciality);
                doctorList.addDoctor(newDoctor);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert: Doctor Added Successfully!!!");
                alert.setHeaderText(null);
                alert.setContentText("Alert: Doctor added successfully!!!");
                alert.showAndWait();

                stage.close();
            }

            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!!!");
                alert.setHeaderText(null);
                alert.setContentText("Please enter valid input (a-z, A-Z only)!");
                alert.showAndWait();
            }
        });

        VBox root = new VBox();
        root.setSpacing(20);
        root.getChildren().addAll(nameLabel, nameTextField, specialityLabel, specialityTextField, saveButton);

        Scene scene = new Scene(root, 300, 200);

        stage.setTitle("Add a New Doctor");
        stage.setScene(scene);
        stage.show();
    }

    // METHOD FOR DISPLAYING ALL DOCTOR FROM THE LIST
    public static void displayDoctor()
    {
        VBox root = new VBox();
        Label headerLabel = new Label("List of All Doctor's:");
        headerLabel.setStyle("-fx-font-weight: bold;");
        root.getChildren().addAll(headerLabel, new Label("-----------------------"));

        for (Doctor doctor : doctorList.getDoctors())
        {
            Label doctorLabel = new Label(doctor.toString());
            root.getChildren().add(doctorLabel);
        }

        ScrollPane scrollPane3 = new ScrollPane(root);
        scrollPane3.setPrefHeight(300);

        Scene scene = new Scene(scrollPane3, 300, 200);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    // METHOD FOR UPDATING A DOCTOR BY ID
    public static void updateDoctor()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Update a Doctor by ID");
        dialog.setHeaderText("Enter Doctor ID to Update:");
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent())
        {
            String strIdToUpdate = result.get();

            try
            {
                int idToUpdate = Integer.parseInt(strIdToUpdate);
                Doctor doctorToUpdate = doctorList.getDoctorById(idToUpdate);

                if (doctorToUpdate == null)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error: Doctor Not Found!!!");
                    alert.showAndWait();
                }

                else
                {
                    Dialog<Pair<String, String>> updateDialog = new Dialog<>();
                    updateDialog.setTitle("Update Doctor Info");
                    updateDialog.setHeaderText("Enter New Doctor Info:");

                    ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
                    updateDialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

                    GridPane gridPane = new GridPane();
                    gridPane.setHgap(10);
                    gridPane.setVgap(10);
                    gridPane.setPadding(new Insets(20, 150, 10, 10));

                    TextField newNameField = new TextField();
                    newNameField.setPromptText("New Doctor Name");
                    TextField newSpecialityField = new TextField();
                    newSpecialityField.setPromptText("New Speciality");

                    gridPane.add(new Label("New Doctor Name:"), 0, 0);
                    gridPane.add(newNameField, 1, 0);
                    gridPane.add(new Label("New Speciality:"), 0, 1);
                    gridPane.add(newSpecialityField, 1, 1);

                    updateDialog.getDialogPane().setContent(gridPane);

                    Platform.runLater(newNameField::requestFocus);

                    updateDialog.setResultConverter(dialogButton ->
                    {
                        if (dialogButton == updateButtonType) {
                            String newName = newNameField.getText();
                            String newSpeciality = newSpecialityField.getText();
                            return new Pair<>(newName, newSpeciality);
                        }
                        return null;
                    });

                    Optional<Pair<String, String>> updateResult = updateDialog.showAndWait();

                    updateResult.ifPresent(newValues ->
                    {
                        String newName = newValues.getKey();
                        String newSpeciality = newValues.getValue();

                        if (!newName.isEmpty())
                        {
                            doctorToUpdate.setName(newName);
                        }

                        if (!newSpeciality.isEmpty())
                        {
                            doctorToUpdate.setSpeciality(newSpeciality);
                        }

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Doctor Updated Successfully!!!");
                        alert.showAndWait();
                    });
                }
            }

            catch (NumberFormatException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error: Invalid Doctor ID!!!");
                alert.showAndWait();
            }
        }
    }

    // METHOD FOR DELETING A DOCTOR FROM THE LIST
    public static void deleteDoctor()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete a Doctor by ID ");
        dialog.setHeaderText("Enter Doctor ID to Delete: ");
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent())
        {
            try
            {
                int did = Integer.parseInt(result.get());
                Doctor doctor = doctorList.getDoctorById(did);

                if (doctor != null)
                {
                    doctorList.deleteDoctor(did);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Doctor ID " + did + " Deleted Successfully!!!");
                    alert.showAndWait();
                }

                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error: Doctor ID Not Found!!!");
                    alert.showAndWait();
                }
            }

            catch (NumberFormatException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error: Invalid Doctor ID!!!");
                alert.showAndWait();
            }
        }
    }

    // METHOD FOR ADDING A NEW PATIENT TO THE LIST
    public static void addPatient()
    {
        Stage stage = new Stage();

        Label nameLabel = new Label("Enter Patient Name:");
        TextField nameTextField = new TextField();

        Label ageLabel = new Label("Enter Patient Age:");
        IntegerField ageTextField = new IntegerField();

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e ->
        {
            String name = nameTextField.getText().trim();
            int age = ageTextField.getValue();

            if (name.matches("[a-z A-Z]+"))
            {
                Patient newPatient = new Patient(0, name, age);
                patientList.addPatient(newPatient);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert: Patient Added Successfully!!!");
                alert.setHeaderText(null);
                alert.setContentText("Alert: Patient added successfully!!!");
                alert.showAndWait();

                stage.close();
            }

            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!!!");
                alert.setHeaderText(null);
                alert.setContentText("Please enter valid input (a-z, A-Z only)!");
                alert.showAndWait();
            }
        });

        VBox root = new VBox();
        root.setSpacing(20);
        root.getChildren().addAll(nameLabel, nameTextField, ageLabel, ageTextField, saveButton);

        Scene scene = new Scene(root, 300, 200);

        stage.setTitle("Add a New Patient");
        stage.setScene(scene);
        stage.show();
    }

    // METHOD FOR DISPLAYING ALL PATIENT FROM THE LIST
    public static void displayPatient()
    {
        VBox root = new VBox();
        Label headerLabel = new Label("List of All Patient's:");
        headerLabel.setStyle("-fx-font-weight: bold;");
        root.getChildren().addAll(headerLabel, new Label("-----------------------"));

        for (Patient patient : patientList.getPatients())
        {
            Label patientLabel = new Label(patient.toString());
            root.getChildren().add(patientLabel);
        }

        ScrollPane scrollPane2 = new ScrollPane(root);
        scrollPane2.setPrefHeight(300);

        Scene scene = new Scene(scrollPane2, 300, 200);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    // METHOD FOR UPDATING A PATIENT BY ID
    public static void updatePatient()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Update a Patient by ID");
        dialog.setHeaderText("Enter Patient ID to Update:");
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent())
        {
            String strIdToUpdate = result.get();

            try
            {
                int idToUpdate = Integer.parseInt(strIdToUpdate);
                Patient patientToUpdate = patientList.getPatientById(idToUpdate);

                if (patientToUpdate == null)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error: Patient Not Found!!!");
                    alert.showAndWait();
                }

                else
                {
                    Dialog<Pair<String, Integer>> updateDialog = new Dialog<>();
                    updateDialog.setTitle("Update Patient Info");
                    updateDialog.setHeaderText("Enter New Patient Info:");

                    ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
                    updateDialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

                    GridPane gridPane = new GridPane();
                    gridPane.setHgap(10);
                    gridPane.setVgap(10);
                    gridPane.setPadding(new Insets(20, 150, 10, 10));

                    TextField newNameField = new TextField();
                    newNameField.setPromptText("New Patient Name");
                    IntegerField newAgeField = new IntegerField();
                    newAgeField.setPromptText("New Age");

                    gridPane.add(new Label("New Patient Name:"), 0, 0);
                    gridPane.add(newNameField, 1, 0);
                    gridPane.add(new Label("New Age:"), 0, 1);
                    gridPane.add(newAgeField, 1, 1);

                    updateDialog.getDialogPane().setContent(gridPane);

                    Platform.runLater(newNameField::requestFocus);

                    updateDialog.setResultConverter(dialogButton ->
                    {
                        if (dialogButton == updateButtonType) {
                            String newName = newNameField.getText();
                            int newAge = newAgeField.getValue();
                            return new Pair<>(newName, newAge);
                        }
                        return null;
                    });

                    Optional<Pair<String, Integer>> updateResult = updateDialog.showAndWait();

                    updateResult.ifPresent(newValues ->
                    {
                        String newName = newValues.getKey();
                        int newAge = newValues.getValue();

                        if (!newName.isEmpty())
                        {
                            patientToUpdate.setName(newName);
                        }

                        patientToUpdate.setAge(newAge);

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Patient Updated Successfully!!!");
                        alert.showAndWait();
                    });
                }
            }

            catch (NumberFormatException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error: Invalid Patient ID!!!");
                alert.showAndWait();
            }
        }
    }

    // METHOD FOR DELETING A PATIENT FROM THE LIST
    public static void deletePatient()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete a Patient by ID ");
        dialog.setHeaderText("Enter Patient ID to Delete: ");
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent())
        {
            try
            {
                int pid = Integer.parseInt(result.get());
                Patient patient = patientList.getPatientById(pid);

                if (patient != null)
                {
                    patientList.deletePatient(pid);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Alert: Patient ID " + pid + " Deleted Successfully!!!");
                    alert.showAndWait();
                }

                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error: Patient ID Not Found!!!");
                    alert.showAndWait();
                }
            }

            catch (NumberFormatException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error: Invalid Patient ID!!!");
                alert.showAndWait();
            }
        }
    }

    // METHOD FOR ADDING A NEW APPOINTMENT TO THE LIST
    public static void addAppointment()
    {
        TextInputDialog doctorDialog = new TextInputDialog();
        doctorDialog.setTitle("Create Appointment");
        doctorDialog.setHeaderText("Enter Doctor ID:");
        Optional<String> doctorResult = doctorDialog.showAndWait();

        if (doctorResult.isPresent())
        {
            int doctorId = Integer.parseInt(doctorResult.get());
            Doctor doctor = doctorList.getDoctorById(doctorId);

            if (doctor == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error: Doctor ID " + doctorId + " Doesn't Exist!!!");
                alert.showAndWait();
                return;
            }

            TextInputDialog patientDialog = new TextInputDialog();
            patientDialog.setHeaderText("Enter Patient ID:");
            Optional<String> patientResult = patientDialog.showAndWait();

            if (patientResult.isPresent())
            {
                int patientId = Integer.parseInt(patientResult.get());
                Patient patient = patientList.getPatientById(patientId);

                if (patient == null)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error: Patient ID " + patientId + " Doesn't Exist!!!");
                    alert.showAndWait();
                    return;
                }

                TextInputDialog diagnosisDialog = new TextInputDialog();
                diagnosisDialog.setHeaderText("Enter Diagnosis:");
                Optional<String> diagnosisResult = diagnosisDialog.showAndWait();

                while (diagnosisResult.isPresent() && !diagnosisResult.get().matches("[a-z A-Z]+"))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error: a-z and/or A-Z Letter Only!!!");
                    alert.showAndWait();
                    diagnosisResult = diagnosisDialog.showAndWait();
                }

                if (diagnosisResult.isPresent())
                {
                    String diagnosis = diagnosisResult.get();

                    TextInputDialog departmentDialog = new TextInputDialog();
                    departmentDialog.setHeaderText("Enter Department:");
                    Optional<String> departmentResult = departmentDialog.showAndWait();

                    while (departmentResult.isPresent() && !departmentResult.get().matches("[a-z A-Z]+"))
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Error: a-z and/or A-Z Letter Only!!!");
                        alert.showAndWait();
                        departmentResult = departmentDialog.showAndWait();
                    }

                    if (departmentResult.isPresent())
                    {
                        String department = departmentResult.get();

                        Appointment appointment = new Appointment(0, doctor, patient, diagnosis, department);
                        appointmentList.addAppointment(appointment);

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Alert: Appointment ID " + appointment.getAppointmentId() + " Created Successfully!!!");
                        alert.showAndWait();
                    }
                }
            }
        }
    }

    // METHOD FOR DISPLAYING ALL APPOINTMENT FROM THE LIST
    public static void displayAppointment()
    {
        VBox root = new VBox();
        Label headerLabel = new Label("List of All Appointment's:");
        headerLabel.setStyle("-fx-font-weight: bold;");
        root.getChildren().addAll(headerLabel, new Label("------------------------------"));

        for (Appointment appointment : appointmentList.getAppointment())
        {
            Label appointmentLabel = new Label(appointment.toString());
            root.getChildren().add(appointmentLabel);
        }

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setPrefHeight(300);

        Scene scene = new Scene(scrollPane, 400, 300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    // METHOD FOR UPDATING A APPOINTMENT BY ID
    public static void updateAppointment()
    {
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Update Appointment");
        idDialog.setHeaderText("Enter Appointment ID:");
        Optional<String> idResult = idDialog.showAndWait();

        if (idResult.isEmpty())
        {
            return;
        }

        int id;

        try
        {
            id = Integer.parseInt(idResult.get());
        }

        catch (NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Appointment ID");
            alert.setContentText("Please enter a valid integer ID.");
            alert.showAndWait();
            return;
        }

        Appointment appointmentToUpdate = appointmentList.getAppointmentById(id);

        if (appointmentToUpdate == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Appointment Not Found");
            alert.setContentText("No appointment with the specified ID was found.");
            alert.showAndWait();
            return;
        }

        Dialog<Pair<Integer, Integer>> idsDialog = new Dialog<>();
        idsDialog.setTitle("Update Appointment");
        idsDialog.setHeaderText("Enter New Doctor and Patient IDs:");

        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        idsDialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField doctorIdField = new TextField();
        doctorIdField.setPromptText("Doctor ID");
        TextField patientIdField = new TextField();
        patientIdField.setPromptText("Patient ID");

        gridPane.add(new Label("Doctor ID:"), 0, 0);
        gridPane.add(doctorIdField, 1, 0);
        gridPane.add(new Label("Patient ID:"), 0, 1);
        gridPane.add(patientIdField, 1, 1);

        Node updateButton = idsDialog.getDialogPane().lookupButton(updateButtonType);
        updateButton.setDisable(true);

        doctorIdField.textProperty().addListener((observable, oldValue, newValue) ->
                updateButton.setDisable(newValue.trim().isEmpty()));

        idsDialog.getDialogPane().setContent(gridPane);

        Platform.runLater(doctorIdField::requestFocus);

        idsDialog.setResultConverter(dialogButton ->
        {
            if (dialogButton == updateButtonType)
            {
                int doctorId = Integer.parseInt(doctorIdField.getText());
                int patientId = Integer.parseInt(patientIdField.getText());
                return new Pair<>(doctorId, patientId);
            }
            return null;
        });

        Optional<Pair<Integer, Integer>> idsResult = idsDialog.showAndWait();

        if (idsResult.isEmpty())
        {
            return;
        }


        TextInputDialog diagnosisDialog = new TextInputDialog(appointmentToUpdate.getDiagnosis());
        diagnosisDialog.setTitle("Update Appointment");
        diagnosisDialog.setHeaderText("Enter New Diagnosis:");

        diagnosisDialog.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z ]*")) {
                diagnosisDialog.getEditor().setText(oldValue);
            }
        });

        Optional<String> diagnosisResult = diagnosisDialog.showAndWait();

        if (diagnosisResult.isPresent()) {
            TextInputDialog departmentDialog = new TextInputDialog(appointmentToUpdate.getDepartment());
            departmentDialog.setTitle("Update Appointment");
            departmentDialog.setHeaderText("Enter New Department:");

            departmentDialog.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("[a-zA-Z ]*")) {
                    departmentDialog.getEditor().setText(oldValue);
                }
            });

            Optional<String> departmentResult = departmentDialog.showAndWait();

            if (departmentResult.isPresent()) {
                String newDiagnosis = diagnosisResult.get();
                String newDepartment = departmentResult.get();

                appointmentToUpdate.setDiagnosis(newDiagnosis);
                appointmentToUpdate.setDepartment(newDepartment);
            }
        }
    }

    // METHOD FOR DELETING AN APPOINTMENT FROM THE LIST
    public static void deleteAppointment()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete an Appointment by ID ");
        dialog.setHeaderText("Enter Appointment ID to Delete: ");
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent())
        {
            try
            {
                int aid = Integer.parseInt(result.get());
                Appointment appointment = appointmentList.getAppointmentById(aid);

                if (appointment != null)
                {
                    appointmentList.deleteAppointment(aid);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Appointment ID " + aid + " Deleted Successfully!!!");
                    alert.showAndWait();
                }

                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error: Appointment ID Not Found!!!");
                    alert.showAndWait();
                }
            }

            catch (NumberFormatException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error: Invalid Appointment ID!!!");
                alert.showAndWait();
            }
        }
    }

    // METHOD TO LOAD DATA FILE
    public static void loadFile()
    {
        try
        {
            // METHOD TO LOAD DOCTOR DATA
            FileReader doctorReader = new FileReader("DoctorData.dat");
            BufferedReader doctorBufferedReader = new BufferedReader(doctorReader);

            String doctorLine;

            while ((doctorLine = doctorBufferedReader.readLine()) != null)
            {
                String[] parts = doctorLine.split(",");

                int doctorId = Integer.parseInt(parts[0]);
                String doctorName = parts[1];
                String doctorSpecialty = parts[2];

                Doctor doctor = new Doctor(doctorId, doctorName, doctorSpecialty);
                doctorList.addDoctor(doctor);
            }

            doctorBufferedReader.close();

            System.out.println();
            System.out.println("Alert: Doctor Data Loaded Successfully!!!");
            System.out.println();

            // METHOD TO LOAD PATIENT DATA
            FileReader patientReader = new FileReader("PatientData.dat");
            BufferedReader patientBufferedReader = new BufferedReader(patientReader);

            String patientLine;

            while ((patientLine = patientBufferedReader.readLine()) != null)
            {
                String[] parts = patientLine.split(",");

                int patientId = Integer.parseInt(parts[0]);
                String patientName = parts[1];
                int patientAge = Integer.parseInt(parts[0]);

                Patient patient = new Patient(patientId, patientName, patientAge);
                patientList.addPatient(patient);
            }

            patientBufferedReader.close();

            System.out.println("Alert: Patient Data Loaded Successfully!!!");
            System.out.println();

            // METHOD TO LOAD APPOINTMENT DATA
            FileReader appointmentReader = new FileReader("AppointmentData.dat");
            BufferedReader appointmentBufferedReader = new BufferedReader(appointmentReader);

            String appointmentLine;

            while ((appointmentLine = appointmentBufferedReader.readLine()) != null)
            {
                String[] parts = appointmentLine.split(",");

                int appointmentId = Integer.parseInt(parts[0]);
                int doctorId = Integer.parseInt(parts[0]);
                int patientId = Integer.parseInt(parts[0]);
                String diagnosis = parts[1];
                String department = parts[2];

                Doctor doctor = doctorList.getDoctorById(doctorId);
                Patient patient = patientList.getPatientById(patientId);

                Appointment appointment = new Appointment(appointmentId, doctor, patient, diagnosis, department);
                appointmentList.addAppointment(appointment);
            }

            appointmentBufferedReader.close();

            System.out.println("Alert: Appointment Data Loaded Successfully!!!");
            System.out.println();
        }

        catch (IOException e)
        {
            System.out.println();
            System.out.println("Error: Couldn't Load Data!!!");
            e.printStackTrace();
        }
    }

    // METHOD TO SAVE DATA FILE
    public static void saveFile()
    {
        try
        {
            File file1 = new File("DoctorData.dat");
            FileWriter writer1 = new FileWriter(file1);

            for (Doctor doctor : doctorList.getDoctors())
            {
                writer1.write(doctor.getId() + ", " + doctor.getName() + ", " + doctor.getSpeciality() + " \n");
            }

            writer1.close();

            File file2 = new File("PatientData.dat");
            FileWriter writer2 = new FileWriter(file2);

            for (Patient patient : patientList.getPatients())
            {
                writer2.write(patient.getId() + ", " + patient.getName() + ", " + patient.getAge() + " \n");
            }

            writer2.close();

            File file3 = new File("AppointmentData.dat");
            FileWriter writer3 = new FileWriter(file3);

            for (Appointment appointment : appointmentList.getAppointment())
            {
                writer3.write(appointment.getAppointmentId() + ", " + appointment.getDoctor().getId() + ", " + appointment.getPatient().getId() + ", " + appointment.getDiagnosis() + ", " + appointment.getDepartment() + " \n\n");
            }

            writer3.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Alert: Data Saved Successfully!!!");
            alert.showAndWait();
        }

        catch (IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while saving the file.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
}
