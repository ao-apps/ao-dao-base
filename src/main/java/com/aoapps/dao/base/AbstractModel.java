/*
 * ao-dao-base - Simple data access objects framework base for implementations.
 * Copyright (C) 2011, 2013, 2015, 2016, 2020, 2021, 2022  AO Industries, Inc.
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
import com.aoapps.lang.text.SmartComparator;
import java.util.Comparator;

/**
 * A base implementation of <code>DaoDatabase</code>.
 */
public abstract class AbstractModel
  implements Model
{

  /**
   * A single Comparator for shared use.
   */
  private static final Comparator<? super String> comparator = new SmartComparator();

  /**
   * By default, sorts using {@link SmartComparator} in the system locale.
   */
  @Override
  public Comparator<? super String> getComparator() {
    return comparator;
  }
}
