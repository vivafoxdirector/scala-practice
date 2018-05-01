package io.yian.db.scalikejdbc

import scalikejdbc._
import scalikejdbc.config._

/**
  * 참조 : https://blog.tiqwab.com/2017/06/04/scalikejdbc.html
  */
object BasicSample extends App {

    // Load `applicaion.conf` and setup.
    DBs.setupAll()

    DB autoCommit { implicit session =>
        sql"""
                create table members (
                id serial not null primary key,
                name nvarchar(64),
                created_at timestamp not null
                )
                """.execute().apply()
    }

    DB localTx { implicit session =>
        Seq("Alice", "Bob", "Chris") foreach { name =>
            sql"insert into members (name, created_at) values($name, current_timestamp)".update.apply()
        }
    }

    DB readOnly { implicit session =>
        val entities : List[Map[String, Any]] = sql"select * from members".map(_.toMap).list.apply()
        println(entities)
    }

}
