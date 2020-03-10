package com.alejandro.ataleofheroes.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/***
 * This class creates the Actor that casts the main Character hitbox. But, mostly, this class allows
 * the character to move generating the sprite actor.
 * sprite -> The sprite of the character
 * dimensions -> The dimensions of the character
 * batch -> The batch of the sprite
 */
public class Actors extends Actor {

    protected Sprite sprite;
    private Rectangle dimensions;
    private Batch batch;
    protected boolean collYN; //this will tell us if the hitbox is hitting something else.
    //protected String nombre;
    private ShapeRenderer shapeRenderer;

    /***
     * This function allows the character to move
     * @param texturePath -> The texture of the sprite with his size
     */
   public Actors(String texturePath) {
        sprite = new Sprite(new Texture(texturePath));
        batch = new SpriteBatch();
        sprite.setBounds(30,23, Gdx.graphics.getWidth()* 10,Gdx.graphics.getHeight()* 10);
        this.setSize(sprite.getWidth(),sprite.getHeight());
        this.setPosition(sprite.getX(),sprite.getY());
        this.setOrigin(this.sprite.getWidth()/2,this.sprite.getHeight()/2);
        sprite.setOrigin(this.getOriginX(),this.getOriginY());
    }


    /***
     * This function allows the program to create actors.
     * @param texturePath -> the texture of the actor
     * @param x -> the 'x' position of the actor
     * @param y -> the 'y' position of the actor
     * @param posAlt -> the position in tiles in the X axis of the actor
     * @param posAnc -> the position in tiles in the Y axis of the actor
     */
    public Actors(String texturePath, float x, float y, float posAlt, float posAnc) {

        sprite=new Sprite(new Texture(texturePath));
        dimensions=new Rectangle((int)x,(int)y,(int)posAlt,(int)posAnc);
        batch=new SpriteBatch();
        sprite.setBounds(x,y, x,y);
        sprite.setPosition(posAnc,posAlt);
        sprite.setOrigin(this.getOriginX(),this.getOriginY());
    }



}
