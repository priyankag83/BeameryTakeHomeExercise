package maven.cucumber;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "Feature"
//		,plugin = { "html:target/cucumber-reports" }
//		,plugin = { "pretty" , "html:target/cucumber-reports" }
		,plugin = { "pretty" , "json:target/cucumber-reports/Cucumber.json" , "html:target/cucumber-reports" }
		)

public class RunnerTest {

}