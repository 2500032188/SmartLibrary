package smartlibrary;

import java.util.Scanner;

public class SmartLibrary {

    static Document[] docs;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        // Load the dataset
        System.out.println("Loading SmartLibrary dataset...");

        docs = DatasetLoader.load("dataset/SmartLibrary_Dataset.csv");

        if (docs == null) {
            docs = new Document[0];
        }

        System.out.println("\n====================================");
        System.out.println(" SmartLibrary Advanced Text Analytics");
        System.out.println("====================================");
        System.out.println("Dataset loaded: " + docs.length + " documents");

        int ch;

        do {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. View Documents");
            System.out.println("2. Pattern Search");
            System.out.println("3. Fuzzy Matching");
            System.out.println("4. Suffix Array and LCP");
            System.out.println("5. Sequence Alignment");
            System.out.println("6. Dynamic Programming Demos");
            System.out.println("7. Network Flow");
            System.out.println("8. Vertex Cover Approximation");
            System.out.println("9. Miller-Rabin Primality Test");
            System.out.println("10. Reservoir Sampling");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            ch = readInt(in);

            switch (ch) {

                case 1: {
                    System.out.println("\n--- DATASET DOCUMENTS ---");

                    if (docs.length == 0) {
                        System.out.println("No documents loaded.");
                    } else {
                        for (int i = 0; i < docs.length; i++) {
                            docs[i].print();
                        }
                    }
                    break;
                }

                case 2: {
                    System.out.print("Enter pattern to search: ");
                    String pattern = in.nextLine().trim().toLowerCase();

                    if (pattern.isEmpty()) {
                        System.out.println("Pattern cannot be empty.");
                        break;
                    }

                    if (docs.length == 0) {
                        System.out.println("Dataset is empty.");
                        break;
                    }

                    int totalMatches = 0;

                    System.out.println("\n--- PATTERN SEARCH RESULTS ---");

                    for (int i = 0; i < docs.length; i++) {
                        String text = docs[i].searchableText().toLowerCase();

                        int naive = StringAlgorithms.naive(text, pattern);
                        int kmp = StringAlgorithms.kmp(text, pattern);
                        int z = StringAlgorithms.zSearch(text, pattern);
                        int rk = StringAlgorithms.rabinKarp(text, pattern);

                        if (kmp >= 0) {
                            totalMatches++;

                            System.out.println("\nDocument " + (i + 1));
                            docs[i].print();
                            System.out.println("Naive position: " + naive);
                            System.out.println("KMP position: " + kmp);
                            System.out.println("Z position: " + z);
                            System.out.println("Rabin-Karp position: " + rk);
                        }
                    }

                    System.out.println("\nDocuments containing pattern: "
                            + totalMatches);
                    break;
                }

                case 3: {
                    System.out.print("Enter first text: ");
                    String text1 = in.nextLine();

                    System.out.print("Enter second text: ");
                    String text2 = in.nextLine();

                    System.out.println("Levenshtein Distance = "
                            + StringAlgorithms.edit(text1, text2));

                    System.out.println("Damerau-Levenshtein Distance = "
                            + StringAlgorithms.damerau(text1, text2));

                    break;
                }

                case 4: {
                    System.out.print("Enter text for suffix analysis: ");
                    String text = in.nextLine();

                    if (text.isEmpty()) {
                        System.out.println("Text cannot be empty.");
                    } else {
                        SuffixStructures.show(text);
                    }
                    break;
                }

                case 5: {
                    System.out.print("Enter sequence A: ");
                    String a = in.nextLine();

                    System.out.print("Enter sequence B: ");
                    String b = in.nextLine();

                    System.out.println("Needleman-Wunsch score = "
                            + DPAlgorithms.align(a, b, false));

                    System.out.println("Smith-Waterman score = "
                            + DPAlgorithms.align(a, b, true));

                    break;
                }

                case 6: {
                    System.out.println("\n--- DYNAMIC PROGRAMMING DEMOS ---");

                    int[] dimensions = {10, 20, 30, 40};

                    System.out.println("Matrix Chain Multiplication = "
                            + DPAlgorithms.matrixChain(dimensions));

                    int[] frequencies = {34, 8, 50};

                    System.out.println("Optimal BST Cost = "
                            + DPAlgorithms.optimalBST(frequencies));

                    int[][] distances = {
                        {0, 10, 15, 20},
                        {10, 0, 35, 25},
                        {15, 35, 0, 30},
                        {20, 25, 30, 0}
                    };

                    System.out.println("TSP Minimum Cost = "
                            + DPAlgorithms.tsp(distances));

                    break;
                }

                case 7: {
                    int[][] capacity = {
                        {0, 3, 2, 0, 0, 0},
                        {0, 0, 0, 3, 2, 0},
                        {0, 0, 0, 0, 0, 2},
                        {0, 0, 0, 0, 0, 2},
                        {0, 0, 0, 0, 0, 3},
                        {0, 0, 0, 0, 0, 0}
                    };

                    System.out.println("Edmonds-Karp Maximum Flow = "
                            + NetworkFlow.maxFlow(capacity, 0, 5));

                    break;
                }

                case 8: {
                    boolean[][] edges = new boolean[4][4];

                    edges[0][1] = edges[1][0] = true;
                    edges[1][2] = edges[2][1] = true;
                    edges[2][3] = edges[3][2] = true;
                    edges[0][3] = edges[3][0] = true;

                    boolean[] cover =
                            Approximation.vertexCover2Approx(edges);

                    System.out.print("Selected vertices: ");

                    for (int i = 0; i < cover.length; i++) {
                        if (cover[i]) {
                            System.out.print(i + " ");
                        }
                    }

                    System.out.println();
                    break;
                }

                case 9: {
                    System.out.print("Enter a positive integer: ");
                    long number = readLong(in);

                    if (number < 2) {
                        System.out.println("Number must be at least 2.");
                    } else {
                        boolean prime =
                                RandomizedAlgorithms.millerRabin(number, 8);

                        System.out.println(prime
                                ? "Probably prime"
                                : "Composite");
                    }

                    break;
                }

                case 10: {
                    System.out.print("Enter population size: ");
                    int n = readInt(in);

                    System.out.print("Enter sample size: ");
                    int k = readInt(in);

                    if (n <= 0 || k <= 0 || k > n) {
                        System.out.println(
                                "Invalid input. Require 0 < k <= n.");
                    } else {
                        int[] sample =
                                RandomizedAlgorithms.reservoir(n, k);

                        System.out.println("Random sample:");

                        for (int value : sample) {
                            System.out.print(value + " ");
                        }

                        System.out.println();
                    }

                    break;
                }

                case 0: {
                    System.out.println("Thank you for using SmartLibrary!");
                    break;
                }

                default: {
                    System.out.println("Invalid choice. Try again.");
                }
            }

        } while (ch != 0);

        in.close();
    }

    private static int readInt(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static long readLong(Scanner scanner) {
        try {
            return Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
