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

public class BirdPngReader {
    private static final Logger logger = Logger.getLogger( BirdPngReader.class.getName() );
    public Image birdImgs[] = new Image[3];
    /**
     * A madar kepeket beolvaso osztaly konstruktora.
     */
    public BirdPngReader() {
        try {
            for (int i = 0; i < birdImgs.length; i++) {
                birdImgs[i] = new Image(this.getClass().getClassLoader().getResourceAsStream("birdFrame" + i + ".png"));
                logger.info("madar beolvasva");
            }
            
        } catch (Exception e) {
            System.out.println("Problem in loading resourses");
        }
    }

}
