package ca.josue.fishapp.data.data_source.local.dao

import androidx.room.*
import ca.josue.fishapp.domain.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id : String) : User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user : User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users : List<User>)
    
    @Update
    suspend fun updateUser(user : User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user")
    suspend fun deleteUsers()

}