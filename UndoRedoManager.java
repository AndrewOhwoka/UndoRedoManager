
/**
 * Implememt an application that support undo/redo functionality. Use a linked list to maintain a sequence of states.\
 * Each state change is stored as a node in the list, allowoing for easy navigation
 * 1<>2<>3<>4<>5
 */

public class UndoRedoManager<T> {
    private class Node {
        private T state;
        private Node prev;
        private Node next;
        private Node (T state) {
            this.state = state;
        }

    }
    private Node currentState;
    //Undo operation
    public T undo(){
        if (currentState == null){
            System.out.println("No state to undo");
            return null;
        }
        //Get the previous state

        Node previousState = currentState.prev;
        if (previousState ==null){
            System.out.println("Reached the initial state");
            return null;
        } else {
            //update the current state to the previous state
            currentState = previousState;
        }
        return currentState.state;
    }

    //perform an operation
    public void  addState (T newState) {
        //create a new node for the new task
        Node newNode = new Node(newState);

        //Set the links for the new Node
        newNode.prev = currentState;
        newNode.next = null;

        //Update the next link for the current state
        if (currentState !=null){
            currentState.next = newNode;
        }
        //update the current to the new state
        currentState = newNode;
    }

    //Redo Operation

    public static void main(String[] args) {
        UndoRedoManager<String> undoRedoManager = new UndoRedoManager<>();
        undoRedoManager.addState("State 1");
        undoRedoManager.addState("State 2");
        undoRedoManager.addState("State 3");
        undoRedoManager.addState("State 4");
        undoRedoManager.addState("State 5");
        undoRedoManager.addState("State 6");
        undoRedoManager.addState("State 7");

        System.out.println("Current State: " + undoRedoManager.currentState.state);
        undoRedoManager.undo();
        System.out.println("Current State: " + undoRedoManager.currentState.state);
        undoRedoManager.undo();
        System.out.println("Current State: " + undoRedoManager.currentState.state);
        undoRedoManager.undo();
        System.out.println("Current State: " + undoRedoManager.currentState.state);



    }
}
