package com.alejandro.ataleofheroes.actors;

import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import gameobjects.CharacterEscalated;

public class hitboxes {

    private Actor[]actores;
    private Rectangle[]rect;
    private Rectangle jugador;


    /*public void checkCollision(TiledMap map, CharacterEscalated personaje) {
        jugador=new Rectangle();
        jugador.set(personaje.getX(),personaje.getY(),personaje.getWidth(),personaje.getHeight());
        MapObjects mons = map.getLayers().get("Colisionables").getObjects();
        // MapObjects mons2 = map.getLayers().get("Entrada").getObjects();
        actores=new Actor[mons.getCount()];
        rect=new Rectangle[mons.getCount()];
        for (int i = 0;i < mons.getCount(); i++) {
            RectangleMapObject obj1 = (RectangleMapObject) mons.get(i);
            Rectangle rect1 = obj1.getRectangle();
            rect[i]=new Rectangle((rect1.x2),(rect1.y2),(rect1.width2),(rect1.height2));

            /*
            rect[i]=rect1;
            rect[i].set(rect1.x,rect1.y,rect1.width,rect1.height);


             /

            actores[i]=new Actor();
            actores[i].setBounds(rect1.x2,rect1.y2,rect1.width2,rect1.height*2);
            //actores[i].setColor(Color.BLUE);

        }
    }

    public Actor[] getActores() {
        return actores;
    }

    public Rectangle[] getRect() {

        return rect;
    }
}*/
}