import java.io;
import java.util.;

public class SearchEngine {

    ArrayList<Document> documents = new ArrayList<>();

    public void loadDocuments() {

        try {

            Scanner inFile = new Scanner(new FileReader("Documents.txt"));

            while (inFile.hasNextLine()) {

                String line = inFile.nextLine();

                String[] parts = line.split("\\|", 4);

                if (parts.length == 4) {

                    Document doc = new Document(
                            parts[0],
                            parts[1],
                            parts[2],
                            parts[3]
                    );

                    documents.add(doc);

                }
            }

            inFile.close();

        } catch (FileNotFoundException e) {

            System.out.println("Documents.txt was not found.");

        }
    }

    public ArrayList<Document> search(String query) {

        ArrayList<Document> results = new ArrayList<>();

        for (int i = 0; i < documents.size(); i++) {

            Document doc = documents.get(i);

            if (doc.title.toLowerCase().contains(query.toLowerCase()) ||
                    doc.keywords.toLowerCase().contains(query.toLowerCase()) ||
                    doc.description.toLowerCase().contains(query.toLowerCase())) {

                results.add(doc);

            }
        }

        return results;
    }
}
