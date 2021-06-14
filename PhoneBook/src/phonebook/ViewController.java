
package phonebook;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private TextField inputFileName;
    
    
    private final String contats = "Kontaktok";
    private final String exit = "Kilépés";
    private final String list = "Lista";
    private final String export = "Exportálás";
    
    private final ObservableList<Person> data =FXCollections.observableArrayList(
        new Person("Példa Zsolt","+36506363363","zsoltpelda@pelda.com"),
        new Person("Nagy Katalin","+36304141414","nagykat@gmail.com"),
        new Person("Kiss Petra","+36705513789","kisspetra44@gmail.com"));
    
    public void addContact(ActionEvent event){
        
        String name = inputName.getText();
        String phone = inputPhone.getText();
        String email = inputEmail.getText();
        
        if(email.length()>4&&email.contains(".") && email.contains("@")){
            
            data.add(new Person(name,phone,email));
            inputName.clear();
            inputPhone.clear();
            inputEmail.clear();
        }
        
    }
    
     public void exportList (ActionEvent event){
         String fileName = inputFileName.getText();
         
     
              System.out.println("OK");
              PdfGeneration pdf = new PdfGeneration();
              pdf.pdfGeneration(fileName, data);
           
    }
    
    private void setTableData() {
        TableColumn nameCol = new TableColumn("Név");
        nameCol.setMinWidth(100);
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setCellValueFactory( new PropertyValueFactory <Person,String>("name"));
        
        nameCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Person,String>>(){
            @Override    
            public void handle(TableColumn.CellEditEvent<Person,String> t){
                ((Person)t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
                 }
            });
        
        TableColumn phoneCol = new TableColumn("Telefonszám");
        phoneCol.setMinWidth(100);
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setCellValueFactory( new PropertyValueFactory <Person,String>("phone"));
        
         phoneCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Person,String>>(){
            @Override    
            public void handle(TableColumn.CellEditEvent<Person,String> t){
                ((Person)t.getTableView().getItems().get(t.getTablePosition().getRow())).setPhone(t.getNewValue());
                 }
            });
         
        TableColumn emailCol = new TableColumn("E-mail");
        emailCol.setMinWidth(200);
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setCellValueFactory( new PropertyValueFactory <Person,String>("email"));
        
        emailCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Person,String>>(){
            @Override    
            public void handle(TableColumn.CellEditEvent<Person,String> t){
                ((Person)t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
                 }
            });
        
        table.getColumns().addAll(nameCol,phoneCol,emailCol);
        table.setItems(data);
        
        
    }
    
    private void setMenuDate(){
        TreeItem<String> treeItemRoot1 = new TreeItem <>("Menü");
        TreeView <String> treeView = new TreeView<>(treeItemRoot1);
        treeView.setShowRoot(false);
        
        TreeItem <String> nodeItemA = new TreeItem <>(contats);
        TreeItem <String> nodeItemB = new TreeItem <>(exit);
        
        Node contactNode = new ImageView(new Image(getClass().getResourceAsStream("/contact.png")));
        Node exportNode = new ImageView(new Image(getClass().getResourceAsStream("/export.png")));
        
        TreeItem <String> nodeItemA1 = new TreeItem <>(list,contactNode);
        TreeItem <String> nodeItemA2 = new TreeItem <>(export,exportNode);
        
        nodeItemA.getChildren().addAll(nodeItemA1,nodeItemA2);
        treeItemRoot1.getChildren().addAll(nodeItemA, nodeItemB);
        menuPane.getChildren().add(treeView);
        
        treeView.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener(){
                public void changed(ObservableValue observable, Object oldValue, Object newValue){
                    
                    TreeItem <String> selectItem =(TreeItem)newValue;
                    String selectedMenu = selectItem.getValue();
                    
                    if(selectedMenu!=null){
                        switch(selectedMenu){
                            case contats:
                                selectItem.setExpanded(true);
                                break;
                            case list:
                                contactPane.setVisible(true);
                                exportPane.setVisible(false);
                                break;
                            case export:
                                contactPane.setVisible(false);
                                exportPane.setVisible(true);
                                break;
                            case exit:
                                System.exit(0);
                                break;            
                        }
                    }
                }
            });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    setTableData();
    setMenuDate();
   
    
    }

}