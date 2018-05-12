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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class FlappyBird_gameloop {
    private static final Logger logger = Logger.getLogger( FlappyBird_gameloop.class.getName() );
    BirdPngReader res = new BirdPngReader();
    Pane root2=new Pane();
    public boolean gameOver = false;
    private boolean NotCountedYet = true;
    int score = 0;
    int highScore = 0;
    int birdRefresh = 0; 
    Bird bird;
    TranslateTransition jump;
    TranslateTransition fall;
    RotateTransition birdRotate;
    ArrayList<Tubes> TubeList = new ArrayList<>();
    Timeline gamePlay;
    boolean IsAchievement_earned=false;
    int achievement = 10;
    int achievement_counter = 0;
    int firstScore;
    int secondScore;
    int thirdScore;
    int fourthScore;
    int fifthScore;
    int sixthScore;
    List<Integer> scores;
    ScoreReader reader = new ScoreReader();
    
    public FlappyBird_gameloop(Pane root){
        this.scores = new ArrayList<>();
        root2 = root;
        reader.readXML();
        firstScore= Integer.valueOf(reader.getFirst());
        secondScore= Integer.valueOf(reader.getSecond());
        thirdScore= Integer.valueOf(reader.getThird());
        fourthScore= Integer.valueOf(reader.getFourth());
        fifthScore= Integer.valueOf(reader.getFifth());
        sixthScore= Integer.valueOf(reader.getSisth());
        scores.add(firstScore);
        scores.add(secondScore);
        scores.add(thirdScore);
        scores.add(fourthScore);
        scores.add(fifthScore);
        scores.add(sixthScore);
    }
    /**
     * Friss?ti a madar kepet.
     */
    private void updateCounters() {
        if (birdRefresh % 4 == 0) {
            bird.refreshBird();
            birdRefresh = 1;
        }
        birdRefresh++;
    }
    /**
     * A madar ugrasat es forgatasat vegrehajto metodus.
     * 
     */
    public void jumpflappy()  {
        logger.info("madar ugrik");
        birdRotate.setDuration(Duration.millis(100));
        birdRotate.setToAngle(-40);
        birdRotate.stop();
        birdRotate.play();
        jump.setByY(-50);
        jump.setCycleCount(1);
        bird.jumping = true;
        fall.stop();
        jump.stop();
        jump.play();
        jump.setOnFinished((finishedEvent) -> {
            birdRotate.setDuration(Duration.millis(500));
            birdRotate.setToAngle(40);
            birdRotate.stop();
            birdRotate.play();
            bird.jumping = false;
            fall.play();
        });
    }
    /**
     * Ellenorzi, hogy a madar utkozott -e a csovel.
    */
    private void checkCollisions() {
        Tubes tube = TubeList.get(0);
        if (tube.getTranslateX() < 35 && NotCountedYet) {
            score++;
            NotCountedYet = false;
            Achievement_earned(score);
        }
        Path p1 = (Path) Shape.intersect(bird.getBounds(), tube.topBody);
        Path p2 = (Path) Shape.intersect(bird.getBounds(), tube.topHead);
        Path p3 = (Path) Shape.intersect(bird.getBounds(), tube.lowerBody);
        Path p4 = (Path) Shape.intersect(bird.getBounds(), tube.lowerHead);
        boolean intersection = !(p1.getElements().isEmpty()
                && p2.getElements().isEmpty()
                && p3.getElements().isEmpty()
                && p4.getElements().isEmpty());
        if (bird.getBounds().getCenterY() + bird.getBounds().getRadiusY() > root2.getHeight() || bird.getBounds().getCenterY() - bird.getBounds().getRadiusY() < 0) {
            intersection = true;
        }
        if (intersection) {
            logger.info("utkozes");
            GameOverLabel gameOverLabel = new GameOverLabel(root2.getWidth() / 2, root2.getHeight() / 2);
            gameOverLabel.setText("Tap to retry. Score: " + score + "\n\tHighScore: " + highScore + "\nYou earned " +achievement_counter+ " achievement");
            saveHighScore();
            root2.getChildren().add(gameOverLabel);
            achievement_counter = 0;
            gameOver = true;
            gamePlay.stop();
            
        }
    }
    /**
     * Elokesziti az ertekeket a jatak megkezdesehez.
     */
    public void initializeGame() {
        logger.info("jatek elokeszitese");
        TubeList.clear();
        root2.getChildren().clear();
        bird.getGraphics().setTranslateX(200);
        bird.getGraphics().setTranslateY(150);
        root2.getChildren().addAll(bird.getGraphics());
        for (int i = 0; i < 5; i++) {
            SimpleDoubleProperty y = new SimpleDoubleProperty(0);
            y.set(root2.getHeight() * Math.random() / 2.0);
            Tubes tube = new Tubes(y, root2);
            tube.setTranslateX(i * (root2.getWidth() / 4 + 10) + 400);
            TubeList.add(tube);
            root2.getChildren().add(tube);
        }
        score = 0;
        NotCountedYet = true;
        gameOver = false;
        bird.jumping = false;
        fall.stop();
        fall.play();
        gamePlay.play();
        
    }
    /**
     * Letrehozza a jatekhoz szukseges valtozokat, objektumokat.
     */
    public void initGame() {
        bird = new Bird(res.birdImgs);
        birdRotate = new RotateTransition(Duration.millis(500), bird.getGraphics());
        jump = new TranslateTransition(Duration.millis(450), bird.getGraphics());
        fall = new TranslateTransition(Duration.millis(5 * root2.getHeight()), bird.getGraphics());
        fall.setByY(root2.getHeight() + 20);
        
        birdRotate.setCycleCount(1);
        gamePlay = new Timeline(new KeyFrame(Duration.millis(13), (ActionEvent e) -> {
            updateCounters();
            checkCollisions();
            if (TubeList.get(0).getTranslateX() <= -root2.getWidth() / 12.3) {
                TubeList.remove(0);
                SimpleDoubleProperty y = new SimpleDoubleProperty(0);
                y.set(root2.getHeight() * Math.random() / 2.0);
                Tubes tube = new Tubes(y, root2);
                tube.setTranslateX(TubeList.get(TubeList.size() - 1).getTranslateX() + (root2.getWidth() / 4 + 10));
                TubeList.add(tube);
                NotCountedYet = true;
                root2.getChildren().remove(1);
                root2.getChildren().add(tube);
                
            }
            for (int i = 0; i < TubeList.size(); i++) {
                TubeList.get(i).setTranslateX(TubeList.get(i).getTranslateX() - 2);
            }
            
        }));
        gamePlay.setCycleCount(-1);
        initializeGame();
        loadHighScore();
    }
    /**
     * Ellenorzi, hogy a madar atlepett -e 10 csovet.
     * @return visszaadja, hogy sikerult -e
     * @param score az aktu?lis eredmeny
     */
    public boolean Achievement_earned(int score){
        if(score>=achievement){
            logger.info("10 cson athaladt");
            IsAchievement_earned=true;
            achievement+=10;
            achievement_counter++;
            return true;
        }else{
        return false;
        }
    }
    /**
     * Betolti az eredmenyeket az xml-bol.
     */
    void loadHighScore() {
        try {
            ScoreReader reader = new ScoreReader();
            reader.readXML();
            highScore = Integer.valueOf(reader.getFirst());
        } catch (Exception e) {
            System.out.print("loadHighScore");
        }
    }
    /**
     * Menti az eredmenyt egy xml-be(csak akkor, ha az eredmeny jobb, mint az elso hat eredmeny valamelyike).
     */
    void saveHighScore() {
        try {
            for(int i =0;i<scores.size();i++){
                if(score>scores.get(i)){
                    scores.set(i, score);
                    //String path = System.getProperty("user.dir");
                    scoreSaver saver = new scoreSaver();
                    saver.saveToXML(scores);
                    break;
                }
            }
            
        } catch (Exception e) {
            System.out.print("saveHighScore");
        }
    }

}
