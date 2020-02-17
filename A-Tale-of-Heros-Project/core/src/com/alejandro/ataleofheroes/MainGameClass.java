package com.alejandro.ataleofheroes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import gameobjects.Character;

public class MainGameClass extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	private OrthogonalTiledMapRenderer mRenderer;
	private OrthographicCamera oCamera;

	private static int WIDTH;
	private static int HEIGHT;

	Character gameCharacter;

	public static final float unitScale = 1/16f;




	@Override
	public void create () {

	    float w = Gdx.graphics.getWidth();
	    float h = Gdx.graphics.getHeight();

	    TiledMap map = new TmxMapLoader().load("maps/TownMapDetailed.tmx");

	    mRenderer = new OrthogonalTiledMapRenderer(map, unitScale);

	    oCamera = new OrthographicCamera();
	    oCamera.zoom = 1f;
        WIDTH = ((TiledMapTileLayer)map.getLayers().get(0)).getWidth();
        HEIGHT = ((TiledMapTileLayer)map.getLayers().get(0)).getHeight();

        oCamera.setToOrtho(false, WIDTH, HEIGHT);

        oCamera.position.x = WIDTH/2;
        oCamera.position.y = HEIGHT/2;

        gameCharacter = new Character(oCamera, map, 10f, 10f);

        oCamera.update();


	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mRenderer.setView(oCamera);
		//batch.begin();
		//batch.draw(img, 0, 0);
		//batch.end();

        //oCamera.update();


        mRenderer.setView(oCamera);
		mRenderer.render();
		gameCharacter.draw();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();

		mRenderer.dispose();
	}
}
