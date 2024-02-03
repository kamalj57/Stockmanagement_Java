package com.example.demo1;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.io.IOException;
import java.sql.Statement;

import static javafx.geometry.Pos.BOTTOM_RIGHT;
import static javafx.geometry.Pos.CENTER;

public class HelloApplication extends Application {
    private String username;
    private String password;
    private String userType;
    private Button bt,button1;
    private Scene sc1,sc3,sc4;
    private BorderPane bp1 = new BorderPane();
    private  BorderPane bp = new BorderPane();
    private TableView<StockItem>  table;
    @Override
    public void start(Stage stage) throws IOException {
        /*******************************************
         *          LOGIN PAGE                     *
         ******************************************/
        BorderPane bp2=new BorderPane();
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(25, 25, 25, 25));
        gp.setVgap(10);
        gp.setHgap(10);
        gp.setAlignment(CENTER);
        gp.setMaxSize(250,200);
        gp.setStyle("-fx-background-color: #D3D3D3;-fx-background-radius:10.0;");
        Text t3 = new Text("Email id");
        t3.setFont(Font.font("Monospaced",15));
        TextField tx2 = new TextField();
        gp.add(t3, 0, 4);
        gp.add(tx2, 2, 4);
        Text t4 = new Text("Password");
        t4.setFont(Font.font("Monospaced",15));
        PasswordField tx3 = new PasswordField();
        gp.add(t4, 0, 5);
        gp.add(tx3, 2, 5);
        Text ts = new Text("User Type");
        ts.setFont(Font.font("Monospaced",15));
        ComboBox cb = new ComboBox();
        ObservableList<String> ob = cb.getItems();
        ob.add("admin");
        ob.add("public");
        gp.add(ts, 0, 6);
        gp.add(cb, 2, 6);
        bt = new Button("Login");
        gp.add(bt, 2, 9);
        button1 =new Button("Sign Up");
        gp.add(button1,0,9);
        bp2.setStyle("-fx-background-color:#4E586E;");
        bp2.setCenter(gp);
        GridPane pg = new GridPane();
        pg.setPadding(new Insets(20, 20, 20, 20));
        pg.setVgap(20);
        pg.setHgap(20);
        BorderPane pg2 = new BorderPane();
        pg2.setPadding(new Insets(20, 20, 20, 20));
        sc1=new Scene(pg2,400,400);
        sc3 = new Scene(pg, 625, 625);
        BorderPane gp2 = new BorderPane();
        sc4 = new Scene(gp2, 500, 500);
        bt.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                username = tx2.getText().toString();
                password = tx3.getText().toString();
                userType = (String) cb.getValue();
                if (tx2.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Please Enter your email id!");
                    return;
                }
                if (tx3.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Please Enter your password!");
                    return;
                }
                if (cb.getValue() == null) {
                    showAlert(Alert.AlertType.ERROR, "Please choose the User Type!");
                    return;
                }
                if (!tx2.getText().isEmpty() && !tx3.getText().isEmpty() && userType != "admin") {
                    try {
                        validLogin();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (!tx2.getText().isEmpty() && !tx3.getText().isEmpty() && userType != "public") {
                    try {
                        validLogin();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Scene sc = new Scene(bp2, 400, 400);
        stage.setScene(sc);
        stage.show();
        /*******************************************
         *     USER - PUBLIC                       *
         ******************************************/
        Menu mn = new Menu("Account");
        MenuItem it1 = new MenuItem("Profile");
        MenuItem it5 = new MenuItem("Categories");
        MenuItem it6 = new MenuItem("Products");
        MenuItem it2 = new MenuItem("Settings");
        MenuItem it3 = new MenuItem("Help");
        MenuItem it4 = new MenuItem("Logout");
        mn.getItems().addAll(it1, it5, it6, it2, it3, it4);
        MenuBar mb = new MenuBar();
        mb.getMenus().addAll(mn);
        bp.setTop(mb);
        TextField tf = new TextField("Search");
        bp.setAlignment(tf, Pos.TOP_RIGHT);
        bp.setRight(tf);
        Button but = new Button("View Products");
        bp.setAlignment(but, BOTTOM_RIGHT);
        bp.setBottom(but);
        but.setOnAction(ActionEvent -> {
            stage.setScene(sc3);
        });
        /*******************************************
         *     PUBLIC - VIEW PRODUCTS              *
         ******************************************/
        pg.setStyle("-fx-background-color:#FFD428;");
        FileInputStream stream = new FileInputStream("D:\\Images\\download (10).jfif");
        Image ig = new Image(stream);
        ImageView im = new ImageView();
        im.setImage(ig);
        im.setFitHeight(100);
        im.setFitWidth(100);
        pg.add(im, 0, 0);
        Button button = new Button("Add to Cart");
        button.setOnAction(ActionEvent->{
            showAlert(Alert.AlertType.INFORMATION,"Product added to cart successfully!");
        });
        Button butt = new Button("Buy Now");
        butt.setOnAction(ActionEvent->{
            showAlert(Alert.AlertType.CONFIRMATION,"Want to buy produt!");
        });
        pg.add(button, 0, 2);
        pg.add(butt, 0, 3);

        FileInputStream stream1 = new FileInputStream("D:\\Images\\images (1).jfif");
        Image ig1 = new Image(stream1);
        ImageView im1 = new ImageView();
        im.setImage(ig1);
        im1.setFitHeight(100);
        im1.setFitWidth(100);
        pg.add(im1, 1, 0);
        Button button1 = new Button("Add to Cart");
        Button butt1 = new Button("Buy Now");
        pg.add(button1, 1, 2);
        pg.add(butt1, 1, 3);

        FileInputStream stream2 = new FileInputStream("D:\\Images\\download (2).jfif");
        Image ig2 = new Image(stream2);
        ImageView im2 = new ImageView();
        im2.setImage(ig2);
        im2.setFitHeight(100);
        im2.setFitWidth(100);
        pg.add(im2, 2, 0);
        Button button2 = new Button("Add to Cart");
        Button butt2 = new Button("Buy Now");
        pg.add(button2, 2, 2);
        pg.add(butt2, 2, 3);

        FileInputStream stream3 = new FileInputStream("D:\\Images\\download (3).jfif");
        Image ig3 = new Image(stream3);
        ImageView im3 = new ImageView();
        im3.setImage(ig3);
        im3.setFitHeight(100);
        im3.setFitWidth(100);
        pg.add(im3, 3, 0);
        Button button3 = new Button("Add to Cart");
        Button butt3 = new Button("Buy Now");
        pg.add(button3, 3, 2);
        pg.add(butt3, 3, 3);

        FileInputStream stream4 = new FileInputStream("D:\\Images\\download (4).jfif");
        Image ig4 = new Image(stream4);
        ImageView im4 = new ImageView();
        im4.setImage(ig4);
        im4.setFitHeight(100);
        im4.setFitWidth(100);
        pg.add(im4, 4, 0);
        Button button4 = new Button("Add to Cart");
        Button butt4 = new Button("Buy Now");
        pg.add(button4, 4, 2);
        pg.add(butt4, 4, 3);

        FileInputStream stream5 = new FileInputStream("D:\\Images\\download (5).jfif");
        Image ig5 = new Image(stream5);
        ImageView im5 = new ImageView();
        im5.setImage(ig5);
        im5.setFitHeight(100);
        im5.setFitWidth(100);
        pg.add(im5, 0, 5);
        Button button5 = new Button("Add to Cart");
        Button butt5 = new Button("Buy Now");
        pg.add(button5, 0, 7);
        pg.add(butt5, 0, 8);
        FileInputStream stream6 = new FileInputStream("D:\\Images\\download (6).jfif");
        Image ig6 = new Image(stream6);
        ImageView im6 = new ImageView();
        im6.setImage(ig6);
        im6.setFitHeight(100);
        im6.setFitWidth(100);
        pg.add(im6, 1, 5);
        Button button6 = new Button("Add to Cart");
        Button butt6 = new Button("Buy Now");
        pg.add(button6, 1, 7);
        pg.add(butt6, 1, 8);

        FileInputStream stream7 = new FileInputStream("D:\\Images\\download (7).jfif");
        Image ig7 = new Image(stream7);
        ImageView im7 = new ImageView();
        im7.setImage(ig7);
        im7.setFitHeight(100);
        im7.setFitWidth(100);
        pg.add(im7, 2, 5);
        Button button7 = new Button("Add to Cart");
        Button butt7 = new Button("Buy Now");
        pg.add(button7, 2, 7);
        pg.add(butt7, 2, 8);

        FileInputStream stream8 = new FileInputStream("D:\\Images\\download (8).jfif");
        Image ig8 = new Image(stream8);
        ImageView im8 = new ImageView();
        im8.setImage(ig8);
        im8.setFitHeight(100);
        im8.setFitWidth(100);
        pg.add(im8, 3, 5);
        Button button8 = new Button("Add to Cart");
        button8.setOnAction(ActionEvent->{
            showAlert(Alert.AlertType.INFORMATION,"Product Added to Cart");
        });
        Button butt8 = new Button("Buy Now");
        butt8.setOnAction(ActionEvent->{
            showAlert(Alert.AlertType.INFORMATION,"Product Buyed Sucessfully");
        });
        pg.add(button8, 3, 7);
        pg.add(butt8, 3, 8);
        Button But = new Button("Request Product");
        pg.add(But, 4, 10);
        But.setOnAction(ActionEvent -> {
            stage.setScene(sc4);
        });
        /*******************************************
         *    PUBLIC USER - PRODUCT REQUEST        *
         ******************************************/
        GridPane gp1=new GridPane();
        gp1.setPadding(new Insets(20, 20, 20, 20));
        gp1.setVgap(10);
        gp1.setHgap(20);
        gp1.setMaxSize(300,300);
        Text t5 = new Text("Name");
        TextField tef = new TextField();
        gp1.add(t5, 0, 0);
        gp1.add(tef, 1, 0);
        Text t6 = new Text("Mobile Number");
        TextField tef1 = new TextField();
        gp1.add(t6, 0, 1);
        gp1.add(tef1, 1, 1);
        Text t7 = new Text("Email id");
        TextField tef2 = new TextField();
        gp1.add(t7, 0, 2);
        gp1.add(tef2, 1, 2);
        Text t8 = new Text("Password");
        TextField tef3 = new TextField();
        gp1.add(t8, 0, 3);
        gp1.add(tef3, 1, 3);
        Text t10 = new Text("Product Name");
        TextField tef5 = new TextField();
        gp1.add(t10, 0, 4);
        gp1.add(tef5, 1, 4);
        Text t9 = new Text("No of Product needed");
        TextField tef4 = new TextField();
        gp1.add(t9, 0, 5);
        gp1.add(tef4, 1, 5);
        Button butt9 = new Button("Submit");
        gp1.add(butt9, 1, 6);
        butt9.setOnAction(ActionEvent -> {
            if(tef.getText().isEmpty() && tef1.getText().isEmpty() && tef2.getText().isEmpty() && tef3.getText().isEmpty() && tef4.getText().isEmpty() && tef5.getText().isEmpty()){
                    showAlert(Alert.AlertType.ERROR,"Please fill the details!");
            }
            else{
            Database connectnow3 = new Database();
            Connection con = connectnow3.getConnection();
            try {
                String x1="INSERT INTO productrequest (Name,Mobile,email,password,prname,quantity) VALUES ('"+tef.getText()+"','"+tef1.getText()+"','"+tef2.getText()+"','"+tef3.getText()+"','"+tef5.getText()+"','"+tef4.getText()+"')";
                Statement stat = con.createStatement();
                int status1=stat.executeUpdate(x1);
                if(status1==1){
                    showAlert(Alert.AlertType.INFORMATION,"Product Requested Successfully!");
                }
                else{
                    showAlert(Alert.AlertType.ERROR,"Product Requested Failed!");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } });
        gp1.setStyle("-fx-background-color: #6059f7;-fx-background-radius:10.0;");
        gp2.setAlignment(gp1, CENTER);
        gp2.setCenter(gp1);
        /*******************************************
         *   PUBLIC USER - SEARCH PRODUCT          *
         ******************************************/
        GridPane grid = new GridPane();
        Text text = new Text("ID");
        TextField text1 = new TextField();
        text1.setPrefWidth(70);
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setAlignment(CENTER);
        grid.add(text, 0, 2);
        grid.add(text1, 1, 2);
        Button b = new Button("Search");
        grid.add(b, 1, 4);
        grid.setStyle("-fx-background-color:#C6CDFF;-fx-background-radius:10.0;");
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setMaxSize(200, 50);
        bp.setAlignment(grid, CENTER);
        bp.setCenter(grid);
        bp.setBackground(new Background(new BackgroundFill(Color.MIDNIGHTBLUE, null, null)));
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Database connectnow = new Database();
                    Connection con = connectnow.getConnection();
                    Statement statement = con.createStatement();
                    ResultSet queryResult = statement.executeQuery("SELECT * FROM product WHERE proid='"+text1.getText() +"'");
                        if (queryResult.next()) {
                            GridPane gp1 = new GridPane();
                            gp1.setVgap(10);
                            gp1.setHgap(10);
                            gp1.setPadding(new Insets(10, 10, 10, 10));
                            gp1.setMaxSize(100, 100);
                            Text t3 = new Text("Product ID: ");
                            Text tf2 = new Text(queryResult.getString("proid"));
                            Text t4=new Text("Name ");
                            Text tf3=new Text(queryResult.getString("proname"));
                            Text t5= new Text("Quantity ");
                            Text tf4=new Text(queryResult.getString("prostock"));
                            Text t6=new Text("Type ");
                            Text tf6=new Text(queryResult.getString("protype"));
                            Text t7=new Text("Cost ");
                            Text tf7=new Text(queryResult.getString("icost"));
                            gp1.add(t3, 1, 1);
                            gp1.add(tf2, 2, 1);
                            gp1.add(t4,1,2);
                            gp1.add(tf3,2,2);
                            gp1.add(t5,1,3);
                            gp1.add(tf4,2,3);
                            gp1.add(t6,1,4);
                            gp1.add(tf6,2,4);
                            gp1.add(t7,1,7);
                            gp1.add(tf7,2,7);
                            Stage popup = new Stage();
                            popup.setTitle("Information");
                            popup.setScene(new Scene(gp1, 200, 200));
                            popup.show();
                        } else {
                            showAlert(Alert.AlertType.ERROR, "Invalid Product ID!");
                        }
                } catch (RuntimeException | SQLException e) {
                    System.out.println(e);
                }
            }
        });
        /*******************************************
         *        ADMIN USER                       *
         ******************************************/
        Menu mn1 = new Menu("Account");
        MenuItem itt1 = new MenuItem("Profile");
        MenuItem itt2 = new MenuItem("Settings");
        MenuItem itt3 = new MenuItem("Help");
        MenuItem itt4 = new MenuItem("Logout");
        mn1.getItems().addAll(itt1, itt2, itt3, itt4);
        MenuBar mb1 = new MenuBar();
        mb1.getMenus().addAll(mn1);
        mb1.setStyle("-fx-background-color:#6200EE;");
        mb1.setMaxHeight(130);
        bp1.setTop(mb1);
        bp1.setStyle("-fx-background-color:#D2D4DA;");
        bp1.setMaxSize(1050,1050);
        /*******************************************
         *   ADMIN USER - TABLE VIEW               *
         ******************************************/
        table = new TableView<>();
        TableColumn<StockItem, String> nameCol = new TableColumn<>("ProductID");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("idproduct"));
        TableColumn<StockItem, String> costPriceCol = new TableColumn<>("Name");
        costPriceCol.setMinWidth(100);
        costPriceCol.setCellValueFactory(new PropertyValueFactory<>("pname"));
        TableColumn<StockItem, String> sellingPriceCol = new TableColumn<>("Quantity");
        sellingPriceCol.setMinWidth(100);
        sellingPriceCol.setCellValueFactory(new PropertyValueFactory<>("pstock"));
        TableColumn<StockItem, String> type = new TableColumn<>("Type");
        type.setMinWidth(78);
        type.setCellValueFactory(new PropertyValueFactory<>("ptype"));
        TableColumn<StockItem, String> cost = new TableColumn<>("Cost");
        cost.setMinWidth(78);
        cost.setCellValueFactory(new PropertyValueFactory<>("icost"));
        TableColumn<StockItem, String> tocost = new TableColumn<>("Total Cost");
        tocost.setMinWidth(78);
        tocost.setCellValueFactory(new PropertyValueFactory<>("tcost"));
        table.getColumns().addAll(nameCol,costPriceCol,sellingPriceCol,type,cost,tocost);
        VBox vb1=new VBox();
        vb1.setMaxSize(540,540);
        vb1.getChildren().addAll(table);
        bp1.setAlignment(vb1, CENTER);
        bp1.setCenter(vb1);
        populateTable();

        /*******************************************
         * ADMIN USER - ADD AND DELETE OF PRODUCTS *
         ******************************************/
        TextField pid =new TextField();
        pid.setPromptText("ID");
        pid.setPrefWidth(70);
        TextField nameInput = new TextField();
        nameInput.setPromptText("Name");
        nameInput.setPrefWidth(70);
        Text tf1=new Text();
        TextField st = new TextField();
        st.setPromptText("InStock");
        st.setPrefWidth(70);
        TextField Type = new TextField();
        Type.setPromptText("Type");
        Type.setPrefWidth(70);
        TextField cost1 = new TextField();
        cost1.setPromptText("cost");
        cost1.setPrefWidth(70);
        TextField tcost = new TextField();
        tcost.setPromptText("cost");
        tcost.setPrefWidth(70);
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(tf1,pid,nameInput, st, Type,cost1,tcost, addButton, deleteButton);
        hBox.setStyle("-fx-background-color:#03DAC5;");
        bp1.setBottom(hBox);
        /*******************************************
         *    ADMIN USER - ADD PRODUCT             *
         ******************************************/
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(pid.getText().isEmpty())
                    showAlert(Alert.AlertType.ERROR,"Enter the product ID!");
                   if(nameInput.getText().isEmpty())
                       showAlert(Alert.AlertType.ERROR,"Enter the Product Name!");
                   if(st.getText().isEmpty())
                       showAlert(Alert.AlertType.ERROR,"Enter the Stock Details!");
                   if(Type.getText().isEmpty() || cost1.getText().isEmpty() || tcost.getText().isEmpty())
                       showAlert(Alert.AlertType.ERROR,"Enter the type of product!");
                   if(!pid.getText().isEmpty() && !nameInput.getText().isEmpty() && !st.getText().isEmpty() && !Type.getText().isEmpty()){
                       try {
                           Class.forName("com.mysql.cj.jdbc.Driver");
                           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sky","root","@20731Jul#jk");
                           String x="INSERT INTO product (proid,proname,prostock,protype,icost,tcost) VALUES ('"+pid.getText()+"','"+nameInput.getText()+"','"+st.getText()+"','"+Type.getText()+"','"+cost1.getText()+"','"+tcost.getText()+"')";
                           Statement v=con.createStatement();
                           int status=v.executeUpdate(x);
                           if(status==1){
                               showAlert(Alert.AlertType.INFORMATION,"Added Succesfully");
                           }
                           else{
                               showAlert(Alert.AlertType.ERROR,"Failed");
                           }
                       } catch (SQLException | ClassNotFoundException e) {
                           throw new RuntimeException(e);
                       }
                   }
            }
        });
        /*******************************************
         *    ADMIN USER -DELETE PRODUCT           *
         ******************************************/
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(pid.getText().isEmpty())
                    showAlert(Alert.AlertType.ERROR,"Enter the product ID!");
                if(nameInput.getText().isEmpty())
                    showAlert(Alert.AlertType.ERROR,"Enter the Product Name!");
                if(st.getText().isEmpty())
                    showAlert(Alert.AlertType.ERROR,"Enter the Stock Details!");
                if(Type.getText().isEmpty())
                    showAlert(Alert.AlertType.ERROR,"Enter the type of product!");
                if(!pid.getText().isEmpty() && !nameInput.getText().isEmpty() && !st.getText().isEmpty() && !Type.getText().isEmpty()){
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sky","root","@20731Jul#jk");
                        PreparedStatement pst=con.prepareStatement("delete from product where proid='" + pid.getText()+"' and proname='"+nameInput.getText()+"' and prostock='" + st.getText()+"' and protype='"+Type.getText()+"'");
                        int status =pst.executeUpdate();
                        if(status==1){
                            showAlert(Alert.AlertType.INFORMATION,"Deleted Succesfully");
                        }
                        else{
                            showAlert(Alert.AlertType.ERROR,"Deletion Failed");
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

    }
    /*******************************************
     *    ADMIN USER-TABLE VIEW METHOD         *
     ******************************************/
    private void populateTable() {
        ObservableList<StockItem> products = FXCollections.observableArrayList();
        try {
            Database connectnow1 = new Database();
            Connection conn = connectnow1.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM product");
            while (rs.next()) {
                products.add(new StockItem(rs.getString("proid"),
                        rs.getString("proname"),
                        rs.getString("prostock"),
                      rs.getString("protype"),
                        rs.getString("icost"),
                        rs.getString("tcost")));
            }
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println("State: " + e.getSQLState());
            System.err.println("Code: " + e.getErrorCode());
        }
        table.setItems(products);
    }
    /*******************************************
     *           LOGIN VALIDATION              *
     ******************************************/
    private void validLogin () throws SQLException
    {
            Database connectnow = new Database();
            Connection connectDB = connectnow.getConnection();
            String verifyLogin = "SELECT userid,password,type FROM useraccount WHERE userid = '" + username + "' AND password ='" + password + "' AND type='" + userType + "'";
            try {
                Statement statement = connectDB.createStatement();
                ResultSet queryResult = statement.executeQuery(verifyLogin);
                while (queryResult.next()) {
                    if (queryResult.getString("type").contains("admin")) {
                        Stage window = (Stage) bt.getScene().getWindow();
                        window.setScene(new Scene(bp1, 500, 500));
                    }
                    if (queryResult.getString("type").contains("public")) {
                        Stage window1 = (Stage) bt.getScene().getWindow();
                        window1.setScene(new Scene(bp, 400, 400));
                    }

                }
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
     /*******************************************
     *        SHOWALERT METHOD                 *
     ******************************************/
        public void showAlert (Alert.AlertType error, String s){
            Alert alert = new Alert(error);
            alert.setHeaderText(null);
            alert.setContentText(s);
            alert.show();
        }
       public static void main(String[] args) {
        launch();
    }
}

