package io.yian.db.scalikejdbc

/**
  * Created by foxdirector on 2018. 4. 30..
  */
case class Group(id:Long, name: String, createAt: DateTime)

object Group extends SQLSyntaxSupport[Group] {
    override val schemaName: Option[String] = None
    override val tableName: String = "groups"

    def apply(g: ResultName[Group])(rs:WrappedResultSet):Group = {
        Group(rs.long(g.id), rs.string(g.name), rs.get(g.createAt))
    }
}

case class Member(id: Long, name: Option[String], groupId: Long, group: Group, createdAt: DateTime)

object Member extends SQLSyntaxSupport[Member] {
    override val schemaName: Option[String] = None
    override val tableName: String = "members"

    def apply(m: ResultName[Member], g: ResultName[Group])(rs: WrappedResultSet): Member = {
        val group = Group(g)(rs)
        Member(rs.long(m.id), rs.stringOpt(m.name), rs.long(m.groupId), group, rs.get(m.createAt))
    }
}