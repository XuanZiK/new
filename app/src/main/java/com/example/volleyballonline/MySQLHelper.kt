package com.example.volleyballonline

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class MySQLHelper {
    private val dbUrl = "jdbc:mysql://rm-wz94570b9nkwq41h49o.mysql.rds.aliyuncs.com:3306/volleyballonline"
    private val username = "VolleyballOnline"
    private val password = "!zfy200312261"

    suspend fun establishConnection(): Connection = withContext(Dispatchers.IO) {
        Class.forName("com.mysql.jdbc.Driver")
        DriverManager.getConnection(dbUrl, username, password)
    }

    // 注册信息写入数据库
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

    //登陆信息数据库中验证
    suspend fun checkLogin(username: String, password: String): Boolean = withContext(Dispatchers.IO) {
        val connection = establishConnection()
        val sql = "SELECT * FROM users WHERE username=? AND password=?" //查询语句
        var result = false

        try {
            val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, username)
            preparedStatement.setString(2, password)
            // 执行查询
            val resultSet: ResultSet = preparedStatement.executeQuery()
            result = resultSet.next()
        } finally {
            connection.close()
        }

        result
    }


    // 以下三个方法是对于fragmentA中的活动名，活动地点，活动时间的获取
    // 对fragmentA中的活动名
    suspend fun findActivityName(activity_id: Int): String? {
        val connection = establishConnection()
        val sql = "SELECT activity_name FROM activity WHERE activity_id=?"
        var activity_name: String? = null

        try {
            val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, activity_id)
            // 执行查询
            val resultSet: ResultSet = preparedStatement.executeQuery()

            // 检查是否有结果并提取用户名
            if (resultSet.next()) {
                activity_name = resultSet.getString("activity_name")
            }
        } catch (e:SQLException) {
            e.printStackTrace()
            return "获取活动名错误"
        } finally {
            connection.close()
        }

        return activity_name
    }
    // 对fragmentA中的活动地点
    suspend fun findActivityLocation(activity_id: Int): String? {
        val connection = establishConnection()
        val sql = "SELECT activity_location FROM activity WHERE activity_id=?"
        var activity_location: String? = null

        try {
            val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, activity_id)
            // 执行查询
            val resultSet: ResultSet = preparedStatement.executeQuery()

            // 检查是否有结果并提取用户名
            if (resultSet.next()) {
                activity_location = resultSet.getString("activity_location")
            }
        } catch (e:SQLException) {
            e.printStackTrace()
            return "获取活动地点错误"
        } finally {
            connection.close()
        }

        return activity_location
    }
    // 对fragmentA中的活动时间
    suspend fun findActivityTime(activity_id: Int): String? {
        val connection = establishConnection()
        val sql = "SELECT activity_time FROM activity WHERE activity_id=?"
        var activity_time: String? = null

        try {
            val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, activity_id)
            // 执行查询
            val resultSet: ResultSet = preparedStatement.executeQuery()

            // 检查是否有结果并提取用户名
            if (resultSet.next()) {
                activity_time = resultSet.getString("activity_time")
            }
        } catch (e:SQLException) {
            e.printStackTrace()
            return "获取活动时间错误"
        } finally {
            connection.close()
        }

        return activity_time
    }
}





































