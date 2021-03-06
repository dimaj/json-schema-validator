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

package org.eel.kitchen.jsonschema.ref;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.eel.kitchen.jsonschema.main.JsonSchemaException;
import org.eel.kitchen.jsonschema.schema.AddressingMode;

/**
 * Abstract class for fragment resolution
 *
 * <p>In a JSON Reference, a fragment can only be a JSON Pointer. All other
 * fragments are illegal.</p>
 *
 * <p>The only possibility for a non JSON Pointer fragment to appear is when
 * inline addressing mode is used.</p>
 *
 * @see IllegalFragment
 * @see JsonPointer
 * @see AddressingMode
 */
public abstract class JsonFragment
    implements Comparable<JsonFragment>
{
    /**
     * Special case fragment (empty)
     */
    private static final JsonFragment EMPTY = new JsonFragment("")
    {
        @Override
        public JsonNode resolve(final JsonNode node)
        {
            return node;
        }
    };

    /**
     * This fragment as a string value
     */
    protected final String asString;

    /**
     * Constructor
     *
     * @param input the input fragment
     */
    protected JsonFragment(final String input)
    {
        asString = input;
    }

    /**
     * The only static factory method to obtain a fragment
     *
     * <p>Depending on the situation, this method will either return a
     * {@link JsonPointer}, an {@link IllegalFragment} or {@link #EMPTY}.</p>
     *
     * @param fragment the fragment as a string
     * @return the fragment
     */
    public static JsonFragment fromFragment(final String fragment)
    {
        if (fragment.isEmpty())
            return EMPTY;

        try {
            return new JsonPointer(fragment);
        } catch (JsonSchemaException ignored) {
            // Not a valid JSON Pointer: illegal
            return new IllegalFragment(fragment);
        }
    }

    /**
     * Resolve this fragment against a given node
     *
     * @param node the node
     * @return the result node ({@link MissingNode} if the fragment is not
     * found)
     */
    public abstract JsonNode resolve(final JsonNode node);

    /**
     * Tell whether this fragment is empty
     *
     * @see JsonRef#isAbsolute()
     * @return true if this fragment is (reference wise) equal to {@link
     * #EMPTY}
     */
    public final boolean isEmpty()
    {
        // This works: we always return EMPTY with a null fragment
        return this == EMPTY;
    }

    @Override
    public final int compareTo(final JsonFragment o)
    {
        return asString.compareTo(o.asString);
    }


    @Override
    public final int hashCode()
    {
        return asString.hashCode();
    }

    @Override
    public final boolean equals(final Object obj)
    {
        if (obj == null)
            return false;
        if (!(obj instanceof JsonFragment))
            return false;

        final JsonFragment other = (JsonFragment) obj;

        return asString.equals(other.asString);
    }

    @Override
    public final String toString()
    {
        return asString;
    }
}
