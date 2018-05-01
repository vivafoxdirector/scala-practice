package io.yian.db.scalikejdbc

import java.time.ZonedDateTime

import scalikejdbc.config._
import scalikejdbc._

// 내용 : QueryDSL (Domain-Specific Language)
// 참조 : http://scalikejdbc.org/documentation/query-dsl.html

object QuerySample extends App {
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
        val (m, g) = (Member.column, Group.column)
        Seq("group1", "group2") foreach { name =>
            withSQL {
                insert.into(Group).columns(g.name, g.createdAt).values(name, ZonedDateTime.now)
            }.update.apply
        }
        Seq(("Alice",1), ("Bob",1), ("Chris",2)) foreach {
            case (name, groupId) =>
                withSQL {
                    insert.into(Member).columns(m.name, m.groupId, m.createdAt).values(name, groupId, ZonedDateTime.now)
                }.update.apply
        }
    }

    DB readOnly { implicit session =>
        val id = 1
        val (m, g) = (Member.syntax("m"), Group.syntax("g"))
        val member = withSQL {
            select.from(Member as m).innerJoin(Group as g).on(m.groupId, g.id)
                    .where.eq(m.id, id)
        }.map(Member(m.resultName, g.resultName)(_)).single.apply
        println(member)
    }
}
