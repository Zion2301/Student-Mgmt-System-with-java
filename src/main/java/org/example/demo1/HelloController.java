    package org.example.demo1;
    
    import javafx.beans.property.SimpleStringProperty;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.scene.chart.BarChart;
    import javafx.scene.chart.LineChart;
    import javafx.scene.chart.XYChart;
    import javafx.scene.control.*;
    import javafx.scene.layout.AnchorPane;
    import java.net.URL;
    import java.rmi.MarshalledObject;
    import java.sql.*;
    import java.time.LocalDate;
    import java.util.HashMap;
    import java.util.Map;
    import java.util.ResourceBundle;
    
    public class HelloController implements Initializable {
        @FXML
        private Button homebutton;
        @FXML
        private Button studentbutton;
    
        @FXML
        private TextField searchshi;
        @FXML
        private Button coursebutton;
        @FXML
        private Button gradebutton;
        @FXML
        private AnchorPane homePane;
        @FXML
        private AnchorPane coursePane;
        @FXML
        private AnchorPane studentPane;
        @FXML
        private AnchorPane gradePane;
        @FXML
        private ComboBox<String> genderchoose;
        @FXML
        private ComboBox<String> coursechoose;
        @FXML
        private ComboBox<String> languagechoose;
        @FXML
        private TextField namefield;
        @FXML
        private DatePicker datefield;
        @FXML
        private TextField lecturerfield;
        @FXML
        private TextField infield;
        @FXML
        private TextField outfield;
        @FXML
        private TextField laptopfield;
        @FXML
        private TableView<Map<String, String>> largetable;
        @FXML
        private TableColumn<Map<String, String>, String> StudentNameColumn;
        @FXML
        private TableColumn<Map<String, String>, String> CourseColumn;
        @FXML
        private TableColumn<Map<String, String>, String> DateColumn;
        @FXML
        private TableColumn<Map<String, String>, String> LecturerColumn;
        @FXML
        private TableColumn<Map<String, String>, String> InColumn;
        @FXML
        private TableColumn<Map<String, String>, String> OutColumn;
        @FXML
        private TableColumn<Map<String, String>, String> LangColumn;
        @FXML
        private TableColumn<Map<String, String>, String> BrandColumn;
        @FXML
        private TableColumn<Map<String, String>, String> GenderColumn;
        @FXML
        private LineChart<String, Number> femalechart;
        @FXML
        private LineChart<String, Number> malechart;
        @FXML
        private BarChart<String, Number> totalchart;
        @FXML
        private Label totalLabel;
        @FXML
        private Label FemaleLabel;
        @FXML
        private Label MaleLabel;
        @FXML
        private ComboBox<String> coursestuff;
        @FXML
        private ComboBox<String> degree;
        @FXML
        private TableView<Map<String, String>> coursetable;
        @FXML
        private TableColumn<Map<String, String>, String> coursecolumn;
        @FXML
        private TableColumn<Map<String, String>, String> descriptioncolumn;
        @FXML
        private TableColumn<Map<String, String>, String> degreecolumn;
        @FXML
        private TextField description;
        @FXML
        private TextField StudentNumber_Input;
        @FXML
        private ComboBox<String> StudentGrade_courseinput;
        @FXML
        private ComboBox<String> StudentGrade_currentinput;
        @FXML
        private TextField StudentGrade_firstinput;
        @FXML
        private TextField StudentGrade_secondinput;
        @FXML
        private TextField StudentGrade_thirdinput;
        @FXML
        private TextField StudentGrade_fourthinput;
        @FXML
        private TableColumn<Map<String, String>, String> StudentGrade_number;
        @FXML
        private TableColumn<Map<String, String>, String> StudentGrade_course;
        @FXML
        private TableColumn<Map<String, String>, String> StudentGrade_Current;
        @FXML
        private TableColumn<Map<String, String>, String> StudentGrade_first;
        @FXML
        private TableColumn<Map<String, String>, String> StudentGrade_two;
        @FXML
        private TableColumn<Map<String, String>, String> StudentGrade_third;
        @FXML
        private TableColumn<Map<String, String>, String> StudentGrade_fourth;
        @FXML
        private TextField studentGrade_search;
        @FXML
        private TableView<Map<String, String>> StudentGrade_tableview;

    
        private int malecount = 0;
        private int femalecount = 0;
    
        private static final String DB_URL = "jdbc:postgresql://localhost:5432/Students DB";
        private static final String USER = "postgres";
        private static final String PASSWORD = "BigZ2301";
    
        private XYChart.Series<String, Number> maleseries = new XYChart.Series<>();
        private XYChart.Series<String, Number> femaleseries = new XYChart.Series<>();
        private XYChart.Series<String, Number> totalseries = new XYChart.Series<>();
        private ObservableList<Map<String, String>> studentObservableList = FXCollections.observableArrayList();
        private ObservableList<Map<String, String>> studentCourseList = FXCollections.observableArrayList();
        private ObservableList<Map<String, String>> studentGradeList = FXCollections.observableArrayList();

        private Map<String, String> selectedStudent;
        private Map<String, String> selectedCourses;
        private Map<String, String> selectedGrades;
    
        // Implementing the initialize method
        @Override
        public void initialize(URL location, ResourceBundle resources) {
            // Initialize TableView columns with map keys
            StudentNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("Name")));
            CourseColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("Course")));
            DateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("Date")));
            LecturerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("Lecturer")));
            InColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("TimeIn")));
            OutColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("TimeOut")));
            LangColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("CurrentLanguage")));
            BrandColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("LaptopBrand")));
            GenderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("Gender")));

            coursecolumn.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().get("CourseColumn")));
            descriptioncolumn.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().get("Description")));
            degreecolumn.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().get("Degree")));

            StudentGrade_number.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("NumberColumn")));
            StudentGrade_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("CourseColumn")));
            StudentGrade_Current.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("CurrentColumn")));
            StudentGrade_first.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("FirstColumn")));
            StudentGrade_two.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("SecondColumn")));
            StudentGrade_third.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("ThirdColumn")));
            StudentGrade_fourth.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("FourthColumn")));

            // Bind the ObservableList to the TableView
            largetable.setItems(studentObservableList);
            coursetable.setItems(studentCourseList);
            StudentGrade_tableview.setItems(studentGradeList);
    
            malechart.getData().add(maleseries);
            femalechart.getData().add(femaleseries);
            totalchart.getData().add(totalseries);
            totalseries.setName("Total Enrollments");
    
            // Initial load of charts
            loadEnrollmentCharts();
    
            // Set up ComboBox options
            setupComboBoxes();
    
            largetable.getSelectionModel().selectedItemProperty().addListener((obs, oldselection, newselection) -> {
                if (newselection != null) {
                    selectedStudent = newselection;
                    loadStudentDetails(selectedStudent);
                }
            });

            coursetable.getSelectionModel().selectedItemProperty().addListener((obscourse, oldcourse, newcourse) -> {
                 if (newcourse != null){
                     selectedCourses = newcourse;
                     loadCoursesDetails(selectedCourses);
                 }
            });

            StudentGrade_tableview.getSelectionModel().selectedItemProperty().addListener((obsgrades, oldgrades, newgrades) -> {
                if (newgrades != null){
                    selectedGrades = newgrades;
                    loadGradesDetails(selectedGrades);
                }
            });
    
            searchshi.textProperty().addListener((obs, old, newval)-> filterStudentList(newval));
            studentGrade_search.textProperty().addListener((obs, oldgrades, newgrades) -> filterGradesList(newgrades));
        }
    
        private void setupComboBoxes() {
            ObservableList<String> options = FXCollections.observableArrayList("Male", "Female");
            genderchoose.setItems(options);
            ObservableList<String> optionscourse = FXCollections.observableArrayList("ADSE", "ACCP", "ACNS", "AI and DataScience", "CyberSecurity", "AMSC", "ADM", "ShortTerm");
            coursechoose.setItems(optionscourse);
            ObservableList<String> optionslangauage = FXCollections.observableArrayList("HTML", "CSS", "JavaScript", "TypeScript", "C", "C++", "C#", "Java", "Python", "SQL", "Dart", "SpringBoot", "MongoDB", "PHP", "ReactJs", "R");
            languagechoose.setItems(optionslangauage);
            ObservableList<String> coursethings = FXCollections.observableArrayList("ADSE", "ACCP", "ACNS", "AI and DataScience", "CyberSecurity", "AMSC", "ADM", "ShortTerm");
            coursestuff.setItems(coursethings);
            ObservableList<String> optiondegree = FXCollections.observableArrayList("ADSE", "ACCP", "ACNS", "AI and DataScience", "CyberSecurity", "AMSC", "ADM", "ShortTerm");
            degree.setItems(optiondegree);
            ObservableList<String> coursegrade = FXCollections.observableArrayList("ADSE", "ACCP", "ACNS", "AI and DataScience", "CyberSecurity", "AMSC", "ADM", "ShortTerm");
            StudentGrade_courseinput.setItems(coursegrade);
            ObservableList<String> currentsem = FXCollections.observableArrayList("1st Semester", "2nd Semester", "3rd Semester", "4th Semester");
            StudentGrade_currentinput.setItems(currentsem);
        }
    
        public void switchpanes(ActionEvent e) {
            homePane.setVisible(e.getSource() == homebutton);
            coursePane.setVisible(e.getSource() == coursebutton);
            studentPane.setVisible(e.getSource() == studentbutton);
            gradePane.setVisible(e.getSource() == gradebutton);
        }

        @FXML
        private void addGrades() {
            String StudentNo = StudentNumber_Input.getText();
            String Course = StudentGrade_courseinput.getValue();
            String Current = StudentGrade_currentinput.getValue();
            String First = StudentGrade_firstinput.getText();
            String Second = StudentGrade_secondinput.getText();
            String Third = StudentGrade_thirdinput.getText();
            String Fourth = StudentGrade_fourthinput.getText();

            if (StudentNo.isEmpty() || Course == null || Current == null || First.isEmpty() || Second.isEmpty() || Third.isEmpty() || Fourth.isEmpty()){
                System.out.println("Input All Fields");
            }

            Map<String, String> GradesMap = new HashMap<>();
            GradesMap.put("NumberColumn", StudentNo);
            GradesMap.put("CourseColumn", Course);
            GradesMap.put("CurrentColumn", Current);
            GradesMap.put("FirstColumn", First);
            GradesMap.put("SecondColumn", Second);
            GradesMap.put("ThirdColumn", Third);
            GradesMap.put("FourthColumn", Fourth);
            studentGradeList.add(GradesMap);

        }
        @FXML
        private void addCourseStudents(){
            String course = coursestuff.getValue();
            String descriptionName = description.getText();
            String degreeName = degree.getValue();

            if (course == null || descriptionName.isEmpty() || degreeName == null){
                System.out.println("Input All Fields");
            }


            Map<String, String>CourseMap = new HashMap<>();

            CourseMap.put("CourseColumn", course);
            CourseMap.put("Description", descriptionName);
            CourseMap.put("Degree",degreeName);

            studentCourseList.add(CourseMap);


        }
    
        @FXML
        private void addStudents() {
            String Name = namefield.getText();
            String Course = coursechoose.getValue();
            String Lecturer = lecturerfield.getText();
            String TimeIn = infield.getText();
            LocalDate selectedDate = datefield.getValue();
            String TimeOut = outfield.getText();
            String CurrentLanguage = languagechoose.getValue();
            String LaptopBrand = laptopfield.getText();
            String Gender = genderchoose.getValue();
    
            // Ensure all required fields are filled
            if (Name.isEmpty() || Course == null || Lecturer.isEmpty() || TimeIn.isEmpty() || selectedDate == null || TimeOut.isEmpty() || CurrentLanguage == null || LaptopBrand.isEmpty() || Gender == null) {
                System.out.println("All fields are required.");
                return; // Show alert or message
            }
    
            // Convert LocalDate to java.sql.Date
            java.sql.Date sqlDate = java.sql.Date.valueOf(selectedDate);
    
            Map<String, String> StudentsMap = new HashMap<>();
            StudentsMap.put("Name", Name);
            StudentsMap.put("Course", Course);
            StudentsMap.put("Lecturer", Lecturer);
            StudentsMap.put("TimeIn", TimeIn);
            StudentsMap.put("TimeOut", TimeOut);
            StudentsMap.put("Date", sqlDate.toString());
            StudentsMap.put("CurrentLanguage", CurrentLanguage);
            StudentsMap.put("LaptopBrand", LaptopBrand);
            StudentsMap.put("Gender", Gender);
    
            studentObservableList.add(StudentsMap);
    
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                 PreparedStatement psmt = conn.prepareStatement(
                         "INSERT INTO Students (StudentName, Course, Date, Lecturer, TimeIn, TimeOut, CurrentLanguage, LaptopBrand, Gender) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                psmt.setString(1, Name);
                psmt.setString(2, Course);
                psmt.setDate(3, sqlDate);
                psmt.setString(4, Lecturer);
                psmt.setString(5, TimeIn);
                psmt.setString(6, TimeOut);
                psmt.setString(7, CurrentLanguage);
                psmt.setString(8, LaptopBrand);
                psmt.setString(9, Gender);
    
                int rowsAffected = psmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Student added successfully!");
                    updateGenderCount(Gender, true);
                    updateEnrollmentChart(); // Update enrollment chart after adding a student
                }
    
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
            }
        }
    
    
        private void loadStudentDetails(Map<String, String> student) {
            namefield.setText(student.get("Name"));
            coursechoose.setValue(student.get("Course"));
            lecturerfield.setText(student.get("Lecturer"));
            infield.setText(student.get("TimeIn"));
            datefield.setValue(LocalDate.parse(student.get("Date")));
            outfield.setText(student.get("TimeOut"));
            languagechoose.setValue(student.get("CurrentLanguage"));
            laptopfield.setText(student.get("LaptopBrand"));
            genderchoose.setValue(student.get("Gender"));
    
    
        }

        private void loadCoursesDetails(Map<String,String> courses){
            coursestuff.setValue(courses.get("CourseColumn"));
            description.setText(courses.get("Description"));
            degree.setValue(courses.get("Degree"));
        }

        private void loadGradesDetails(Map<String, String> grades){
            StudentNumber_Input.setText(grades.get("NumberColumn"));
            StudentGrade_courseinput.setValue(grades.get("CourseColumn"));
            StudentGrade_currentinput.setValue(grades.get("CurrentValue"));
            StudentGrade_firstinput.setText(grades.get("FirstColumn"));
            StudentGrade_secondinput.setText(grades.get("SecondColumn"));
            StudentGrade_thirdinput.setText(grades.get("ThirdColumn"));
            StudentGrade_fourthinput.setText(grades.get("FourthColumn"));
        }
    
    
        private void loadEnrollmentCharts() {
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT Gender, COUNT(*) AS GenderCount FROM Students GROUP BY Gender")) {
    
                malecount = 0;
                femalecount = 0;
    
                while (rs.next()) {
                    String gender = rs.getString("Gender");
                    int count = rs.getInt("GenderCount");
                    if ("Male".equals(gender)) {
                        malecount = count;
                    } else if ("Female".equals(gender)) {
                        femalecount = count;
                    }
                }
    
                MaleLabel.setText(String.valueOf(malecount));
                FemaleLabel.setText(String.valueOf(femalecount));
                totalLabel.setText(String.valueOf(malecount + femalecount));
    
                maleseries.getData().clear();
                femaleseries.getData().clear();
                maleseries.getData().add(new XYChart.Data<>("Male", malecount));
                femaleseries.getData().add(new XYChart.Data<>("Female", femalecount));
    
                updateEnrollmentChart(); // Update total enrollment chart
    
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
            }
        }
    
        private void updateGenderCount(String gender, boolean isNew) {
            if (isNew) {
                if ("Male".equals(gender)) {
                    malecount++;
                } else if ("Female".equals(gender)) {
                    femalecount++;
                }
                MaleLabel.setText(formatCount(malecount));
                FemaleLabel.setText(formatCount(femalecount));
                totalLabel.setText(formatCount(malecount + femalecount));
    
                // Update the chart data
                maleseries.getData().clear();
                femaleseries.getData().clear();
                maleseries.getData().add(new XYChart.Data<>("Male", malecount));
                femaleseries.getData().add(new XYChart.Data<>("Female", femalecount));
            }
        }

        @FXML
        private void updateCourse(){
            if (selectedCourses == null){
                System.out.println("No Course Selected");
                return;
            }

            String newCourse = coursestuff.getValue();
            String newDescription = description.getText();
            String newDegree = degree.getValue();

            selectedCourses.put("CourseColumn", newCourse);
            selectedCourses.put("Description", newDescription);
            selectedCourses.put("Degree", newDegree);
            coursetable.refresh();
        }

        @FXML
        private void updateGrade(){
            if (selectedGrades == null){
                System.out.println("No Grades Selected");
                return;
            }

            String newNumber = StudentNumber_Input.getText();
            String newCourse = StudentGrade_courseinput.getValue();
            String newCurrent = StudentGrade_currentinput.getValue();
            String newFirst = StudentGrade_firstinput.getText();
            String newSecond = StudentGrade_secondinput.getText();
            String newThird = StudentGrade_thirdinput.getText();
            String newFourth = StudentGrade_fourthinput.getText();

            selectedGrades.put("NumberColumn", newNumber);
            selectedGrades.put("CourseColumn", newCourse);
            selectedGrades.put("CurrentColumn", newCurrent);
            selectedGrades.put("FirstColumn", newFirst);
            selectedGrades.put("SecondColumn", newSecond);
            selectedGrades.put("ThirdColumn", newThird);
            selectedGrades.put("FourthColumn",newFourth);
            StudentGrade_tableview.refresh();
        }
        @FXML
        private void updateStudent() {
            if (selectedStudent == null) {
                System.out.println("No student selected.");
                return;
            }
    
            String newName = namefield.getText();
            String newCourse = coursechoose.getValue();
            String newLecturer = lecturerfield.getText();
            String newTimeIn = infield.getText();
            LocalDate newDate = datefield.getValue();
            String newTimeOut = outfield.getText();
            String newLanguage = languagechoose.getValue();
            String newLaptopBrand = laptopfield.getText();
            String newGender = genderchoose.getValue();
    
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                 PreparedStatement psmt = conn.prepareStatement(
                         "UPDATE Students SET StudentName = ?, Course = ?, Date = ?, Lecturer = ?, TimeIn = ?, TimeOut = ?, CurrentLanguage = ?, LaptopBrand = ?, Gender = ? WHERE StudentName = ? AND Date = ?")) {
                psmt.setString(1, newName);
                psmt.setString(2, newCourse);
                psmt.setDate(3, Date.valueOf(newDate));
                psmt.setString(4, newLecturer);
                psmt.setString(5, newTimeIn);
                psmt.setString(6, newTimeOut);
                psmt.setString(7, newLanguage);
                psmt.setString(8, newLaptopBrand);
                psmt.setString(9, newGender);
                psmt.setString(10, selectedStudent.get("Name")); // Original Name
                psmt.setDate(11, Date.valueOf(selectedStudent.get("Date"))); // Original Date
    
                int rowsAffected = psmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Student updated successfully!");
    
                    // Update ObservableList to reflect changes in UI
                    selectedStudent.put("Name", newName);
                    selectedStudent.put("Course", newCourse);
                    selectedStudent.put("Date", newDate.toString());
                    selectedStudent.put("Lecturer", newLecturer);
                    selectedStudent.put("TimeIn", newTimeIn);
                    selectedStudent.put("TimeOut", newTimeOut);
                    selectedStudent.put("CurrentLanguage", newLanguage);
                    selectedStudent.put("LaptopBrand", newLaptopBrand);
                    selectedStudent.put("Gender", newGender);
                    largetable.refresh();
    
                    loadEnrollmentCharts(); // Update charts
                }
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
            }
        }
    
        private void updateEnrollmentChart() {
            // Update the total enrollment data for the bar chart
            totalseries.getData().clear(); // Clear previous data
            totalseries.getData().add(new XYChart.Data<>("Male", malecount));
            totalseries.getData().add(new XYChart.Data<>("Female", femalecount));
            totalseries.getData().add(new XYChart.Data<>("Total", malecount + femalecount));
        }
    
        private String formatCount(int count) {
            if (count >= 1_000_000) {
                return String.format("%.1fm", count / 1_000_000.0);
            } else if (count >= 1_000) {
                return String.format("%.1fk", count / 1_000.0);
            } else {
                return String.valueOf(count);
            }
        }
    
        private void filterStudentList(String searchTerm) {
            if (searchTerm == null || searchTerm.isEmpty()) {
                // If search term is empty, show all students
                largetable.setItems(studentObservableList);
            } else {
                // Create a filtered list based on the search term
                ObservableList<Map<String, String>> filteredList = FXCollections.observableArrayList();
    
                for (Map<String, String> student : studentObservableList) {
                    String name = student.get("Name").toLowerCase();
                    String course = student.get("Course").toLowerCase();
    
                    // Check if the search term matches the student name or course
                    if (name.contains(searchTerm.toLowerCase()) || course.contains(searchTerm.toLowerCase())) {
                        filteredList.add(student);
                    }
                }
                largetable.setItems(filteredList);
            }
        }

        private void filterGradesList(String searchTerm) {
            if (searchTerm == null || searchTerm.isEmpty()) {
                // If search term is empty, show all students
                StudentGrade_tableview.setItems(studentGradeList);
            } else {
                // Create a filtered list based on the search term
                ObservableList<Map<String, String>> filteredList = FXCollections.observableArrayList();

                for (Map<String, String> student : studentGradeList) {
                    String number = student.get("NumberColumn").toLowerCase();
                    String course = student.get("CourseColumn").toLowerCase();

                    // Check if the search term matches the student name or course
                    if (number.contains(searchTerm.toLowerCase()) || course.contains(searchTerm.toLowerCase())) {
                        filteredList.add(student);
                    }
                }
                StudentGrade_tableview.setItems(filteredList);
            }
        }
    }
