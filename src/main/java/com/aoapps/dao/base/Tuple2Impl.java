/*
 * ao-dao-base - Simple data access objects framework base for implementations.
 * Copyright (C) 2013, 2015, 2016, 2020, 2021, 2022  AO Industries, Inc.
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

import com.aoapps.dao.Tuple2;
import java.util.Comparator;

/**
 * A compound key with two columns.
 *
 * @author  AO Industries, Inc.
 */
public class Tuple2Impl<
  C1 extends Comparable<? super C1>,
  C2 extends Comparable<? super C2>
>
  extends AbstractTuple<Tuple2Impl<C1, C2>>
  implements
    Tuple2<C1, C2, Tuple2Impl<C1, C2>>,
    Comparable<Tuple2Impl<C1, C2>>
{

  private final C1 column1;
  private final C2 column2;

  public Tuple2Impl(Comparator<? super String> comparator, C1 column1, C2 column2) {
    super(comparator);
    this.column1 = column1;
    this.column2 = column2;
  }

  @Override
  public Comparable<?>[] getColumns() {
    return new Comparable<?>[] {
      column1,
      column2
    };
  }

  @Override
  public C1 getColumn1() {
    return column1;
  }

  @Override
  public C2 getColumn2() {
    return column2;
  }
}
