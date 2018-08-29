package work.doestheinternetstill.mousemover.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestOptions {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void test() {
		Options testOptions = new Options();
		testOptions.setMovementType(Options.MovementType.Random);
		assertEquals(Options.MovementType.Random, testOptions.getMovementType());
	}
}
