package ics202.lab04;

import ics202.*;

public class PriorityQueueAsLinkedList extends QueueAsLinkedList {

    @Override
    public void enqueue(Object obj) {
        if (list.isEmpty()) {
            list.prepend(obj);
        } else {
            MyLinkedList.Element x = list.getHead();
            // Advance while current element <= obj (min-first order)
            while (x != null && ((MyComparable) x.getDatum()).isLE((MyComparable) obj)) {
                x = x.getNext();
            }
            if (x == null) {
                list.append(obj);  // obj is larger than all
            } else {
                x.insertBefore(obj);  // insert before first element > obj
            }
        }
        count++;
    }
}
