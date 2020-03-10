package gameobjects;

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

/***
 * Yeah, I know, is 'Scalated' instead of 'Escalated'. The name was only a Joke. Anyways, this class
 * models the entire character, included its sprite, and contains the function that move it.
 * cRectangle -> The rectangle hitbox of the character
 * cSprite -> The character's sprite
 * camera -> The camera that is received from the map
 * tilesPosition -> A Vector3 parameter that allows the character to set position and size
 * cBatch -> The character's sprite batch
 * map -> The main map
 * pixelWidth -> The width of the character parsed in pixels
 * pixelHeight -> The height of the character parsed in pixels
 * texture -> The sprite texture
 * x -> the position in the X axis of the character
 * y -> the position in te Y axis of the character
 * widthpj -> The character's width
 * heightpj -> The caracter's height
 */
public class CharacterEscalated extends Actor {

    //private boolean isHB;
    private Rectangle cRectangle;

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


    /***
     * This function allows the program to recover the character's hitbox
     * @return -> The hitbox of the character
     */
    public Rectangle getHitbox(){
          return cSprite.getBoundingRectangle();
    }

    /***
     * This constructor models the object of the game's character
     * @param camera -> the map's camera
     * @param map -> the main map
     * @param posX -> the position in the X axis of the character
     * @param posY -> the position in the Y axis of the character
     * @param pjWidth -> the character's width
     * @param pjHeight -> the character's height
     */
    public CharacterEscalated(OrthographicCamera camera, TiledMap map, int posX, int posY, float pjWidth, float pjHeight) {

        //isHB = new Boolean(false);
        //gmHitboxes.checkCollision(map, this);
        //cRectangles = gmHitboxes.getRect();

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

    /***
     * This function allows the program to draw the character
     */
    public void draw() {
        // setCamera();

        cBatch.begin();
        cSprite.draw(cBatch);
        cBatch.end();
    }

    /***
     * This function allows the program to set the camera on the player
     */
    private void setCamera() {

        cSprite.setSize(((Gdx.graphics.getWidth()*cSprite.getTexture().getWidth())
                        /pixelWidth)*(1.9f/camera.zoom),
                ((Gdx.graphics.getHeight()*cSprite.getTexture().getHeight())
                        /pixelHeight)
                        *(1.9f/camera.zoom));
    }

    /***
     * This function allows the character to move on the map based on the character typed
     * @param direction -> The char that indicates the function where the character move
     */
    public void move(char direction) {

        switch (direction) {

            case 'u':

               if(tilesPosition.y < this.tileHeight - 1) {


                    cSprite.setPosition(cSprite.getX(), cSprite.getY() + 10);
                }

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

    /***
     * This function allows the program to recover the character's camera
     * @return
     */
    public OrthographicCamera getCamera() {
        return camera;
    }

    /***
     * This function allows the program to dispose the character
     */
    public void dispose() {
        cBatch.dispose();
    }
}
