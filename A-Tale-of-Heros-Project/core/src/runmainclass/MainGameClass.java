package runmainclass;

// import com.alejandro.ataleofheroes.inputs.AndroidInput;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import databases.DatabaseGame;
import gameobjects.CharacterEscalated;

/***
 * This class creates the map, implements the mechanics and set the sprite and buttons in the map.
 * batch -> The sprite batch
 * img -> the image of the resources
 * mRenderer -> The map renderer
 * oCamera -> The camera that is settled down on the center of the map
 * rectPriv -> The rectangle's hitbox parameter
 * rectPriv2 -> The second rectangle's hitbox parameter
 * WIDTH -> The width of the map
 * HEIGHT -> The height of the map
 * escalatedGameCharacter -> The character parameter
 * buttonRight -> The right button of the table
 * buttonLeft -> The left button of the table
 * buttonUp -> The up button of the table
 * buttonDown -> The down button of the table
 * buttonAtlas -> The parameter that allows us to get the button from the .pack file
 * stg -> The stage parameter that allows us to create the table and add the viewports
 * w -> Provisional width
 * h -> Provisional height
 * mapObjects -> Parameter that gets the collisions of the map
 * properties -> Parameter that gets the properties of the map objects
 * theme -> the music track that cast in loop in the game
 * stepAmmount -> The ammount of steps that will be added to the database
 * mDatabase -> The object of the database that will initialize it
 * actor -> An array of Actors that will save the ammount of actors (this will be for the first rectangle hitbox)
 * actor2 -> A second array of Actors that will save the second ammount of actors (this will be for the second rectangle hitbox)
 * mapCollision -> the first collision of one rectangle in the map
 * mapCollision2 -> the second collision of one rectangle in the map
 * characterHitbox -> The hitbox of the character
 * map -> The main map that the game loads
 * unitScale -> the scale of the map, its settle in 1/16
 * tileWidth -> the total width in tiles
 * tileHeight -> the total height in tiles
 * mapWidth -> the width of the map in pixels
 * pixelHeight -> the height of the map in pixels
 * mapWidthInTiles -> the width of the map in tiles
 * mapHeightInTiles -> the height of the map in tiles
 * mapWidthInPixels -> the width of the map in pixels
 * mapHeightInPixels -> the height of the map in pixels
 */

public class MainGameClass extends Game {
	SpriteBatch batch;
	Texture img;

	private OrthogonalTiledMapRenderer mRenderer;
	private OrthographicCamera oCamera;
	private Rectangle rectPriv;
	private Rectangle rectPriv2;

	private static int WIDTH;
	private static int HEIGHT;

	CharacterEscalated escalatedGameCharacter;

	private ImageButton buttonRight;
	private ImageButton buttonLeft;
	private ImageButton buttonUp;
	private ImageButton buttonDown;

	private TextureAtlas buttonAtlas;

	private Stage stg;
	float w;
	float h;

	private MapObjects mapObjects;

	private MapProperties properties;


	private Music theme;

	private int stepAmmount;
	private DatabaseGame mDatabase;


	//
	private Actor[]actor;
	private Actor[]actor2;
	private Rectangle[] mapCollision;
	private Rectangle[] mapCollision2;
	private Rectangle characterHitbox;
	private Map map;
	//


	public static final float unitScale = 1/16f;


	protected int tileWidth, tileHeight,
			mapWidthInTiles, mapHeightInTiles,
			mapWidthInPixels, mapHeightInPixels;


	/***
	 * This function initializes the database
	 * @param db -> the game's database from the interface
	 */
	public MainGameClass(DatabaseGame db) {
		mDatabase = db;
	}

	/***
	 * This constructor allows the program to execute in desktop configuration, uncomment it if is necesary to try the
	 * desktop configuration
	 */
	/*public MainGameClass() {

	}*/

	/***
	 * this function creates everything related of the execution of the game, map, character and database saves.
	 */
	@Override
	public void create () {

		stepAmmount = 0;



		//Here we initialize the stage, set the inputProcessor and create the character and the map
		stg = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stg);



		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();


		TiledMap map = new TmxMapLoader().load("maps/TownMapDetailed.tmx");

		//Here we settle down the map properties
		properties = map.getProperties();
		tileWidth = properties.get("tilewidth", Integer.class);
		tileHeight = properties.get("tileheight", Integer.class);
		mapWidthInTiles = properties.get("width", Integer.class);
		mapHeightInTiles = properties.get("height", Integer.class);
		mapWidthInPixels = mapWidthInTiles * tileWidth;
		mapHeightInPixels = mapHeightInTiles * tileHeight;

		tileWidth = properties.get("tilewidth", Integer.class);
		tileHeight = properties.get("tileheight", Integer.class);
		mapWidthInTiles = properties.get("width", Integer.class);
		mapHeightInTiles = properties.get("height", Integer.class);
		mapWidthInPixels = mapWidthInTiles * tileWidth;
		mapHeightInPixels = mapHeightInTiles * tileHeight;

