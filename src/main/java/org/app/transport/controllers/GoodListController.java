package org.app.transport.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.app.transport.model.User;
import org.app.transport.services.UserService;

import java.io.IOException;
import java.util.ArrayList;

public class GoodListController {
    @FXML
    private Button ReturnHome;
    @FXML
    private ListView<String> listView;
    @FXML
    private Button filter;
    @FXML
    private Button ProposeButton;
    private String username;
    private String listElement;
    private String LocT;
    private String LocF;
    private ArrayList<String> a =new ArrayList<String>();
    public void setUsername(String username,String LocF,String LocT)
    {   this.LocF=LocF;
         this.LocT=LocT;
        this.username=username;
        String[] splits;
        String [] splits2;
        String b;
        for(User user: UserService.userRepository.find())
            if(user.getRole().compareTo("Transport client")==0) {
                splits=user.getGood().split("/");
                for (String s : splits) {
                    if(s.compareTo("*")!=0&&s.compareTo("")!=0) {
                        splits2 = s.split("-");
                        if(LocF.compareTo("Everywhere")==0&&LocT.compareTo("Everywhere")==0)
                        {
                            b = splits2[3] + ":" + splits2[1] + "-" + splits2[2];
                            a.add(b);
                            listView.getItems().add(b);
                        }
                        else
                        if(LocF.compareTo("Everywhere")==0&&splits2[2].compareTo(LocT)==0)
                        {
                            b = splits2[3] + ":" + splits2[1] + "-" + splits2[2];
                            a.add(b);
                            listView.getItems().add(b);
                        }
                        else
                            if(LocT.compareTo("Everywhere")==0&&splits2[1].compareTo(LocF)==0)
                            {
                                b = splits2[3] + ":" + splits2[1] + "-" + splits2[2];
                                a.add(b);
                                listView.getItems().add(b);
                            }
                            else
                        if(splits2[1].compareTo(LocF)==0&&splits2[2].compareTo(LocT)==0) {
                            b = splits2[3] + ":" + splits2[1] + "-" + splits2[2];
                            a.add(b);
                            listView.getItems().add(b);
                        }
                    }
                }
            }
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                listElement = listView.getSelectionModel().getSelectedItem();
            }
        });

    }

    public void handleReturn(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/truckingHomePage.fxml"));
            Parent root = (Parent) loader.load();
            TruckingHomePageController log=loader.getController();
            log.setUserName(username);
            Stage window = (Stage) ReturnHome.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void handleRefresh(MouseEvent mouseEvent) {
        listView.getItems().removeAll(a);
        a.removeAll(a);
        String[] splits;
        String [] splits2;
        String b;
        for(User user: UserService.userRepository.find())
            if(user.getRole().compareTo("Transport client")==0) {
                splits=user.getGood().split("/");
                for (String s : splits) {
                    if(s.compareTo("*")!=0&&s.compareTo("")!=0) {
                        splits2 = s.split("-");
                        if(LocF.compareTo("Everywhere")==0&&LocT.compareTo("Everywhere")==0)
                        {
                            b = splits2[3] + ":" + splits2[1] + "-" + splits2[2];
                            a.add(b);
                            listView.getItems().add(b);
                        }
                        else
                        if(LocF.compareTo("Everywhere")==0&&splits2[2].compareTo(LocT)==0)
                        {
                            b = splits2[3] + ":" + splits2[1] + "-" + splits2[2];
                            a.add(b);
                            listView.getItems().add(b);
                        }
                        else
                        if(LocT.compareTo("Everywhere")==0&&splits2[1].compareTo(LocF)==0)
                        {
                            b = splits2[3] + ":" + splits2[1] + "-" + splits2[2];
                            a.add(b);
                            listView.getItems().add(b);
                        }
                        else
                        if(splits2[1].compareTo(LocF)==0&&splits2[2].compareTo(LocT)==0) {
                            b = splits2[3] + ":" + splits2[1] + "-" + splits2[2];
                            a.add(b);
                            listView.getItems().add(b);
                        }
                    }
                }
            }
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                listElement = listView.getSelectionModel().getSelectedItem();
            }
        });

    }

    public void handleFilter(MouseEvent mouseEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/filterPage.fxml"));
            Parent root = (Parent) loader.load();
            FilterPageController log=loader.getController();
            log.setUserName(username);
            Stage window = (Stage) filter.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void handlePropose(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PricePage.fxml"));
            Parent root = (Parent) loader.load();
            PricePageController log=loader.getController();
            log.setUserDetails(username,listElement);
            Stage window = (Stage) ProposeButton.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
