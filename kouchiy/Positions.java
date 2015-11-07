import java.util.HashMap;


public class Positions extends HashMap<Byte, Position> {

//	public final List<Position> positions;

	public Positions(byte keta) {
		for (Position.Pos pos : Position.Pos.values()) {
			try {
				this.put(pos.positionNo, new Position(pos.positionNo));
			} catch (Exception e) {
				System.out.println("public Positions(byte keta)" +e);;
			}
		}
	}



//	public Position p1 = Position.P1;
//	public Position p2 = Position.P2;
//	public Position p3 = Position.P3;
//	public Position p4 = Position.P4;
//	public Position p5 = Position.P5;
//	public Position p6 = Position.P6;
//	public Position p7 = Position.P7;
//	public Position p8 = Position.P8;
//	public Position p9 = Position.P9;
//	public Position p0 = Position.P0;

}