		w = w/ mapWidthInPixels;
		h = h/ mapHeightInPixels;





		//Here we create initialize the skin of the button atlas
	    buttonAtlas = new TextureAtlas("buttons/buttons.pack");
		Skin buttonSkin = new Skin();
	    buttonSkin.addRegions(buttonAtlas);

	    //Here we settle down the camera
	    mRenderer = new OrthogonalTiledMapRenderer(map, unitScale / 2);



	    oCamera = new OrthographicCamera();
        WIDTH = ((TiledMapTileLayer)map.getLayers().get(0)).getWidth();
        HEIGHT = ((TiledMapTileLayer)map.getLayers().get(0)).getHeight();

        oCamera.setToOrtho(false, WIDTH, HEIGHT);

        oCamera.position.x = WIDTH/2;
        oCamera.position.y = HEIGHT/2;

        oCamera.zoom = 0.9f;

        //Here we initialize and create the characer
		escalatedGameCharacter = new CharacterEscalated(oCamera, map, 70, 70, mapWidthInPixels /10, mapHeightInPixels /10);





		//Here we create the right style of the button and we add it to the table that it's above of the screen.

		ImageButton.ImageButtonStyle rightStyle = new ImageButton.ImageButtonStyle();
		rightStyle.up = buttonSkin.getDrawable("rightRemastered");
		buttonRight = new ImageButton(rightStyle);
		buttonRight.addListener(new InputListener() {
			/***
			 * Listener of the right button that cast the character's move function. Also, it add
			 * one step to the ammount of steps, save it on the database and load it to verify if the
			 * ammount of steps is 50, if that is the case, the game will close.
			 * @param event -> event of the button
			 * @param x -> X axis of the button
			 * @param y -> Y axis of the button
			 * @param pointer -> Pointer of the button
			 * @param button -> The button itself
			 */
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				escalatedGameCharacter.move('r');
				stepAmmount += 1;
				mDatabase.save(stepAmmount);

				if(mDatabase.load() == 50) {
					System.exit(0);
				}

				Gdx.app.log("pasos", "Pasos: " + mDatabase.load());




			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {



				escalatedGameCharacter.move('r');


				return true;
			}
		});

		//Here we create and add the style of the left button and its listener
		ImageButton.ImageButtonStyle leftStyle = new ImageButton.ImageButtonStyle();
		leftStyle.up = buttonSkin.getDrawable("leftRemastered");
		buttonLeft = new ImageButton(leftStyle);
		buttonLeft.addListener(new InputListener() {
			/***
			 * Listener of the left button that cast the character's move function. Also, it add
			 * one step to the ammount of steps, save it on the database and load it to verify if the
			 * ammount of steps is 50, if that is the case, the game will close.
			 * @param event -> event of the button
			 * @param x -> X axis of the button
			 * @param y -> Y axis of the button
			 * @param pointer -> Pointer of the button
			 * @param button -> The button itself
			 */
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				escalatedGameCharacter.move('l');
				stepAmmount += 1;
				mDatabase.save(stepAmmount);

				if(mDatabase.load() == 50) {
					System.exit(0);
				}




			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {




					escalatedGameCharacter.move('l');



				return true;
			}
		});


		//Here we create the style of the up button and its listener
		ImageButton.ImageButtonStyle upStyle = new ImageButton.ImageButtonStyle();
		upStyle.up = buttonSkin.getDrawable("upRemastered");
		buttonUp= new ImageButton(upStyle);
		buttonUp.addListener(new InputListener() {
			/***
			 * Listener of the up button that cast the character's move function. Also, it add
			 * one step to the ammount of steps, save it on the database and load it to verify if the
			 * ammount of steps is 50, if that is the case, the game will close.
			 * @param event -> event of the button
			 * @param x -> X axis of the button
			 * @param y -> Y axis of the button
			 * @param pointer -> Pointer of the button
			 * @param button -> The button itself
			 */
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				escalatedGameCharacter.move('u');
				stepAmmount += 1;
				mDatabase.save(stepAmmount);

				if(mDatabase.load() == 50) {
					System.exit(0);
				}






			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {



				escalatedGameCharacter.move('u');

				return true;
			}
		});


		//Here we create the syle of the down button and its listener
		ImageButton.ImageButtonStyle downStyle = new ImageButton.ImageButtonStyle();
		downStyle.up = buttonSkin.getDrawable("downRemastered");
		buttonDown= new ImageButton(downStyle);
		buttonDown.addListener(new InputListener() {
			/***
			 * Listener of the down button that cast the character's move function. Also, it add
			 * one step to the ammount of steps, save it on the database and load it to verify if the
			 * ammount of steps is 50, if that is the case, the game will close.
			 * @param event -> event of the button
			 * @param x -> X axis of the button
			 * @param y -> Y axis of the button
			 * @param pointer -> Pointer of the button
			 * @param button -> The button itself
			 */
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				escalatedGameCharacter.move('d');
				stepAmmount += 1;
				mDatabase.save(stepAmmount);

				if(mDatabase.load() == 50) {
					System.exit(0);
				}



			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {



				escalatedGameCharacter.move('d');

				return true;
			}
		});




