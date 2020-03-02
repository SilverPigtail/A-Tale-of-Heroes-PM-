package gameobjects;

import com.alejandro.ataleofheroes.actors.GameHitboxes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CharacterEscalated extends Actor {

    private boolean isHB;
    private Rectangle cRectangle;
    private Rectangle[] cRectangles;
    private GameHitboxes gmHitboxes;

    private Sprite cSprite;
    private OrthographicCamera camera;
    private Vector3 tilesPosition;
    private Batch cBatch;

    private TiledMap map;
    private int pixelWidth;
    private int pixelHeight;
    private int tileWidth;
    private int tileHeight;

    private Texture texture;

    private int x, y;
    float widthpj, heightpj;



    public CharacterEscalated(OrthographicCamera camera, TiledMap map, int posX, int posY, float pjWidth, float pjHeight) {

        isHB = new Boolean(false);
        this.gmHitboxes = new GameHitboxes();
        gmHitboxes.checkCollision(map, this);
        cRectangles = gmHitboxes.getRect();

        this.x = posX;
        this.y = posY;
        this.widthpj = pjWidth;
        this.heightpj = pjHeight;

        texture = new Texture(Gdx.files.internal("pjsprites/upS.png"));
        this.cSprite = new Sprite(texture);

        cRectangle = new Rectangle(posX, posY, texture.getWidth(), texture.getHeight());

        this.camera = camera;
        tilesPosition = this.camera.position;
        cBatch = new SpriteBatch();
        this.map = map;

        tileWidth = ((TiledMapTileLayer)map.getLayers().get(0)).getWidth();
        tileHeight = ((TiledMapTileLayer)map.getLayers().get(0)).getHeight();
        pixelWidth = tileWidth * (int)map.getProperties().get("width");
        pixelHeight = tileHeight * (int)map.getProperties().get("height");


        //Uso un setSize porque funciona de momento. En teoría, no debería afectar a la hitbox.
        
        cSprite.setSize(65, 65);

        Vector3 pixelPos = camera.project(new Vector3(camera.position.x, camera.position.y, 0));

        cSprite.setPosition(pixelPos.x, pixelPos.y);


    }

    public void draw() {
        // setCamera();

        cBatch.begin();
        cSprite.draw(cBatch);
        cBatch.end();
    }

    private void setCamera() {

        cSprite.setSize(((Gdx.graphics.getWidth()*cSprite.getTexture().getWidth())
                        /pixelWidth)*(1.9f/camera.zoom),
                ((Gdx.graphics.getHeight()*cSprite.getTexture().getHeight())
                        /pixelHeight)
                        *(1.9f/camera.zoom));
    }

    public void move(char direction) {

        switch (direction) {

            case 'u':

               if(tilesPosition.y < this.tileHeight - 1) {


                    cSprite.setPosition(cSprite.getX(), cSprite.getY() + 10);
                }

                /*for(int b=0;b<cRectangles.length;b++){
                    if(cRectangles[b].overlaps(cRectangle.set(x,y+7,cSprite.getWidth(),cSprite.getHeight()))){
                        isHB=true;

                        System.out.println(isHB);

                        break;
                    }else{
                        isHB=false;
                        System.out.println(isHB);
                    }
                }
                if(isHB==false){

                    cSprite.setPosition(cSprite.getX(), cSprite.getY() + 10);

                }*/



                break;

            case 'd':

                if(tilesPosition.y > 0) {
                    cSprite.setPosition(cSprite.getX(), cSprite.getY() - 10);
                }



                break;

            case 'l':

                if(tilesPosition.x > 0) {


                    cSprite.setPosition(cSprite.getX() - 10, cSprite.getY());
                }




                break;

            case 'r':

                if(tilesPosition.x < this.tileWidth - 1) {
                    cSprite.setPosition(cSprite.getX() + 10, cSprite.getY());
                }


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
