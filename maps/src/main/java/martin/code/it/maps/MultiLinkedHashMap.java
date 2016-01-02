/*****************************************************************
 * Copyright 06/12/15 Paolo Martinello
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************/
package martin.code.it.maps;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * A multikey LinkedHashMap. It is basically a Map of Maps.
 *
 * @param <K1> the first key to identify an element in the Map.
 * @param <K2> the second key to identify an element in the Map.
 * @param <V>  the element stored in the Map.
 */
public class MultiLinkedHashMap<K1, K2, V> {

    private LinkedHashMap<K1, LinkedHashMap<K2, V>> lHmpaK1;

    /**
     * Construtor.
     * @see MultiLinkedHashMap
     */
    public MultiLinkedHashMap() {
        lHmpaK1 = new LinkedHashMap();
    }

    /**
     * Used to get an element from the Map, using the given keys.
     * See also {@link #getByKey1(Object)}, {@link #getByKey2(Object)} and {@link #put(Object, Object, Object)}.
     * @param key1 the first key to identify an element in the Map.
     * @param key2 the second key to identify an element in the Map.
     * @return the element from the Map. Null if no element corresponds to the given keys.
     * @see MultiLinkedHashMap
     */
    public V get(K1 key1, K2 key2) {
        LinkedHashMap tempK1 = lHmpaK1.get(key1);
        if (tempK1 == null) return null;
        else return lHmpaK1.get(key1).get(key2);
    }

    /**
     * Used to put an element in the Map, using the given keys.
     * See also {@link #get(Object, Object)}.
     * @param key1 the first key to identify an element in the Map.
     * @param key2 the second key to identify an element in the Map.
     * @param o the element to store in the Map.
     * @see MultiLinkedHashMap
     */
    public void put(K1 key1, K2 key2, V o) {
        LinkedHashMap<K2, V> tempK2 = lHmpaK1.get(key1);
        if (tempK2 == null) {
            tempK2 = new LinkedHashMap();
            lHmpaK1.put(key1, tempK2);
        }
        tempK2.put(key2, o);
    }

    /**
     * Used to get all the elements of the Map in the form of an ArrayList.
     * @return an ArrayList object containing all the Map elements.
     * @see MultiLinkedHashMap
     */
    public ArrayList<V> valuesByArrayList() {
        ArrayList<V> retVal = new ArrayList();
        for (LinkedHashMap<K2, V> l : lHmpaK1.values()) {
            for (V v : l.values()) {
                retVal.add(v);
            }
        }
        return retVal;
    }


    /**
     * Get an ArrayList with all occurrences of objects V that match with key2, indipendently from key1.
     * See also {@link #get(Object, Object)}, {@link #getByKey1(Object)} and {@link #put(Object, Object, Object)}.
     * @param key2 the second key to identify one or more elements in the Map.
     * @return an ArrayList object containing the Map elements stored with the given key.
     * @see MultiLinkedHashMap
     */
    public ArrayList<V> getByKey2(K2 key2) {
        ArrayList<V> retVal = new ArrayList();
        for (LinkedHashMap<K2, V> l : lHmpaK1.values()) {
            for (K2 k2 : l.keySet()) {
                if (k2.equals(key2)) retVal.add(l.get(key2));
            }
        }
        return retVal;
    }

    /**
     * Get a LinkedHashMap with the elements corresponding to key1.
     * See also {@link #get(Object, Object)}, {@link #getByKey2(Object)} and {@link #put(Object, Object, Object)}.
     * @param key1 the first key to identify one or more elements in the Map.
     * @return a Map related to elements corresponding to the given key.
     * @see MultiLinkedHashMap
     */
    public LinkedHashMap<K2, V> getByKey1(K1 key1) {
        return lHmpaK1.get(key1);
    }

    /**
     * Clears the content of the Map.
     * @see MultiLinkedHashMap
     */
    public void clear() {
        for (LinkedHashMap<K2, V> l : lHmpaK1.values()) {
            l.clear();
        }
        lHmpaK1.clear();
    }

}