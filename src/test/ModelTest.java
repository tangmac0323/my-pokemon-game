package test;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.Map;

import org.junit.Test;

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
		Mission newMission = new Mission(MissionType.FIFTYPOKEMON);
		model.setMission(newMission);
		assertTrue(model.getMission() == newMission);
		assertTrue(model.getDir() == model.getTrainer().getFaceDir());
		Point tempPoint = new Point();
		tempPoint.setLocation(65, 65);
		assertTrue(model.getLocation().equals(tempPoint));
	}
}
