package smartlibrary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DatasetLoader {

    public static Document[] load(String path) {

        Document[] temp = new Document[3000];
        int count = 0;

        try (BufferedReader br =
                new BufferedReader(new FileReader(path))) {

            // Skip CSV header
            br.readLine();

            
            String line;

            while ((line = br.readLine()) != null
                    && count < temp.length) {

                String[] columns = parse(line);

                if (columns.length < 7) {
                    continue;
                }

                try {
                    int id = Integer.parseInt(columns[0].trim());
                    String title = columns[1].trim();
                    String author = columns[2].trim();
                    int year = Integer.parseInt(columns[3].trim());
                    String abstractText = columns[4].trim();
                    String keywords = columns[5].trim();
                    int citations = Integer.parseInt(columns[6].trim());

                    temp[count] = new Document(
                            id,
                            title,
                            author,
                            year,
                            abstractText,
                            keywords,
                            citations
                    );

                    count++;

                } catch (NumberFormatException e) {
                    System.out.println("Skipped invalid CSV row: " + line);
                }
            }

            System.out.println("Dataset loaded successfully.");

        } catch (IOException e) {
            System.out.println("Dataset could not be opened: " + path);
            System.out.println("Check that the CSV file exists in the project folder.");
        }

        Document[] result = new Document[count];

        for (int i = 0; i < count; i++) {
            result[i] = temp[i];
        }

        return result;
    }

    private static String[] parse(String line) {

        String[] values = new String[20];
        int count = 0;

        StringBuilder field = new StringBuilder();
        boolean insideQuotes = false;

        for (int i = 0; i < line.length(); i++) {

            char ch = line.charAt(i);

            if (ch == '"') {

                if (insideQuotes
                        && i + 1 < line.length()
                        && line.charAt(i + 1) == '"') {

                    field.append('"');
                    i++;

                } else {
                    insideQuotes = !insideQuotes;
                }

            } else if (ch == ',' && !insideQuotes) {

                if (count == values.length) {
                    return new String[0];
                }

                values[count++] = field.toString();
                field.setLength(0);

            } else {
                field.append(ch);
            }
        }

        if (count == values.length) {
            return new String[0];
        }

        values[count++] = field.toString();

        String[] result = new String[count];

        for (int i = 0; i < count; i++) {
            result[i] = values[i];
        }

        return result;
    }
}
