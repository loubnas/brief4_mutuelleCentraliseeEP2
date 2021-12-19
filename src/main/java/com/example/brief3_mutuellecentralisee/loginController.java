package com.example.brief3_mutuellecentralisee;

import com.example.brief3_mutuellecentralisee.dao.implimentation.UsersImp;
import com.example.brief3_mutuellecentralisee.helpers.alertHelper;
import com.example.brief3_mutuellecentralisee.helpers.fileHelper;
import com.example.brief3_mutuellecentralisee.helpers.jsonHelper;
import com.example.brief3_mutuellecentralisee.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    private Stage parentStage;
    @FXML private TextField username;
    @FXML private TextField password;

    private UsersImp UIMP=new UsersImp();


    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
    }

    @FXML
    private void loginClick(ActionEvent event)
    {
        try {
            String user=username.getText();
            String pass=password.getText();

            boolean connected=false;

            User u=UIMP.Connect(user,pass);
            if(u!=null){
                FXMLLoader fxmlLoader = new FXMLLoader(application.class.getResource("home-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 900, 600);
                this.parentStage.setScene(scene);
                this.parentStage.setTitle("Home");
            }
            else{
                System.out.println("Erreur Connexion");
                alertHelper.ShowError("Erreur","Erreur de connexion","login ou mot de passe incorrect");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}