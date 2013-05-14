package org.magiclist;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        // construct a sample list
        List<Person> list = new ArrayList<Person>();
        list.add(new Person("a", 10, new Dog("a's dog", 1)));
        list.add(new Person("b", 20, new Dog("b's dog", 2)));
        list.add(new Person("c", 30, new Dog("c's dog", 3)));
        list.add(new Person("d", 40, new Dog("d's dog", 4)));
        list.add(new Person("e", 50, new Dog("e's dog", 5)));
        list.add(new Person("f", 60, new Dog("f's dog", 6)));
        list.add(new Person("g", 70, new Dog("g's dog", 7)));
        list.add(new Person("h", 70, new Dog("h's dog", 7)));

        Collections.shuffle(list);

        // construct the magic list
        MagicList<Person> magicList = new MagicList<Person>(list);
        // do the sql-like query
        magicList = magicList.where("dog.age>=5").orderBy("age", false);
        // print the result
        for (Person person : magicList) {
            System.out.println(person);
        }


    }
}
