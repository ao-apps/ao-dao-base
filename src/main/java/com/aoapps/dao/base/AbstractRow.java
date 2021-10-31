/*
 * ao-dao-base - Simple data access objects framework base for implementations.
 * Copyright (C) 2011, 2013, 2015, 2016, 2020, 2021  AO Industries, Inc.
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
 * along with ao-dao-base.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.aoapps.dao.base;

import com.aoapps.dao.Model;
import com.aoapps.dao.Row;

public abstract class AbstractRow<
	K extends Comparable<? super K>,
	R extends AbstractRow<K, ?> & Comparable<? super R>
>
	implements Row<K, R>
{

	private final Model model;
	private final Class<R> clazz;

	protected AbstractRow(
		Model model,
		Class<R> clazz
	) {
		this.model = model;
		this.clazz = clazz;
	}

	/**
	 * The default String representation is the key value.
	 */
	@Override
	public String toString() {
		return getKey().toString();
	}

	/**
	 * The default hashCode is based on the key value.
	 */
	@Override
	public int hashCode() {
		return getKey().hashCode();
	}

	/**
	 * By default equality is based on same model, compatible class, and equal canonical key objects.
	 */
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof AbstractRow<?, ?>)) return false;
		if(!clazz.isInstance(o)) return false;
		AbstractRow<K, ?> other = clazz.cast(o);
		if(model!=other.model) return false;
		K canonicalKey1 = getTable().canonicalize(getKey());
		K canonicalKey2 = other.getTable().canonicalize(other.getKey());
		return canonicalKey1.equals(canonicalKey2);
	}

	/**
	 * The default ordering is based on key comparison.  If both keys
	 * are Strings, will use {@linkplain Model#getComparator() the model comparator}.
	 */
	//@Override
	public int compareTo(R o) {
		K key1 = getKey();
		K key2 = o.getKey();
		if(key1.getClass()==String.class && key2.getClass()==String.class) {
			String s1 = key1.toString();
			String s2 = key2.toString();
			return s1.equals(s2) ? 0 : getModel().getComparator().compare(s1, s2);
		} else {
			return key1.compareTo(key2);
		}
	}

	protected Model getModel() {
		return model;
	}
}
