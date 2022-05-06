package maquinasdesexo;
import robocode.*;
import java.awt.Color;
import robocode.util.Utils;

public class TheBigFodao extends TeamRobot {
	public void run() {
		setBodyColor(Color.pink);
	    setGunColor(Color.red);
	    setRadarColor(Color.black);
	    setScanColor(Color.red);
	    setBulletColor(Color.pink);
		setAdjustRadarForGunTurn(true);
		while (true) {
			setMaxVelocity(3);
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
			setAhead(distancia);
			if(getGunHeat() == 0 && distancia < 100){
				setFire(20);
			} else {
				setFire(5);
			}		
		}
		
	}
	
	public void onHitRobot(HitRobotEvent e){
		if(isTeammate(e.getName())){
			setBack(100);
		} else {
			double radar = getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians();
		   	setTurnRadarRightRadians(Utils.normalRelativeAngle(radar));
			mira(e.getBearing());
		}
	}
}