package org.app.transport.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.app.transport.model.Good;
import org.app.transport.model.User;
import org.app.transport.services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditGoodsController  {
    @FXML
    private Button returnToHome;
    @FXML
    private TextField weight;
    @FXML
    private TextField locationFrom;
    @FXML
    private TextField locationTo;
    @FXML
    private TextField companyName;
    @FXML
    private TextField detailedAddress;
    @FXML
    private Text exception;
    private String userName;
    private String listItem;
    public void setUserNameListItem(String listItem,String userName)
    {
        this.listItem=listItem;
        this.userName=userName;
        String[] splits = listItem.split("-");
        weight.setText(splits[0]);
        locationFrom.setText(splits[1]);
        locationTo.setText(splits[2]);
        companyName.setText(splits[3]);
        detailedAddress.setText(splits[4]);
    }
    public void handleReturn(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MyGoodsList.fxml"));
            Parent root = (Parent) loader.load();
            MyGoodsList log=loader.getController();
            log.setUserName1(userName);
            Stage window = (Stage) returnToHome.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void handleEdit(MouseEvent mouseEvent) {
        User c= UserService.FindTheUser(userName);
        if(weight.getText().isEmpty()==true||locationFrom.getText().isEmpty()==true||locationTo.getText().isEmpty()==true||companyName.getText().isEmpty()==true||detailedAddress.getText().isEmpty()==true)
            exception.setText("All fields must be completed!");
        else {
            Good g = new Good(weight.getText(), locationFrom.getText(), locationTo.getText(), companyName.getText(), detailedAddress.getText());
            String[] splits = c.getGood().split("/");
            int k=0;
            for (String s : splits) {
                if (s.compareTo(listItem) == 0) {
                   splits[k]=g.toString();
                }
                k++;
            }
                String b="*";
                boolean sw=true;
                for(String s:splits)
                {
                    if(sw==true)
                    {
                        b=b+s;
                        sw=false;
                    }
                    else
                    {
                        b=b+"/"+s;
                    }
                }
                b=b.substring(1);
                c.set(b);
            UserService.updateUser(c,userName);
            exception.setText("Your good is public!");
        }
    }
}
