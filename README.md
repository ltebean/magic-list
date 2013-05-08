
MagicList improves the ArrayList by adding lots of filtering functionality, allowing you to some sql-like query to a list.

###Example:

Suppose a person has a name, age, and a dog

            // construct the magic list
            MagicList<Person> magicList = new MagicList<Person>(personList);
            // do the sql-like query, select person whose dog's age >= 5
            // then order the list by person's name
            magicList = magicList.where("dog.age>=5").orderBy("name", false);


