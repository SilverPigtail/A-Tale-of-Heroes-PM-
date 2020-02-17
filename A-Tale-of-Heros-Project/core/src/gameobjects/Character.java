package gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;

public class Character {

    private Sprite cSprite;
    private Vector3 tilesPosition;
    private OrthographicCamera cCamera;
    private int tMWidth;
    private int tMHeight;
    private int pMWidth;
    private int pMHeight;
    private SpriteBatch cBatch;
    private TiledMap map;

    private float hp;
    private float at;

    public Character(OrthographicCamera cCamera, TiledMap map , float hp, float at) {

        this.hp = hp;
        this.at = at;
        this.cSprite = new Sprite(new Texture("pjsprites/backCSprite.png"));

        cBatch = new SpriteBatch();
        this.cCamera = cCamera; // Cuidado con esto
        this.map = map;

        tMWidth = ((TiledMapTileLayer)map.getLayers().get(0)).getWidth();
        tMHeight = ((TiledMapTileLayer)map.getLayers().get(0)).getHeight();
        pMWidth = tMWidth * (int)map.getProperties().get("width");
        pMHeight = tMHeight * (int)map.getProperties().get("height");

        Vector3 pixelPositionV = cCamera.project(new Vector3((cCamera.position.x), cCamera.position.y, 0));

        cSprite.setPosition(pixelPositionV.x, pixelPositionV.y);


    }

    private void setCamera() {

        cSprite.setSize((Gdx.graphics.getWidth() * cSprite.getTexture().getWidth() / pMWidth) * (1 / cCamera.zoom),
                (Gdx.graphics.getHeight() * cSprite.getTexture().getHeight() / pMHeight)
                       *(1/ cCamera.zoom));


    }


    public void moveCharacter(char direction) {

        switch(direction){

            case 'u':

                if(tilesPosition.y < this.tMHeight - 1) {
                    tilesPosition.y++;
                }

                cCamera.position.y = tilesPosition.y;

                break;

            case 'd':

                if(tilesPosition.y > 0) {
                    tilesPosition.y--;
                }

                break;

            case 'r':

                if(tilesPosition.x < this.tMWidth - 1) {
                    tilesPosition.x++;
                }

                break;

            case 'l':

                if(tilesPosition.x > 0) {
                    tilesPosition.x--;
                }
                break;
        }

        cCamera.update();
    }

    public void draw() {
        setCamera();
        cBatch.begin();
        cSprite.draw(cBatch);
        cBatch.end();
    }

    public void dispose() {
        cBatch.dispose();
    }
}
