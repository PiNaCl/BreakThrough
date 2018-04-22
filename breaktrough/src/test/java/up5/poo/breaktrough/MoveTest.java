package up5.poo.breaktrough;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MoveTest extends TestCase {
	
	public MoveTest(String testName){
		super(testName);
	}
	
	public static Test suite(){
		return new TestSuite(MoveTest.class);
	}
	
	public void testIsLegal(){

		assertTrue (true);
		
	}

}
