@file:Suppress("DEPRECATION")

package kh.edu.rupp.ite.crudretrofit

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.create
import kotlin.math.log

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)

        //Get
        findViewById<Button>(R.id.btnGet).setOnClickListener {
            getUserByid()
        }

        //Puth
        findViewById<Button>(R.id.btnUpdate).setOnClickListener {
            updateUser()
        }

        //Delete
        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            deleteUser()
        }

        //POST
        findViewById<Button>(R.id.btnCreate).setOnClickListener {
            createUser()
        }
    }

    private fun createUser(){
        lifecycleScope.launch {
            showLoading("Please wait...")
            val body = JsonObject().apply {
                addProperty("name","HOUT")
                addProperty("job","WEB DEV")
            }
            val result = apiService.createUser(body)
            if(result.isSuccessful){
                Log.d("oooo","Post success: {${result.body()}}")
            }else{
                Log.d("oooo","Post fail: {${result.message()}}")
            }
            progressDialog?.dismiss()
        }
    }

    private fun deleteUser(){
        lifecycleScope.launch {
            showLoading("Please wait...")
            val result = apiService.deleteUser("2")
            if(result.isSuccessful){
                Log.d("oooo","Delete success: {${result.body()}}")
            }else{
                Log.d("oooo","Delete fail: {${result.message()}}")
            }
            progressDialog?.dismiss()
        }
    }

    private fun updateUser(){
        lifecycleScope.launch {
            showLoading("Please wait...")
            val body = JsonObject().apply {
                addProperty("name","HOUT")
                addProperty("job","WEB DEV")
            }
            val result = apiService.updateUser("2",body)
            if(result.isSuccessful){
                Log.d("oooo","Update success: {${result.body()}}")
            }else{
                Log.d("oooo","Update fail: {${result.message()}}")
            }
            progressDialog?.dismiss()
        }
    }

    private fun getUserByid() {
        lifecycleScope.launch {
            showLoading("Please wait...")
            val result = apiService.getUserByID("2")
            if(result.isSuccessful){
                Log.d("oooo","getUserByID success: {${result.body()?.data}}")
            }else{
                Log.d("oooo","getUserByID failed: ${result.message()}")
            }
            progressDialog?.dismiss()
        }

    }

    private fun showLoading(msg:String){
        progressDialog = ProgressDialog.show(this,null,msg,true)
    }

}