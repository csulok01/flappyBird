package Test;

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
import com.mycompany.model.BirdPngReader;
import com.mycompany.model.FlappyBird_gameloop;
import static org.junit.Assert.assertEquals;
import com.mycompany.model.ScoreReader;
import com.mycompany.model.Tubes;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;
import javax.xml.transform.TransformerException;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.xml.sax.SAXException;

public class Flappy_Test {
    @Test
    public void testisdocEmpty() {
        ScoreReader reader = new ScoreReader();
        reader.readXML();
        
        assertTrue(!(reader.getFirst().isEmpty()));
        assertTrue(!(reader.getSecond().isEmpty()));
        assertTrue(!(reader.getThird().isEmpty()));
        assertTrue(!(reader.getFourth().isEmpty()));
        assertTrue(!(reader.getFifth().isEmpty()));
        assertTrue(!(reader.getSisth().isEmpty()));
    }
    @Test
    public void TestIsScoresInOrder() throws SAXException, TransformerException {
        ScoreReader reader = new ScoreReader();
        reader.readXML();
        assertTrue(Integer.valueOf(reader.getFirst()) >= Integer.valueOf(reader.getSecond()));
        assertTrue(Integer.valueOf(reader.getSecond()) >= Integer.valueOf(reader.getThird()));
        assertTrue(Integer.valueOf(reader.getFourth()) >= Integer.valueOf(reader.getFifth()));
        assertTrue(Integer.valueOf(reader.getFifth()) >= Integer.valueOf(reader.getSisth()));
    }
    @Test
    public void TestImagesSameSize(){
        BirdPngReader pngReader = new BirdPngReader();
        assertEquals((int)pngReader.birdImgs[0].getWidth(),(int)pngReader.birdImgs[1].getWidth());
        assertEquals((int)pngReader.birdImgs[1].getWidth(),(int)pngReader.birdImgs[2].getWidth());
        assertEquals((int)pngReader.birdImgs[0].getHeight(),(int)pngReader.birdImgs[1].getHeight());
        assertEquals((int)pngReader.birdImgs[1].getHeight(),(int)pngReader.birdImgs[2].getHeight());
    }
    @Test
    public void TestTubesSameSize(){
        Pane root = new Pane();
        root.setPrefSize(1000, 500);
        SimpleDoubleProperty gapLocation = new SimpleDoubleProperty(0);
        gapLocation.set(root.getHeight() * Math.random() / 2.0);
        Tubes tube = new Tubes(gapLocation,root);
        assertEquals((int)tube.lowerBody.getWidth(),(int)tube.topBody.getWidth());
        assertEquals((int)tube.lowerHead.getWidth(),(int)tube.lowerHead.getWidth());
        assertEquals((int)tube.lowerHead.getHeight(),(int)tube.lowerHead.getHeight());
    }
    
    @Test
    public void TestIsAchievementEarned(){
        Pane root = new Pane();
        FlappyBird_gameloop game = new FlappyBird_gameloop(root);
        assertTrue(game.Achievement_earned(11));
        assertEquals(true,game.Achievement_earned(21));
        assertEquals(false,game.Achievement_earned(21));
    }
}
