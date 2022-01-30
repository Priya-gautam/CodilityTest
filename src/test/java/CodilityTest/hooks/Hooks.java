package CodilityTest.hooks;

import java.io.IOException;
import java.util.Map;

import CodilityTest.webdriverfactory.TestSessionInitiator;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	private TestSessionInitiator test;
	public Map<String, String> configSettings;
	private SessonInitiator sessionInitiator;

	public Hooks(SessonInitiator sessionInitiator) {
		this.sessionInitiator = sessionInitiator;
	}

	@Before
	public void startTest() throws IOException {
		configSettings = TestSessionInitiator._getSessionConfig();
		this.sessionInitiator.test = new TestSessionInitiator("CukeTest");
		test = this.sessionInitiator.test;
	}

	@After
	public void closeBrowser(Scenario s) {
		test.closeTestSession();
	}

}
