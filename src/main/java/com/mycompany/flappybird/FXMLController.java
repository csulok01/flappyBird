package com.mycompany.flappybird;

/*-
 * #%L
 * flappyBird
 * %%
 * Copyright (C) 2018 Debreceni Egyetem
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 * #L%
 */

import com.mycompany.model.ScoreReader;
import com.mycompany.model.FlappyBird_gameloop;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXMLController implements Initializable {


    @FXML
    public Button closeButton;
    @FXML
    public Label score1ID;
    @FXML
    public Label score2ID;
    @FXML
    public Label score3ID;
    @FXML
    public Label score4ID;
    @FXML
    public Label score5ID;
    @FXML
    public Label score6ID;
    
    @FXML
    private void start(ActionEvent event) throws Exception{
        Pane root2 = FXMLLoader.load(getClass().getResource("/fxml/playScene.fxml"));
        FlappyBird_gameloop game = new FlappyBird_gameloop(root2);
        Scene scene = new Scene(root2);
        Stage stage = new Stage();
        stage.setTitle("Flappy bird");
        stage.setScene(scene);
        stage.show();
        game.initGame();
        
        root2.setOnMouseClicked(e -> {
            if (!game.gameOver) {
                game.jumpflappy();
            } else {
                game.initializeGame();
            }
        });
        score1ID.setOpacity(0);
        score2ID.setOpacity(0);
        score3ID.setOpacity(0);
        score4ID.setOpacity(0);
        score5ID.setOpacity(0);
        score6ID.setOpacity(0);
        //Stage stage2 = (Stage) closeButton.getScene().getWindow();
        //stage2.close();
    }

    @FXML
    private void leaderBoards(ActionEvent event) throws IOException, InterruptedException {
        ScoreReader reader = new ScoreReader();
        String path =System.getProperty("user.dir");
        reader.readXML();
        score1ID.setText("1: " + reader.getFirst());
        score2ID.setText("2: " + reader.getSecond());
        score3ID.setText("3: " + reader.getThird());
        score4ID.setText("4: " + reader.getFourth());
        score5ID.setText("5: " + reader.getFifth());
        score6ID.setText("6: " + reader.getSisth());
        score1ID.setOpacity(1);
        score2ID.setOpacity(1);
        score3ID.setOpacity(1);
        score4ID.setOpacity(1);
        score5ID.setOpacity(1);
        score6ID.setOpacity(1);

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

}
    
 
