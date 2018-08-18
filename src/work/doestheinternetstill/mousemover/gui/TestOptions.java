package work.doestheinternetstill.mousemover.gui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class TestOptions {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void test() {
		Options testOptions = new Options();
		testOptions.setMovementType(Options.MovementType.RANDOM);
		assertEquals(Options.MovementType.RANDOM, testOptions.getMovementType());
	}
}