		//Here we create the tablepad that will contains the buttons
		Table tablePad = new Table();
		tablePad.bottom();
		tablePad.setFillParent(true);

		//Here we add the buttons to the tablepad
		tablePad.add(buttonUp).height(Gdx.graphics.getHeight() / 6).width(Gdx.graphics.getWidth() / 7);
		tablePad.add(buttonDown).height(Gdx.graphics.getHeight() / 6).width(Gdx.graphics.getWidth() / 7);
		tablePad.add(buttonLeft).height(Gdx.graphics.getHeight() / 6).width(Gdx.graphics.getWidth() / 7);
		tablePad.add(buttonRight).height(Gdx.graphics.getHeight() / 6).width(Gdx.graphics.getWidth() / 7).padRight(Gdx.graphics.getWidth()/2.6f);

		//Here we add the tablepad as an actor
		stg.addActor(tablePad);






		//Here we initialize the character's hitbox
		characterHitbox = new Rectangle();
		characterHitbox.set(escalatedGameCharacter.getX(), escalatedGameCharacter.getY(), escalatedGameCharacter.getWidth(), escalatedGameCharacter.getHeight());
		MapObjects mons= map.getLayers().get("colisiones").getObjects();
		actor = new Actor[mons.getCount()];

		//Here we get the first collision of the map (IMPORTANT: THIS COLLISION IS LOCATED ON THE HOUSE AT THE RIGHT AND BOTTON SIDE)
		mapCollision = new Rectangle[mons.getCount()];
		for(int i = 0; i < mons.getCount(); i++) {
			RectangleMapObject obj1 = (RectangleMapObject) mons.get(i);
			Rectangle rect1 = obj1.getRectangle();
			rectPriv=rect1;
			mapCollision[i] = rect1;
			mapCollision[i].set((Gdx.graphics.getWidth() * rectPriv.x)/1120,  (Gdx.graphics.getWidth() * 119)/1120, rect1.width , rect1.height );
			actor[i] = new Actor();
			actor[i].setBounds(rect1.x, rect1.y, rect1.width, rect1.height);

		}


		//Here we get the second collision of the map (IMPORTANT: THIS COLLISION IS LOCATED ON THE LAKE AT THE LEFT AND BOTTON SIDE)
		MapObjects mons2= map.getLayers().get("colisiones2").getObjects();
		actor2 = new Actor[mons2.getCount()];

		mapCollision2 = new Rectangle[mons2.getCount()];
		for(int i = 0; i < mons2.getCount(); i++) {
			RectangleMapObject obj2 = (RectangleMapObject) mons2.get(i);
			Rectangle rect2 = obj2.getRectangle();
			rectPriv2=rect2;
			mapCollision2[i] = rect2;
			mapCollision2[i].set((Gdx.graphics.getWidth() * rectPriv2.x)/1120,  (Gdx.graphics.getWidth() * 119)/1120, rect2.width , rect2.height );
			actor2[i] = new Actor();
			actor2[i].setBounds(rect2.x, rect2.y, rect2.width, rect2.height);

		}







		//Here we update the camera
		oCamera.update();



			//Here we cast the music and put in loop
			theme = Gdx.audio.newMusic(Gdx.files.internal("music/track.mp3"));
			theme.setLooping(true);
			theme.setVolume(56.5f);
			theme.play();
	}

	/***
	 * This function allows the program to render the map, camera, character, and check if the character is colliding with one of the two hitboxes.
	 * If the character is colliding, it will move to the left if is colliding with the left and botton collision, and to the right if is colliding
	 * with the lake collision.
	 */
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mRenderer.setView(oCamera);
		mRenderer.render();
		escalatedGameCharacter.draw();
        Gdx.app.log("Player Coord",escalatedGameCharacter.getHitbox().toString());
        Gdx.app.log("Rectangle Coord",rectPriv.toString());
		Gdx.app.log("Second rectangle coord",rectPriv2.toString());

		//HOUSE COLLISION
		if (escalatedGameCharacter.getHitbox().overlaps(rectPriv)) {
			Gdx.app.log("Colision","Is colliding with rectangle 1");
			escalatedGameCharacter.move('l');

		}

		//LAKE COLLISION
		if (escalatedGameCharacter.getHitbox().overlaps(rectPriv2)) {
			Gdx.app.log("OJO_2","JUGADOR COLISIONA CON RECTANGULO 2");
            escalatedGameCharacter.move('d');
		}
		stg.act();
		stg.draw();



	}

	/***
	 * This function allows the program to dispose the music, the character and the renderer.
	 */
	@Override
	public void dispose () {


		theme.dispose();
		escalatedGameCharacter.dispose();
		mRenderer.dispose();
	}




}

