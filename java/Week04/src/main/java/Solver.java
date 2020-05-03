import edu.princeton.cs.algs4.MinPQ;
import java.util.Deque;
import java.util.LinkedList;

public class Solver {

    private boolean isSolvable = true;
    private Node endNode;

    private static class Node implements Comparable<Node> {
        private final Board board;
        private final int gCost;
        private final int hCost;
        private final Node previous;

        public Node(Board board, Node previous) {
            this.board = board;
            this.previous = previous;
            this.hCost = board.manhattan();
            if (previous == null)
                this.gCost = 0;
            else
                this.gCost = previous.getMoves() + 1;
        }

        private int totalCost() {
            return this.gCost + this.hCost;
        }

        @Override
        public int compareTo(Node other) {
            return totalCost() - other.totalCost();
        }

        public Board getBoard() {
            return board;
        }

        public int getMoves() {
            return gCost;
        }

        public Node getPrevious() {
            return previous;
        }
    }

    // find a solution to the initial board (using the A* algorithm)

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("Null is not accepted.");
        MinPQ<Node> queue = new MinPQ<>();
        queue.insert(new Node(initial, null));
        solve(queue);
    }

    private void solve(MinPQ<Node> queue) {

        endNode = null;
        while (true) {
            Node currNode = queue.delMin();
            Board currBoard = currNode.getBoard();

            if (currBoard.isGoal()) {
                isSolvable = true;
                endNode = currNode;
                break;
            }

            if (currBoard.manhattan() <= 3) {
                for (Board item : currBoard.neighbors()) {
                    if (item.twin().isGoal()) {
                        isSolvable = false;
                        break;
                    }
                }
                if (!isSolvable) break;
            }

            int moves = currNode.getMoves();
            Board prevBoard = null;
            if (moves > 0) {
                prevBoard = currNode.getPrevious().getBoard();
            }

            for (Board nextBoard : currBoard.neighbors()) {
                if (nextBoard.equals(prevBoard)) {
                    continue;
                }
                queue.insert(new Node(nextBoard, currNode));
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board
    public int moves() {
        if (endNode == null) return -1;
        return endNode.getMoves();
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        if (endNode == null) return null;
        Deque<Board> iterable = new LinkedList<>();
        Node node = endNode;
        while (node != null) {
            iterable.addFirst(node.getBoard());
            node = node.getPrevious();
        }
        return iterable;
    }


}