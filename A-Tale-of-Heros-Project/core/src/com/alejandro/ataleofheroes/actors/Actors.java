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
 * This class creates the Actor that casts the main Character hitbox.
 */
public class Actors extends Actor {

    protected Sprite sprite;
    private Rectangle dimensions;
    private Batch batch;
    protected boolean collYN; //this will tell us if the hitbox is hitting something else.
    //protected String nombre;
    private ShapeRenderer shapeRenderer;

   /* public Actors(String rutaTextura) {
        //Cambio Posición del Sprite
        sprite=new Sprite(new Texture(rutaTextura));
        batch=new SpriteBatch();
        sprite.setBounds(30,23, Gdx.graphics.getWidth()* 10,Gdx.graphics.getHeight()* 10);
        this.setSize(sprite.getWidth(),sprite.getHeight());
        this.setPosition(sprite.getX(),sprite.getY()); //Cambio posición del actor
        this.setOrigin(this.sprite.getWidth()/2,this.sprite.getHeight()/2);
        sprite.setOrigin(this.getOriginX(),this.getOriginY());
    }


    public Actors(String rutaTextura, float x, float y, float posAlt, float posAnc) {
        //shapeRenderer=new ShapeRenderer();
        //Cambio Posición del Sprite
        sprite=new Sprite(new Texture(rutaTextura));
        dimensions=new Rectangle((int)x,(int)y,(int)posAlt,(int)posAnc);
        batch=new SpriteBatch();
        sprite.setBounds(x,y, x,y);
        //this.setSize(Gdx.graphics.getWidth()100,Gdx.graphics.getHeight()100);
        sprite.setPosition(posAnc,posAlt);
        //Cambio posición del actor
        //this.setOrigin(this.sprite.getWidth()/2,this.sprite.getHeight()/2);
        sprite.setOrigin(this.getOriginX(),this.getOriginY());



    }
    public void dibujar(){
        batch.begin();
        sprite.draw(batch);
        batch.end();

    }
    public Rectangle getHitBox(){
        return sprite.getBoundingRectangle();
    }

    public boolean checkCollision(Actors c){
        boolean overlaps=getHitBox().overlaps(c.getHitBox());
        if(overlaps&&collYN==false){
            collYN=true;
            Gdx.app.log("Colisionando","con "+c.getClass().getName());
        }else if(!overlaps){
            collYN=false;
        }
        return collYN;
    }*/

}
