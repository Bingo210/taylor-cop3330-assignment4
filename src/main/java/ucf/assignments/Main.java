/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Noah Taylor
 */

package ucf.assignments;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// Add and delete buttons currently have full functionality

public class Main extends Application {

    Stage window;
    TableView<Items> table;
    TextField numberInput, dateInput, descriptionInput;
    BorderPane layout;

    // Main method to launch application
    public static void main(String[] args) {
        launch(args);
    }

    @Override

    // Start method to display GUI from FXML file
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("To Do List 1");

        /*
        First, the main menu bar must be created
        Basic functionality is added for now, but will be improved later
            Create a new menu, title it
            Create dropdown options for the menu item
            Use a lambda expression to implement a functional interface
                Just a basic println statement for now
        Repeat for each item in menu bar
         */

        // Creates a file menu
        Menu fileMenu = new Menu("File");

        // Adds new list option and provides functionality
        MenuItem newList = new MenuItem("Open New List...");
        newList.setOnAction(e -> System.out.println("Creates a new to do list"));
        fileMenu.getItems().add(newList);

        // Adds save list option and provides functionality
        MenuItem saveList = new MenuItem("Save Current List...");
        saveList.setOnAction(e -> System.out.println("Saves current list"));
        fileMenu.getItems().add(saveList);

        // Adds view all lists option and provides functionality
        MenuItem loadLists = new MenuItem("Load Saved Lists...");
        loadLists.setOnAction(e -> System.out.println(
                "Opens menu with all saved lists, offers user choice to add/delete lists, " +
                        "as well as save all or delete all lists choices"));
        fileMenu.getItems().add(loadLists);

        // Creates separator line between view all and exit
        fileMenu.getItems().add(new SeparatorMenuItem());

        // Adds exit menu option and provides functionality
        MenuItem exitMenu = new MenuItem("Exit");
        exitMenu.setOnAction(e -> System.out.println("Exits menu"));
        fileMenu.getItems().addAll(exitMenu);

        // Creates an edit menu
        Menu editMenu = new Menu("Edit");

        // Adds edit title option and provides functionality
        MenuItem editTitle = new MenuItem("Edit List Title...");
        editTitle.setOnAction(e -> System.out.println("Allows user to edit list title"));
        editMenu.getItems().add(editTitle);

        // Adds edit title option and provides functionality
        MenuItem editDescription = new MenuItem("Edit Item Description...");
        editDescription.setOnAction(e -> System.out.println(
                "Allows user to edit the description of any item"));
        editMenu.getItems().add(editDescription);

        // Adds edit due date option and provides functionality
        MenuItem editDate = new MenuItem("Edit Item Due Date...");
        editDate.setOnAction(e -> System.out.println(
                "Allows user to edit the due date of any item"));
        editMenu.getItems().add(editDate);

        // Creates a display menu
        Menu displayMenu = new Menu("Display");

        // Adds display all option and provides functionality
        MenuItem displayAll = new MenuItem("Display All Items...");
        displayAll.setOnAction(e -> System.out.println("Allows user to display all items in list"));
        displayMenu.getItems().add(displayAll);

        // Adds display incompleted option and provides functionality
        MenuItem displayIncompleted = new MenuItem("Display All Incompleted Items...");
        displayIncompleted.setOnAction(e -> System.out.println(
                "Allows user to display all incompleted items in list"));
        displayMenu.getItems().add(displayIncompleted);

        // Adds display completed option and provides functionality
        MenuItem displayCompleted = new MenuItem("Display All Completed Items...");
        displayCompleted.setOnAction(e -> System.out.println(
                "Allows user to display all completed items in list"));
        displayMenu.getItems().add(displayCompleted);

        // Creates main menu bar, adds file, edit and display menus to it
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu, displayMenu);

        /*
        Next we must create a container for the GUI and add features
        Add the menu bar to the top of the borderpane

        Create a few columns for the tableview by:
            Declaring and titling each column
            Setting width parameter
            Use setCellValueFactory to specify how to populate cells
        Repeat for the three needed cells

        Create input text fields at bottom for user input
        Set some prompt text for clarity

        Create buttons needed for list to function by
            Making and titling the buttons
            Use lambdas to call a method for button click handling
         */

        // Creates borderpane container, places menu bar at the top
        layout = new BorderPane();
        layout.setTop(menuBar);

        // Creates number column, sets width
        TableColumn<Items, String> numberColumn = new TableColumn<>("Number");
        numberColumn.setMinWidth(10);
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        // Creates due date column, sets width
        TableColumn<Items, String> dateColumn = new TableColumn<>("Due Date");
        dateColumn.setMinWidth(150);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Creates description column, sets width
        TableColumn<Items, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setMinWidth(600);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Number input
        numberInput = new TextField();
        numberInput.setPromptText("Number");
        numberInput.setMinWidth(100);

        // Date input
        dateInput = new TextField();
        dateInput.setPromptText("Due Date");

        // Description input
        descriptionInput = new TextField();
        descriptionInput.setPromptText("Description");

        // Creates add, delete, and completed buttons, implements button functionality
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addButtonClicked());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());
        Button completedButton = new Button("Completed");
        completedButton.setOnAction(e -> System.out.println(
                "Marks selected list item as completed, press again to undo"));
        /*
        Now, the buttons and text fields need to be put into an hbox container
            Declare the hbox
            Give it some padding/spacing for aesthetic purposes
            Add the buttons and input fields using getChildren
         */

        // Creates HBox container, adds buttons, padding, and input text fields
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(numberInput, dateInput, descriptionInput,
                addButton, deleteButton, completedButton);

        /*
        Let us now create the table
            Declare the new table
            Insert the first example row using setItems
            Add the columns to the table

        Combine them all into a singular interface
            Declare a new vbox container
            Add the menu, table, and hbox to a parent container
            Make a new scene, add the vbox and show it in a new window
         */

        // Creates the table, adds items and columns
        table = new TableView<>();
        table.setItems(getItems());
        table.getColumns().addAll(numberColumn, dateColumn, descriptionColumn);

        // Creates vbox container to store the menu, table, and hbox for buttons
        VBox vBox = new VBox();
        vBox.getChildren().addAll(menuBar, table, hBox);

        // Creates new scene, shows GUI in new window
        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    // Method for handling add button clicked
    public void addButtonClicked(){
        Items Items = new Items();
        Items.setNumber(Integer.parseInt(numberInput.getText()));
        Items.setDate(dateInput.getText());
        Items.setDescription(descriptionInput.getText());
        table.getItems().add(Items);
        numberInput.clear();
        dateInput.clear();
        descriptionInput.clear();
    }

    // Method for handling delete button clicked
    public void deleteButtonClicked(){
        ObservableList<Items> ItemsSelected, allItems;
        allItems = table.getItems();
        ItemsSelected = table.getSelectionModel().getSelectedItems();
        ItemsSelected.forEach(allItems::remove);
    }

    // Converts objects from items class into observable list
    public ObservableList<Items> getItems(){
        ObservableList<Items> Items = FXCollections.observableArrayList();
        Items.add(new Items(1, "2021/09/01",
                "*****list formatted like this, delete and add your own*****"));
        return Items;
    }
}