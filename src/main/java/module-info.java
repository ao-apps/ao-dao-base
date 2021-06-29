/*
 * ao-dao-base - Simple data access objects framework base for implementations.
 * Copyright (C) 2021  AO Industries, Inc.
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
module com.aoapps.dao.base {
	exports com.aoapps.dao.base;
	// Javadoc-only
	requires static com.aoapps.security; // <groupId>com.aoapps</groupId><artifactId>ao-security</artifactId>
	// Direct
	requires com.aoapps.dao.api; // <groupId>com.aoapps</groupId><artifactId>ao-dao-api</artifactId>
	requires com.aoapps.dbc; // <groupId>com.aoapps</groupId><artifactId>ao-dbc</artifactId>
	requires com.aoapps.lang; // <groupId>com.aoapps</groupId><artifactId>ao-lang</artifactId>
	// Java SE
	requires java.sql;
}
