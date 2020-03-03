package com.alejandro.ataleofheroes.actors;



import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
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

    private Actor[]actor;
    private Rectangle[] mapCollision;
    private Rectangle characterHitbox;
    private Map map;


    /*public void checkCollision(TiledMap map, CharacterEscalated personaje, float widthPixel, float heightPixel) {
        gCharacter=new Rectangle();
        gCharacter.set(personaje.getX(),personaje.getY(),personaje.getWidth(),personaje.getHeight());
        MapObjects mons = map.getLayers().get("hitboxes").getObjects();
        // MapObjects mons2 = map.getLayers().get("Entrada").getObjects();
        actors=new Actor[mons.getCount()];
        rect=new Rectangle[mons.getCount()];
        for (int i = 0;i < mons.getCount(); i++) {
            RectangleMapObject obj1 = (RectangleMapObject) mons.get(i);
            Rectangle rect1 = obj1.getRectangle();
            rect[i]=new Rectangle((rect1.x * widthPixel),(rect1.y * heightPixel),(rect1.width * widthPixel),(rect1.height * heightPixel));

            actors[i]=new Actor();
            actors[i].setBounds(rect1.x,rect1.y,rect1.width ,rect1.height);
            //actores[i].setColor(Color.BLUE);

        }
    }

    public Actor[] getActors() {
        return actors;
    }

    public Rectangle[] getRect() {

        return rect;
    }*/


   /* public void checkCollision(TiledMap tiledMap, CharacterEscalated character) {

        characterHitbox = new Rectangle();
        characterHitbox.set(character.getX(), character.getY(), character.getWidth(), character.getHeight());
        MapObjects mons= tiledMap.getLayers().get("hitboxes").getObjects();
        actor = new Actor[mons.getCount()];
        map = new Map();
        mapCollision = new Rectangle[mons.getCount()];

        for(int i = 0; i < mons.getCount(); i++) {
            RectangleMapObject obj1 = (RectangleMapObject) mons.get(i);
            Rectangle rect1 = obj1.getRectangle();
            mapCollision[i] = rect1;
            mapCollision[i].set(rect1.x * map.get)
        }


    }*/
}

