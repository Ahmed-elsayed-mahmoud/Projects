// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.Saw.game;

import android.graphics.Paint;
import com.Saw.framework.Game;
import com.Saw.framework.Graphics;
import com.Saw.framework.Image;
import com.Saw.framework.Input;
import com.Saw.framework.Screen;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Referenced classes of package com.Saw.game:
//            Background, Robot, Heliboy, Assets, 
//            Animation, MainMenuScreen, SampleGame, Tile, 
//            Projectile

public class GameScreen extends Screen
{
    static final class GameState extends Enum
    {

        private static final GameState ENUM$VALUES[];
        public static final GameState GameOver;
        public static final GameState Paused;
        public static final GameState Ready;
        public static final GameState Running;

        public static GameState valueOf(String s)
        {
            return (GameState)Enum.valueOf(com/Saw/game/GameScreen$GameState, s);
        }

        public static GameState[] values()
        {
            GameState agamestate[] = ENUM$VALUES;
            int i = agamestate.length;
            GameState agamestate1[] = new GameState[i];
            System.arraycopy(agamestate, 0, agamestate1, 0, i);
            return agamestate1;
        }

        static 
        {
            Ready = new GameState("Ready", 0);
            Running = new GameState("Running", 1);
            Paused = new GameState("Paused", 2);
            GameOver = new GameState("GameOver", 3);
            ENUM$VALUES = (new GameState[] {
                Ready, Running, Paused, GameOver
            });
        }

        private GameState(String s, int i)
        {
            super(s, i);
        }
    }


    private static Background bg1;
    private static Background bg2;
    public static Heliboy hb;
    public static Heliboy hb2;
    private static Robot robot;
    private Animation anim;
    private Image character;
    private Image character2;
    private Image character3;
    private Image currentSprite;
    private Animation hanim;
    private Image heliboy;
    private Image heliboy2;
    private Image heliboy3;
    private Image heliboy4;
    private Image heliboy5;
    int livesLeft;
    Paint paint;
    Paint paint2;
    Paint paint3;
    GameState state;
    private ArrayList tilearray;

    public GameScreen(Game game)
    {
        super(game);
        state = GameState.Ready;
        tilearray = new ArrayList();
        livesLeft = 1;
        bg1 = new Background(0, 0);
        bg2 = new Background(2160, 0);
        robot = new Robot();
        hb = new Heliboy(340, 360);
        hb2 = new Heliboy(700, 360);
        character = Assets.character;
        character2 = Assets.character2;
        character3 = Assets.character3;
        heliboy = Assets.heliboy;
        heliboy2 = Assets.heliboy2;
        heliboy3 = Assets.heliboy3;
        heliboy4 = Assets.heliboy4;
        heliboy5 = Assets.heliboy5;
        anim = new Animation();
        anim.addFrame(character, 1250L);
        anim.addFrame(character2, 50L);
        anim.addFrame(character3, 50L);
        anim.addFrame(character2, 50L);
        hanim = new Animation();
        hanim.addFrame(heliboy, 100L);
        hanim.addFrame(heliboy2, 100L);
        hanim.addFrame(heliboy3, 100L);
        hanim.addFrame(heliboy4, 100L);
        hanim.addFrame(heliboy5, 100L);
        hanim.addFrame(heliboy4, 100L);
        hanim.addFrame(heliboy3, 100L);
        hanim.addFrame(heliboy2, 100L);
        currentSprite = anim.getImage();
        loadMap();
        paint = new Paint();
        paint.setTextSize(30F);
        paint.setTextAlign(android.graphics.Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(-1);
        paint2 = new Paint();
        paint2.setTextSize(100F);
        paint2.setTextAlign(android.graphics.Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(-1);
        paint3 = new Paint();
        paint3.setTextSize(40F);
        paint3.setTextAlign(android.graphics.Paint.Align.CENTER);
        paint3.setAntiAlias(true);
        paint3.setColor(0xffff0000);
    }

    private void drawGameOverUI()
    {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, 0xff000000);
        String s = (new Integer(robot.score)).toString();
        if (robot.score > 0 && bg2.getBgX() > 2000)
        {
            g.drawString("You Win !!!!", 400, 210, paint2);
            g.drawString("Your Score is :", 400, 290, paint3);
            g.drawString(s, 400, 370, paint2);
        } else
        if (robot.score > 0)
        {
            g.drawString("GAME OVER.", 400, 210, paint2);
            g.drawString("Your Score is :", 400, 290, paint3);
            g.drawString(s, 400, 370, paint2);
        } else
        {
            g.drawString("GAME OVER.", 400, 210, paint2);
            g.drawString("Your Score is :", 400, 290, paint3);
            g.drawString("0", 400, 370, paint2);
        }
        g.drawString("Tap to return.", 400, 410, paint);
    }

    private void drawPausedUI()
    {
        Graphics g = game.getGraphics();
        g.drawARGB(155, 0, 0, 0);
        g.drawString("Resume", 400, 165, paint2);
        g.drawString("Menu", 400, 360, paint2);
    }

    private void drawReadyUI()
    {
        Graphics g = game.getGraphics();
        g.drawARGB(155, 0, 0, 0);
        g.drawString("Tap to Start.", 400, 240, paint);
    }

    private void drawRunningUI()
    {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.button, 0, 285, 0, 0, 65, 65);
        g.drawImage(Assets.button, 0, 350, 0, 65, 65, 65);
        g.drawImage(Assets.button, 0, 415, 0, 130, 65, 65);
        g.drawImage(Assets.button, 0, 0, 0, 195, 35, 35);
    }

