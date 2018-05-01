package io.yian.db.scalikejdbc

import java.time.ZonedDateTime

import scalikejdbc._

case class Group(id:Long, name: String, createdAt: ZonedDateTime)

object Group extends SQLSyntaxSupport[Group] {
    override val schemaName: Option[String] = None
    override val tableName: String = "groups"

    def apply(g: ResultName[Group])(rs:WrappedResultSet):Group = {
        Group(rs.long(g.c("id")), rs.string(g.c("name")), rs.zonedDateTime(g.createdAt))
    }
}

case class Member(id: Long, name: Option[String], groupId: Long, group: Group, createdAt: ZonedDateTime)

object Member extends SQLSyntaxSupport[Member] {
    override val schemaName: Option[String] = None
    override val tableName: String = "members"

    def apply(m: ResultName[Member], g: ResultName[Group])(rs: WrappedResultSet): Member = {
        val group = Group(g)(rs)
        Member(rs.long(m.id), rs.stringOpt(m.name), rs.long(m.groupId), group, rs.zonedDateTime(m.createdAt))
    }
}