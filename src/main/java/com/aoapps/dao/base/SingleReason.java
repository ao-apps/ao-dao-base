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

/**
 * A {@link Reason} with a single cause.
 */
public final class SingleReason extends AbstractReason {

  private final String reason;

  public SingleReason(String reason) {
    this.reason = reason;
  }

  @Override
  public String toString() {
    return reason;
  }

  public String getReason() {
    return reason;
  }

  @Override
  public SingleReason merge(Reason other) {
    if (!(other instanceof SingleReason)) {
      return null;
    }
    SingleReason otherSingleReason = (SingleReason) other;
    // Must have the same reason
    if (reason.equals(otherSingleReason.reason)) {
      return this;
    }
    return null;
  }

  @Override
  public int compareTo(Reason other) {
    if (other instanceof SingleReason) {
      SingleReason otherSingleReason = (SingleReason) other;
      // Sort by lexical display in current locale
      return new SmartComparator(ThreadLocale.get()).compare(reason, otherSingleReason.reason);
    } else {
      return -1; // Single reasons before aggregate
    }
  }
}
