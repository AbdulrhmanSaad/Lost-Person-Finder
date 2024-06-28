package com.example.lostpeoplefinder

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lostpeoplefinder.API.RetrofitClient
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.color.utilities.Cam16
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeActivity : AppCompatActivity() ,OnItemClickListener{
    lateinit var missingRv:RecyclerView
    lateinit var foundRv:RecyclerView
    lateinit var adapter:CommonAdapter
    lateinit var searchview:SearchView
    lateinit var reportFindingButton:Button
    lateinit var reportMissingButton:Button
    lateinit var backBtn:ImageView
    private lateinit var mShimmerViewContainer: ShimmerFrameLayout
    private lateinit var mShimmerViewContainer2: ShimmerFrameLayout


    //    lateinit var filterBtn:ImageView
//    lateinit var missingBtn:Button
//    lateinit var findingBtn:Button
//    lateinit var searchIcon: ImageView
//    lateinit var  textView :TextView
//    private var isEditTextFocused = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var lostList:ArrayList<Person>
        var foundList:ArrayList<Person>
        lostList=ArrayList()
        foundList=ArrayList()
        missingRv=findViewById(R.id.messingRecyclerView)
        foundRv=findViewById(R.id.findingRecyclerView)
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container)
        mShimmerViewContainer2 = findViewById(R.id.shimmer_view_container2)
        //searchview=findViewById<SearchView>(R.id.searchView)
        //filterBtn=findViewById(R.id.btn_filter)
        //searchview=findViewById(R.id.searchView)
        reportFindingButton=findViewById(R.id.reportFindingButton)
        reportMissingButton=findViewById(R.id.reportMissingButton)
        backBtn=findViewById(R.id.logoutButton)
       // FirebaseApp.initializeApp(this)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    //startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.navigation_reports -> {
                    startActivity(Intent(this, UserReports::class.java))
                    true
                }
                R.id.navigation_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }





//        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                Log.w("bkr", "Fetching FCM registration token failed", task.exception)
//                return@addOnCompleteListener
//            }
//
//            // Get new FCM registration token
//            val token = task.result
//            if (token != null) {
//                // Log and toast
//                Log.d("bkr", token)
//                Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
//            } else {
//                Log.w("bkr", "FCM registration token is null")
//            }
//        }


        //filterBtn=findViewById(R.id.btn_filter)
        //textView=searchview.findViewById<TextView>(androidx.appcompat.R.id.search_src_text)
        // Set query hint

