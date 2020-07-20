package training.tabletennistable;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FileService {
    public League load(String path)
    {
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(path));
            return new League(IntStream.range(0, lines.size())
                    .mapToObj(index -> deserialiseRow(lines.get(index), index))
                    .collect(Collectors.toList()));
        }
        catch (IOException e)
        {
            throw new IllegalArgumentException("Could not load league " + path, e);
        }
    }

    private LeagueRow deserialiseRow(String line, int index)
    {
        LeagueRow row = new LeagueRow(index + 1);
        for (String player : line.split(",")) {
            row.add(player);
        }
        return row;
    }

    public void save(String path, League league)
    {
        try
        {
            FileWriter writer = new FileWriter(path);
            for (LeagueRow row : league.getRows()) {
                writer.write(this.serialiseRow(row) + "\r\n");
            }
            writer.close();
        }
        catch (IOException e)
        {
            throw new IllegalArgumentException("Could not save league " + path, e);
        }
    }

    private String serialiseRow(LeagueRow row)
    {
        return String.join(",", row.getPlayers());
    }
}