    public static Background getBg1()
    {
        return bg1;
    }

    public static Background getBg2()
    {
        return bg2;
    }

    public static Heliboy getHeliboy()
    {
        return hb;
    }

    public static Heliboy getHeliboy2()
    {
        return hb2;
    }

    public static Robot getRobot()
    {
        return robot;
    }

    private void goToMenu()
    {
        game.setScreen(new MainMenuScreen(game));
    }

    private boolean inBounds(com.Saw.framework.Input.TouchEvent touchevent, int i, int j, int k, int l)
    {
        return touchevent.x > i && touchevent.x < (i + k) - 1 && touchevent.y > j && touchevent.y < (j + l) - 1;
    }

    private void loadMap()
    {
        ArrayList arraylist;
        Object obj;
        int i;
        arraylist = new ArrayList();
        i = 0;
        obj = new Scanner(SampleGame.map);
_L7:
        if (((Scanner) (obj)).hasNextLine()) goto _L2; else goto _L1
_L1:
        int j;
        arraylist.size();
        j = 0;
          goto _L3
_L2:
        if ((s = ((Scanner) (obj)).nextLine()) == null) goto _L1; else goto _L4
_L4:
        if (!s.startsWith("!"))
        {
            arraylist.add(s);
            i = Math.max(i, s.length());
        }
        continue; /* Loop/switch isn't completed */
_L3:
        String s;
        if (j >= 12)
        {
            return;
        }
        obj = (String)arraylist.get(j);
        int k = 0;
        do
        {
label0:
            {
                if (k < i)
                {
                    break label0;
                }
                j++;
            }
            if (true)
            {
                continue;
            }
            if (k < ((String) (obj)).length())
            {
                Tile tile = new Tile(k, j, Character.getNumericValue(((String) (obj)).charAt(k)));
                tilearray.add(tile);
            }
            k++;
        } while (true);
        if (true) goto _L3; else goto _L5
_L5:
        if (true) goto _L7; else goto _L6
_L6:
    }

    private void nullify()
    {
        paint = null;
        bg1 = null;
        bg2 = null;
        robot = null;
        hb = null;
        hb2 = null;
        currentSprite = null;
        character = null;
        character2 = null;
        character3 = null;
        heliboy = null;
        heliboy2 = null;
        heliboy3 = null;
        heliboy4 = null;
        heliboy5 = null;
        anim = null;
        hanim = null;
        System.gc();
    }

    private void paintTiles(Graphics g)
    {
        int i = 0;
        do
        {
            if (i >= tilearray.size())
            {
                return;
            }
            Tile tile = (Tile)tilearray.get(i);
            if (tile.type != 0)
            {
                g.drawImage(tile.getTileImage(), tile.getTileX(), tile.getTileY());
            }
            i++;
        } while (true);
    }

    private void updateGameOver(List list)
    {
        int j = list.size();
        int i = 0;
        do
        {
            if (i >= j)
            {
                return;
            }
            com.Saw.framework.Input.TouchEvent touchevent = (com.Saw.framework.Input.TouchEvent)list.get(i);
            if (touchevent.type == 0 && inBounds(touchevent, 0, 0, 800, 480))
            {
                nullify();
                game.setScreen(new MainMenuScreen(game));
                return;
            }
            i++;
        } while (true);
    }

    private void updatePaused(List list)
    {
        int j = list.size();
        int i = 0;
        do
        {
            if (i >= j)
            {
                return;
            }
            com.Saw.framework.Input.TouchEvent touchevent = (com.Saw.framework.Input.TouchEvent)list.get(i);
            if (touchevent.type == 1)
            {
                if (inBounds(touchevent, 0, 0, 800, 240) && !inBounds(touchevent, 0, 0, 35, 35))
                {
                    resume();
                }
                if (inBounds(touchevent, 0, 240, 800, 240))
                {
                    nullify();
                    goToMenu();
                }
            }
            i++;
        } while (true);
    }

    private void updateReady(List list)
    {
        if (list.size() > 0)
        {
            state = GameState.Running;
        }
    }

