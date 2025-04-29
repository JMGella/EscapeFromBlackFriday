package com.svalero.EFBF.manager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;
import com.svalero.EFBF.EFBF;
import com.svalero.EFBF.characters.Enemy;
import com.svalero.EFBF.characters.Player;
import com.svalero.EFBF.items.Item;
import com.svalero.EFBF.screen.GameScreen;
import com.svalero.EFBF.screen.PauseScreen;
import com.svalero.EFBF.util.Constants;

import static com.svalero.EFBF.util.Constants.TILE_WIDTH;


public class LogicManager {

    public int currentLevel;

    private EFBF game;

    public Player player;

    public Array<Enemy> enemies;
    public Array<Item> items;


    public LogicManager(EFBF game) {
        this.game = game;
        currentLevel = 1;
        createPlayer();
        load();

    }

    private void load(){
        enemies = new Array<>();
        items = new Array<>();
    }




    private void createPlayer() {
        player = new Player(R.getTexture("player_idle_right"));
    }

    private void managePlayerInput(float dt) {
        player.setVelocity(0,0);

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.moveRight();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.moveLeft();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.jump();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.isPaused = true;
            Screen currentScreen = game.getScreen();
            game.setScreen(new PauseScreen(game, currentScreen));
        }
    }

    private void enemiesMoves(float dt){
        for (Enemy enemy: enemies){
            if (player.getX() < enemy.getX()){
                enemy.setVelocity(enemy.speed,0);
            } else if (player.getX() > enemy.getX()){
                enemy.setVelocity(enemy.speed,0);
            }

        }
    }

    private void manageColitions(){
        for (Enemy enemy : enemies){
            if (enemy.getRectangle().overlaps(player.getRectangle())){
                if(ConfigurationManager.isSoundEnabled()){
                    R.getSound("hit").play();
                }
                player.getDamage();
                if (player.lives == 0){
                    if(ConfigurationManager.isSoundEnabled()){
                        R.getSound("game-over").play();
                    }
                    game.setScreen(new GameScreen(game, 1));
                }
                player.move(player.getX(), player.getY() + 10);
                enemy.setGhosted(true);
                enemy.setVelocity(0,-1000);

            }
        }
        for (Item item : items){
            if (item.getRectangle().overlaps(player.getRectangle())){
                if(item.name.equals("exit")){
                    if(player.score == 4){
                        if(ConfigurationManager.isSoundEnabled()){
                            R.getSound("exit").play();
                        }
                        game.setScreen(new GameScreen(game, currentLevel + 1));
                    } else{
                        player.move(player.getX() - 20, player.getY());
                    }
                } else {
                    player.takeItem(item.name);
                    items.removeValue(item, true);
                    if (ConfigurationManager.isSoundEnabled()) {
                        R.getSound("coin").play();
                    }
                }
            }
        }

    }




    public void update(float dt) {


        managePlayerInput(dt);
        manageColitions();
        player.update(dt);


    }
}


