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

public class MultiLinkedHashMap<K1, K2, V> {

    private LinkedHashMap<K1, LinkedHashMap<K2, V>> lHmpaK1;

    public MultiLinkedHashMap() {
        lHmpaK1 = new LinkedHashMap();
    }

    public V get(K1 key1, K2 key2) {
        LinkedHashMap tempK1 = lHmpaK1.get(key1);
        if (tempK1 == null) return null;
        else return lHmpaK1.get(key1).get(key2);
    }

    public void put(K1 key1, K2 key2, V o) {
        LinkedHashMap<K2, V> tempK2 = lHmpaK1.get(key1);
        if (tempK2 == null) {
            tempK2 = new LinkedHashMap();
            lHmpaK1.put(key1, tempK2);
        }
        tempK2.put(key2, o);
    }

    public ArrayList<V> valuesByArrayList() {
        ArrayList<V> retVal = new ArrayList();
        for (LinkedHashMap<K2, V> l : lHmpaK1.values()) {
            for (V v : l.values()) {
                retVal.add(v);
            }
        }
        return retVal;
    }

    //get an ArrayList with all occurrences of objects V that match with key2
    public ArrayList<V> getByKey2(K2 key2) {
        ArrayList<V> retVal = new ArrayList();
        for (LinkedHashMap<K2, V> l : lHmpaK1.values()) {
            for (K2 k2 : l.keySet()) {
                if (k2.equals(key2)) retVal.add(l.get(key2));
            }
        }
        return retVal;
    }

    public LinkedHashMap<K2, V> getByKey1(K1 key1) {
        return lHmpaK1.get(key1);
    }

    public void clear() {
        for (LinkedHashMap<K2, V> l : lHmpaK1.values()) {
            l.clear();
        }
        lHmpaK1.clear();
    }

}