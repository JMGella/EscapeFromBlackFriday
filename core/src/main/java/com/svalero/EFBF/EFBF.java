package com.svalero.EFBF;


import com.badlogic.gdx.Game;

import com.svalero.EFBF.manager.R;
import com.svalero.EFBF.screen.GameScreen;
import com.svalero.EFBF.screen.SplashScreen;


public class EFBF extends Game {



    @Override
    public void create() {
      setScreen(new SplashScreen(this));
    }

    @Override
    public void render() {
    super.render();
    }

    @Override
    public void dispose() {
       super.dispose();
    }
}
