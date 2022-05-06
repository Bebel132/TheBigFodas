package maquinasdesexo;
import robocode.*;
import java.awt.Color;
import robocode.util.Utils;

public class TheBigFoda extends TeamRobot {
	public void run() {
		setBodyColor(Color.pink);
	    setGunColor(Color.red);
	    setRadarColor(Color.black);
	    setScanColor(Color.red);
	    setBulletColor(Color.pink);
		while (true) {
			setMaxVelocity(8);
			turnRadarRightRadians(Double.POSITIVE_INFINITY);
			execute();
		}
	}
	
	public void mira(double Adv) {
	double A=getHeading()+Adv-getGunHeading();
	if (!(A > -180 && A <= 180)) {
		while (A <= -180) {
			A += 360;
		}
		while (A > 180) {
			A -= 360;
		}
	}
	turnGunRight(A);
}

	public void onScannedRobot(ScannedRobotEvent e) {
			if(isTeammate(e.getName())){
				return;
			} else {
				double radarTurn = getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians();
		    	setTurnRadarRightRadians(Utils.normalRelativeAngle(radarTurn));
				double distancia = e.getDistance();
				setTurnRight(e.getBearing()+e.getVelocity());
				mira(e.getBearing());
				if(getGunHeat() == 0 && distancia <= 200){
					setFire(5);
					setAhead(50);
					setBack(50);
				} else if(distancia > 200 && distancia < 300){
					setFire(4);
					setAhead(distancia);
				} else if(distancia > 300){
					setAhead(distancia);
				}
			}
	}
	
	public void onHitRobot(HitRobotEvent e) {
		if(isTeammate(e.getName())){
			return;
		} else {
			double radar = getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians();
		   	setTurnRadarRightRadians(Utils.normalRelativeAngle(radar));
			mira(e.getBearing());
		}
	}
	
	public void onHitWall(HitWallEvent e) {
		setBack(100);
		setTurnRight(90);
	}
}