    private void updateRunning(List list, float f)
    {
        int i;
        int j;
        j = list.size();
        i = 0;
_L3:
        if (i < j) goto _L2; else goto _L1
_L1:
        if (livesLeft == 0)
        {
            state = GameState.GameOver;
        }
        robot.update();
        com.Saw.framework.Input.TouchEvent touchevent;
        if (robot.isJumped())
        {
            currentSprite = Assets.characterJump;
        } else
        if (!robot.isJumped() && !robot.isDucked())
        {
            currentSprite = anim.getImage();
        }
        list = robot.getProjectiles();
        i = 0;
_L4:
        if (i >= list.size())
        {
            if (hb.health == 0 && bg1.getBgX() > 500)
            {
                hb = new Heliboy(400, 360);
            }
            if (hb.health == 0 && bg1.getBgX() > 500)
            {
                hb2 = new Heliboy(700, 360);
            }
            updateTiles();
            hb.update();
            hb2.update();
            bg1.update();
            bg2.update();
            animate();
            if (robot.getCenterY() > 500)
            {
                state = GameState.GameOver;
            }
            return;
        }
        break MISSING_BLOCK_LABEL_499;
_L2:
        touchevent = (com.Saw.framework.Input.TouchEvent)list.get(i);
        if (touchevent.type == 0)
        {
            if (inBounds(touchevent, 0, 285, 65, 65))
            {
                robot.jump();
                currentSprite = anim.getImage();
                robot.setDucked(false);
            } else
            if (inBounds(touchevent, 0, 350, 65, 65))
            {
                if (!robot.isDucked() && !robot.isJumped() && robot.isReadyToFire())
                {
                    robot.shoot();
                }
            } else
            if (inBounds(touchevent, 0, 415, 65, 65) && !robot.isJumped())
            {
                currentSprite = Assets.characterDown;
                robot.setDucked(true);
                robot.setSpeedX(0);
            }
            if (touchevent.x > 400)
            {
                robot.moveRight();
                robot.setMovingRight(true);
            }
        }
        if (touchevent.type == 1)
        {
            if (inBounds(touchevent, 0, 415, 65, 65))
            {
                currentSprite = anim.getImage();
                robot.setDucked(false);
            }
            if (inBounds(touchevent, 0, 0, 35, 35))
            {
                pause();
            }
            if (touchevent.x > 400)
            {
                robot.stopRight();
            }
        }
        i++;
          goto _L3
        Projectile projectile = (Projectile)list.get(i);
        if (projectile.isVisible())
        {
            projectile.update();
        } else
        {
            list.remove(i);
        }
        i++;
          goto _L4
    }

    private void updateTiles()
    {
        int i = 0;
        do
        {
            if (i >= tilearray.size())
            {
                return;
            }
            ((Tile)tilearray.get(i)).update();
            i++;
        } while (true);
    }

    public void animate()
    {
        anim.update(10L);
        hanim.update(50L);
    }

    public void backButton()
    {
        pause();
    }

    public void dispose()
    {
    }

    public void paint(float f)
    {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
        g.drawImage(Assets.background, bg2.getBgX(), bg2.getBgY());
        paintTiles(g);
        ArrayList arraylist = robot.getProjectiles();
        int i = 0;
        do
        {
            if (i >= arraylist.size())
            {
                g.drawImage(currentSprite, robot.getCenterX() - 61, robot.getCenterY() - 63);
                g.drawImage(hanim.getImage(), hb.getCenterX() - 40, hb.getCenterY() - 40);
                g.drawImage(hanim.getImage(), hb2.getCenterX() - 40, hb2.getCenterY() - 40);
                if (state == GameState.Ready)
                {
                    drawReadyUI();
                }
                if (state == GameState.Running)
                {
                    drawRunningUI();
                }
                if (state == GameState.Paused)
                {
                    drawPausedUI();
                }
                if (state == GameState.GameOver)
                {
                    drawGameOverUI();
                }
                return;
            }
            Projectile projectile = (Projectile)arraylist.get(i);
            g.drawRect(projectile.getX(), projectile.getY(), 10, 5, -256);
            i++;
        } while (true);
    }

    public void pause()
    {
        if (state == GameState.Running)
        {
            state = GameState.Paused;
        }
    }

    public void resume()
    {
        if (state == GameState.Paused)
        {
            state = GameState.Running;
        }
    }

    public void update(float f)
    {
        List list = game.getInput().getTouchEvents();
        if (state == GameState.Ready)
        {
            updateReady(list);
        }
        if (state == GameState.Running)
        {
            updateRunning(list, f);
        }
        if (state == GameState.Paused)
        {
            updatePaused(list);
        }
        if (state == GameState.GameOver)
        {
            updateGameOver(list);
        }
    }
}
