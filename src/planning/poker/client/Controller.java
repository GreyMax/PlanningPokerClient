package planning.poker.client;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import planning.poker.Estimate;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public TextField optimisticField;
    public TextField pessimisticField;
    public TextField moreLikelyField;
    public Button submitButton;
    private Requester client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        client = new Requester();
        new Thread(new Runnable() {
            @Override
            public void run() {
                client.run();
            }
        }).start();
    }

    public void submitEstimate(ActionEvent actionEvent) {
        Double optimistic = Double.parseDouble(optimisticField.getText());
        Double pessimistic = Double.parseDouble(pessimisticField.getText());
        Double moreLikely = Double.parseDouble(moreLikelyField.getText());
        Estimate estimate = new Estimate("bla-bla", optimistic, pessimistic, moreLikely);
        estimate.evaluateWeightedAvg();
        client.sendEstimate(estimate);
    }
}
