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

import com.aoapps.dao.Reason;
import com.aoapps.lang.i18n.ThreadLocale;
import com.aoapps.lang.text.SmartComparator;

public final class AggregateReason
  extends AbstractReason
{

  private final int count;
  private final String singularPrefix;
  private final String pluralPrefix;
  private final String singularSuffix;
  private final String pluralSuffix;

  AggregateReason(int count, String singularPrefix, String pluralPrefix, String singularSuffix, String pluralSuffix) {
    this.count = count;
    this.singularPrefix = singularPrefix;
    this.pluralPrefix = pluralPrefix;
    this.singularSuffix = singularSuffix;
    this.pluralSuffix = pluralSuffix;
  }

  @Override
  public String toString() {
    if (count == 1) {
      return singularPrefix+"1"+singularSuffix;
    }
    return pluralPrefix+count+pluralSuffix;
  }

  public int getCount() {
    return count;
  }

  public String getSingularPrefix() {
    return singularPrefix;
  }

  public String getPluralPrefix() {
    return pluralPrefix;
  }

  public String getSingularSuffix() {
    return singularSuffix;
  }

  public String getPluralSuffix() {
    return pluralSuffix;
  }

  @Override
  public AggregateReason merge(Reason other) {
    if (!(other instanceof AggregateReason)) {
      return null;
    }
    AggregateReason otherAggregateReason = (AggregateReason)other;
    // Must have the same text descriptions
    if (
      singularPrefix.equals(otherAggregateReason.singularPrefix)
      && pluralPrefix.equals(otherAggregateReason.pluralPrefix)
      && singularSuffix.equals(otherAggregateReason.singularSuffix)
      && pluralSuffix.equals(otherAggregateReason.pluralSuffix)
    ) {
      return new AggregateReason(count + otherAggregateReason.count, singularPrefix, pluralPrefix, singularSuffix, pluralSuffix);
    }
    return null;
  }

  @Override
  public int compareTo(Reason other) {
    if (other instanceof AggregateReason) {
      AggregateReason otherAggregateReason = (AggregateReason)other;
      // Descending by count first
      if (count<otherAggregateReason.count) {
        return 1;
      }
      if (count>otherAggregateReason.count) {
        return -1;
      }
      // Sort by lexical display in current locale
      return new SmartComparator(ThreadLocale.get()).compare(toString(), otherAggregateReason.toString());
    } else {
      return 1; // Aggregate reasons after single
    }
  }
}
