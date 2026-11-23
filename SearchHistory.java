import java.io.;

public class SearchHistory {

    // Save search information
    public void saveSearch(String query, int count) {

        try {

            PrintWriter outFile = new PrintWriter(
                    new FileWriter("SearchHistory.dat", true)
            );

            outFile.println("Search: " + query + " | Matches: " + count);

            outFile.close();

        } catch (IOException e) {

            System.out.println("Error saving search history.");

        }
    }
}
