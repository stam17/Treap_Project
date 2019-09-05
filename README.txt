RUNNABLE JAVA FILES = Experiment.java
HELPER CLASSES = Treap.java, AFTreap.java, Node.java, RequestGenerator.java

Experiment.java
-Runs the experiment with desired parameters on treap and access frequency treap; takes the average runtime of 15 trials 
-Final int RANGE specifies the range of numbers requested / added to the treap, we altered this value for each test (e.g. for RANGE=10, numbers are between 1-10, inclusive) 

fisherYatesShuffle(int[] array)
-Shuffles a sorted int[] array (parameter) and returns the shuffled array

run()
-Fills a Treap and AFTreap with shuffled numbers in specified range
-For 10^6 requests (either uniform or zipf, from RequestGenerator), performs lookups on the Treap and AFTreap, and tracks the sums of the lookup times after the first 10^4 requests
-Prints the average lookup times for Treap and AFTreap at the end

main(String args[])
-calls run() multiple times

RequestGenerator.java
The class generates uniform and Zipf requests.

The constructor takes no input and returns nothing. It called the changeDenominator() method and establishZipf() method.

changeDenominator() method takes no input and returns nothing; it changes the value of the 
double ZIPF_PMF_DENOMINATOR in the field according to the Experiment class’s field int RANGE.

establishZipf() method takes no input and returns nothing; it initiates a static double[] zipfPdf, which contains the probability mass function of each integers in the request sequence under Zipf distribution.

getUniform() takes no input and returns an integer in the request sequence under uniform distribution.

getZipf() takes no input and returns an integer following Zipf distribution.

getZipfPdf() takes no input and returns a double array which is the zipfPdf in the field.



Treap.java
The class generates instances of Treap.

The constructor takes no input and returns nothing. It has no functions other than serving as a constructor.

Insert() takes two Integer inputs, Key and value, and returns nothing. By calling insertHelper() method, It inserts a new node into the treap with its corresponding Key, value, and a random priority.

insertHelper() takes three inputs: Node parent, Integer key, Integer value. It returns a boolean, which tells the insert() method whether there the inserted node has a new key. insertHelper() method recursively calls itself to place the node at the appropriate position. It then calls siftUp() method to rotate the node up to restore the heap property of the treap.

search() takes an Integer Key and returns a Node. It calls searchHelper() method and returns what the searchHelper() returns.

searchHelper() takes Node parent and Integer key; It returns a Node. searchHelper() recursively calls itself to find the Node with the key. If the Node is not found, it returns a null.

delete() takes an Integer Key and returns an Integer; it calls search() method to find the node which contains the Key. If search() returns a null, it delete() will return a null. If search() returns the required node, then change the priority of the node to negative infinity and call siftDown() method. Return the value of the node.

rotateLeft() takes a Node, P, and returns nothing. It changes the parent of P to its right child, Q, and make P the left child of Q.

rotateRight() takes a Node, P, and returns nothing. It changes the parent of P to its left child, Q, and make P the right child of Q.

siftUp() takes a Node node and returns nothing. It calls rotateLeft() and rotateRight() on node’s parent until node’s priority is lower than its parents.

siftDown() takes a Node node and returns nothing. It calls rotateLeft() and rotateRight() on node until node becomes a leaf.

AFTreap.java
The class generates instances of the Access Frequency Treap.

AFTreap.java contains all the methods from Treap.java. All the methods are identical to their counterparts in Treap.java, except for search();

search(): takes an Integer Key and returns a Node. It calls searchHelper() method. Once node is found, it generates a new random priority. If new priority is greater than current priority, updates the node’s priority to new priority and calls siftUp() on the node to reorganize AFTreap based on new priority. search() then returns the node found.



