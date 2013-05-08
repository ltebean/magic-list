/**
 *
 */
package com.dianping.magiclist;

import ognl.Ognl;
import ognl.OgnlException;

import java.util.Comparator;

/**
 * @author cong.yu
 * @param <T>
 *
 */
class MagicComparator<T> implements Comparator<T> {

    private final String expression;

    private final boolean asc;

    private final Object ognlTree;

    public MagicComparator(String expression, boolean asc) {
        super();
        this.expression = expression;
        this.asc = asc;
        try {
            this.ognlTree = Ognl.parseExpression(expression);
        } catch (OgnlException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int compare(T obj1, T obj2) {
        try {
            Comparable<T> field1 = (Comparable<T>) Ognl
                    .getValue(ognlTree, obj1);
            Comparable<T> field2 = (Comparable<T>) Ognl
                    .getValue(ognlTree, obj2);
            if (asc) {
                return field1.compareTo((T) field2);
            } else {
                return -(field1.compareTo((T) field2));
            }

        } catch (OgnlException e) {
            throw new RuntimeException("bad expression: " + expression);
        }

    }
}
