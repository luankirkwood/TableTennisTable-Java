package training.tabletennistable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class AppTest {
    @Mock
    LeagueRenderer renderer;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPrintsCurrentState()
    {
        League league = new League();
        Mockito.when(renderer.render(league)).thenReturn("Rendered League");

        App app = new App(league, renderer, null);

        Assert.assertEquals("Rendered League", app.sendCommand("print"));
    }

    @Mock
    League league;

    @Test
    public void testAddPlayer()
    {
        league.addPlayer("Me");

        App app = new App(league, null, null);

        Mockito.verify(league).addPlayer("Me");
        Assert.assertEquals("Added player Me", app.sendCommand("add player Me"));

    }

    @Test
    public void testRecordWin()
    {
        league.recordWin("Jack", "Jill");
        App app = new App(league, null, null);

        app.sendCommand("add player Jack");
        app.sendCommand("add player Jill");

        Assert.assertEquals("Recorded Jack win against Jill", app.sendCommand("record win Jack Jill"));

    }

    @Test
    public void testWinner()
    {
        App app = new App(league, null, null);
        String response = app.sendCommand("winner");
        Assert.assertEquals(league.getWinner(), response);
    }


    @Mock
    FileService fileService;

    @Test
    public void testSaveGame() {
        String path = "test/file.csv";
        App app = new App(league, null, fileService);
        String response = app.sendCommand("save " + path);
        Assert.assertEquals("Saved " + path, response);
        Mockito.verify(fileService).save(path, league);
    }
}
