package data_structure.tree;

/**
 * BSTree是二叉树，它保护了二叉树的根节点mRoot；mRoot是BSTNode类型，而BSTNode是二叉查找树的节点，它是BSTree的内部类。BSTNode包含二叉查找树的几个基本信息：
 */
public class BSTree<T extends Comparable> {

    private BSTNode<T> root; // 根结点

    /**
     * 前序遍历
     *
     * @param tree 根节点
     */
    private void preOrder(BSTNode<T> tree) {
        if (tree != null) {
            System.out.println(tree.key + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    /**
     * 中序遍历
     *
     * @param tree
     */
    private void inOrder(BSTNode<T> tree) {
        if (tree != null) {
            inOrder(tree.left);
            System.out.println(tree.key + " ");
            inOrder(tree.right);
        }
    }

    public void inOrder() {
        inOrder(root);
    }


    /**
     * 后序遍历
     *
     * @param tree
     */
    private void postOrder(BSTNode<T> tree) {
        if (tree != null) {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.println(tree.key + " ");
        }
    }

    public void postOrder() {
        postOrder(root);
    }


    /**
     * (递归实现)查找"二叉树"中键值为key的节点
     *
     * @param node 二叉树
     * @param key  键值
     * @return 键值为key的节点
     */
    private BSTNode<T> search(BSTNode<T> node, T key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            return search(node.left, key);
        } else if (cmp > 0) {
            return search(node.right, key);
        }

        return node;
    }

    public BSTNode<T> search(T key) {
        return search(root, key);
    }

    /**
     * (非递归实现)查找"二叉树"中键值为key的节点
     *
     * @param tree 二叉树
     * @param key
     * @return 键值为key的节点
     */
    private BSTNode<T> iterativeSearch(BSTNode<T> tree, T key) {
        while (tree != null) {
            int cmp = key.compareTo(tree.key);

            if (cmp < 0) {
                tree = tree.left;
            } else if (cmp > 0) {
                tree = tree.right;
            } else {
                return tree;
            }
        }
        return null;
    }

    public BSTNode<T> iterativeSearch(T key) {
        return iterativeSearch(root, key);
    }

    /**
     * 查找最大结点：返回tree为根结点的二叉树的最大结点。
     *
     * @param tree 二叉树
     * @return 最大结点
     */
    private BSTNode<T> maximum(BSTNode<T> tree) {
        while (tree != null && tree.right != null) {
            tree = tree.right;
        }

        return tree;
    }

    public T maximum() {
        BSTNode<T> node = maximum(root);
        if (node != null) {
            return node.key;
        }
        return null;
    }

    /**
     * 查找最小结点：返回tree为根结点的二叉树的最小结点。
     *
     * @param tree 二叉树
     * @return 最小结点
     */
    private BSTNode<T> minimum(BSTNode<T> tree) {
        while (tree != null && tree.left != null) {
            tree = tree.left;
        }

        return tree;
    }

    public T minimum() {
        BSTNode<T> node = minimum(root);
        if (node != null) {
            return node.key;
        }
        return null;
    }

    /**
     * 找结点(x)的前驱结点。即，查找"二叉树中数据值小于该结点"的"最大结点"。
     *
     * @param tree 二叉树
     * @return 数据值小于该结点"的"最大结点"
     */
    public BSTNode<T> predecessor(BSTNode<T> tree) {
        if (tree == null) {
            return null;
        }

        // 如果x存在左孩子，则"x的前驱结点"为 "以其左孩子为根的子树的最大结点"。
        if (tree.left != null) {
            return maximum(tree.left);
        }

        // 如果x没有左孩子。则x有以下两种可能：
        // (01) x是"一个右孩子"，则"x的前驱结点"为 "它的父结点"。
        // (01) x是"一个左孩子"，则查找"x的最低的父结点，并且该父结点要具有右孩子"，找到的这个"最低的父结点"就是"x的前驱结点"。
        BSTNode<T> parent = tree.parent;
        while (parent != null && parent.left == tree) {
            tree = parent;
            parent = parent.parent;
        }

        return parent;
    }

    /**
     * 找结点(x)的后继结点。即，查找"二叉树中数据值大于该结点"的"最小结点"。
     *
     * @param tree 二叉树
     * @return 最小结点
     */
    public BSTNode<T> successor(BSTNode<T> tree) {
        // 如果x存在右孩子，则"x的后继结点"为 "以其右孩子为根的子树的最小结点"。
        if (tree.right != null)
            return minimum(tree.right);

        // 如果x没有右孩子。则x有以下两种可能：
        // (01) x是"一个左孩子"，则"x的后继结点"为 "它的父结点"。
        // (02) x是"一个右孩子"，则查找"x的最低的父结点，并且该父结点要具有左孩子"，找到的这个"最低的父结点"就是"x的后继结点"。
        BSTNode<T> parent = tree.parent;
        while (parent != null && parent.right == tree) {
            tree = parent;
            parent = parent.parent;
        }

        return parent;
    }

    /**
     * 新建结点(key)，并将其插入到二叉树中
     * <p>
     * 允许插入相同键值的节点的
     *
     * @param key 插入的值
     * @return 插入的节点
     */
    public BSTNode<T> insert(T key) {

        if (root == null) {
            root = new BSTNode<>(key, null, null, null);
            return root;
        }

        BSTNode<T> node = root;
        while (true) {
            int cmp = key.compareTo(node.key);

            if (cmp < 0) { // 要插入的地方在左子树
                if (node.left == null) {
                    return new BSTNode<>(key, node, true);
                }
                node = node.left;
            } else if (cmp > 0) { // 要插入的地方在右子树
                if (node.right == null) {
                    return new BSTNode<>(key, node, false);

                }
                node = node.right;
            } else {
                // 要插入的跟自身一致。
                node.key = key;
                return node;
            }
        }
    }


    /**
     * 删除结点(z)，并返回被删除的结点
     *
     * @param tree       二叉树
     * @param removeNode 删除的结点
     * @return 删除的结点
     */
    public BSTNode<T> remove(BSTree<T> tree, BSTNode<T> removeNode) {
        BSTNode<T> x = null;
        BSTNode<T> y = null;


        y = removeNode.left == null || removeNode.right == null ? removeNode : successor(removeNode);

        x = y.left != null ? y.left : y.right;

        if (x != null) {
            x.parent = y.parent;
        }


        if (y.parent == null)
            tree.root = x;
        else if (y == y.parent.left)
            y.parent.left = x;
        else
            y.parent.right = x;

        if (y != removeNode)
            removeNode.key = y.key;

        return y;
    }


    /**
     * 删除结点(z)，并返回被删除的结点
     *
     * @param key 删除的结点的值
     */
    public void remove(T key) {
        BSTNode<T> z, node;

        if ((z = search(root, key)) != null)
            if ((node = remove(this, z)) != null)
                node = null;
    }


    /**
     * 打印"二叉查找树"
     *
     * @param tree      二叉查找树
     * @param key       节点的键值
     * @param direction 0，表示该节点是根节点;
     *                  -1，表示该节点是它的父结点的左孩子;
     *                  1，表示该节点是它的父结点的右孩子。
     */
    private void print(BSTNode<T> tree, T key, int direction) {
        if (tree != null) {

            if (direction == 0)    // tree是根节点
                System.out.printf("%2d is root\n", tree.key);
            else                // tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n", tree.key, key, direction == 1 ? "right" : "left");

            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }

    public void print() {
        if (root != null)
            print(root, root.key, 0);
        else {
            System.out.println("这是一颗空树!");
        }
    }

    /**
     * 销毁二叉树
     *
     * @param node 要删除的节点
     */
    private void destroy(BSTNode<T> node) {
        if (node == null)
            return;

        if (node.left != null)
            destroy(node.left);
        if (node.right != null)
            destroy(node.right);

        node = null;
    }

    public void clear() {
        destroy(root);
        root = null;
    }

    class BSTNode<T extends Comparable> {

        T key; // 关键字(键值) 是用来对二叉查找树的节点进行排序的
        BSTNode<T> left;// 指向当前节点的左孩子
        BSTNode<T> right;  // 指向当前节点的右孩子
        BSTNode<T> parent; // 指向当前节点的父结点

        public BSTNode(T key, BSTNode<T> parent, boolean isLeft) {
            this.key = key;

            if (parent != null) {
                if (isLeft) {
                    parent.setLeft(this);
                } else {
                    parent.setRight(this);
                }
            }
        }

        public BSTNode(T key, BSTNode<T> parent, BSTNode<T> left, BSTNode<T> right) {
            setParent(parent);
            setLeft(left);
            setRight(right);
            this.key = key;
        }

        public void setLeft(BSTNode<T> left) {
            this.left = left;
            if (left != null) {
                left.parent = this;
            }
        }

        public void setRight(BSTNode<T> right) {
            this.right = right;
            if (right != null) {
                right.parent = this;
            }
        }

        public void setParent(BSTNode<T> parent) {
            this.parent = parent;
        }
    }

    public static void main(String[] args) {
        // 创建二叉查找树
        BSTree<Integer> bst = new BSTree<>();

        System.out.println("== 依次添加: 1 5 4 3 2 6 ");
        bst.insert(5);
        bst.insert(1);
        bst.insert(4);
        bst.insert(3);
        bst.insert(2);
        bst.insert(6);
        System.out.println("== 树的详细信息: ");
        bst.print();

        System.out.println("== 删除根节点: 3");
        bst.remove(5);
        System.out.println("== 树的详细信息: ");
        bst.print();


        System.out.println("== 树的清空 ");
        bst.clear();
        System.out.println("== 树的详细信息: ");
        bst.print();



    }
}
