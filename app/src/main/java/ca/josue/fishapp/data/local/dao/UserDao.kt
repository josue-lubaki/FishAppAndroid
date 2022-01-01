package ca.josue.fishapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.josue.fishapp.domain.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUser(id : String) : User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user : User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users : List<User>)

}