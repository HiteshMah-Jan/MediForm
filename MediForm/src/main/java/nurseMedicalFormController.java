import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
Hand <= product.ReorderLevel)
            {
                string subject = string.Format("{0} at or below reorder levels", product.ProductName);

                foreach (var subscriber in product.ProductSubscribers)
                {
                    List<string> mailTos = new List<string>();

                    string message = string.Format("<html><body>Dear {0} {1}.<br></br><p>The product '{2}' is at or below the reorder level:<br></br>The reorder level is {3} but you currently only have {4} on hand.<br></br>For info, the number of units on order is {5}.</p></body></html>", subscriber.Employee.FirstName, subscriber.Employee.LastName, product.ProductName, product.ReorderLevel, unitsOnHand, unitsOnOrder);

                    mailTos.Add(subscriber.Employee.Email);

                    SendEmail(mailTos, subject, message);
                }

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class nurseMedicalFormController implements Initializable{
    @FXML
    private TextField firstName1;

    @FXML
    private TextField lastName1;

    @FXML
    private TextField height1;

    @FXML
    private TextField temp1;

    @FXML
    private TextField weight1;

    @FXML
    private TextField bloodPress1;

    @FXML
    private TextField pulseRate1;

    @FXML
    private TextField assignedPhysician1;

    @FXML
    private TextField symptoms1;

    @FXML
    private TextField medications1;

    @FXML
    private TextField tests1;

    @FXML
    private TextField diagnosis1;

    @FXML
    void submitVitals(ActionEvent event) throws IOException {
        Main.patient.setHeight(Float.parseFloat(height1.getText()));
        Main.patient.setWeight(Float.parseFloat(weight1.getText()));
        Main.patient.setTemperature(Float.parseFloat(height1.getText()));
        Main.patient.setBloodPressure(bloodPress1.getText());
        Main.patient.setPulseRate(pulseRate1.getText());
        Main.patient.setAssignedPhysician(assignedPhysician1.getText());

        DatabaseInterface.updatePatient(Main.patient);

        Parent nurseMenuParent = FXMLLoader.load(getClass().getResource("nurseMenu.fxml"));
        Scene nurseMenuScene = new Scene(nurseMenuParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(nurseMenuScene);
        window.show();
    }

    @FXML
    void toNurseMenu(ActionEvent event) throws IOException {
        Parent nurseMenuParent = FXMLLoader.load(getClass().getResource("nurseMenu.fxml"));
        Scene nurseMenuScene = new Scene(nurseMenuParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(nurseMenuScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String name = Main.patient.getName();
        String[] nameSplit = name.split(" ");
        firstName1.setText(nameSplit[0]);
        lastName1.setText(nameSplit[1]);

        Float f = (Float) Main.patient.getHeight();
        if(f == 0) height1.setText("");
        else height1.setText(f.toString());

        f = (Float) Main.patient.getWeight();
        if(f == 0) weight1.setText("");
        else weight1.setText(f.toString());

        f = (Float) Main.patient.getTemperature();
        if(f == 0) temp1.setText("");
        else temp1.setText(f.toString());

        String s = Main.patient.getBloodPressure();
        if(s == null) bloodPress1.setText("");
        else bloodPress1.setText(s);

        s = Main.patient.getPulseRate();
        if(s == null) pulseRate1.setText("");
        else pulseRate1.setText(s);

        s = Main.patient.getAssignedPhysician();
        if(s == null) assignedPhysician1.setText("");
        else assignedPhysician1.setText(s);

        symptoms1.setText(Main.patient.getSymptoms());

        StringBuilder sb = new StringBuilder();

        if(Main.patient.getPain() != 0) {
            sb.append("Pain: ");
            sb.append(Main.patient.getPain());
            sb.append("; ");
        }
        if(Main.patient.getAnticoagulant() != 0) {
            sb.append("Anticoagulant: ");
            sb.append(Main.patient.getAnticoagulant());
            sb.append("; ");
        }
        if(Main.patient.getBetaBlocker() != 0) {
            sb.append("Beta Blocker: ");
            sb.append(Main.patient.getBetaBlocker());
            sb.append("; ");
        }
        if(Main.patient.getAntiretroviral() != 0) {
            sb.append("Antiretroviral: ");
            sb.append(Main.patient.getAntiretroviral());
            sb.append("; ");
        }
        if(Main.patient.getInsulin() != 0) {
            sb.append("Insulin: ");
            sb.append(Main.patient.getInsulin());
            sb.append("; ");
        }
        if(Main.patient.getAntiInflammatory() != 0) {
            sb.append("Anti Inflammatory: ");
            sb.append(Main.patient.getAntiInflammatory());
            sb.append("; ");
        }
        if(Main.patient.getUrsodiol() != 0) {
            sb.append("Ursodiol: ");
            sb.append(Main.patient.getUrsodiol());
            sb.append("; ");
        }
        if(Main.patient.getCalciumReducer() != 0) {
            sb.append("Calcium Reducer: ");
            sb.append(Main.patient.getCalciumReducer());
            sb.append("; ");
        }

        medications1.setText(sb.toString());
        sb.setLength(0);

        if(Main.patient.getNucleicAcid())
            sb.append("Calcium Reducer; ");
        if(Main.patient.getCoagulationPanel())
            sb.append("Coagulation Panel; ");
        if(Main.patient.getDheaSulfateSerum())
            sb.append("Dhea Sulfate Serum; ");
        if(Main.patient.getcReactiveProtein())
            sb.append("C Reactive Protein; ");
        if(Main.patient.getAlc())
            sb.append("ALC; ");
        if(Main.patient.getXray())
            sb.append("XRay; ");
        if(Main.patient.getMri())
            sb.append("MRI; ");
        if(Main.patient.getCtScan())
            sb.append("CT Scan; ");
        if(Main.patient.getUrinalysis())
            sb.append("Urinalysis; ");
        if(Main.patient.getStoolCultures())
            sb.append("Stool Cultures; ");

        tests1.setText(sb.toString());
        sb.setLength(0);

        if(Main.patient.getBrokenBone())
            sb.append("Broken bone; ");
        if(Main.patient.getHeartAttack())
            sb.append("Heart attack; ");
        if(Main.patient.getLaceration())
            sb.append("Laceration; ");
        if(Main.patient.getHivAIDS())
            sb.append("HIV/AIDS; ");
        if(Main.patient.getLiverFailure())
            sb.append("Liver failure; ");
        if(Main.patient.getKidneyFailure())
            sb.append("Kidney Failure; ");
        if(Main.patient.getDiabetes())
            sb.append("Diabetes; ");
        if(Main.patient.getInfammatoryBowlDisease())
            sb.append("Inflammatory bowl disease; ");
        if(Main.patient.getStroke())
            sb.append("Stroke; ");
        if(Main.patient.getTornMuscleTendon())
            sb.append("Torn muscle/Tendon; ");

        diagnosis1.setText(sb.toString());
    }
}
