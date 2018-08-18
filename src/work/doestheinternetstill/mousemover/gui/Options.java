package work.doestheinternetstill.mousemover.gui;

public class Options {
	public enum MovementType {
		RANDOM, JITTER, CIRCULAR, PATH
	}

	MovementType movementType;

	public Options() {
		;
	}

	public void setMovementType(MovementType movementType) {
		this.movementType = movementType;
	}

	public MovementType getMovementType() {
		return this.movementType;
	}
}
