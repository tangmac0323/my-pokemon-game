package Map;

import java.awt.Point;

public class Map_BottomRight extends Map{

	private static final long serialVersionUID = -2250637403160453799L;
	
	private Point topPortal;
	private Point leftPortal;

	@Override
	public void mapGenerator() {
		// TODO Auto-generated method stub
		
	}
	
	
	public Point getTopPortal(){
		return this.topPortal;
	}
	
	public Point getLeftPortal(){
		return this.leftPortal;
	}

}
