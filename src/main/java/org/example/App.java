package org.example;


import org.example.model.Actor;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Movie.class).addAnnotatedClass(Actor.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try(sessionFactory){
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();


            Actor actor1 = session.get(Actor.class, 2);
            Movie movie = actor1.getMovies().get(0);
            actor1.getMovies().remove(0);
            movie.getActors().remove(actor1);


            session.getTransaction().commit();
        }finally {
            sessionFactory.close();
        }



    }
}
