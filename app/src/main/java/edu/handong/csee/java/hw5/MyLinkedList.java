package edu.handong.csee.java.hw5;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This is one of Data structure.
 * @author kimkwang-il
 *
 * @param <D>
 */
public class MyLinkedList<D> {
	private ListNode head;
	private ListNode tail;

	
	/**
	 * this is constructor about the MyLinekdList
	 */
	public MyLinkedList() {
		head = null;

	}
	
	/**
	 * this is method that show List.
	 */
	public void showList() {
		ListNode position = head;
		while(position != null) {
			System.out.println(position.data);
			position = position.link;
		}
	}
	
	/**
	 * this is method that return length
	 * @return
	 */
	public int length() {
		int count = 0;
		ListNode position = head;
		while(position != null) {
			count++;
			position = position.link;
		}
		return count;
	}

	
	/**
	 * this is method that add Node at Start
	 * @param addData
	 */
	public void addANodeToStart(D addData) {
		if(length()<2)
			tail = head;
		head = new ListNode(addData,head);
	}
	
	/**
	 * this is method that delete node at start
	 */
	public void deleteHeadNode() {
		if(head != null) {
			head = head.link;
			if(head==null)
				tail=null;
		}	
		else {
			System.out.println("Deleting from an empty list.");
			System.exit(0);
		}
	}
	
	/**
	 * this is medthod that add node at tail
	 * @param addData
	 */
	public void addANodeToTail(D addData) {
		if(length()==0) {
			tail=new ListNode(addData,tail);
			head=tail;
		}else {
		    ListNode newNode = new ListNode(addData, null);
		    tail.link = newNode;
		    tail = newNode;
		}
//		}else {
//			tail.link = new ListNode(addData,tail.link);
//		}
	}
	
	
	/**
	 * this is method that delete node at tail
	 */
	public void deleteTailNode() {
		// Get previous node
		ListNode previous = getPreviousNode();
		
		// assing previous node to tail and 
		// make the previous node link to null.
		tail = previous;
		previous.link = null;
		
	}
	
	private ListNode getPreviousNode() {
		ListNode current = head;
		ListNode previous = null;
		while(true) {
			if(current==tail)
				return previous;
			
			previous = current; // set previons with current at it will be changed into the next node.
			current = current.link; // move to next
		}
	}

	/**
	 * this is method that return find data test.
	 * @param target
	 * @return
	 */
	public boolean onList(D target) {
		return find(target) != null;
	}
	
	private ListNode find(D target) {
		boolean found = false;
		ListNode position = head;
		while((position != null) && !found) {
			D dataAtPosition = position.data;
			if(dataAtPosition.equals(target))
				found = true;
			else
				position = position.link;
		}
		return position;
	}
	
	/**
	 * this is method that makeing arrayList
	 * @return
	 */
	public ArrayList<D> toArrayList() {
		ArrayList<D> list = new ArrayList<D>(length());
		ListNode position = head;
		while(position != null) {
			list.add(position.data);
			position = position.link;
		}
		return list;
	}
	
	private class ListNode{
		private D data;
		
		/**
		 * this is getter that data
		 * @return
		 */
		public D getData() {
			return data;
		}

		/**
		 * this is getter that Link
		 * @return
		 */
		public ListNode getLink() {
			return link;
		}

		/**
		 * this is setter that data
		 * @param data
		 */
		public void setData(D data) {
			this.data = data;
		}

		/**
		 * this is setter that link
		 * @param link
		 */
		public void setLink(ListNode link) {
			this.link = link;
		}

		private ListNode link;
			
		/**
		 * this is constructor that ListNode
		 * @param newData
		 * @param linkedNode
		 */
		public ListNode(D newData, ListNode linkedNode) {
			data = newData;
			link = linkedNode;
		}
	}


	/**
	 * this is method that sort ArrayList
	 * @param comparator
	 * @return
	 */
	public ArrayList<D> sort(Comparator<D> comparator) {
		
		ArrayList<D> list = toArrayList();
		Collections.sort(list,comparator);
		
		return list;
	}
	
	/**
	 * this is method that get Value at index
	 * @param index
	 * @return
	 */
	public D getValueAt(int index) {
	    if (index < 0 || index >= length()) {
	        throw new IndexOutOfBoundsException("Invalid index");
	    }

	    ListNode position = head;
	    for (int i = 0; i < index; i++) {
	        position = position.link;
	    }

	    return position.data;
	}
	
	/**
	 * this is method that set value at index
	 * @param index
	 * @param value
	 */
	public void setValueAt(int index, D value) {
	    if (index < 0 || index >= length()) {
	        throw new IndexOutOfBoundsException("Invalid index");
	    }

	    ListNode position = head;
	    for (int i = 0; i < index; i++) {
	        position = position.link;
	    }


	    position.data = value;

	}
	
	/**
	 * this is method that set value at index
	 * @param index
	 * @param value
	 */

	
	/**
	 * this is method that join value
	 * @param delimiter
	 * @return
	 */
	public String join(String delimiter) {
	    StringBuilder sb = new StringBuilder();
	    ListNode position = head;
	    while (position != null) {
	        sb.append(position.data);
	        if (position.link != null) {
	            sb.append(delimiter);
	        }
	        position = position.link;
	    }
	    return sb.toString();
	}
//	public MyLinkedList<D> toList() {
//	    MyLinkedList<D> newList = new MyLinkedList<>();
//	    ListNode position = head;
//	    while (position != null) {
//	        newList.addANodeToTail(position.data);
//	        position = position.link;
//	    }
//	    return newList.toList;
//	}
}