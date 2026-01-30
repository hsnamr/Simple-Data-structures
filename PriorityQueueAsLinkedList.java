package ics202.lab04;

import ics202.*;

public class PriorityQueueAsLinkedList extends QueueAsLinkedList {

    @Override
    public void enqueue(Object obj) {
        if (list.isEmpty()) {
            list.prepend(obj);
        } else {
            MyLinkedList.Element x = list.getHead();
            while (x != null && ((MyComparable) x.getDatum()).isGE((MyComparable) obj)) {
                x = x.getNext();
            }
            if (x == null) {
                list.append(obj);
            } else {
                x.insertBefore(obj);
            }
        }
        count++;
    }
}
