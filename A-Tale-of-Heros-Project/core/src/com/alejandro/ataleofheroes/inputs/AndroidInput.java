package com.alejandro.ataleofheroes.inputs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import gameobjects.Character;

public class AndroidInput implements GestureDetector.GestureListener {

    Character gameCharacter;

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {

        if(x < Gdx.graphics.getWidth() / 3) {
            gameCharacter.moveCharacter('l');
        }

        if(x > Gdx.graphics.getWidth() / 3*2) {
            gameCharacter.moveCharacter('r');

        }

        if(y < Gdx.graphics.getHeight() / 3*2) {
            gameCharacter.moveCharacter('d');
        }

        if(y > Gdx.graphics.getHeight() / 3) {
            gameCharacter.moveCharacter('u');
        }

        return false;

    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
