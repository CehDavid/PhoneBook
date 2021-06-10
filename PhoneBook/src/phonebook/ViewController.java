
package phonebook;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class ViewController implements Initializable {
    
    @FXML
    private TableView table;
    @FXML
    private TextField inputName;
    @FXML
    private TextField inputPhone;
    @FXML
    private TextField inputEmail;
    @FXML
    private Button addContact;
    @FXML
    private StackPane menuPane;
    @FXML
    private Pane contactPane;
    @FXML
    private Pane exportPane;
    
    private final ObservableList<Person> data =FXCollections.observableArrayList(
        new Person("Példa Zsolt","+36506363363","zsoltpelda@pelda.com"),
        new Person("Nagy Katalin","+36304141414","nagykat@gmail.com"),
        new Person("Kiss Petra","+36705513789","kisspetra44@gmail.com"));
    
    private void setTableData() {
        TableColumn nameCol = new TableColumn("Név");
        nameCol.setMinWidth(100);
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setCellValueFactory( new PropertyValueFactory <Person,String>("name"));
        
        TableColumn phoneCol = new TableColumn("Telefonszám");
        phoneCol.setMinWidth(100);
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setCellValueFactory( new PropertyValueFactory <Person,String>("phone"));

        TableColumn emailCol = new TableColumn("E-mail");
        emailCol.setMinWidth(200);
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setCellValueFactory( new PropertyValueFactory <Person,String>("email"));
        
        table.getColumns().addAll(nameCol,phoneCol,emailCol);
        table.setItems(data);
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    setTableData();
    
    }    
    
}
