/*
 * Copyright (c) 2012, Francis Galiegue <fgaliegue@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * Lesser GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * JSON Reference related classes
 *
 * <p>The main package which reprensents a JSON Reference is {@link
 * org.eel.kitchen.jsonschema.ref.JsonRef}.</p>
 *
 * <p>Fragments play a particularly important role in JSON References, as such
 * there is a dedicated {@link org.eel.kitchen.jsonschema.ref.JsonFragment}
 * class to help resolve a fragment into a JSON schema. It has two
 * implementations: {@link org.eel.kitchen.jsonschema.ref.JsonPointer} and
 * {@link org.eel.kitchen.jsonschema.ref.IdFragment}.</p>
 *
 * <p>It should be noted that {@link org.eel.kitchen.jsonschema.ref.IdFragment}
 * breaks the draft somewhat. But this is because section 5.27 of the draft is
 * just insane, and this class is based on the experience gathered by the author
 * about real-world {@code id} usage.</p>
 */
package org.eel.kitchen.jsonschema.ref;
