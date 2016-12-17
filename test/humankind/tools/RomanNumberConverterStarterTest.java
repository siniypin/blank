package humankind.tools;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class RomanNumberConverterStarterTest {
	@Test
	public void ShouldBeTrivialToCreate() {
		new RomanNumberConverter();
	}
}
