package org.app.transport.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.app.transport.model.Good;
import org.app.transport.model.Offer;
import org.app.transport.model.User;
import org.app.transport.UserService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PricePageController {
    @FXML
    private Button returnButton;
    @FXML
    private DatePicker date;
    @FXML
    private DatePicker date1;
    @FXML
    private TextField price;
    @FXML
    private TextArea info;
    @FXML
    private Text message;
    private String username;
    private String listItem;
    private String LocT,LocF;
    private String goodInfo;
    @FXML
    private ChoiceBox<String> currency;
    public void setUserDetails(String username,String listItem,String LocF,String LocT,String goodInfo)
    {
        this.username=username;
        this.listItem=listItem;
        this.LocF=LocF;
        this.LocT=LocT;
        this.goodInfo=goodInfo;
    }
    @FXML
    public void initialize() {
        currency.getItems().addAll("lei", "euro");
    }
    public void handleReturn(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GoodList.fxml"));
            Parent root = (Parent) loader.load();
            GoodListController log=loader.getController();
            log.setUsername(username,LocF,LocT);
            Stage window = (Stage) returnButton.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void handleClear(MouseEvent mouseEvent) {
       date.getEditor().clear();
        price.setText("");
        info.setText("");
    }

    public void handleOffer(MouseEvent mouseEvent) {
        if(date.getEditor().getText()!=null&& price.getText()!=null&&currency.getValue()!=null&&(price.getText().compareTo("")!=0))
        {

            User c= UserService.FindTheUser(username);
            String [] split=listItem.split(":");
            String [] split2=goodInfo.split("-");
            Good g=new Good(split2[0],split2[1],split2[2],split2[3],split2[4]);
            if(info.getText().length()<2)
                info.setText("No information!");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            LocalDate localDate=LocalDate.parse(date.getEditor().getText(),formatter);
            date1=new DatePicker(localDate);
            Offer o=new Offer(split[0],username,price.getText()+" "+currency.getValue(),date1.getValue().toString(),info.getText(),"In pending",g);
            System.out.println(o.toString());
            c.setSomething(o.toString());
            UserService.updateUser(c,username);
            message.setText("Your offer will be send!");
        }
        else
            message.setText("The fields must be completed!");
    }
}
