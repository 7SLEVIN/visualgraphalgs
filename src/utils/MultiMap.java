package utils;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MultiMap<K1 extends Object, K2 extends Object, V extends Object> {

	private Map<K1, V> map1;
	private Map<K2, V> map2;

	/**
	 * @param map1
	 * @param map2
	 */
	public MultiMap() {
		this.map1 = new HashMap<K1, V>();
		this.map2 = new HashMap<K2, V>();
	}

	public void put(K1 key1, K2 key2, V value) {
		this.map1.put(key1, value);
		this.map2.put(key2, value);
	}

	public V getByKey1(K1 key) {
		return this.map1.get(key);
	}

	public V getByKey2(K2 key) {
		return this.map2.get(key);
	}
	
	public V removeByKey1(K1 key) {
		V res = this.map1.remove(key);
		K2 k = null;
		for (Map.Entry<K2, V> entry : this.map2.entrySet()) {
			if (entry.getValue() == res) {
				k = entry.getKey();
				break;
			}
		}
		this.map2.remove(k);
		return res;
	}
	
	public V removeByKey2(K2 key) {
		V res = this.map2.remove(key);
		K1 k = null;
		for (Map.Entry<K1, V> entry : this.map1.entrySet()) {
			if (entry.getValue() == res) {
				k = entry.getKey();
				break;
			}
		}
		this.map1.remove(k);
		return res;
	}
	
	public boolean isEmpty() {
		return this.map1.isEmpty() && this.map2.isEmpty();
	}
	
	public int size() {
		return this.map1.size();
	}
	
	public Collection<V> values() {
		return this.map1.values();
	}
}
