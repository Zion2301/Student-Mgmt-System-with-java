package org.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;



public class HelloController{
     @FXML
     private Button homebutton;

     @FXML
     private Button studentbutton;

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
     private LineChart<Number, Number> femalechart;

     @FXML
     private LineChart<Number, Number> malechart;

     @FXML
     private BarChart<Number, Number> totalchart;

     @FXML
     private Label totalLabel;

     @FXML
     private Label FemaleLabelx;

     @FXML
     private Label MaleLabel;







     public void initialize(){
          ObservableList<String> options = FXCollections.observableArrayList(
                  "Male", "Female"
          );

          genderchoose.setItems(options);

          ObservableList<String> optionscourse = FXCollections.observableArrayList(
                  "ADSE", "ACCP", "ACNS", "AI and DataScience", "CyberSecurity", "AMSC", "ADM", "AMSC","ShortTerm"
          );

          coursechoose.setItems(optionscourse);

          ObservableList<String> optionslangauage = FXCollections.observableArrayList(
                  "HTML", "CSS", "JavaScript","TypeScript","C", "C++", "C#","Java","Python","SQL","Dart","SpringBoot","MongoDB","PHP","ReactJs","R"
          );

          languagechoose.setItems(optionslangauage);


     }

     public void switchpanes(ActionEvent e){
          if (e.getSource() == homebutton){
               homePane.setVisible(true);
               coursePane.setVisible(false);
               studentPane.setVisible(false);
               gradePane.setVisible(false);
          } else if(e.getSource() == studentbutton) {
               homePane.setVisible(false);
               coursePane.setVisible(false);
               studentPane.setVisible(true);
               gradePane.setVisible(false);
          } else if(e.getSource() == coursebutton){
               homePane.setVisible(false);
               coursePane.setVisible(true);
               studentPane.setVisible(false);
               gradePane.setVisible(false);
          } else if(e.getSource() == gradebutton){
               homePane.setVisible(false);
               coursePane.setVisible(false);
               studentPane.setVisible(false);
               gradePane.setVisible(true);
          }
     }

     private static final String DB_URL = "jdbc:postgresql://localhost:5432/Students DB";
     private static final String USER = "postgres";
     private static final String PASSWORD = "BigZ2301";




}