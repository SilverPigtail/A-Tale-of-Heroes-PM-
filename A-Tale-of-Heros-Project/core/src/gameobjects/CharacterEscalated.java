package gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;

public class CharacterEscalated {

    private Sprite cSprite;
    private OrthographicCamera camera;
    private Vector3 tilesPosition;
    private Batch cBatch;

    private TiledMap map;
    private int pixelWidth;
    private int pixelHeight;
    private int tileWidth;
    private int tileHeight;


    public CharacterEscalated(OrthographicCamera camera, TiledMap map) {

        this.cSprite = new Sprite(new Texture("pjsprites/upS.png"));
        this.camera = camera;
        tilesPosition = this.camera.position;
        cBatch = new SpriteBatch();
        this.map = map;

        tileWidth = ((TiledMapTileLayer)map.getLayers().get(0)).getWidth();
        tileHeight = ((TiledMapTileLayer)map.getLayers().get(0)).getHeight();
        pixelWidth = tileWidth * (int)map.getProperties().get("width");
        pixelHeight = tileHeight * (int)map.getProperties().get("height");


        Vector3 pixelPos = camera.project(new Vector3(camera.position.x, camera.position.y, 0));

        cSprite.setPosition(pixelPos.x, pixelPos.y);

    }

    public void draw() {
        setCamera();

        cBatch.begin();
        cSprite.draw(cBatch);
        cBatch.end();
    }

    private void setCamera() {

        cSprite.setSize(((Gdx.graphics.getWidth()*cSprite.getTexture().getWidth())
                        /pixelWidth)*(4f/camera.zoom),
                ((Gdx.graphics.getHeight()*cSprite.getTexture().getHeight())
                        /pixelHeight)
                        *(4f/camera.zoom));
    }

    public void move(char direction) {

        switch (direction) {

            case 'u':

                if(tilesPosition.y < this.tileHeight - 1) {

                    tilesPosition.y++;
                    //cSprite.set(new Sprite(new Texture("pjsprites/upS.png")));

                }

                camera.position.y = tilesPosition.y;

                break;

            case 'd':

                if(tilesPosition.y > 0) {
                    tilesPosition.y--;
                }

                camera.position.y = tilesPosition.y;

                break;

            case 'l':

                if(tilesPosition.x > 0) {

                    tilesPosition.x--;
                }

                camera.position.x = tilesPosition.x;


                break;

            case 'r':

                if(tilesPosition.x < this.tileWidth - 1) {
                    tilesPosition.x++;
                }

                camera.position.x = tilesPosition.x;
                break;


        }

        camera.update();

    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void dispose() {
        cBatch.dispose();
    }

    public void changeSprite(char direction){

        /*switch (direction) {

            case 'u':
                cSprite = new Sprite(new Texture("pjsprites/upS.png"));

                break;

            case 'd':
               cSprite = new Sprite(new Texture("pjsprites/downS.png"));
                break;

            case 'l':
               cSprite = new Sprite(new Texture("pjsprites/leftS.png"));
                break;

            case 'r':
                cSprite = new Sprite(new Texture("pjsprites/rightS.png"));
                break;

        }*/

    }
}
