package data_structure.array;

import java.lang.reflect.Array;

public class Stack<T> {

    private static final int DEFAULT_SIZE = 32;
    private T[] array;
    private int count;

    public Stack(Class<T> type) {
        this(type, DEFAULT_SIZE);
    }

    public Stack(Class<T> type, int size) {
        // 不能直接使用mArray = new T[DEFAULT_SIZE];
        array = (T[]) Array.newInstance(type, size);
        count = 0;
    }

    /**
     * 将val添加到栈中
     *
     * @param val 值
     */
    public void push(T val) {
        array[count++] = val;
    }

    /**
     * 返回“栈顶元素值”
     *
     * @return
     */
    public T peek() {
        return array[count - 1];
    }

    /**
     * 返回“栈顶元素值”，并删除“栈顶元素”
     *
     * @return 栈顶元素值
     */
    public T pop() {
        T ret = array[count - 1];
        count--;
        return ret;
    }

    /**
     * 返回“栈”的大小
     *
     * @return 大小
     */
    public int size() {
        return count;
    }

    /**
     * 返回“栈”是否为空
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isEmpty()) {
            sb.append("stack is Empty");
        } else {
            sb.append(String.format("stack size: %d\n", size()));
            int i = size() - 1;
            while (i >= 0) {
                sb.append(array[i]).append('\n');
                i--;
            }
        }
        return sb.toString();
    }

    public static void StackTest() {
        java.util.Stack<Integer> stack = new java.util.Stack<>();

        // 将10, 20, 30 依次推入栈中
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // 将“栈顶元素”赋值给tmp，并删除“栈顶元素”
        System.out.println(stack.pop());

        // 只将“栈顶”赋值给tmp，不删除该元素.
        System.out.println(stack.peek());

        stack.push(40);

        while (!stack.empty()) {
            System.out.printf("tmp=%d\n", stack.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(Integer.class);

        // 将10, 20, 30 依次推入栈中
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // 将“栈顶元素”赋值给tmp，并删除“栈顶元素”
        System.out.println(stack.pop());

        // 只将“栈顶”赋值给tmp，不删除该元素.
        System.out.println(stack.peek());

        stack.push(40);

        System.out.println(stack.toString());
    }


}
