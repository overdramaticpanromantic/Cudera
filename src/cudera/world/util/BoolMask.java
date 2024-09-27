package cudera.world.util;

import cudera.world.blocks.HeatedBlock;
import mindustry.Vars;
import mindustry.gen.Building;


//wrapper class by Photon_Gravity, because when java gives you problems you throw objects at it until it gives up
public class BoolMask {
	protected boolean[][] values;

	protected int width, height;

	public BoolMask(int width, int height){
		this.width = width;
		this.height = height;
		this.values = new boolean[width][height];
	}

	public void addHeatOfBlock(Building build){
		if(build instanceof HeatedBlock hbuild){
			//establish circle bounds
			int minX = (int)Math.max(0, Math.floor(build.x/8- hbuild.getHeatRadius()));
			int minY = (int)Math.max(0, Math.floor(build.y/8- hbuild.getHeatRadius()));
			int maxX = (int)Math.min(Vars.world.width(), Math.ceil(build.x/8 + hbuild.getHeatRadius()));
			int maxY = (int)Math.min(Vars.world.height(), Math.ceil(build.y/8 + hbuild.getHeatRadius()));

			//set one to every tile that has a lower distance to hbuild than the heatradius
			for(int i=minX;i<=maxX;i++){
				for(int j=minY;j<=maxY;j++){
					if(hbuild.isHeating(i, j)){
						values[i][j]=true;
					}
				}
			}
		}
	}

	public boolean get(int x, int y){
		return values[x][y];
	}
}
