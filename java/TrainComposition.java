/**
 *  Basically a doubly linked list
 */
public class TrainComposition {

    private TrainWagon head;
    private TrainWagon tail;

    private class TrainWagon {
        int wagonId;
        TrainWagon left = null;
        TrainWagon right = null;

        public TrainWagon(int wagonId) {
            this.wagonId = wagonId;
        }
    }

    public void attachWagonFromLeft(int wagonId) {
        TrainWagon trainWagon = new TrainWagon(wagonId);
        if (this.head == null) {
            this.head = trainWagon;
            this.tail = this.head;
        } else {
            TrainWagon tempWagon = this.head;
            this.head = trainWagon;
            this.head.right = tempWagon;
            tempWagon.left = this.head;
        }
    }

    public void attachWagonFromRight(int wagonId) {
        TrainWagon trainWagon = new TrainWagon(wagonId);
        if (this.tail == null) {
            this.tail = trainWagon;
            this.head = this.tail;
        }
        else {
            TrainWagon tempWagon = this.tail;
            this.tail = trainWagon;
            this.tail.left = tempWagon;
            tempWagon.right = this.tail;
        }
    }

    public int detachWagonFromLeft() {
        TrainWagon detachedHead = this.head;
        this.head = this.head.right;
        if (this.head == null) {
            this.tail = null;
        }
        else if (this.head.right == null) {
            this.tail = this.head;
        }

        return detachedHead.wagonId;
    }

    public int detachWagonFromRight() {
        TrainWagon detachedTail = this.tail;
        this.tail = this.tail.left;
        if (this.tail == null) {
            this.head = null;
        }
        else if (this.tail.left == null) {
            this.head = this.tail;
        }

        return detachedTail.wagonId;
    }

    public static void main(String[] args) {
        TrainComposition tree = new TrainComposition();
        tree.attachWagonFromLeft(7);
        tree.attachWagonFromLeft(13);
        tree.printTrain();
        System.out.println(tree.detachWagonFromRight()); // 7
        System.out.println(tree.detachWagonFromLeft()); // 13
    }

    public void printTrain() {
        TrainWagon marker = this.head;
        while (marker != null) {
            System.out.println(marker.wagonId);
            marker = marker.right;
        }
    }

}