/*
 * ao-dao-base - Simple data access objects framework base for implementations.
 * Copyright (C) 2016, 2021, 2022  AO Industries, Inc.
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
import com.aoapps.dao.Table;
import com.aoapps.dbc.NoRowException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

/**
 * An empty table does not return any rows and never finds any object.
 */
public class EmptyTable<
    K extends Comparable<? super K>,
    R extends Row<K, ?>
    > implements Table<K, R> {

  private final Model model;

  protected EmptyTable(Model model) {
    this.model = model;
  }

  @Override
  public Model getModel() {
    return model;
  }

  @Override
  public Map<K, ? extends R> getMap() {
    return Collections.emptyMap();
  }

  @Override
  public SortedMap<K, ? extends R> getSortedMap() {
    return Collections.emptySortedMap();
  }

  @Override
  public Set<? extends R> getUnsortedRows() {
    return Collections.emptySet();
  }

  @Override
  public SortedSet<? extends R> getRows() {
    return Collections.emptySortedSet();
  }

  @Override
  public R get(K key) throws NoRowException {
    throw new NoRowException(getName() + " not found: " + key);
  }
}
