// To read from the file, when the code prompts the user to enter the name of the file then enter LettersProbability.txt

import java.io.*;
import java.util.*;

// This class executes the Huffman Coding program to build a Huffman tree.
public class Huffman {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        System.out.println("Huffman Coding");
        System.out.print("Enter the name of the file with letters and probability: ");
        String file = in.nextLine();

        // Lists that stores the values of the letters and their probabilities.
        List<Character> listOfValues = new ArrayList<>();
        List<Double> listOfProbabilities = new ArrayList<>();


        // This reads the letters and their probabilities from the input file.
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() < 2) continue;
                char val = line.charAt(0);
                double probability = Double.parseDouble(line.substring(2));
                listOfValues.add(val);
                listOfProbabilities.add(probability);
            }

            char[] value = new char[listOfValues.size()];
            double[] prob = new double[listOfProbabilities.size()];
            for (int i = 0; i < listOfValues.size(); i++) {
                value[i] = listOfValues.get(i);
                prob[i] = listOfProbabilities.get(i);
            }

            System.out.println("\nBuilding the Huffman tree ...");

            BinaryTree<Pair> huffmanTree = huffmanTreeStructure(value, prob, value.length);
            String[] codes = new String[26];
            huffmanCodes(huffmanTree, codes, "");

            System.out.println("Huffman coding completed.");
            System.out.print("\nEnter a line (uppercase letters only): ");
            String text = in.nextLine();

            String encodedBits = encode(text, codes);
            System.out.println("Here’s the encoded line: " + encodedBits);

            String decodedBits = decode(encodedBits, huffmanTree);
            System.out.println("The decoded line is: " + decodedBits);
        }
    }

    /**
     * referred to this website for some guidance " https://www.geeksforgeeks.org/queue-poll-method-in-java/ ".
     * This method builds the huffman tree structure using 2 queues, queueS and queueT.
     * @param value The letter.
     * @param prob The probability of the character.
     * @param size The size of the inputs.
     * @return The constructed huffman tree.
     */
    public static BinaryTree<Pair> huffmanTreeStructure(char[] value, double[] prob, int size) {
        Queue<BinaryTree<Pair>> queueS = new LinkedList<>();
        Queue<BinaryTree<Pair>> queueT = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            Pair pair = new Pair(value[i], prob[i]);
            BinaryTree<Pair> tree = new BinaryTree<>();
            tree.makeRoot(pair);
            queueS.offer(tree);
        }

        while (!queueS.isEmpty() || queueT.size() > 1) {
            BinaryTree<Pair> A;
            if (!queueT.isEmpty() && (queueS.isEmpty() || queueT.peek().getData().getProb() <= queueS.peek().getData().getProb())) {
                A = queueT.poll();
            } else {
                A = queueS.poll();
            }

            BinaryTree<Pair> B;
            if (!queueT.isEmpty() && (queueS.isEmpty() || queueT.peek().getData().getProb() <= queueS.peek().getData().getProb())) {
                B = queueT.poll();
            } else {
                B = queueS.poll();
            }

            // This combines the A and B into a new combined tree.
            double combinedProbability = A.getData().getProb() + B.getData().getProb();
            Pair combinedPair = new Pair('\0', combinedProbability);
            BinaryTree<Pair> combinedTree = new BinaryTree<>();
            combinedTree.makeRoot(combinedPair);
            combinedTree.setLeft(A);
            combinedTree.setRight(B);

            queueT.offer(combinedTree);
        }

        return queueT.poll();
    }

    /**
     * This method makes the huffman codes for each of the letter.
     * @param tree The main part of the huffman tree.
     * @param codes The array that stores the huffman codes.
     * @param code The huffman code.
     */
    public static void huffmanCodes(BinaryTree<Pair> tree, String[] codes, String code) {
        if (tree == null) return;

        if (tree.getLeft() == null && tree.getRight() == null) {
            char chr = tree.getData().getValue();
            if (chr >= 'A' && chr <= 'Z') {
                codes[chr - 'A'] = code;
            }
            return;
        }

        huffmanCodes(tree.getLeft(), codes, code + "0");
        huffmanCodes(tree.getRight(), codes, code + "1");
    }

    /**
     * This method encodes the given text/sentence into binary with the help of the huffman codes.
     * @param text The input text/sentence.
     * @param codes The array that stores the huffman codes.
     * @return The encoded binary sentence.
     */
    public static String encode(String text, String[] codes) {
        StringBuilder encodedBits = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (character == ' ') {
                encodedBits.append(" ");
            } else if (character >= 'A' && character <= 'Z') {
                encodedBits.append(codes[character - 'A']);
            }
        }
        return encodedBits.toString();
    }

    /**
     * This method decodes the binary sentence back to the original text/sentence.
     * @param bits The binary sentence to decode.
     * @param huffmanTree The main part of the huffman tree.
     * @return The decoded original text/sentence.
     */
    public static String decode(String bits, BinaryTree<Pair> huffmanTree) {
        StringBuilder decodedBits = new StringBuilder();
        BinaryTree<Pair> currentNode = huffmanTree;

        for (char bit : bits.toCharArray()) {
            if (bit == ' ') {
                decodedBits.append(" ");
            } else if (bit == '0') {
                currentNode = currentNode.getLeft();
            } else if (bit == '1') {
                currentNode = currentNode.getRight();
            }

            if (currentNode.getLeft() == null && currentNode.getRight() == null) {
                decodedBits.append(currentNode.getData().getValue());
                currentNode = huffmanTree;
            }
        }
        return decodedBits.toString();
    }


}
