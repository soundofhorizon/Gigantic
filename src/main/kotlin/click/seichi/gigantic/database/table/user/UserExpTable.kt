package click.seichi.gigantic.database.table.user

import org.jetbrains.exposed.dao.IntIdTable

/**
 * @author tar0ss
 */
object UserExpTable : IntIdTable("users_exps") {

    val userId = reference("unique_id", UserTable).primaryKey()

    val reasonId = integer("reason_id").primaryKey()

    val exp = decimal("exp", 30, 6).default(0.toBigDecimal())

}