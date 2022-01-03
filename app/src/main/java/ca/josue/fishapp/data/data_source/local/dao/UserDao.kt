package ca.josue.fishapp.data.data_source.local.dao

import androidx.room.*
import ca.josue.fishapp.domain.model.UserRoom

@Dao
interface UserDao {

    @Query("SELECT * FROM userroom WHERE idUser = :id")
    suspend fun getUserById(id : String) : UserRoom?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user : UserRoom)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users : List<UserRoom>)
    
    @Update
    suspend fun updateUser(user : UserRoom)

    @Delete
    suspend fun deleteUser(user: UserRoom)

    @Query("DELETE FROM userroom")
    suspend fun deleteUsers()

}