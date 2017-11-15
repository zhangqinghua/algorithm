package data_structure.array;

public class Queue<T> {
    private static final int DEFAULT_SIZE = 2;
    // 队列数据
    private T[] data;
    // 队列大小
    private int size;
    private int front;


    public Queue() {
        data = (T[]) new Object[DEFAULT_SIZE];
        size = 0;
        front = 0;
    }

    /**
     * 入队: 从队尾加入一元素
     *
     * @param target 元素
     */
    public void add(T target) {
        if (isFull()) {
            enlarge();
        }
        data[size++] = target;
    }

    /**
     * 返回队头元素
     *
     * @return 队头元素
     */
    public T front() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空!");
        }
        return data[front];
    }

    /**
     * 出队: 移走队头元素
     *
     * @return 队头元素
     */
    public T remove() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空!");
        }

        T target = data[front];
        data[front] = null;

        front++;
        size--;

        return target;
    }

    /**
     * 当前队列中的元素个数
     *
     * @return 元素个数
     */
    public int size() {
        return size;
    }

    /**
     * 判断当前队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断当前队列是否已满
     *
     * @return 是否已满
     */
    public boolean isFull() {
        return data.length == size + front;
    }

    /**
     * 将数组容量扩大两倍
     */
    public void enlarge() {
        T[] newData = (T[]) new Object[size * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[front + i];
        }
        data = newData;
        front = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isEmpty()) {
            sb.append("Queen is emtpy");
        } else {
            sb.append("Queen Start:\n");
            for (int i = 0; i < size(); i++) {
                sb.append(String.format("%d: %s \n", i, data[front + i]));
            }
            sb.append("Queen End!");
        }

        return sb.toString();

    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();

        System.out.println("queue is empty: " + queue.isEmpty());
        System.out.println("queue size: " + queue.size());
        System.out.println(queue.toString());

        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println(queue.toString());

        System.out.println("出队: " + queue.remove());

        System.out.println("队头: " + queue.front());

        System.out.println(queue.toString());
    }
}
