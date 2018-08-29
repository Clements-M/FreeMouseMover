package work.doestheinternetstill.mousemover.gui;

import work.doestheinternetstill.mousemover.JitterMovement;
import work.doestheinternetstill.mousemover.Movement;
import work.doestheinternetstill.mousemover.RandomMovement;

public class Options {
	public enum MovementType {
		Random(RandomMovement.generateRandomScreenMovement()), Jitter(new JitterMovement());

		private Movement movementTypeObject;

		private MovementType(Movement movementTypeObject) {
			this.movementTypeObject = movementTypeObject;
		}

		public Movement getM0vementTypeObject() {
			return movementTypeObject;
		}
	}

	MovementType movementType;

	public enum MovementSpeed {
		High(250), Medium(1000), Low(2500);

		private int speedValueInverse;

		private MovementSpeed(int speedValueInverse) {
			this.speedValueInverse = speedValueInverse;
		}

		public int getInverseSpeed() {
			return speedValueInverse;
		}
	}

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
