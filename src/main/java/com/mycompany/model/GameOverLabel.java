package com.mycompany.model;

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

import java.util.logging.Logger;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class GameOverLabel/*ScoreLabel*/ extends Pane {
    private static final Logger logger = Logger.getLogger( GameOverLabel.class.getName() );
    Text status = new Text("Score: 0");
    /**
     * Gameover label-t eloallito osztaly konstruktora.
     * @param x a label x pozicioja
     * @param y a label y pozicioja
     */
    public GameOverLabel(double x, double y) {
        setPrefHeight(100);
        setPrefWidth(300);
        setTranslateX(x );
        setTranslateY(y + 10);
        setStyle("-fx-background-color: #CCC;");
        setOpacity(0.8);
        status.setTranslateY(+40);
        status.setTranslateX(70);
        getChildren().addAll(status);
        logger.info("GameOverLabel elkeszitve");
    }
    /**
     * Beallitja a status szoveget.
     * @param message a statusz leendo szovege
     */
    public void setText(String message) {
        status.setText(message);
    }
}
