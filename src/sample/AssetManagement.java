package sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AssetManagement extends Application {

    //Record class establishes the Assets' property methods that can be used to create and edit asset objects
    public class Record {

        public Record()
        {
        }

        Record(String idNumber, String assetName, String assetType, String assetLocation, String usedBy, String assetState) {
            this.setIdNumber(idNumber);
            this.setAssetName(assetName);
            this.setAssetType(assetType);
            this.setAssetLocation(assetLocation);
            this.setUsedBy(usedBy);
            this.setAssetState(assetState);
        }

        private String idNumber, assetName, assetType, assetLocation, usedBy, assetState;

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public String getAssetName() {
            return assetName;
        }

        public void setAssetName(String assetName) {
            this.assetName = assetName;
        }

        public String getAssetType() {
            return assetType;
        }

        public void setAssetType(String assetType) {
            this.assetType = assetType;
        }

        public String getAssetLocation() {
            return assetLocation;
        }

        public void setAssetLocation(String assetLocation) {
            this.assetLocation = assetLocation;
        }

        public String getUsedBy() {
            return usedBy;
        }

        public void setUsedBy(String usedBy) {
            this.usedBy = usedBy;
        }

        public String getAssetState() {
            return assetState;
        }

        public void setAssetState(String assetState) {
            this.assetState = assetState;
        }

    }
    //end of Record class

    public TableView<Record> tableView = new TableView<>();
    public ObservableList<Record> dataList
            = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Table of Assets");

        Group root = new Group();
        BorderPane firstB = new BorderPane();
        BorderPane bottom = new BorderPane();
        BorderPane left = new BorderPane();
        BorderPane left1 = new BorderPane();
        BorderPane bottom1 = new BorderPane();

        TableColumn columnF1 = new TableColumn("Column 1");
        columnF1.setCellValueFactory(
                new PropertyValueFactory<>("idNumber"));

        TableColumn columnF2 = new TableColumn("Column 2");
        columnF2.setCellValueFactory(
                new PropertyValueFactory<>("assetName"));

        TableColumn columnF3 = new TableColumn("Column 3");
        columnF3.setCellValueFactory(
                new PropertyValueFactory<>("assetType"));

        TableColumn columnF4 = new TableColumn("Column 4");
        columnF4.setCellValueFactory(
                new PropertyValueFactory<>("assetLocation"));

        TableColumn columnF5 = new TableColumn("Column 5");
        columnF5.setCellValueFactory(
                new PropertyValueFactory<>("usedBy"));

        TableColumn columnF6 = new TableColumn("Column 6");
        columnF6.setCellValueFactory(
                new PropertyValueFactory<>("assetState"));

        tableView.setItems(dataList);
        tableView.getColumns().addAll(
                columnF1, columnF2, columnF3, columnF4, columnF5, columnF6);

        //buttons for the 3 actions created here
        Button button1 = new Button("Click here to edit or delete an existing asset");
        Button button2 = new Button("Click here to add an asset");
        Button button3 = new Button("Click her to view the number of assets per category");

        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.getChildren().add(tableView);
        vBox.getChildren().add(firstB);
        vBox.getChildren().add(bottom);
        vBox.getChildren().add(bottom1);
        firstB.setBottom(button1);
        left.setLeft(button2);
        bottom.setBottom(left);
        left1.setLeft(button3);
        bottom1.setBottom(left1);

        //eventhandler created to handle asset editing and deleting events
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Dialog<Record> dialog = new Dialog<>();
                dialog.setTitle("Edit/Delete Assets");
                dialog.setHeaderText(null);
                dialog.setResizable(true);

                Label label1 = new Label("Row number of asset to edit/delete: ");
                Label label2 = new Label("\"Edit\" or \"Delete\" the asset? ");
                Label label3 = new Label("If editing, what is the exact field name of the asset you would like to change? (leave blank if N/A) ");
                Label label4 = new Label("If editing, what would you like the new field value to be? (leave blank if N/A) ");
                TextField text1 = new TextField();
                TextField text2 = new TextField();
                TextField text3 = new TextField();
                TextField text4 = new TextField();

                Button okButton = new Button("Enter!");
                GridPane grid = new GridPane();
                grid.add(label1, 1, 1);
                grid.add(text1, 2, 1);
                grid.add(label2, 1, 2);
                grid.add(text2, 2, 2);
                grid.add(label3, 1, 3);
                grid.add(text3, 2, 3);
                grid.add(label4, 1, 4);
                grid.add(text4, 2, 4);
                grid.add(okButton, 5, 9);
                dialog.getDialogPane().setContent(grid);

                ButtonType buttonTypeOk = new ButtonType("Click when done entering information to see table of updated records", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
                dialog.show();

                okButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if(text2.getText().equals("Edit"))
                        {
                            if(text3.getText().equals("AssetIDNumber"))
                            {
                                int index = (Integer.parseInt(text1.getText()));
                                dataList.get(index).setIdNumber(text4.getText());
                                readCSV();
                            }
                            if(text3.getText().equals("AssetName"))
                            {
                                int index = (Integer.parseInt(text1.getText()));
                                dataList.get(index).setAssetName(text4.getText());
                                readCSV();
                            }
                            if(text3.getText().equals("AssetType"))
                            {
                                int index = (Integer.parseInt(text1.getText()));
                                dataList.get(index).setAssetType(text4.getText());
                                readCSV();
                            }
                            if(text3.getText().equals("AssetLocation"))
                            {
                                int index = (Integer.parseInt(text1.getText()));
                                dataList.get(index).setAssetLocation(text4.getText());
                                readCSV();
                            }
                            if(text3.getText().equals("UsedBy"))
                            {
                                int index = (Integer.parseInt(text1.getText()));
                                dataList.get(index).setUsedBy(text4.getText());
                                readCSV();
                            }
                            if(text3.getText().equals("AssetState"))
                            {
                                int index = (Integer.parseInt(text1.getText()));
                                dataList.get(index).setAssetState(text4.getText());
                                readCSV();
                            }
                        }
                        else
                        {
                            int index = (Integer.parseInt(text1.getText()));
                            dataList.remove(index);
                            readCSV();
                        }
                    }
                });

            }
        });

        //event handler created to handle asset adding events
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Dialog<Record> dialog = new Dialog<>();
                dialog.setTitle("Create an Asset");
                dialog.setHeaderText(null);
                dialog.setResizable(true);

                Label label1 = new Label("Do you want to add a \"Computer\", a \"Printer\", or an \"Audio/Video\" asset? ");
                Label label2 = new Label("Enter the AssetIDNumber: ");
                Label label3 = new Label("Enter the AssetName: ");
                Label label4 = new Label("Enter the AssetType: ");
                Label label5 = new Label("Enter the AssetLocation");
                Label label6 = new Label("Enter the UsedBy information: ");
                Label label7 = new Label("Enter the AssetState: ");
                TextField text1 = new TextField();
                TextField text2 = new TextField();
                TextField text3 = new TextField();
                TextField text4 = new TextField();
                TextField text5 = new TextField();
                TextField text6 = new TextField();
                TextField text7 = new TextField();

                Button okButton = new Button("Enter!");
                GridPane grid = new GridPane();
                grid.add(label1, 1, 1);
                grid.add(text1, 2, 1);
                grid.add(label2, 1, 2);
                grid.add(text2, 2, 2);
                grid.add(label3, 1, 3);
                grid.add(text3, 2, 3);
                grid.add(label4, 1, 4);
                grid.add(text4, 2, 4);
                grid.add(label5, 1, 5);
                grid.add(text5, 2, 5);
                grid.add(label6, 1, 6);
                grid.add(text6, 2, 6);
                grid.add(label7, 1, 7);
                grid.add(text7, 2, 7);
                grid.add(okButton, 7, 13);
                dialog.getDialogPane().setContent(grid);

                ButtonType buttonTypeOk = new ButtonType("Click when done entering information to see table of updated records", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
                dialog.show();

                okButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if(text1.getText().equals("Computer")||text1.getText().equals("computer"))
                        {
                            Computers comp = new Computers(text2.getText(), text3.getText(), text4.getText(),text5.getText(),text6.getText(),text7.getText());
                            dataList.add(1, comp);
                            readCSV();
                        }
                        else if(text1.getText().equals("Printer")||text1.getText().equals("printer"))
                        {
                            Printers print = new Printers(text2.getText(), text3.getText(), text4.getText(),text5.getText(),text6.getText(),text7.getText());
                            dataList.add(1, print);
                            readCSV();
                        }
                        else
                        {
                            AudioVideo av = new AudioVideo(text2.getText(), text3.getText(), text4.getText(),text5.getText(),text6.getText(),text7.getText());
                            dataList.add(1, av);
                            readCSV();
                        }
                    }
                });
            }
        });

        //event handler created to handle asset counting
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int countc = 0;
                int countp = 0;
                int countav = 0;

                for(int i = 1; i < dataList.size(); i++)
                {
                    if(dataList.get(i).assetType.equals("Computer")||dataList.get(i).assetType.equals("computer")||dataList.get(i).assetType.equals("Computers")||dataList.get(i).assetType.equals("computers"))
                    {
                        countc++;
                    }
                    else if(dataList.get(i).assetType.equals("Printer")||dataList.get(i).assetType.equals("printer")||dataList.get(i).assetType.equals("Printers")||dataList.get(i).assetType.equals("printers"))
                    {
                        countp++;
                    }
                    else
                    {
                        countav++;
                    }
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Number of Assets per Category");
                alert.setHeaderText(null);
                alert.setContentText("Number of Computers: " + countc + "\nNumber of Printers: " + countp + "\nNumber of Audio/Video: " + countav);
                alert.showAndWait();
            }
        });

        //adds all objects to stage and displays the table
        root.getChildren().add(vBox);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        readCSV();
    }

    //method that iterates through the CSV file
    private void readCSV() {

        String CsvFile = "assets.csv";
        String FieldDelimiter = ",";

        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(CsvFile));

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);

                Record record = new Record(fields[0], fields[1], fields[2],
                        fields[3], fields[4], fields[5]);
                dataList.add(record);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AssetManagement.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AssetManagement.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

    }


    //Asset category child classes and interfaces are defined below
    public class Computers extends Record implements MaintenanceComputers, IPConfigComp
    {
        public Computers(String idNum, String name, String type, String location, String useBy, String state)
        {
            setIdNumber(idNum);
            setAssetName(name);
            setAssetType(type);
            setAssetLocation(location);
            setUsedBy(useBy);
            setAssetState(state);
        }
        public void computerUpdates()
        {
            System.out.println("Provides security and software updates to computers");
        }

        @Override
        public void getIPcomp() {
            System.out.println("Configures computers' and laptops' IP addresses");
        }
    }

    public class Printers extends Record implements MaintenancePrinters, IPConfigPrint
    {
        public Printers(String idNum, String name, String type, String location, String useBy, String state)
        {
            setIdNumber(idNum);
            setAssetName(name);
            setAssetType(type);
            setAssetLocation(location);
            setUsedBy(useBy);
            setAssetState(state);
        }
        @Override
        public void printerUpdates() {

            System.out.println("Updates driver software and cleans the cartridge when needed");
        }

        @Override
        public void getIPPrint() {
            System.out.println("Configures IP addresses of printers");
        }
    }

    public class AudioVideo extends Record implements MaintenanceAV, IPConfigAV
    {
        public AudioVideo(String idNum, String name, String type, String location, String useBy, String state)
        {
            setIdNumber(idNum);
            setAssetName(name);
            setAssetType(type);
            setAssetLocation(location);
            setUsedBy(useBy);
            setAssetState(state);
        }
        public void avUpdates()
        {
            System.out.println("Cleans the projector window and replaces projector Lamp");
        }

        @Override
        public void getIPav() {
            System.out.println("Configures IP addresses of audio/video equipment (projectors)");
        }
    }

    interface MaintenanceComputers
    {
        public void computerUpdates();
    }

    interface MaintenancePrinters
    {
        public void printerUpdates();
    }

    interface MaintenanceAV
    {
        public void avUpdates();
    }

    interface IPConfigComp
    {
        public void getIPcomp();
    }

    interface IPConfigPrint
    {
        public void getIPPrint();
    }

    interface IPConfigAV
    {
        public void getIPav();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

