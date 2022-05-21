# Genome
This program was made to be able to find repeating patterns in a genome in the form xyx and index those patterns, as well as the starting indicies (Mode 1). The patterns that are found are then printed out to a text file in the out directory. The program can also generate genomic sequences that contain no repeating xyx substring patterns(Mode 2) . The functionality to input a certain time frame (in seconds) for genomes to be generated can also be specified(Mode 3).

The program arguments as follows.
Mode 1 : [1] [*.txt] - (The last argument is the text file containing the genomic string to be checked for repitions)
Mode 2 : [2] [1 - 4] - (The letters to be used in generating a genomic sequence from the "ACGT" string] ["A" || "C" || "G" || "T"] - (The root string) [int] - (integer value of the maximum length to which strings need to be generated *If the int value is 0 then the program will keep on generating until no more possible strings can be generated without causing a substring xyx repitition)
Mode 3 : [2] [1 - 4] - (The letters to be used in generating a genomic sequence from the "ACGT" string] ["A" || "C" || "G" || "T"] - (The root string) [double] - (The time limit of the program *The program will be generating strings indefinitely until the time limit is reached and ouutput the longest string that has been generated)
