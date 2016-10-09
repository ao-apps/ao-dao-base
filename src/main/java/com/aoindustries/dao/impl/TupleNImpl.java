/*
 * ao-dao-base - Simple data access objects framework base for implementations.
 * Copyright (C) 2013, 2015, 2016  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of ao-dao-base.
 *
 * ao-dao-base is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ao-dao-base is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with ao-dao-base.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aoindustries.dao.impl;

import com.aoindustries.dao.TupleN;
import java.text.Collator;
import java.util.Arrays;

/**
 * A compound key with any number of columns of the same type.
 *
 * @author  AO Industries, Inc.
 */
public class TupleNImpl<
	C extends Comparable<? super C>
>
	extends AbstractTuple<TupleNImpl<C>>
	implements
		TupleN<C,TupleNImpl<C>>,
		Comparable<TupleNImpl<C>>
{

	private final C[] columns;

	@SafeVarargs
	// Java 1.8 compiler still giving warning even with @SafeVarargs
	@SuppressWarnings({"unchecked", "varargs"})
	public TupleNImpl(Collator collator, C ... columns) {
		super(collator);
		this.columns = Arrays.copyOf(columns, columns.length); // Defensive copy
	}

	@Override
	public C[] getColumns() {
		return Arrays.copyOf(columns, columns.length); // Defensive copy
	}
}
