/**
 *
 */
package org.magiclist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ognl.Ognl;
import ognl.OgnlException;

/**
 * This class enables the sql-like query to select the list
 *
 * @author cong.yu
 * @param <E>
 *
 */
public class MagicList<E> extends ArrayList<E> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MagicList() {
        super();
    }

    public MagicList(List<E> list) {
        super();
        this.addAll(list);
    }

    /**
     * filter the list according to the specified "where" clause
     *
     * @param expression
     *            the ognl expression, for example: if the list contains the
     *            elements which has a property called "age", the expression
     *            "age<30" will filter all the elements whose age is less than
     *            30.
     * @return the result list
     */
    public MagicList<E> where(String expression) {
        MagicList<E> result = new MagicList<E>();
        try {
            final Object ognlTree = Ognl.parseExpression(expression);
            for (int i = 0; i < this.size(); i++) {
                E object = this.get(i);
                if ((Boolean) Ognl.getValue(ognlTree, object)) {
                    result.add(object);
                }
            }
        } catch (OgnlException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * order the list according to the specified "orderBy" clause
     *
     * @param expression
     *            the ognl expression, specifies which property to order by
     * @param asc
     *            <code>true</code> means ascending order,<code>false</code>
     *            means descending order
     * @return the result list
     */
    public MagicList<E> orderBy(String expression, boolean asc) {
        Collections.sort(this, new MagicComparator<E>(expression, asc));
        return this;
    }

    /**
     * select the first n elements
     *
     * @param count
     *            the count of the elements to select
     * @return the result list
     */
    public MagicList<E> take(int count) {
        MagicList<E> result = new MagicList<E>();
        for (int i = 0; i < this.size() && i < count; i++) {
            result.add(this.get(i));
        }
        return result;

    }

    /**

     * select a sublist, if the endIndex is out of bound, the result list will
     * end with the last element
     *
     * @param startIndex the startIndex, inclusive
     * @param endIndex the endIndex, also inclusive
     * @return the result list
     */
    public MagicList<E> subList(int startIndex, int endIndex) {
        if (startIndex <0) {
            throw new IllegalArgumentException(
                    "startIndex must not less than zero");
        }
        if (startIndex >= endIndex) {
            throw new IllegalArgumentException(
                    "startIndex must less than end endIndex");
        }
        MagicList<E> result = new MagicList<E>();
        for (int i = startIndex; i < this.size() && i <= endIndex; i++) {
            result.add(this.get(i));
        }
        return result;
    }

    /**

     * shuffle the list
     *
     * @return the result list
     */
    public MagicList<E> shuffle() {
        Collections.shuffle(this);
        return this;
    }

    /**
     * make a distinct list according to the specified expression
     *
     * @param expression
     * @return
     */
    public MagicList<E> distinct(String expression) {
        MagicList<E> result = new MagicList<E>();
        Set<Object> set = new HashSet<Object>();
        try {
            final Object ognlTree = Ognl.parseExpression(expression);
            for (int i = 0; i < this.size(); i++) {
                E object = this.get(i);
                Object target = Ognl.getValue(ognlTree, object);
                if (!set.contains(target)) {
                    set.add(target);
                    result.add(object);
                }
            }
        } catch (OgnlException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public MagicList getPropertyList(String expression) {
        MagicList result = new MagicList();
        try {
            final Object ognlTree = Ognl.parseExpression(expression);
            for (int i = 0; i < this.size(); i++) {
                E object = this.get(i);
                Object target = Ognl.getValue(ognlTree, object);
                result.add(target);
            }
        } catch (OgnlException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
