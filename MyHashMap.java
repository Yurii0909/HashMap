/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject1;

/**
 *
 * @author 073Kolesnikovyua
 */

public class MyHashMap {
    private static final int DEFAULT_CAPACITY = 16;
    private Node[] table;
    private int size;

    public MyHashMap() {
        table = new Node[DEFAULT_CAPACITY];
    }

    public Object put(Integer key, Integer value) {
        int index = key.hashCode() % table.length;
        Node newNode = new Node(key, value);
        if (containsKey(key)) {
            replays(key, value);
        } else {
            if (table[index] == null) {
                table[index] = newNode;
                size++;
            } else {
                Node current = table[index];
                while (current.getNext() != null) {
                    current = current.getNext();
                }
                current.setNext(newNode);
                size++;
            }
        }
        return value;
    }

    public Object get(Integer key) {
        int index = key.hashCode() % table.length;
        Node current = table[index];
        while (current != null) {
            if (current.getKey() == key) {
                return current.getValue();
            }
            current = current.getNext();
        }
        return null;
    }

    public Object remove(Integer key) {
        int index = key.hashCode() % table.length;
        Node current = table[index];
        Node prev = null;
        while (current != null) {
            if (current.getKey() == key) {
                if (prev == null) {
                    table[index] = current.getNext();
                } else {
                    prev.setNext(current.getNext());
                }
                size--;
                return current.getValue();
            }
            prev = current;
            current = current.getNext();
        }
        return null;
    }

    public Object replays(Integer key, Integer value) {
        int index = key.hashCode() % table.length;
        Node current = table[index];
        while (current != null) {
            if (current.getKey() == key) {
                current.setValue(value);
                return value;
            }
            current = current.getNext();
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean containsKey(Integer key) {
        int index = key.hashCode() % table.length;
        Node current = table[index];
        while (current != null) {
            if (current.getKey() == key) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public boolean containsValue(Integer v) {
        for (Node node : table) {
            Node current = node;
            while (current != null) {
                if (current.getValue() == v) {
                    return true;
                }
                current = current.getNext();
            }
        }
        return false;
  }

    private class Node {
        private final Integer key;
        private Integer value;
        private Node next;

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MyHashMap myMap = new MyHashMap();
        myMap.put(1, 5);
        myMap.put(2, 8);
        myMap.put(3, 12);
        System.out.println("Value for key 2: " + myMap.get(2));
        System.out.println("Size of map: " + myMap.size());
        System.out.println("Removing value for key 2: " + myMap.remove(2));
        System.out.println("Updated size of map: " + myMap.size());
        System.out.println("Contains key 1: " + myMap.containsKey(1));
        System.out.println("Contains value 12: " + myMap.containsValue(12));
    }
}