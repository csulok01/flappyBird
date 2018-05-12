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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * A madarat letrehozo osztaly.
 */
public class Bird {
    private static final Logger logger = Logger.getLogger( Bird.class.getName() );
    private ImageView graphics = new ImageView();
    private Image frames[];
    private int frameCounter = 0;
    public boolean jumping = false;
    Ellipse bounds;
    
    /**
     * @return visszaadja az akutalis madar kepet
     */
    public ImageView getGraphics() {
        return graphics;
    }
    /**
     *  @return visszaadja a madar hitbox-at
     */
    public Ellipse getBounds() {
        return bounds;
    }
    /**
     * A madarat letrehozo osztaly konstruktora.
     * @param frames a madar kepek tombje
     */
    public Bird(Image[] frames) {
        this.frames = frames;
        this.bounds = new Ellipse(frames[0].getWidth() / 2.0, frames[0].getHeight() / 2.0/*11.5*/);
        graphics.setImage(frames[0]);
        bounds.setFill(Color.TRANSPARENT);
        bounds.setStroke(Color.BLACK);
        bounds.centerXProperty().bind(graphics.translateXProperty().add(frames[0].getWidth() / 2.0));
        bounds.centerYProperty().bind(graphics.translateYProperty().add(frames[0].getHeight() / 2.0/*12.0*/));
        bounds.rotateProperty().bind(graphics.rotateProperty());
        logger.info("madar elkeszitve");
    }
    /**
     * Az akutalis madar kepet valtoztato metodus.
     */
    public void refreshBird() {
        graphics.setImage(frames[frameCounter++]);
        if (frameCounter == 3) {
            frameCounter = 0;
        }
    }

}
