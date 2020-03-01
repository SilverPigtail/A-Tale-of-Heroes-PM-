package runmainclass;

// import com.alejandro.ataleofheroes.inputs.AndroidInput;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
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
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import gameobjects.Character;
import gameobjects.CharacterEscalated;

public class MainGameClass extends Game {
	SpriteBatch batch;
	Texture img;

	private OrthogonalTiledMapRenderer mRenderer;
	private OrthographicCamera oCamera;

	private static int WIDTH;
	private static int HEIGHT;

	Character gameCharacter;
	CharacterEscalated escalatedGameCharacter;

	private ImageButton buttonRight;
	private ImageButton buttonLeft;
	private ImageButton buttonUp;
	private ImageButton buttonDown;

	private TextureAtlas buttonAtlas;

	private World world;
	private Stage stg;

	public static final float unitScale = 1/16f;




	@Override
	public void create () {

		stg = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stg);

	    float w = Gdx.graphics.getWidth();
	    float h = Gdx.graphics.getHeight();

	    TiledMap map = new TmxMapLoader().load("maps/TownMapDetailed.tmx");
	    //TiledMap map = new TmxMapLoader().load("maps/HouseMap.tmx");

		//MapObjects objetosMapa = map.getLayers().get("objetos").getObjects();

	    buttonAtlas = new TextureAtlas("buttons/buttons.pack");
		Skin buttonSkin = new Skin();
	    buttonSkin.addRegions(buttonAtlas);

	    mRenderer = new OrthogonalTiledMapRenderer(map, unitScale / 2);

	    oCamera = new OrthographicCamera();
	    //oCamera.zoom = 1f;
        WIDTH = ((TiledMapTileLayer)map.getLayers().get(0)).getWidth();
        HEIGHT = ((TiledMapTileLayer)map.getLayers().get(0)).getHeight();

        //oCamera.setToOrtho(false, WIDTH, HEIGHT);
        oCamera.setToOrtho(false, WIDTH, HEIGHT);

        oCamera.position.x = WIDTH/2;
        oCamera.position.y = HEIGHT/2;

        oCamera.zoom = 0.9f;
        //gameCharacter = new Character(oCamera, map, 10f, 10f);

		escalatedGameCharacter = new CharacterEscalated(oCamera, map);



		ImageButton.ImageButtonStyle rightStyle = new ImageButton.ImageButtonStyle();
		rightStyle.up = buttonSkin.getDrawable("rightRemastered");
		buttonRight = new ImageButton(rightStyle);
		buttonRight.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				Gdx.app.log("movimiento", "Botón clickado");



			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

				Gdx.app.log("movimiento", "Botón clickado");

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

				Gdx.app.log("movimiento", "Botón clickado");



			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

				Gdx.app.log("movimiento", "Botón clickado");

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

				Gdx.app.log("movimiento", "Botón clickado");



			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

				Gdx.app.log("movimiento", "Botón clickado");

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

				Gdx.app.log("movimiento", "Botón clickado");



			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

				Gdx.app.log("movimiento", "Botón clickado");

				escalatedGameCharacter.move('d');

				return true;
			}
		});




		Table tablePad = new Table();
		tablePad.bottom();
		tablePad.debug();
		tablePad.setFillParent(true);

		tablePad.add(buttonUp).height(Gdx.graphics.getHeight() / 6).width(Gdx.graphics.getWidth() / 7);
		tablePad.add(buttonDown).height(Gdx.graphics.getHeight() / 6).width(Gdx.graphics.getWidth() / 7);
		tablePad.add(buttonLeft).height(Gdx.graphics.getHeight() / 6).width(Gdx.graphics.getWidth() / 7);
		tablePad.add(buttonRight).height(Gdx.graphics.getHeight() / 6).width(Gdx.graphics.getWidth() / 7).padRight(Gdx.graphics.getWidth()/2.6f);

		stg.addActor(tablePad);



		/*for(MapObject objeto : objetosMapa) {
			if (objeto instanceof RectangleMapObject) {
				Rectangle rect = ((RectangleMapObject) objeto).getRectangle();
			} else if (objeto instanceof PolygonMapObject) {
				Polygon elementoEscenario = ((PolygonMapObject) objeto).getPolygon();
				Polygon hitboxPersonaje = new Polygon(new float[] { 0, 0, ash1.getHitbox().width, 0, ash1.getHitbox().width,
						ash1.getHitbox().height, 0, ash1.getHitbox().height });
				hitboxPersonaje.setPosition(ash1.getHitbox().x, ash1.getHitbox().y);
				if (Intersector.overlapConvexPolygons(hitboxPersonaje, elementoEscenario)){
					Gdx.app.log("Colision!!!",objeto.getName());
				}
			} else if (objeto instanceof PolylineMapObject) {
				Polyline chain = ((PolylineMapObject) objeto).getPolyline();
				// do something with chain...
			} else if (objeto instanceof CircleMapObject) {
				Circle circle = ((CircleMapObject) objeto).getCircle();
				// do something with circle...
			}
		}*/


		oCamera.update();



	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		mRenderer.setView(oCamera);
		mRenderer.render();
		escalatedGameCharacter.draw();
		stg.act();
		stg.draw();


	}
	
	@Override
	public void dispose () {

		escalatedGameCharacter.dispose();
		mRenderer.dispose();
	}

	/*private static PolygonShape getRectangle(RectangleMapObject rectangleObject) {
		Rectangle rectangle = rectangleObject.getRectangle();
		PolygonShape polygon = new PolygonShape();
		Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) /unitScale,
				(rectangle.y + rectangle.height * 0.5f ) / unitScale);
		polygon.setAsBox(rectangle.width * 0.5f /unitScale,
				rectangle.height * 0.5f / unitScale,
				size,
				0.0f);
		return polygon;
	}*/
}
