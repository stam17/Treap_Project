public class Node {
        private Integer key;
        private Integer value;
        private Double priority;
        private Node parent;
        private Node left;
        private Node right;

        Node() {
        }

        public String toString() {
            Integer var10000 = this.key;
            Integer var10001 = this.value;
            return String.valueOf(this.priority);
        }

        public Integer getKey() {
            return this.key;
        }

        public void changeKey(Integer var1) {
            this.key = var1;
        }

        public Integer getValue() {
            return this.value;
        }

        public void changeValue(Integer var1) {
            this.value = var1;
        }

        public Double getPriority() {
            return this.priority;
        }

        public void changePriority(Double var1) {
            this.priority = var1;
        }

        public Node getParent() {
            return this.parent;
        }

        public void changeParent(Node var1) {
            this.parent = var1;
        }

        public Node getleft() {
            return this.left;
        }

        public void changeleft(Node var1) {
            this.left = var1;
        }

        public Node getright() {
            return this.right;
        }

        public void changeright(Node var1) {
            this.right = var1;
        }
}
