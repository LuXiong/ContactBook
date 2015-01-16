package com.ruanko.control;

import java.util.Comparator;

public class ContactItemComparator implements Comparator<ContactItemInterface> {

	@Override
	public int compare(ContactItemInterface lhs, ContactItemInterface rhs) {
		if (lhs.getOrderingItem() == null || rhs.getOrderingItem() == null)
			return -1;

		return (lhs.getOrderingItem().compareTo(rhs.getOrderingItem()));

	}

}
