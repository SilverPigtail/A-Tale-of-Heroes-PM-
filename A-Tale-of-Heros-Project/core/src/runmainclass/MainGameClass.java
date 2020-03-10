package runmainclass;

// import com.alejandro.ataleofheroes.inputs.AndroidInput;
import com.alejandro.ataleofheroes.actors.GameHitboxes;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import databases.DatabaseGame;
import gameobjects.Character;
import gameobjects.CharacterEscalated;

/***
 * This class creates the map, implements the mechanics and set the sprite and buttons in the map.
 */
public class MainGameClass extends Game {
	SpriteBatch batch;
	Texture img;

	private OrthogonalTiledMapRenderer mRenderer;
	private OrthographicCamera oCamera;
	private Rectangle rectPriv;
	private Rectangle rectPriv2;
	private Rectangle rectPriv3;

	private static int WIDTH;
	private static int HEIGHT;

	//Character gameCharacter;
	CharacterEscalated escalatedGameCharacter;

	private ImageButton buttonRight;
	private ImageButton buttonLeft;
	private ImageButton buttonUp;
	private ImageButton buttonDown;

	private TextureAtlas buttonAtlas;
	private GameHitboxes gmHitboxes;

	//private World world;
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
	private Rectangle[] getMapCollision;
	private Rectangle characterHitbox;
	private Map map;
	//

	private Batch textBatch;

	public static final float unitScale = 1/16f;


	protected int tileWidth, tileHeight,
			mapWidthInTiles, mapHeightInTiles,
			mapWidthInPixels, mapHeightInPixels;


	public MainGameClass(DatabaseGame db) {
		mDatabase = db;
	}

	@Override
	public void create () {

		stepAmmount = 0;

		// Inicializamos el stage y le damos un inputProcessor:



		stg = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stg);

	   // float w = Gdx.graphics.getWidth();
	    // float h = Gdx.graphics.getHeight();

		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();


		TiledMap map = new TmxMapLoader().load("maps/TownMapDetailed.tmx");

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





	    buttonAtlas = new TextureAtlas("buttons/buttons.pack");
		Skin buttonSkin = new Skin();
	    buttonSkin.addRegions(buttonAtlas);

	    mRenderer = new OrthogonalTiledMapRenderer(map, unitScale / 2);

	    oCamera = new OrthographicCamera();
	    //oCamera.zoom = 1f;
        WIDTH = ((TiledMapTileLayer)map.getLayers().get(0)).getWidth();
        HEIGHT = ((TiledMapTileLayer)map.getLayers().get(0)).getHeight();

        oCamera.setToOrtho(false, WIDTH, HEIGHT);

        oCamera.position.x = WIDTH/2;
        oCamera.position.y = HEIGHT/2;

        oCamera.zoom = 0.9f;

		System.out.println(mapHeightInPixels);
		System.out.println(mapWidthInPixels);
		escalatedGameCharacter = new CharacterEscalated(oCamera, map, 70, 70, mapWidthInPixels /10, mapHeightInPixels /10);

		//mDatabase.load();





		ImageButton.ImageButtonStyle rightStyle = new ImageButton.ImageButtonStyle();
		rightStyle.up = buttonSkin.getDrawable("rightRemastered");
		buttonRight = new ImageButton(rightStyle);
		buttonRight.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				escalatedGameCharacter.move('r');
				stepAmmount += 1;
				mDatabase.save(stepAmmount);

				/*if(mDatabase.load() == 10) {
					System.exit(0);
				}*/

