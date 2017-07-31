 package adt.bst;
   
   public class BSTImpl<T extends Comparable<T>> implements BST<T> {
   
      protected BSTNode<T> root;
   
      public BSTImpl() {
         root = new BSTNode<T>();
      }
  
     public BSTNode<T> getRoot() {
        return this.root;
     }
  
     @Override
     public boolean isEmpty() {
        return root.isEmpty();
     }
  
     @Override
     public int height() {
        return height(this.root) - 1;
     }
  
     protected int height(BSTNode<T> node) {
        int height = 0;
        if (!node.isEmpty()) {
           int leftHeight = height((BSTNode<T>) node.getLeft());
           int rightHeight = height((BSTNode<T>) node.getRight());
           height = 1 + Math.max(leftHeight, rightHeight);
        }
        return height;
     }
  
     @Override
     public BSTNode<T> search(T element) {
        BSTNode<T> result = new BSTNode<>();
        if (element != null) {
           result = search(this.root, element);
        }
        return result;
     }
  
     private BSTNode<T> search(BSTNode<T> node, T element) {
        BSTNode<T> result = null;
        if (!node.isEmpty()) {
           if (node.getData().compareTo(element) > 0)
              result = search((BSTNode<T>) node.getLeft(), element);
           else if (node.getData().compareTo(element) < 0)
              result = search((BSTNode<T>) node.getRight(), element);
        }
        if (result == null) {
           result = node;
        }
        return result;
     }
  
     @Override
     public void insert(T element) {
        if (element != null)
           insert(this.root, element);
     }
  
     protected BSTNode<T> insert(BSTNode<T> node, T element) {
        BSTNode<T> added = node;
        if (node.isEmpty()) {
           node.setData(element);
           node.setLeft(new BSTNode<T>());
           node.getLeft().setParent(node);
           node.setRight(new BSTNode<T>());
           node.getRight().setParent(node);
        } else {
           if (node.getData().compareTo(element) > 0)
              added = insert((BSTNode<T>) node.getLeft(), element);
           else if (node.getData().compareTo(element) < 0)
              added = insert((BSTNode<T>) node.getRight(), element);
        }
        return added;
     }
  
     @Override
     public BSTNode<T> maximum() {
        if (this.isEmpty()) {
           return null;
        }
        return maximum(this.root);
     }
  
     private BSTNode<T> maximum(BSTNode<T> node) {
        BSTNode<T> result = null;
        if (node.isLeaf() || node.getRight().isEmpty())
           result = node;
        else if (!node.getRight().isEmpty())
           result = maximum((BSTNode<T>) node.getRight());
        return result;
     }
  
     @Override
     public BSTNode<T> minimum() {
       if (this.isEmpty()) {
          return null;
       }
       return minimum(this.root);
    }
 
    private BSTNode<T> minimum(BSTNode<T> nodeAt) {
       BSTNode<T> result = null;
       if (nodeAt.isLeaf() || nodeAt.getLeft().isEmpty())
          result = nodeAt;
       else if (!nodeAt.getLeft().isEmpty())
          result = minimum((BSTNode<T>) nodeAt.getLeft());
       return result;
    }
 
    @Override
    public BSTNode<T> sucessor(T element) {
       BSTNode<T> result = null;
       BSTNode<T> elementNode = search(element);
       if (element != null && !elementNode.isEmpty()) {
          result = sucessor(elementNode, elementNode);
       }
       return result;
    }
 
    private BSTNode<T> sucessor(BSTNode<T> node, BSTNode<T> nodeFrom) {
       BSTNode<T> result = null;
       if (!node.isEmpty()) {
          boolean isRightEligible = !node.getRight().isEmpty() && !node.getRight().getData().equals(nodeFrom.getData());
          if (isRightEligible) {
             result = minimum((BSTNode<T>) node.getRight());
          } else if (node.getParent() != null) {
             if (node.getParent().getData().compareTo(node.getData()) > 0)
                result = (BSTNode<T>) node.getParent();
             else
                result = sucessor((BSTNode<T>) node.getParent(), node);
          }
       }
       return result;
    }
 
    @Override
    public BSTNode<T> predecessor(T element) {
       BSTNode<T> result = null;
       BSTNode<T> elementNode = search(element);
       if (element != null && !elementNode.isEmpty()) {
          result = predecessor(elementNode, elementNode);
       }
       return result;
    }
 
    private BSTNode<T> predecessor(BSTNode<T> node, BSTNode<T> from) {
       BSTNode<T> result = null;
       if (!node.isEmpty()) {
          boolean isLeftEmpty = node.getLeft().isEmpty();
          boolean isLeftEligible = !isLeftEmpty && !node.getLeft().getData().equals(from.getData());
          if (isLeftEligible) {
             result = maximum((BSTNode<T>) node.getLeft());
          } else if (node.getParent() != null) {
             if (node.getParent().getData().compareTo(node.getData()) < 0)
                result = (BSTNode<T>) node.getParent();
             else
                result = predecessor((BSTNode<T>) node.getParent(), node);
          }
       }
       return result;
    }
 
    @Override
    public void remove(T element) {
       BSTNode<T> nodeToRemove = search(element);
       if (element != null && nodeToRemove != null)
          remove(nodeToRemove);
    }
 
    protected BSTNode<T> remove(BSTNode<T> node) {
       BSTNode<T> removed = node;
       if (!node.isEmpty()) {
          if (node.getLeft().isEmpty() || node.getRight().isEmpty()) {
             BSTNode<T> newThis = (BSTNode<T>) node.getLeft();
             if (newThis.isEmpty())
                newThis = (BSTNode<T>) node.getRight();
             node.setData(newThis.getData());
             node.setLeft(newThis.getLeft());
             node.setRight(newThis.getRight());
             if (node.getLeft() != null) {
                node.getLeft().setParent(node);
             }
             if (node.getRight() != null) {
                node.getRight().setParent(node);
             }
 
          } else {
             BSTNode<T> newThis = sucessor(node.getData());
             node.setData(newThis.getData());
             removed = remove(newThis);
          }
       }
       return removed;
    }
 
    @Override
    public T[] preOrder() {
       return walkByTree(root, -1);
    }
 
    @Override
    public T[] order() {
       return walkByTree(root, 0);
    }
 
    @Override
    public T[] postOrder() {
       return walkByTree(root, 1);
    }
 
    @SuppressWarnings("unchecked")
    private T[] walkByTree(BSTNode<T> node, int rootPosition) {
       T[] array = (T[]) new Comparable[0];
       if (!node.isEmpty()) {
          T[] leftTree = walkByTree((BSTNode<T>) node.getLeft(), rootPosition);
          T[] rightTree = walkByTree((BSTNode<T>) node.getRight(), rootPosition);
          array = (T[]) new Comparable[1 + leftTree.length + rightTree.length];
 
          int start = 0;
          if (rootPosition == -1)
             array[start++] = node.getData();
 
          for (T e : leftTree) {
             array[start++] = e;
          }
          if (rootPosition == 0)
             array[start++] = node.getData();
 
          for (T e : rightTree) {
             array[start++] = e;
          }
          if (rootPosition == 1)
             array[start++] = node.getData();
 
       }
       return array;
    }
    
    @Override
    public int size() {
       return size(root);
    }
 
    private int size(BSTNode<T> node) {
       int result = 0;
       // base case means doing nothing (return 0)
       if (!node.isEmpty()) { // indusctive case
          result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
       }
       return result;
    }
 }