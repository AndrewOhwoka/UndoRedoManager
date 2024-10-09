//  * Implememt an application that support undo/redo functionality. Use a linked list to maintain a sequence of states.\
//  * Each state change is stored as a node in the list, allowoing for easy navigation
//  * 1<>2<>3<>4<>5
//  */

public class UndoRedoManager<T> {
    private class Node {
        private T state;
        private Node prev;
        private Node next;

        private Node(T state) {
            this.state = state;
        }
    }

    private Node currentState;

    // Undo operation
    public T undo() {
        if (currentState == null) {
            System.out.println("No state to undo");
            return null;
        }
        // Get the previous state
        Node previousState = currentState.prev;
        if (previousState == null) {
            System.out.println("Reached the initial state");
            return null;
        } else {
            // Update the current state to the previous state
            currentState = previousState;
        }
        return currentState.state;
    }

    // Redo operation
    public T redo() {
        if (currentState == null) {
            System.out.println("No state to redo");
            return null;
        }
        // Get the next state
        Node nextState = currentState.next;
        if (nextState == null) {
            System.out.println("Reached the most recent state");
            return null;
        } else {
            // Update the current state to the next state
            currentState = nextState;
        }
        return currentState.state;
    }

    // Perform an operation
    public void addState(T newState) {
        // Create a new node for the new state
        Node newNode = new Node(newState);

        // Set the links for the new node
        newNode.prev = currentState;
        newNode.next = null;

        // Update the next link for the current state
        if (currentState != null) {
            currentState.next = newNode;
        }

        // Update the current state to the new state
        currentState = newNode;
    }

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
        undoRedoManager.redo();
        System.out.println("Current State: " + undoRedoManager.currentState.state); 
        undoRedoManager.redo();
        System.out.println("Current State: " + undoRedoManager.currentState.state); 
        undoRedoManager.redo();
        System.out.println("Current State: " + undoRedoManager.currentState.state); 
    }
}