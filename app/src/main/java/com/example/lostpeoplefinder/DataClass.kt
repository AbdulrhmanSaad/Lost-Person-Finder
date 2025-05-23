package com.example.lostpeoplefinder

import java.io.Serializable
import android.location.Location
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

data class UserData(val username: String, val email: String, val password: String, val phone_number: String,val city:String,val token:String,var isEnabledNofification:Int,
                    var lng:Double, var lat:Double) : Serializable

data class VerifyData(val username: String, val email: String, val password: String, val phone_number: String,val code: String)

data class LoginData(val email: String, val password: String)

data class ApiResponse(val message: String?, val error: String?)

data class LoginResponse(
    val id: Int?,
    val message: String
)
data class RegisterRequest(
    val email: String
)
data class PersonModel(var headerText:String,val img:Int,var personName:String,var personData:String)

data class OutputModel(val img:Int, var personName:String, var personAge:Int,
                       var personGender:String, var last_date: Date, var personLastLocation:Location)

data class ReportPerson(
    val person_name: String?,
    val age: String?,
    val date_of_found: String?,
    val phone_number: String?,
    val image: File,
    val email: String?,
    val lng: String?,
    val lat: String?,
    val gender: String?

)

data class Report(
    val person_name: String?,
    val age: String?,
    val date_of_found: String?,
    val phone_number: String?,
    val image: File,
    val email: String?,
    val lng: String?,
    val lat: String?,
    val gender: String?,
    val user_id:String?
):Serializable

data class Response<T>(
    val data: T? = null,
    val message: String? = null
)

data class LostPersonResponse(
    val final_result: List<Person>?
)


data class FindResponse(
    val final_result: List<ResponseReport>? = null,
    val message: String? = null,
    val error: String? = null
)



data class Person(
    val person_name: String,
    val age: String,
    val date_of_lost: String,
    val phone_number: String,
    val email: String,
    val image_url: String?, // Assuming you're sending a file
    val lng: String,
    val lat: String,
    val gender: String,
    val city:String
): Serializable
data class ResponseReport(
val person_name: String,
val age: String,
val date_of_lost: String,
val check_lost: Int,
val city: String,
val notes: String?,
val phone_number: String,
val email: String,
val lng: String,
val lat: String,
val gender: String,
val image_url: String?,
var user_id:String,// Optional, if you need to display the image

)

data class ParentModel(var title:String, var list:ArrayList<OutputModel>)

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val phone_number: String,
    val longitude: Double,
    val latitude: Double,
    val city: String,
    val notification_enabled: Int,
    val token: String
):Serializable
data class ReportData(
    var person_name: String?,
    var check_lost: String?,
    var age: String?,
    var date_of_lost: String?,
    var phone_number: String?,
    var email: String?,
    var lng: String?,
    var lat: String?,
    var gender: Int?,
    var image_url: String?, // Optional, if you need to display the image
    var city: String?,
    var notes: String?,

) : Serializable