package up5.poo.breaktrough;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);

	}

	public void testArrayCopy() {
		int[] toCopy = Utils.generateBoard();
		int[] testClone = Utils.copyBoard(toCopy);
		assertNotSame(toCopy, testClone);
		assertTrue(java.util.Arrays.equals(toCopy, testClone));
	}

	public void testNode() {
		Node node = new Node();
		node.expandOneChild();
		assertSame(node.getChildren().size(), 1);
		assertEquals(node, node.getChildren().get(0).getParent());

	}

	public void testSameChildrenGeneration() {
		Node node = new Node();
		Node node2 = new Node();
		node2.createChildren();
		for (int i = 0; i < Utils.getPlayerLegalMoves(node.getState(), node.getPlayer()).size(); i++) {
			node.expandOneChild();
			assertTrue(node.getChildren().get(i).getMove().getDestination()
					.equals(node2.getChildren().get(i).getMove().getDestination()));
			assertTrue(node.getChildren().get(i).getMove().getPosition()
					.equals(node2.getChildren().get(i).getMove().getPosition()));
		}
	}

	public void testMove() {
		Node node = new Node();
		// 22 Moves possible on first play
		assertTrue(Utils.getPlayerLegalMoves(node.getState(), node.getPlayer()).size() == 22);
		node.createChildren();
		java.util.Random rand = new java.util.Random();
		Node child = node.getChildren().get(rand.nextInt(node.getChildren().size()));
		child.createChildren();
		assertTrue(Utils.getPlayerLegalMoves(child.getState(), child.getPlayer()).size() == 22);
		assertTrue(node.getChildren().size() == 22);
		assertTrue(child.getChildren().size() == 22);

	}
}
