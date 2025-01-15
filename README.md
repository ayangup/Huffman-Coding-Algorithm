##Huffman Coding Algorithm Project##

#Overview
This project implements the Huffman Coding Algorithm using the Binary Tree data structure. The goal of the project is to demonstrate the principles of efficient text compression through encoding and decoding based on character probabilities. It reads a file containing character-probability pairs, constructs a Huffman tree, and allows the user to encode and decode text messages in uppercase letters.

The project is designed with simplicity and functionality in mind, focusing on clear, reusable code that adheres to fundamental data structure concepts.

#Features
- Huffman Tree Construction:
The program reads a file containing letters and their associated probabilities, then builds the Huffman tree using efficient tree-building algorithms.

- Encoding and Decoding:
The program accepts user input, encodes it into a binary string based on Huffman codes, and then decodes it back to the original message, ensuring the decoded message matches the input exactly.

- File Handling:
The program reads from an external text file (LettersProbability.txt) for the letter-probability pairs and allows the user to input their own text for encoding and decoding.

- Binary Tree Data Structure:
The project makes extensive use of a generic binary tree to manage the Huffman tree and apply tree-based operations.

#How It Works
1. Reading the Input
The program starts by reading the LettersProbability.txt file, which contains uppercase letters and their corresponding probabilities. These are used to construct the Huffman tree. The probabilities are assumed to be normalized, meaning they sum to 1.

2. Building the Huffman Tree
Using the letter-probability pairs, the program constructs the Huffman tree. The tree is built by repeatedly merging the two nodes with the lowest probabilities until there is only one tree left. This tree represents the optimal encoding structure for the given input.

3. Encoding Text
Once the tree is built, the program allows the user to enter a line of text. The program then encodes the text by replacing each character with its corresponding Huffman code, which is a binary string.

4. Decoding Text
The program decodes the binary string back into the original text. If the encoding and decoding are successful, the decoded message will exactly match the original input.
