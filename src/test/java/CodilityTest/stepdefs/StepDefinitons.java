package CodilityTest.stepdefs;

import CodilityTest.hooks.SessonInitiator;
import CodilityTest.webdriverfactory.TestSessionInitiator;
import io.cucumber.java.en.Given;

public class StepDefinitons {

	private TestSessionInitiator test;

	public StepDefinitons(SessonInitiator sessonInitiator) {
		test = sessonInitiator.test;
	}

	@Given("I want to write a step with precondition")
	public void firstAction() {
		test.pageOne.click();
	}

}
