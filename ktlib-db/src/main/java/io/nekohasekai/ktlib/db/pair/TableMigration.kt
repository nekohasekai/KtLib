package io.nekohasekai.ktlib.db.pair

import io.nekohasekai.ktlib.core.defaultLog
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.Transaction

fun migrateDatabase(schemeTable: SchemeTable, currentVersion: Int, migration: (fromVersion: Int) -> Unit) {

    val oldVersion = schemeTable.getItem<Int>("db_version") ?: 0

    if (oldVersion < currentVersion) {

        migration(oldVersion)

        schemeTable.setItem("db_version", currentVersion)

    }

}

fun Transaction.recreateTable(table: Table, creator: (tableName: String) -> Table) {

    val cacheTable = creator("cache_table")

    SchemaUtils.drop(cacheTable)
    SchemaUtils.create(cacheTable)

    exec("INSERT INTO ${cacheTable.tableName} SELECT * FROM ${table.tableName}")

    SchemaUtils.drop(table)

    try {

        exec("ALTER TABLE ${cacheTable.tableName} RENAME TO ${table.tableName}")

    } catch (e: ExposedSQLException) {

        if (e.message?.contains("Query returns results") != true) {

            defaultLog.warn(e)

        }

        SchemaUtils.create(table)

        exec("INSERT INTO ${table.tableName} SELECT * FROM ${cacheTable.tableName}")

        SchemaUtils.drop(cacheTable)

    }

}