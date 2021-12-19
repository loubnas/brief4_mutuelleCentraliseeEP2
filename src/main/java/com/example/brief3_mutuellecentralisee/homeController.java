package com.example.brief3_mutuellecentralisee;

import com.example.brief3_mutuellecentralisee.dao.implimentation.ClientsImp;
import com.example.brief3_mutuellecentralisee.helpers.alertHelper;
import com.example.brief3_mutuellecentralisee.helpers.jsonHelper;
import com.example.brief3_mutuellecentralisee.helpers.validationHelper;
import com.example.brief3_mutuellecentralisee.models.ChartData;
import com.example.brief3_mutuellecentralisee.models.Client;
import com.example.brief3_mutuellecentralisee.models.CountryCode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class homeController implements Initializable {

    @FXML private TableColumn col_nameCompany;
    @FXML private TableColumn col_dateStartWork;
    @FXML private TableColumn col_firstName;
    @FXML private TableColumn col_lastName;
    @FXML private TableColumn col_cinpass;
    @FXML private TableColumn col_phone;
    @FXML private TableColumn col_email;
    @FXML private TableColumn col_adress;
    @FXML private TableColumn col_dateRegister;
    @FXML private TableColumn col_numBadgework;

    @FXML private TableView dataGrid;

    @FXML private TextField nameCompany;
    @FXML private DatePicker dateStartWork;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField cinpass;
    @FXML private RadioButton cin;
    @FXML private RadioButton pass;

    @FXML private ComboBox phoneCode;
    @FXML private TextField phone;
    @FXML private TextField email;
    @FXML private TextArea adress;
    @FXML private ToggleGroup identity;
    @FXML private TextField NumbadgeWork;
    @FXML private DatePicker dateRegister;

    @FXML private ComboBox company;
    @FXML private TextField filter;

    @FXML private LineChart chart;
    @FXML private CategoryAxis DateAxis;
    @FXML private NumberAxis CountAxis;

    private ObservableList<Client> clients;
    private List<CountryCode> countryCodes;

    private ClientsImp CIMP=new ClientsImp();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
        cin.setSelected(true);

            countryCodes=jsonHelper.GetPhoneCountryCodes();
            for(CountryCode cc : countryCodes) {
                phoneCode.getItems().add(cc.getDial_code());
            }


        col_nameCompany.setCellValueFactory(new PropertyValueFactory<Client, String>("nameCompany"));
        col_dateStartWork.setCellValueFactory(new PropertyValueFactory<Client, String>("dateStartWork"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        col_cinpass.setCellValueFactory(new PropertyValueFactory<Client, String>("cinpass"));
        col_phone.setCellValueFactory(new PropertyValueFactory<Client, String>("phone"));
        col_email.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        col_adress.setCellValueFactory(new PropertyValueFactory<Client, String>("adress"));
        col_dateRegister.setCellValueFactory(new PropertyValueFactory<Client, String>("dateRegister"));
        col_numBadgework.setCellValueFactory(new PropertyValueFactory<Client, String>("numBadgework"));


        clients= FXCollections.<Client>observableArrayList(); // initialisation d'une liste observable de type clients


        dataGrid.setItems(clients); // definie les elements a afficher (liste clients) dans la tablevie

        clients.addAll(CIMP.getAllClients());
        loadCompanies();
        loadStatistiques();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    private void loadCompanies() {

        company.getItems().clear();
        //Companies List
        List<String> companies=CIMP.getAllCompanies();
        company.getItems().add("");
        for(String nc : companies) {
            company.getItems().add(nc);
        }
    }

    private void loadStatistiques(){

        XYChart.Series series = new XYChart.Series();
        series.setName("Statistiques - Clients par date");
        List<ChartData> listChartData=CIMP.getClientsByDateRegister();

        // Tri par date :
        for(int i =0;i<listChartData.size();i++){

            for(int j =i+1;j<listChartData.size();j++){
                if(listChartData.get(i).getDate().compareTo(listChartData.get(j).getDate())>0){
                    ChartData x=listChartData.get(i);
                    listChartData.set(i,listChartData.get(j));
                    listChartData.set(j,x);
                }
            }
        }
        //fin Tri

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        int maxValue=1;
        for(ChartData cd:listChartData) {
            XYChart.Data data=new XYChart.Data(formatter.format(cd.getDate()),cd.getCount());
            if(cd.getCount()>maxValue){
                maxValue=cd.getCount();
            }
            series.getData().add(data);
        }


        CountAxis.setAutoRanging(false); // hauteur
        CountAxis.setLowerBound(0); // limite inferieur charte
        CountAxis.setUpperBound(maxValue+1); // limite superieur charte
        CountAxis.setTickUnit(1); // l'unite de gradulation pour l'axe

        chart.getData().clear();
        chart.getData().add(series);

        chart.setPrefWidth(50* listChartData.size());
    }


    @FXML
    private void addClientClick(ActionEvent event) {

        RadioButton selectedRadioButton = (RadioButton) identity.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();

        // Verification des données avant l'ajout du client - erreur en cas de non validité
        if(Validation()) {

            // L'ajout du client
            Client c = new Client(nameCompany.getText(), dateStartWork.getEditor().getText(), firstName.getText(), lastName.getText(), cinpass.getText(), phoneCode.getSelectionModel().getSelectedItem() +"-"+ phone.getText(), email.getText(), adress.getText(),dateRegister.getEditor().getText(),NumbadgeWork.getText());

            // tester cinpass si il existe deja ou non
            if(CIMP.getClientsByCinPass(cinpass.getText())==null){
            if(CIMP.insertClient(c)) {
                clients.add(c);

                // Message d'ajout :
                alertHelper.ShowSuccess("OK", "Client ajouter", "Client ajouter avec success");
                // Reset les valeurs des controls apres l'ajout
                nameCompany.setText("");
                dateStartWork.setValue(null);
                firstName.setText("");
                lastName.setText("");
                cinpass.setText("");
                phoneCode.getEditor().setText("");
                phone.setText("");
                email.setText("");
                adress.setText("");
                dateRegister.setValue(null);
                NumbadgeWork.setText("");


                loadStatistiques();
            }
            else{
                alertHelper.ShowError("Error","erreur lors de l'ajout au DB.","");
            }

            }else{
                alertHelper.ShowError("Error","Client avec meme CIN ou PASSPORT exist déja..","");
            }
        }

    }
    // --------- Validation ---------------------------------------------------------------------------
    private boolean Validation() {
        boolean valide=true;
        List<String> messages=new ArrayList<>();

        // dateRegister:
        if(dateRegister.getEditor().getText().equals("")){
            messages.add("Champ vide ('Date Register')");
            valide=  false;
        }

        //dateStartWork:
        if(dateStartWork.getEditor().getText().equals("")){
            messages.add("Champ vide ('Date Start Work')");
            valide=  false;
        }

        // firstname:
        if(!validationHelper.IsNotEmpty(firstName.getText())){
            messages.add("Champ vide ('First Name')");
            valide=  false;
        }
        if(!validationHelper.IsValidLength(firstName.getText(),50)){
            messages.add("Longeur incorrect ('First Name')");
            valide=  false;
        }


        // lastname:
        if(!validationHelper.IsNotEmpty(lastName.getText())){
            messages.add("Champ vide ('Last Name')");
            valide=  false;
        }
        if(!validationHelper.IsValidLength(lastName.getText(),50)){
            messages.add("Longeur incorrect ('Last Name')");
            valide=  false;
        }

        //cin passport:
        if(cin.isSelected()){
            if(!validationHelper.IsValidCIN(cinpass.getText())){
                messages.add("Format incorrect ('CIN') - ex : AA000000.");
                valide=  false;
            }
        }else{
            if(!validationHelper.IsValidPASSPORT(cinpass.getText())){
                messages.add("Format incorrect ('PASSPORT') - ex : AA0000000.");
                valide=  false;
            }
        }


        //phone:
        if(!validationHelper.IsValidPhone(phone.getText())){
            messages.add("Format incorrect ('Phone') - ex : +000-60000000.");
            valide=  false;
        }

        //email :
        if(!validationHelper.IsValidEmail(email.getText())){
            messages.add("Format incorrect ('Email') - ex : email@domain.com");
            valide=  false;
        }

        // adresse:
        if(!validationHelper.IsNotEmpty(adress.getText())){
            messages.add("Champ vide ('Adresse')");
            valide=  false;
        }

        //N badge:
        if(!validationHelper.IsNotEmptyNumber(NumbadgeWork.getText())){
            messages.add("Champ vide ('NumBadgeWork')");
            valide=  false;
        }

        if(!validationHelper.IsValidLength(NumbadgeWork.getText(),10)){
            messages.add("Longeur incorrect ('N Badge')");
            valide=  false;
        }


        //Name Company:

        if(!validationHelper.IsNotEmpty(nameCompany.getText())){
            messages.add("Champ vide ('Name Company')");
            valide=  false;
        }

        if(!validationHelper.IsValidLength(nameCompany.getText(),50)){
           messages.add("Longeur incorrect ('Name Company')");
            valide=  false;
        }

    if(!valide){
         alertHelper.ShowError("Erreur de validation","",String.join("\n",messages));

    }
    return valide;
    }

    //--------------------------------------------------------------------------------------------------


    // -------------------------     FILTRAGE  ----------------------------------------------------------

    private ObservableList<Client> getByCompany(String company,String searchKeyword){
        ObservableList<Client> clients = FXCollections.observableArrayList(CIMP.getByCompany(company,searchKeyword));
        return clients;
    }

    @FXML
    private void companyHandler(ActionEvent event){

        Filter();
    }

    @FXML
    private void filterHandler(KeyEvent event) {

        Filter();
    }

    void Filter(){
   if(company.getValue()!=null){
       // filtre by company & search :
        if(!company.getValue().toString().equals("")) {
            dataGrid.getItems().clear();
            dataGrid.setItems(getByCompany(company.getValue().toString(),filter.getText()));
        }
        // search
        else{
            dataGrid.getItems().clear();
            clients.clear();
            clients.addAll(CIMP.getAllClients(filter.getText()));
            dataGrid.setItems(clients);
        }

   } else{

            dataGrid.getItems().clear();
            clients.clear();
            clients.addAll(CIMP.getAllClients(filter.getText()));
            dataGrid.setItems(clients);
        }
    }

}
//------------------------------------------------------------------------------------------------------

