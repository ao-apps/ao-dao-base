/*
 * ao-dao-base - Simple data access objects framework base for implementations.
 * Copyright (C) 2011, 2012, 2013, 2015, 2016, 2020, 2021, 2022  AO Industries, Inc.
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
import com.aoapps.lang.exception.WrappedException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;

public abstract class AbstractTable<
    K extends Comparable<? super K>,
    R extends Row<K, ?>
>
    implements Table<K, R>
{

  private final Class<K> keyClass;
  private final Class<R> rowClass;
  private final Model model;

  class TableMap implements Map<K, R> {

    @Override
    public int size() {
      return AbstractTable.this.size();
    }

    @Override
    public boolean isEmpty() {
      return size() == 0;
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public boolean containsKey(Object key) {
      return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
      if (value != null && rowClass.isInstance(value)) {
        try {
          R row = AbstractTable.this.get(rowClass.cast(value).getKey());
          if (row == null) {
            throw new AssertionError();
          }
          return true;
        } catch (NoRowException err) {
          return false;
        } catch (SQLException err) {
          throw new WrappedException(err);
        }
      } else {
        return false;
      }
    }

    @Override
    public R get(Object key) {
      if (key != null && keyClass.isInstance(key)) {
        try {
          R row = AbstractTable.this.get(keyClass.cast(key));
          if (row == null) {
            throw new AssertionError("NoRowException should have been thrown");
          }
          return row;
        } catch (NoRowException err) {
          return null;
        } catch (SQLException err) {
          throw new WrappedException(err);
        }
      } else {
        return null;
      }
    }

    @Override
    public R put(K key, R value) {
      throw new UnsupportedOperationException();
    }

    @Override
    public R remove(Object key) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends K, ? extends R> map) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
      throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
      throw new UnsupportedOperationException("TODO: Not supported yet.");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<R> values() {
      try {
        return (Collection<R>) getUnsortedRows();
      } catch (SQLException err) {
        throw new WrappedException(err);
      }
    }

    @Override
    public Set<Map.Entry<K, R>> entrySet() {
      throw new UnsupportedOperationException("TODO: Not supported yet.");
    }
  }

  class TableSortedMap extends TableMap implements SortedMap<K, R> {
    @Override
    public Comparator<? super K> comparator() {
      return null;
    }

    @Override
    public SortedMap<K, R> subMap(K fromKey, K toKey) {
      throw new UnsupportedOperationException("TODO: Not supported yet.");
    }

    @Override
    public SortedMap<K, R> headMap(K toKey) {
      throw new UnsupportedOperationException("TODO: Not supported yet.");
    }

    @Override
    public SortedMap<K, R> tailMap(K fromKey) {
      throw new UnsupportedOperationException("TODO: Not supported yet.");
    }

    @Override
    public K firstKey() throws NoSuchElementException {
      try {
        return getRows().first().getKey();
      } catch (SQLException err) {
        throw new WrappedException(err);
      }
    }

    @Override
    public K lastKey() {
      try {
        return getRows().last().getKey();
      } catch (SQLException err) {
        throw new WrappedException(err);
      }
    }
  }

  protected final Map<K, R> map = new TableMap();

  protected final SortedMap<K, R> sortedMap = new TableSortedMap();

  protected AbstractTable(Class<K> keyClass, Class<R> rowClass, Model model) {
    this.keyClass = keyClass;
    this.rowClass = rowClass;
    this.model = model;
  }

  @Override
  public Model getModel() {
    return model;
  }

  @Override
  @SuppressWarnings("ReturnOfCollectionOrArrayField")
  public Map<K, ? extends R> getMap() {
    return map;
  }

  @Override
  @SuppressWarnings("ReturnOfCollectionOrArrayField")
  public SortedMap<K, ? extends R> getSortedMap() {
    return sortedMap;
  }
}
