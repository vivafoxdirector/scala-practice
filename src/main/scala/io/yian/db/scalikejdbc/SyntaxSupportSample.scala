package io.yian.db.scalikejdbc

import scalikejdbc._
import scalikejdbc.config._

/**
  * Created by foxdirector on 2018. 5. 1..
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
              name nvarchar*64),
              group_id bigint,
              created_at timestamp not null,
              foreign key (group_id) references groups (id)
              )
            """.execute().apply()
    }
}
