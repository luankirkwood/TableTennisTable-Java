package training.tabletennistable;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LeagueTest {
    @Test
    public void testAddPlayer()
    {
        // Given
        League league = new League();

        // When
        league.addPlayer("Bob");

        // Then
        List<LeagueRow> rows = league.getRows();
        Assert.assertEquals(1, rows.size());
        List<String> firstRowPlayers = rows.get(0).getPlayers();
        Assert.assertEquals(1, firstRowPlayers.size());
        Assert.assertThat(firstRowPlayers, IsCollectionContaining.hasItem("Bob"));
    }

    @Test
    public void testGetRows(){

        League league = new League();

        league.addPlayer("Bob");
        league.addPlayer("Slob");

        List<LeagueRow> rows = league.getRows();
        Assert.assertEquals("Bob", rows.get(0).getPlayers().get(0));
        Assert.assertEquals("Slob", rows.get(1).getPlayers().get(0));
    }

    @Test
    public void testRecordWin(){ //winner must be one row below

        League league = new League();

        league.addPlayer("Harry");
        league.addPlayer("Tom");
        league.addPlayer("Bob");
        league.addPlayer("Slob");

        league.recordWin("Tom", "Harry");

        List<LeagueRow> rows = league.getRows();
        Assert.assertEquals("Tom", rows.get(0).getPlayers().get(0));
        Assert.assertEquals("Harry", rows.get(1).getPlayers().get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRecordWinWinnerInAnyRow(){ //winner can be in any row

        League league = new League();

        league.addPlayer("Harry");
        league.addPlayer("Tom");
        league.addPlayer("Bob");
        league.addPlayer("Slob");

        league.recordWin("Slob", "Harry");

        List<LeagueRow> rows = league.getRows();
        Assert.assertEquals("Slob", rows.get(0).getPlayers().get(0));
        Assert.assertEquals("Harry", rows.get(1).getPlayers().get(0));
    }

    @Test
    public void testGetWinner(){

        League league = new League();

        league.addPlayer("Harry");
        league.addPlayer("Tom");
        league.addPlayer("Bob");
        league.addPlayer("Slob");

        Assert.assertEquals("Harry", league.getWinner());
    }

    @Test
    public void testGetWinnerPlayerNotInGame(){

        League league = new League();

        league.addPlayer("Harry");
        league.addPlayer("Tom");
        league.addPlayer("Bob");
        league.addPlayer("Slob");

        Assert.assertEquals("Alex", league.getWinner());
    }
}
