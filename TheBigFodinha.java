package maquinasdesexo;
import robocode.*;
import java.awt.Color;
 public class TheBigFodinha extends TeamRobot {
 	public int direcao = 0;
    public void run() {
	    setBodyColor(Color.pink);
	    setGunColor(Color.red);
	    setRadarColor(Color.black);
	    setScanColor(Color.pink);
	    setBulletColor(Color.pink);
		while(true){
		setMaxVelocity(5);
		if (direcao == 0){
			setAhead(100);
		    setTurnLeft(90);
		    setAhead(200);
		    setTurnGunRight(90);
		}else if(direcao == 1){
   		    setBack(100);
		    setTurnRight(90);
		    setBack(200);
		    setTurnGunLeft(90);
			}
			System.out.println(direcao);
			execute();
	    }   
    } 

    public void onScannedRobot(ScannedRobotEvent e) {
		if(isTeammate(e.getName())){
			return;
		} else {
        	if(e.getDistance() < 100) {
		        setFire(10);
		    } else {
			    setFire(3);
			    scan();
			 
			    double angulo = e.getBearing();
			    double distancia = e.getDistance();
				
		    	if(distancia < 200) {
			        setTurnGunRight(angulo);
			        setFire(10);
			    }
		    }
		}
    }

    public void onHitByBullet(HitByBulletEvent e){
	    setBack(50);
    }

    public void onHitWall(HitWallEvent e){
	    setTurnRight(90);
	    setAhead(70);
    }

    public void onHitRobot(HitRobotEvent e){
		if (direcao == 0){
			setBack(100);
			setTurnRight(180);
			direcao = 1;
		} else if (direcao == 1){
			setBack(100);
			setTurnRight(180);
			direcao = 0;
		}
    }
    public void onWin(WinEvent e) {
	    setTurnRight(36000);
    }
}
