package test;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.Map;

import org.junit.Test;

import GameModel.Direction;
import GameModel.GameModel;
import Map.*;
import Mission.Mission;
import Mission.MissionType;
import Trainer.Trainer;

public class ModelTest {
	@Test
	public void GameModelTest() {
		GameModel model = new GameModel();
		Trainer newTrn = new Trainer("lulu");
		model.setTrainer(newTrn);
		assertTrue(model.getTrainer().getID().equals("lulu"));
		Map_BottomLeft newMap = new Map_BottomLeft();
		model.setCurMap(newMap);
		assertTrue(model.getCurMap() == newMap);
		model.chooseMap(0);
		model.chooseMap(1);
		model.chooseMap(2);
		model.chooseMap(3);
		model.chooseMap(0);
		Mission newMission = new Mission(MissionType.FIFTYPOKEMON);
		model.setMission(newMission);
		assertTrue(model.getMission() == newMission);
		assertTrue(model.getDir() == model.getTrainer().getFaceDir());
		Point tempPoint = new Point();
		tempPoint.setLocation(65, 65);
		assertTrue(model.getLocation().equals(tempPoint));
		assertTrue(model.getPrevLocation().equals(tempPoint));
		tempPoint.setLocation(0, 0);
		model.update();
		for (int i = 0; i < 2000; i ++){
			if (Math.random() < 0.25){
				model.moveTrainer(Direction.WEST);
			}
			else if (Math.random() > 0.75){
				model.moveTrainer(Direction.EAST);
			}
			else if (Math.random() > 0.5 && Math.random() < 0.75){
				model.moveTrainer(Direction.NORTH);
			}
			else{
				model.moveTrainer(Direction.SOUTH);
			}
		}
		assertTrue(!model.isLost());
		assertTrue(!model.isWin());
		assertTrue(!model.isOver());
		assertTrue(model.getStepCount() != 0);
		assertTrue(model.getCurMap().getBlock(0, 0).getGround() == GroundType.GRASSLAND);
		assertTrue(model.getCurMap().getBlock(0, 0).getPassType() == PassableType.AIR);
		assertFalse(model.getCurMap().getBlock(0, 0).isPassable());

		
	}
}
