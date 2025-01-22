package org.reactome.web.pwp.model.client.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Taken from Hank Gay (http://stackoverflow.com/users/4203/hank-gay)
 *
 * @author Antonio Fabregat (fabregat@ebi.ac.uk)
 */
@SuppressWarnings("unused")
public class LruCache<A, B> extends LinkedHashMap<A, B> {

    private final static int DEFAULT = 10;
    private transient final int maxEntries;

    public LruCache(final int maxEntries) {
        super(maxEntries + 1, 1.0f, true);
        this.maxEntries = maxEntries;
    }

    public LruCache() {
        super(DEFAULT + 1, 1.0f, true);
        this.maxEntries = DEFAULT;
    }

    /**
     * Returns <code>true</code> if this <code>LruCache</code> has more entries than the maximum specified when it was
     * created.
     *
     * <p>
     * This method <em>does not</em> modify the underlying <code>Map</code>; it relies on the implementation of
     * <code>LinkedHashMap</code> to do that, but that behavior is documented in the JavaDoc for
     * <code>LinkedHashMap</code>.
     * </p>
     *
     * @param eldest
     *            the <code>Entry</code> in question; this implementation doesn't care what it is, since the
     *            implementation is only dependent on the size of the cache
     * @return <code>true</code> if the oldest
     * @see java.util.LinkedHashMap#removeEldestEntry(Map.Entry)
     */
    @Override
    protected boolean removeEldestEntry(final Map.Entry<A, B> eldest) {
        return super.size() > maxEntries;
    }
}