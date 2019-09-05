import java.util.Random;

public class Treap{
    public Node root;
    private int size = 0;
    private Random rand = new Random();

    public Treap(){

    }

    public void insert(Integer key, Integer value) {
        //System.out.println("in add");

        // am I adding the first node in the tree?
        if (size == 0) {
            root = new Node();
            //System.out.println("in add size" + size);
            root.changeKey(key);
            root.changeValue(value);
            root.changePriority(rand.nextDouble());
            size++;
        }

        // if I am not adding the first element tree, I will check left or right tree
        // addHelper will return true if a new key is added
        else if (insertHelper(root, key, value)){
            size ++;
        }

    }

    private boolean insertHelper(Node parent, Integer key, Integer value){

        // parent's key is bigger
        //System.out.println("parent key : intput key = "+ key.compareTo(parent.getKey()));
        if (key.compareTo(parent.getKey()) < 0){
            //System.out.println("in addHelper, parent's key is bigger ");
            if (parent.getleft() == null){
                Node newNode = new Node();
                parent.changeleft(newNode);
                parent.getleft().changeKey(key);
                parent.getleft().changeValue(value);
                parent.getleft().changePriority(rand.nextDouble());
                parent.getleft().changeParent(parent);
                siftUp(parent.getleft());
                return true;
            }
            return insertHelper(parent.getleft(), key, value);
        }

        // input key is bigger
        else if (key.compareTo(parent.getKey()) > 0){
            //System.out.println("in addHelper, input key is bigger ");
            if (parent.getright() == null){
                Node newNode = new Node();
                parent.changeright(newNode);
                parent.getright().changeKey(key);
                parent.getright().changeValue(value);
                parent.getright().changePriority(rand.nextDouble());
                parent.getright().changeParent(parent);
                siftUp(parent.getright());
                return true;
            }
            return insertHelper(parent.getright(), key, value);
        }

        // parent's key == input key
        else{
            //System.out.println("in addHelper, same key");
            parent.changeValue(value);
            return false;
        }

    }

    public Integer delete(Integer key){
        if (search(key) == null){
            return null;
        }

        if (size == 1){
            Integer toReturn = root.getValue();
            root.changeKey(null);
            root.changeValue(null);
            size --;
            return toReturn;
        }

        else {
            size --;
            Node node = search(key);
            node.changePriority(Double.NEGATIVE_INFINITY);
            siftDown(node);
            // remove the node, which is a leaf
            if (node.getParent().getleft() == node){
                node.getParent().changeleft(null);
            }
            else {
                node.getParent().changeright(null);
            }
            node.changeParent(null);
            return (Integer) node.getValue();
        }

    }


    public Node search (Integer key){
        //System.out.println("In lookup");
        if (size == 0){
            //System.out.println("Size = " + size);
            return null;
        }
        else return searchHelper(root, key);
    }

    private Node searchHelper (Node parent, Integer key){

        //System.out.println("In lookupHelper");
        // parent's key is bigger
        if (key.compareTo(parent.getKey()) < 0){
            if (parent.getleft() == null){
                return null;
            }
            return searchHelper(parent.getleft(), key);
        }

        // input key is bigger
        else if (key.compareTo(parent.getKey()) > 0){
            if (parent.getright() == null){
                return null;
            }
            return searchHelper(parent.getright(), key);
        }

        // a == b
        else{
            return parent;
        }

    }



    public void siftUp(Node node){
        while (node.getParent() != null && node.getParent().getPriority().compareTo(node.getPriority())<0) {
            if (node.getParent().getright() == node) {
                rotateLeft(node.getParent());
            } else {
                rotateRight(node.getParent());
            }
        }
        if (node.getParent() == null) {
            root = node;
        }
    }

    public void siftDown(Node node){
        while(node.getleft() != null || node.getright() != null){
            if (node.getleft() == null){
                rotateLeft(node);
            }
            else if (node.getright() == null){
                rotateRight(node);
            }
            else if (node.getleft().getPriority().compareTo(node.getright().getPriority()) < 0){
                rotateLeft(node);
            }
            else {
                rotateRight(node);
            }
            if (root == node){
                root = node.getParent();
            }
        }

        // if node is a leaf: left child and right child are null, no rotation is needed.
    }



    public void rotateLeft(Node parent) {
        Node rightChild = parent.getright();

        // update the parent pointer of rightChild
        // if the parent is the root, do something special
        if (parent == root) {
            root = rightChild;
            root.changeParent(null);
        }
        else{
            rightChild.changeParent(parent.getParent());

            // update the left/right child pointer of parent's parent
            // only when parent is not the root, so parent's parent is not null
            if (rightChild.getParent().getleft() == parent) {
                rightChild.getParent().changeleft(rightChild);
            } else {
                rightChild.getParent().changeright(rightChild);
            }
        }


        // update the rightChild of the parent as the leftChild of the rightChild
        parent.changeright(rightChild.getleft());

        // if the leftChild of the rightChild (now right child of parent) is not null,
        // update the parent pointer of it.
        if (parent.getright() != null) {
            parent.getright().changeParent(parent);
        }

        // change the parent pointer of parent to the rightChild
        parent.changeParent(rightChild);
        rightChild.changeleft(parent);
    }

    public void rotateRight(Node parent) {

        Node leftChild = parent.getleft();


        if (parent == root) {
            root = leftChild;
            root.changeParent(null);
        }
        else{
            leftChild.changeParent(parent.getParent());

            // update the left/right child pointer of parent's parent
            // only when parent is not the root, so parent's parent is not null
            if (leftChild.getParent().getleft() == parent) {
                leftChild.getParent().changeleft(leftChild);
            } else {
                leftChild.getParent().changeright(leftChild);
            }
        }

        parent.changeleft(leftChild.getright());

        if (parent.getleft() != null) {
            parent.getleft().changeParent(parent);
        }

        parent.changeParent(leftChild);
        leftChild.changeright(parent);

    }


}


