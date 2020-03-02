package com.alejandro.ataleofheroes.actors;

import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import gameobjects.CharacterEscalated;

/***
 * This class creates the hitboxes of the map and try to interact with the game character.
 */
public class GameHitboxes {

    private Actor[]actors;
    private Rectangle[]rect;
    private Rectangle gCharacter;


    public void checkCollision(TiledMap map, CharacterEscalated personaje) {
        gCharacter=new Rectangle();
        gCharacter.set(personaje.getX(),personaje.getY(),personaje.getWidth(),personaje.getHeight());
        MapObjects mons = map.getLayers().get("hitboxes").getObjects();
        // MapObjects mons2 = map.getLayers().get("Entrada").getObjects();
        actors=new Actor[mons.getCount()];
        rect=new Rectangle[mons.getCount()];
        for (int i = 0;i < mons.getCount(); i++) {
            RectangleMapObject obj1 = (RectangleMapObject) mons.get(i);
            Rectangle rect1 = obj1.getRectangle();
            rect[i]=new Rectangle((rect1.x * 2),(rect1.y * 2),(rect1.width * 2),(rect1.height* 2));


            /*
            rect[i]=rect1;
            rect[i].set(rect1.x,rect1.y,rect1.width,rect1.height);
            */


            actors[i]=new Actor();
            actors[i].setBounds(rect1.x * 2,rect1.y * 2,rect1.width * 2,rect1.height * 2);
            //actores[i].setColor(Color.BLUE);

        }
    }

    public Actor[] getActors() {
        return actors;
    }

    public Rectangle[] getRect() {

        return rect;
    }
}

