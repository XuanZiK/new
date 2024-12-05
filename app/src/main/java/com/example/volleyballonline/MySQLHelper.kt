package com.example.volleyballonline

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

class MySQLHelper {
    private val dbUrl = "私密信息1"
    private val username = "私密信息2"
    private val password = "私密信息3"

    suspend fun establishConnection(): Connection = withContext(Dispatchers.IO) {
        Class.forName("com.mysql.jdbc.Driver")
        DriverManager.getConnection(dbUrl, username, password)
    }

    suspend fun insertUser(username: String, password: String, age: String, phone_number: String) {
        withContext(Dispatchers.IO) {
            val connection = establishConnection()
            val sql = "INSERT INTO users (username, password, age, phone_number) VALUES (?, ?, ?, ?)"  //建表语句，向users表内插入用户名和密码

            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
                preparedStatement.setString(1, username)
                preparedStatement.setString(2, password)
                preparedStatement.setString(3, age)
                preparedStatement.setString(4, phone_number)
                preparedStatement.executeUpdate()
            } finally {
                connection.close()
            }
        }
    }

    suspend fun checkLogin(username: String, password: String): Boolean = withContext(Dispatchers.IO) {
        val connection = establishConnection()
        val sql = "SELECT * FROM users WHERE username=? AND password=?" //查询语句
        var result = false

        try {
            val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, username)
            preparedStatement.setString(2, password)
            val resultSet: ResultSet = preparedStatement.executeQuery()
            result = resultSet.next()
        } finally {
            connection.close()
        }

        result
    }
}