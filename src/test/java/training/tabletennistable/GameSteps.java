package training.tabletennistable;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class GameSteps {
    // Current app instance
    private App app;

    // Last response
    private String response;

    @Before
    public void createApp()
    {
        League league = new League();
        LeagueRenderer leagueRenderer = new LeagueRenderer();
        FileService fileService = new FileService();
        app = new App(league, leagueRenderer, fileService);
    }

    @Given("the league has no players")
    public void givenTheLeagueHasNoPlayers()
    {
        // Nothing to do - the default league starts with no players
    }

    @When("I print the league")
    public void whenIPrintTheLeague()
    {
        response = app.sendCommand("print");
    }

    @Then("I should see {string}")
    public void iShouldSeeString(String expected)
    {
        Assert.assertEquals(expected, response);
    }
}
