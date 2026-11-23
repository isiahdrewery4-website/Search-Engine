
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        SearchEngine engine = new SearchEngine();
        SearchHistory history = new SearchHistory();

        engine.loadDocuments();

        JFrame frame = new JFrame("Simple File Search Engine");

        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel searchLabel = new JLabel("Enter a keyword or phrase:");

        JTextField searchBar = new JTextField(20);

        JButton searchButton = new JButton("Search");

        JTextArea resultsArea = new JTextArea();
        resultsArea.setEditable(false);

        JLabel resultCount = new JLabel("Enter a search to begin.");

        JPanel topPanel = new JPanel();

        topPanel.add(searchLabel);
        topPanel.add(searchBar);
        topPanel.add(searchButton);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(resultsArea), BorderLayout.CENTER);
        frame.add(resultCount, BorderLayout.SOUTH);

        searchButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String query = searchBar.getText().trim();

                if (query.equals("")) {

                    resultsArea.setText("Please enter a keyword or phrase.");
                    resultCount.setText("0 matches");

                } else {

                    ArrayList<Document> results = engine.search(query);

                    resultsArea.setText("");

                    if (results.size() == 0) {

                        resultsArea.setText("No results found.");

                    } else {

                        for (int i = 0; i < results.size(); i++) {

                            Document doc = results.get(i);

                            resultsArea.append("Document ID: " + doc.id + "\n");

                            resultsArea.append("Title: " + doc.title + "\n");

                            resultsArea.append("Description: " + doc.description + "\n");

                            resultsArea.append("--------------------------\n");

                        }
                    }

                    resultCount.setText(results.size() + " match(es) found.");

                    history.saveSearch(query, results.size());

                }
            }
        });

        frame.setVisible(true);

    }
}