				Gdx.app.log("pasos", "Pasos: " + mDatabase.load());




			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {



				escalatedGameCharacter.move('r');
				escalatedGameCharacter.changeSprite('r');


				return true;
			}
		});

		ImageButton.ImageButtonStyle leftStyle = new ImageButton.ImageButtonStyle();
		leftStyle.up = buttonSkin.getDrawable("leftRemastered");
		buttonLeft = new ImageButton(leftStyle);
		buttonLeft.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				escalatedGameCharacter.move('l');
				stepAmmount += 1;
				mDatabase.save(stepAmmount);

				/*if(mDatabase.load() == 10) {
					System.exit(0);
				}*/




			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {




					escalatedGameCharacter.move('l');



				return true;
			}
		});


		ImageButton.ImageButtonStyle upStyle = new ImageButton.ImageButtonStyle();
		upStyle.up = buttonSkin.getDrawable("upRemastered");
		buttonUp= new ImageButton(upStyle);
		buttonUp.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				escalatedGameCharacter.move('u');
				stepAmmount += 1;
				mDatabase.save(stepAmmount);

				/*if(mDatabase.load() == 10) {
					System.exit(0);
				}*/






			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {



				escalatedGameCharacter.move('u');

				return true;
			}
		});


		ImageButton.ImageButtonStyle downStyle = new ImageButton.ImageButtonStyle();
		downStyle.up = buttonSkin.getDrawable("downRemastered");
		buttonDown= new ImageButton(downStyle);
		buttonDown.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				escalatedGameCharacter.move('d');
				stepAmmount += 1;
				mDatabase.save(stepAmmount);

				/*if(mDatabase.load() == 10) {
					System.exit(0);
				}*/



			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {



				escalatedGameCharacter.move('d');

				return true;
			}
		});




		Table tablePad = new Table();
		tablePad.bottom();
		// tablePad.debug();
		tablePad.setFillParent(true);

		tablePad.add(buttonUp).height(Gdx.graphics.getHeight() / 6).width(Gdx.graphics.getWidth() / 7);
		tablePad.add(buttonDown).height(Gdx.graphics.getHeight() / 6).width(Gdx.graphics.getWidth() / 7);
		tablePad.add(buttonLeft).height(Gdx.graphics.getHeight() / 6).width(Gdx.graphics.getWidth() / 7);
		tablePad.add(buttonRight).height(Gdx.graphics.getHeight() / 6).width(Gdx.graphics.getWidth() / 7).padRight(Gdx.graphics.getWidth()/2.6f);

		stg.addActor(tablePad);





		stg.setDebugAll(true);

		characterHitbox = new Rectangle();
		characterHitbox.set(escalatedGameCharacter.getX(), escalatedGameCharacter.getY(), escalatedGameCharacter.getWidth(), escalatedGameCharacter.getHeight());
		MapObjects mons= map.getLayers().get("colisiones").getObjects();
		actor = new Actor[mons.getCount()];

		mapCollision = new Rectangle[mons.getCount()];
		for(int i = 0; i < mons.getCount(); i++) {
			RectangleMapObject obj1 = (RectangleMapObject) mons.get(i);
			Rectangle rect1 = obj1.getRectangle();
			rectPriv=rect1;
            //REajustar segun reglas de 3
			mapCollision[i] = rect1;
			mapCollision[i].set((Gdx.graphics.getWidth() * rectPriv.x)/1120,  (Gdx.graphics.getWidth() * 119)/1120, rect1.width , rect1.height );
			actor[i] = new Actor();
			actor[i].setBounds(rect1.x, rect1.y, rect1.width, rect1.height);

		}


		MapObjects mons2= map.getLayers().get("colisiones2").getObjects();
		actor2 = new Actor[mons2.getCount()];

		mapCollision2 = new Rectangle[mons2.getCount()];
		for(int i = 0; i < mons2.getCount(); i++) {
			RectangleMapObject obj2 = (RectangleMapObject) mons2.get(i);
			Rectangle rect2 = obj2.getRectangle();
			rectPriv2=rect2;
			//REajustar segun reglas de 3
			mapCollision2[i] = rect2;
			mapCollision2[i].set((Gdx.graphics.getWidth() * rectPriv2.x)/1120,  (Gdx.graphics.getWidth() * 119)/1120, rect2.width , rect2.height );
			actor2[i] = new Actor();
			actor2[i].setBounds(rect2.x, rect2.y, rect2.width, rect2.height);

		}


		Gdx.app.log("etiquetaCaminar", "pasos: " + stepAmmount);





		stg.setDebugAll(true);
		oCamera.update();



			theme = Gdx.audio.newMusic(Gdx.files.internal("music/track.mp3"));
			theme.setLooping(true);
			theme.setVolume(56.5f);
			theme.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mRenderer.setView(oCamera);
		mRenderer.render();
		escalatedGameCharacter.draw();
        Gdx.app.log("Coordenada Jugador",escalatedGameCharacter.getHitbox().toString());
        Gdx.app.log("Coordenada Rectangulo",rectPriv.toString());
		Gdx.app.log("Coordenada Rectangulo 2",rectPriv2.toString());
		if (escalatedGameCharacter.getHitbox().overlaps(rectPriv)) {
			Gdx.app.log("Colision","Is colliding with rectangle 1");
			escalatedGameCharacter.move('l');

		}

		if (escalatedGameCharacter.getHitbox().overlaps(rectPriv2)) {
			Gdx.app.log("OJO_2","JUGADOR COLISIONA CON RECTANGULO 2");
            escalatedGameCharacter.move('l');
		}
		stg.act();
		stg.draw();



	}
	
	@Override
	public void dispose () {


		theme.dispose();
		escalatedGameCharacter.dispose();
		mRenderer.dispose();
	}




}

