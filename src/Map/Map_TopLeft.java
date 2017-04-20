package Map;

import java.awt.Point;

public class Map_TopLeft extends Map{
	

	private static final long serialVersionUID = -7762644513628487590L;
	
	private Point bottomPortal;
	private Point rightPortal;

	@Override
	public void mapGenerator() {
		// TODO Auto-generated method stub
		
	}
	
	
	public Point getBottomPortal(){
		return this.bottomPortal;
	}
	
	public Point getRightPortal(){
		return this.rightPortal;
	}

}
