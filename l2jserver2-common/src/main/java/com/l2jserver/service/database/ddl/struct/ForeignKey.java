/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.service.database.ddl.struct;

import java.util.Collections;
import java.util.List;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class ForeignKey {
	/**
	 * The key name
	 */
	private final String name;
	/**
	 * The key columns
	 */
	private final List<Column> columns;

	/**
	 * @param name
	 *            the key name
	 * @param columns
	 *            the key columns
	 */
	public ForeignKey(String name, List<Column> columns) {
		this.name = name;
		this.columns = columns;
	}

	/**
	 * @return the columns
	 */
	public List<Column> getColumns() {
		return Collections.unmodifiableList(columns);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