//        searchview.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                // Handle the query submission here
//                handleQuery(query)
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                // Handle the text change here
//                return false
//            }
//        })

        val call = RetrofitClient.instance.getLostPeople()
        call.enqueue(object : Callback<Map<String, Person>> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<Map<String, Person>>,
                response: Response<Map<String, Person>>
            ) {
                if (response.isSuccessful) {
                    val lostPeople = response.body()

                     lostList = ArrayList(lostPeople?.values)
                            //Toast.makeText(this@HomeActivity, personList.toString(), Toast.LENGTH_SHORT).show()
                        // Handle the response here
                        Log.d("MainActivity", "Lost people: $lostPeople")
                        Log.d("MainActivity", "Lost: $lostList")
                    adapter = CommonAdapter(this@HomeActivity,this@HomeActivity, lostList,"Missing")
                    missingRv.adapter = adapter
                        //Toast.makeText(this@HomeActivity, lostPeople.toString(), Toast.LENGTH_SHORT).show()
                }

                else {
                    Log.e("MainActivity", "Error: ${response.message()}")
                    Toast.makeText(this@HomeActivity, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()

                }
                mShimmerViewContainer.stopShimmer()
                mShimmerViewContainer.visibility=View.GONE

                mShimmerViewContainer2.stopShimmer()
                mShimmerViewContainer2.visibility=View.GONE
            }

            override fun onFailure(call: Call<Map<String, Person>>, t: Throwable) {
                Log.e("MainActivity", "Error: ${t.message}")
                Toast.makeText(this@HomeActivity, t.message, Toast.LENGTH_SHORT).show()

            }
        })

        val call2 = RetrofitClient.instance.getFoundPeople()
        call2.enqueue(object : Callback<Map<String, Person>> {
            override fun onResponse(
                call: Call<Map<String, Person>>,
                response: Response<Map<String, Person>>
            ) {
                if (response.isSuccessful) {
                    val lostPeople = response.body()

                    foundList = ArrayList(lostPeople?.values)
                    //Toast.makeText(this@HomeActivity, personList.toString(), Toast.LENGTH_SHORT).show()
                    // Handle the response here
                    Log.d("MainActivity", "Lost people: $lostPeople")
                    Log.d("MainActivity", "Lost: $foundList")
                    adapter=CommonAdapter(this@HomeActivity,this@HomeActivity,foundList,"Found")
                    foundRv.adapter=adapter
                    //Toast.makeText(this@HomeActivity, lostPeople.toString(), Toast.LENGTH_SHORT).show()
                }

                else {
                    Log.e("MainActivity", "Error: ${response.message()}")
                    Toast.makeText(this@HomeActivity, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()

                }
                mShimmerViewContainer.stopShimmer()
                mShimmerViewContainer.visibility=View.GONE

                mShimmerViewContainer2.stopShimmer()
                mShimmerViewContainer2.visibility=View.GONE
            }

            override fun onFailure(call: Call<Map<String, Person>>, t: Throwable) {
                Log.e("MainActivity", "Error: ${t.message}")
                Toast.makeText(this@HomeActivity, t.message, Toast.LENGTH_SHORT).show()

            }
        })


        missingRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        foundRv.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //searchIcon = searchview.findViewById(androidx.appcompat.R.id.search_mag_icon)

//        searchIcon.setOnClickListener {
//            // Handle click event for the search icon here
//            Toast.makeText(this, "Search icon clicked!", Toast.LENGTH_SHORT).show()
//        }

//        searchview.setOnFocusChangeListener { v, hasFocus ->
//            isEditTextFocused = hasFocus
//            if(hasFocus){
//                textView.setTextColor(ContextCompat.getColor(this, android.R.color.black))
//            }
//        }







        reportFindingButton.setOnClickListener {
            val intent= Intent(this,DetailsActivity1::class.java)
            intent.putExtra("report","1")
            startActivity(intent)
        }

        reportMissingButton.setOnClickListener {
            val intent= Intent(this,DetailsActivity1::class.java)
            intent.putExtra("report","2")
            startActivity(intent)
        }

        backBtn.setOnClickListener(View.OnClickListener {
            clearUserSession()
            // Navigate to login screen
            navigateToLoginScreen()
        })
    }

    private fun handleQuery(query: String) {
        // Here, you can perform actions based on the entered query
        // For example, you can filter a list or perform a search operation
        Toast.makeText(this, "Query: $query", Toast.LENGTH_SHORT).show()
    }


//    override fun onResume() {
//        super.onResume()
//        isEditTextFocused = false
//    }

    override fun onBackPressed() {
        if (searchview.hasFocus()) {
            searchview.clearFocus()
        } else {
            super.onBackPressed()
        }
    }

    private fun clearUserSession() {
     var rm=RememberMeHandler.getInstance(this)
      rm.clearRememberMeSession()
    }

    private fun navigateToLoginScreen() {
        // Start LoginActivity to allow the user to log in again
        val intent = Intent(this, LoginPageActivity::class.java)
        startActivity(intent)
        finish() // Finish the current activity to prevent going back to it after logout
    }


    override fun onItemClick(Item: Person) {
        val intent = Intent(this, PersonDetailsActivity::class.java)
        intent.putExtra("image", Item.image_url)
        intent.putExtra("name", Item.person_name.toString())
        intent.putExtra("age", Item.age.toString())
        intent.putExtra("gender", Item.gender.toString())
        intent.putExtra("date", Item.date_of_lost.toString())
        intent.putExtra("location", Item.lat.toString()+Item.lng.toString())
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        mShimmerViewContainer.startShimmer()
        mShimmerViewContainer2.startShimmer()
    }

    override fun onPause() {
        mShimmerViewContainer.stopShimmer()
        mShimmerViewContainer2.stopShimmer()
        super.onPause()
    }
}