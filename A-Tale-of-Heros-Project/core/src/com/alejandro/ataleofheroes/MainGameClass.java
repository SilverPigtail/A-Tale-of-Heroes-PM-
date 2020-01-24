package com.alejandro.ataleofheroes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MainGameClass extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	private TiledMap map;
	private OrthogonalTiledMapRenderer oRenderer;

	public static final float unitScale = 1/16f;


	@Override
	public void create () {
		/*batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");*/

		float widthMap = Gdx.graphics.getWidth();
		float heightMap = Gdx.graphics.getHeight();

		map = new TmxMapLoader().load("maps/TownMap.tmx");

		oRenderer = new OrthogonalTiledMapRenderer(map, unitScale);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
