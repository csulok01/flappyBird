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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class Tubes extends Group {
    private static final Logger logger = Logger.getLogger( Tubes.class.getName() );
    public Rectangle topHead, lowerHead;
    public Rectangle topBody, lowerBody;
    double GAP = 120;
    boolean vertical_move = false;
    Timeline verticalTube;
    double rand = Math.random();
    int cycleup=0;
    int cycledown=0;
    /**
     *  A csoveket eloallito osztaly konstruktora.
     *  @param gapLocation a csovek kozotti hezag pozicioja
     *  @param root2 a jatek ablak panelje
     */
    public Tubes(SimpleDoubleProperty gapLocation, Pane root2) {
        vertical_move= rand < 0.3 ? true : false;
        
        if (vertical_move) {
            logger.info("mozgo cso keszites");
            GAP=160;
            verticalTube = new Timeline(new KeyFrame(Duration.millis(30), e -> {
                if(cycleup<60){
                gapLocation.set(gapLocation.getValue() + 1);
                cycleup++;
                }
                else{
                gapLocation.set(gapLocation.getValue() - 1);
                cycledown++;
                if(cycledown%60 == 0){
                    cycleup = 0;
                }
                }
                
            }));
            verticalTube.setCycleCount(-1);
            verticalTube.play();
        }
        topBody = new Rectangle();
        topBody.widthProperty().bind(root2.widthProperty().divide(12.3));
        topBody.heightProperty().bind(gapLocation);
        topHead = new Rectangle();
        topHead.widthProperty().bind(root2.widthProperty().divide(11.4));
        topHead.heightProperty().bind(root2.heightProperty().divide(12));
        topBody.setX(2.5);
        topHead.yProperty().bind(gapLocation);
        lowerHead = new Rectangle();
        lowerHead.widthProperty().bind(root2.widthProperty().divide(11.4));
        lowerHead.heightProperty().bind(root2.heightProperty().divide(12));
        lowerBody = new Rectangle();
        lowerBody.widthProperty().bind(root2.widthProperty().divide(12.3));
        lowerBody.heightProperty().bind(root2.heightProperty().add(-GAP - 50).subtract(gapLocation));
        lowerBody.setX(2.5);
        lowerHead.yProperty().bind(gapLocation.add(GAP).add(root2.heightProperty().divide(12)));
        lowerBody.yProperty().bind(gapLocation.add(GAP).add(root2.heightProperty().divide(6)));
        lowerHead.setFill(Color.GREEN);
        lowerBody.setFill(Color.GREEN);
        topHead.setFill(Color.GREEN);
        topBody.setFill(Color.GREEN);
        lowerHead.setStroke(Color.BLACK);
        lowerBody.setStroke(Color.BLACK);
        topHead.setStroke(Color.BLACK);
        topBody.setStroke(Color.BLACK);
        getChildren().addAll(topBody, topHead, lowerBody, lowerHead);
        logger.info("cso keszen");
    }
}

