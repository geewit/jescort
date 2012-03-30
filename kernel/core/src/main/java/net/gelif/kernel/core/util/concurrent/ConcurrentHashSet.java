/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
package net.gelif.kernel.core.util.concurrent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * A Set backed by ConcurrentHashMap.
 */
public class ConcurrentHashSet<E> extends AbstractSet<E> implements Serializable
{
    private static final long serialVersionUID = 3748155740205839388L;

    /**
     * The backing Map.
     */
    private final ConcurrentMap<E, Object> map;

    /**
     * Backing's KeySet.
     */
    private transient Set<E> keySet;

    /**
     * Dummy value to associate with the key in the backing map.
     */
    private static final Object PRESENT = new Object();

    public ConcurrentHashSet()
    {
        map = new ConcurrentHashMap<E, Object>();
        keySet = map.keySet();
    }

    public ConcurrentHashSet(int initialCapacity)
    {
        map = new ConcurrentHashMap<E, Object>(initialCapacity);
        keySet = map.keySet();
    }

    public ConcurrentHashSet(int initialCapacity, float loadFactor, int concurrencyLevel)
    {
        map = new ConcurrentHashMap<E, Object>(initialCapacity, loadFactor, concurrencyLevel);
        keySet = map.keySet();
    }

    public int size()
    {
        return map.size();
    }

    public boolean isEmpty()
    {
        return map.isEmpty();
    }

    public boolean contains(Object o)
    {
        return map.containsKey(o);
    }

    public Iterator<E> iterator()
    {
        return keySet.iterator();
    }

    public Object[] toArray()
    {
        return keySet.toArray();
    }

    public <T> T[] toArray(T[] a)
    {
        return keySet.toArray(a);
    }

    public boolean add(E e)
    {
        return map.put(e, PRESENT) == null;
    }

    public boolean remove(Object o)
    {
        return map.remove(o) != null;
    }

    public boolean removeAll(Collection<?> c)
    {
        return keySet.removeAll(c);
    }

    public boolean retainAll(Collection<?> c)
    {
        return keySet.retainAll(c);
    }

    public void clear()
    {
        map.clear();
    }

    public boolean equals(Object o)
    {
        return keySet.equals(o);
    }

    public int hashCode()
    {
        return keySet.hashCode();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        keySet = map.keySet();
    }
}
