package data_structure.array;

/**
 * 双向链表
 */
public class DoubleLink<T> {

    // 表头
    private Node<T> head;
    // 节点个数
    private int count;

    public static void main(String[] args) {
        // 创建双向链表
        DoubleLink<Integer> dlink = new DoubleLink<Integer>();

        // 将 20 插入到第一个位置
        dlink.insert(0, 20);

        // 将 10 追加到链表末尾
        dlink.appendLast(10);

        // 将 30 插入到第一个位置
        dlink.insertFirst(30);

        // 双向链表是否为空
        System.out.printf("isEmpty()=%b\n", dlink.isEmpty());

        // 双向链表的大小
        System.out.printf("size()=%d\n", dlink.size());

        // 打印出全部的节点
        System.out.println(dlink.toString());
    }


    public DoubleLink() {
        // 创建“表头”。注意：表头没有存储数据！
        head = new Node<>(null, null, null);
        head.prev = head;
        head.next = head;
        // 初始化“节点个数”为0
        count = 0;
    }

    /**
     * 获取第index位置的节点
     *
     * @param index 位置
     * @return 第index位置的节点
     */
    private Node<T> getNode(int index) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException();
        }


        if (index <= count / 2) {// 正向查找
            Node<T> node = head.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }

        // 反向查找
        Node<T> node = head.prev;
        int rindex = count - index - 1;
        for (int j = 0; j < rindex; j++) {
            node = node.prev;
        }
        return node;
    }

    /**
     * 获取第index位置的节点的值
     *
     * @param index 位置
     * @return 节点的值
     */
    public T get(int index) {
        return getNode(index).value;
    }

    /**
     * 获取第1个节点的值
     *
     * @return 节点的值
     */
    public T getFirst() {
        return getNode(0).value;
    }

    /**
     * 获取最后一个节点的值
     *
     * @return 节点的值
     */
    public T getLast() {
        return getNode(count - 1).value;
    }

    /**
     * 将节点插入到第index位置之前
     *
     * @param index 位置
     * @param t     节点的值
     */
    public void insert(int index, T t) {
        Node<T> node = getNode(index);
        new Node<>(t, node.prev, node);
        count++;
    }


    /**
     * 将节点插入第一个节点处。
     *
     * @param t 节点值
     */
    public void insertFirst(T t) {
        insert(0, t);
    }

    /**
     * 将节点追加到链表的末尾
     *
     * @param t 节点值
     */
    public void appendLast(T t) {
        new Node(t, head.prev, head);
        count++;
    }

    /**
     * 删除index位置的节点
     *
     * @param index 位置
     */
    public void del(int index) {
        Node<T> node = getNode(index);
        node.prev.next = node.next;

        node = null;
        count--;
    }

    /**
     * 删除第一个节点
     */
    public void delFirst() {
        del(0);
    }

    /**
     * 删除最后一个节点
     */
    public void delLast() {
        del(count - 1);
    }


    /**
     * 返回节点数目
     *
     * @return 节点数目
     */
    public int size() {
        return count;
    }

    /**
     * 返回链表是否为空
     *
     * @return 链表是否为空
     */
    public boolean isEmpty() {
        return count == 0;
    }


    /**
     * 双向链表“节点”对应的结构体
     */
    private class Node<T> {
        public Node prev;
        public Node next;
        public T value;

        public Node(T value, Node prev, Node next) {
            setPrev(prev);
            setNext(next);
            this.value = value;
        }

        public void setPrev(Node prev) {
            this.prev = prev;

            if (prev != null) {
                prev.next = this;
            }

        }

        public void setNext(Node next) {
            this.next = next;
            if (next != null) {
                next.prev = this;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("dlink(" + i + ")=" + get(i)).append("\n");
        }
        return sb.toString();
    }
}
