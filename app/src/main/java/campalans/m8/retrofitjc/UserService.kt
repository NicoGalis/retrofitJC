package campalans.m8.retrofitjc

import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @GET(Constants.USERS_PATH)
    suspend fun getUsers(): List<User>

    @GET("${Constants.USERS_PATH}/{id}")
    suspend fun getUserById(@Path("id") id: Int): User

    @POST(Constants.USERS_PATH)
    suspend fun addUser(@Body user: User): Response<User>

    @PUT("${Constants.USERS_PATH}/{id}")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body user: User
    ): Response<User>

    @DELETE("${Constants.USERS_PATH}/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Unit>
}