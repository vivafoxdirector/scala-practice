package io.yian.db.scalikejdbc

import scalikejdbc._
import scalikejdbc.config._

/**
  * 참조: http://scalikejdbc.org/
  * 참조: https://blog.tiqwab.com/2017/06/04/scalikejdbc.html
  */
object SyntaxSupportSample extends App{
    DBs.setupAll

    DB autoCommit { implicit session =>
        sql"""
              create table groups (
                  id serial not null primary key,
                  name nvarchar(64) not null,
                  created_at timestamp not null
              )
            """.execute.apply
        sql"""
              create table members (
                  id serial not null primary key,
                  name nvarchar(64),
                  group_id bigint,
                  created_at timestamp not null,
                  foreign key (group_id) references groups (id)
              )
            """.execute.apply
    }

    DB localTx { implicit session =>
        val g = Group.column
        Seq("group1", "group2") foreach { name =>
            sql"insert into ${Group.table} (${g.name}, ${g.createdAt}) values ($name, current_timestamp)".update.apply
        }
    }

    DB readOnly { implicit session =>
        val (m, g) = (Member.syntax("m"), Group.syntax("g"))
        val member =
            sql"""
                  SELECT
                  ${m.result.*}, ${g.result.*}
                  FROM
                  ${Member as m} inner join ${Group as g}
                  WHERE
                  ${m.groupId} = ${g.id}
            """.map(Member(m.resultName, g.resultName)(_)).first.apply
        println(member)
    }
}
