package info.irina.mario.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import info.irina.mario.MarioBros;
import info.irina.mario.Sprites.Brick;
import info.irina.mario.Sprites.Power;

/**
 * Created by User on 30.08.2016.
 */
public class B2WorldCreator {
    public B2WorldCreator(World world,TiledMap map){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //create ground
        for (MapObject object: map.getLayers().get(2).getObjects().getByType((RectangleMapObject.class))){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM, (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / MarioBros.PPM, rect.getHeight() / 2 / MarioBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //create pipes
        for (MapObject object: map.getLayers().get(3).getObjects().getByType((RectangleMapObject.class))){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM, (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / MarioBros.PPM, rect.getHeight() / 2 / MarioBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //create power
        for (MapObject object: map.getLayers().get(5).getObjects().getByType((RectangleMapObject.class))){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

           new Power(world, map, rect);
        }

        //create blocks
        for (MapObject object: map.getLayers().get(6).getObjects().getByType((RectangleMapObject.class))){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Brick(world, map, rect);
        }
    }
}
